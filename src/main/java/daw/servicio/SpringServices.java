package daw.servicio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import daw.Dto.PeliculasDto;
import daw.Entities.Peliculas;
import daw.repositories.PeliculasRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SpringServices {
    @Autowired
    private PeliculasRepository peliculasRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate; //Para poder importarla hay que poner las dependencias de Rabbit
    /*
    * 	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
    * */

    public String jsonPelicula(String nombre,
                                int duracion, String tipoPelicula) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PeliculasDto p1 = new PeliculasDto(nombre, duracion, tipoPelicula);
            String pJson = objectMapper.writeValueAsString(p1);
            rabbitTemplate.convertAndSend("Cola", pJson); //Nombre cola, lo que manda
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Se ha enviado correctamente";
    }

    public String insertarPelicula(String nombre, Integer duracion, String tipoPelicula) {
        Peliculas p1 = new Peliculas();
        p1.setNombrePeli(nombre);
        p1.setDuracion(duracion);
        p1.setTipoPelicula(tipoPelicula);
        p1.setFechCreacion(LocalDate.now());

        peliculasRepository.save(p1); //Java lo guarda directamente en la base de datos --> Hace un insert dentro de la función

        return "Se ha enviado correctamente";

    }

    public String enviarPeli(String idPelicula) { //Pasar como String todito porque si no se ralla
        rabbitTemplate.convertAndSend("ColaBorrar", idPelicula); //Nombre cola, lo que manda
        return "Se ha enviado correctamente";
    }

    //Borrar película
    public String deletePelicula(Long idPelicula) {
        Optional<Peliculas> opt = peliculasRepository.findById(idPelicula);
        if (opt.isPresent()) {
            peliculasRepository.deleteById(idPelicula);
            return "Pelicula eliminada";
        } else {
            return "Pelicula no encontrada";
        }
    }

}
