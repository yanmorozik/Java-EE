package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingWithRelationIdsDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long bookId;

    private Long userId;
}
