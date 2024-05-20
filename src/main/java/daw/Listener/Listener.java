package daw.Listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daw.Dto.PeliculasDto;
import daw.Entities.Peliculas;
import daw.servicio.SpringServices;
import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Cola")
public class Listener {
    @Autowired //--> Si da null pointer exception es porque esto no está escrito
    private SpringServices springServices;

    @RabbitHandler
    public void onMessage(String message /* Esto es el json */) {;
        ObjectMapper mapper = new ObjectMapper();
        try {
            PeliculasDto peli = mapper.readValue(message, PeliculasDto.class); //Hace falta el mapper si ya te lo da como un String?
            springServices.insertarPelicula(peli.getNombrePeli(), peli.getDuracion(), peli.getTipoPelicula());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
