package by.bsuir.andrei.http.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public class ConnectionManager {

    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE_KEY = "db.poolsize";
    private static final String DRIVER_KEY = "db.driver";
    private static final Integer DEFAULT_POOL_SIZE = 10;
    private static final String CLOSE_CONNECTION_METHOD_NAME = "close";

    private static BlockingQueue<Connection> pool;
    private static List<Connection> originalPool;

    static {
        loadDriver();
        loadPool();
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtil.get(DRIVER_KEY));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadPool() {
        String poolSizeValue = PropertiesUtil.get(POOL_SIZE_KEY);
        int poolSize = poolSizeValue != null ? Integer.valueOf(poolSizeValue) : DEFAULT_POOL_SIZE;
        pool = new ArrayBlockingQueue<>(poolSize);
        originalPool = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = open();
            var proxyInstance = (Connection) Proxy.newProxyInstance(
                    ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) ->
                            CLOSE_CONNECTION_METHOD_NAME.equals(method.getName())
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args)
            );
            pool.add(proxyInstance);
            originalPool.add(connection);
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection open() {
        try {
            String username = PropertiesUtil.get(USERNAME_KEY);
            String password = PropertiesUtil.get(PASSWORD_KEY);
            String url = PropertiesUtil.get(URL_KEY);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        try {
            for (Connection connection : originalPool) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
