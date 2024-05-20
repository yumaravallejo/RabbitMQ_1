package daw.Listener;

import daw.servicio.SpringServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.spi.MidiDeviceProvider;
@Slf4j
@Component
@RabbitListener(queues = "ColaBorrar")
public class ListenerDelete {

    @Autowired //--> Si da null pointer exception es porque esto no está escrito
    private SpringServices springServices;

    @RabbitHandler
    public void onMessage(String message){
        log.info(message); //Te muestra lo que te está recibiendo por consola
        springServices.deletePelicula(Long.parseLong(message));
    }
}
