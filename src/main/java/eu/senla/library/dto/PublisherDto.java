package eu.senla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {

    private Long id;

    private String namePublisher;

    private String telephone;
}
