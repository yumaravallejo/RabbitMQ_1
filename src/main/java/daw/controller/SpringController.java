package daw.controller;

import daw.servicio.SpringServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Controller es el papa de restcontroller --> en @controller tienes que especificar lo que devuelve con @body
//RestController --> un controlador que da respuestas de por si (Respond body)
@RequiredArgsConstructor
public class SpringController {

    private final SpringServices springServices;

    @PostMapping("/jsonPelicula")
    public String generarPeliJson(
                               @RequestParam(value = "nombre") String nombre,
                               @RequestParam(value = "duracion") int duracion,
                               @RequestParam(value = "tipoPelicula") String tipoPelicula) {

        return springServices.jsonPelicula(nombre, duracion, tipoPelicula);
    }

    @PostMapping("/deletePelicula/{idPelicula}")
    public String enviarPelicula(@PathVariable(value = "idPelicula") Long idPelicula ) {
        return springServices.enviarPeli(idPelicula.toString()); //Como
    }


}
