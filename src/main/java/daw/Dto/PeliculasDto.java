package daw.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Como es un json no cogeremos entity
public class PeliculasDto {
    private String nombrePeli;
    private Integer duracion;
    private String tipoPelicula;
}
