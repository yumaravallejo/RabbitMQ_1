package daw.repositories;

import daw.Entities.Peliculas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeliculasRepository extends CrudRepository<Peliculas, Long> {
    List<Peliculas> findByNombrePeli(String nombrePeli); //Un select por nombres --> nombre igual que Java
}

