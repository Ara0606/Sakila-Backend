package edu.ucb.bo.sakilabackend.Api;

import edu.ucb.bo.sakilabackend.BL.CarritoBl;
import edu.ucb.bo.sakilabackend.BL.CostumerBL;
import edu.ucb.bo.sakilabackend.BL.FilmBl;
import edu.ucb.bo.sakilabackend.DTO.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
public class Api {

    FilmBl FilmBl;
    CarritoBl carritoBl;
    CostumerBL costumerBL;

    @Autowired
    public Api(FilmBl FilmBl) {
        this.FilmBl = FilmBl;
        this.costumerBL=  costumerBL;
        ;
        this.carritoBl= carritoBl;
    }
// Obtener peliculas por tienda
    @GetMapping(value = "/film/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findByCountry(@PathVariable(name = "country") String country) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return FilmBl.findByCountry(country);
    }
    //Obtener los estrenos
    @GetMapping(value = "/film/news/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> NEWS(@PathVariable(name = "country") String country) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return FilmBl.News(country);
    }
    //Las más rentadas
    @GetMapping(value = "/film/mostRental/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> MostRental(@PathVariable(name = "country") String country) {
        System.out.println("Invocando al metodo GET!!!!!!!!!!!");
        return FilmBl.MostRentals(country);
    }
// Buscar peliculas por titulo
    @GetMapping(value = "/film/{country}/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findByTitle(@PathVariable(name = "country") String country,@PathVariable(name = "title") String title) {
        System.out.println("Invocando al metodo GET por titulo!!!!!!!!!!!");
        System.out.print("country "+country);
        System.out.print("title "+title);
        return FilmBl.findByTitle(country,title);
        //return FilmBl.findByCountry(country);
    }
    //Buscar peliculas por actor
    @GetMapping(value = "/film/{country}/{actor_name}/{actor_lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> findByActor(@PathVariable(name = "country") String country,@PathVariable(name = "actor_name") String actor_name , @PathVariable(name = "actor_lastname") String actor_lastname) {
        System.out.println("Invocando al metodo GET por actor!!!!!!!!!!!");
        System.out.println("country "+country);
        System.out.println("actor "+actor_name);
        System.out.println("actor_lastname "+actor_lastname);
        return FilmBl.findByActor(country,actor_name,actor_lastname);
    }
    //Añadir nueva pelicula al carrito

// Eliminar pelicula del carrito


    //Añadir nuevo cliente
    @PostMapping(value = "/costumer/{country}/{address}/{address2}/{district}/{name}/{lastname}/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String NewCostumer(@PathVariable(name = "country") String country, @PathVariable(name = "address") String adress, @PathVariable(name = "address2") String adress2, @PathVariable(name = "district") String district, @PathVariable(name = "name") String name, @PathVariable(name = "lastname") String lastname, @PathVariable(name = "email") String email) {
        System.out.println("Invocando al metodo GET por actor!!!!!!!!!!!");
        return costumerBL.NewCostumer(country,adress,adress2,district,name,lastname,email);
    }
    // Retornar direccion
    //Modifica la direccion de envio
    // Email
    // Insertar nuevo pago
}

