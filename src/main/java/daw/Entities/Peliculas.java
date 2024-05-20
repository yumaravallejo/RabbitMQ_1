package daw.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Table /*Para que sepa que es una table de la base de datos*/
@Data /*Para no tener que poner getter, toString, setter, equals y hashcode*/
@Entity /*Para que sepa que es una entidad --> pide clave primaria @Id --> */
@NoArgsConstructor //Constructor con ningún parámetro
@AllArgsConstructor //Constructor con todos los parámetros
public class Peliculas {
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increment
    @Id
    @Column(name = "id_pelicula")
    private long idPelicula;
    @Column(name = "nombre_peli")
    private String nombrePeli;
    @Column(name = "duracion")
    private Integer duracion;
    @CreationTimestamp //Automáticamente te pone la fecha y hora
    @Column(name = "fecha_creacion")
    private LocalDate fechCreacion;
    @Column(name = "tipo_pelicula")
    private String tipoPelicula;

}