package by.bsuir.andrei.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketDto {

    Integer id;
    String description;
}
