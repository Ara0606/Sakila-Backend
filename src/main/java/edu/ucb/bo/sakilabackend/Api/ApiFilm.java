package edu.ucb.bo.sakilabackend.Api;

import edu.ucb.bo.sakilabackend.BL.CostumerBL;
import edu.ucb.bo.sakilabackend.BL.FilmBl;
import edu.ucb.bo.sakilabackend.DTO.Costumer;
import edu.ucb.bo.sakilabackend.DTO.Film;
import edu.ucb.bo.sakilabackend.DTO.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
public class Api {
    JavaMailSender javaMailSender;
    FilmBl FilmBl;
    CostumerBL costumerBL;


    @Autowired
    public Api(JavaMailSender javaMailSender, FilmBl FilmBl, CostumerBL costumerBL) {
        this.javaMailSender = javaMailSender;
        this.FilmBl = FilmBl;
        this.costumerBL = costumerBL;

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
    @GetMapping(value = "/actor/{country}/{actor_name}/{actor_lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @PostMapping(value = "/new_costumer/", produces = MediaType.APPLICATION_JSON_VALUE, consumes  = MediaType.APPLICATION_JSON_VALUE)
    public String NewCostumer( @RequestBody Costumer costumer){
        String country=String.valueOf(costumer.getCountry());
        String address=costumer.getAdress();
        String address2=costumer.getAdress2();
        String district=costumer.getDistrito();
        String name=costumer.getNombre();
        String lastname=costumer.getApellido();
        String email=costumer.getEmail();

        System.out.print(country);
        System.out.println(address);
        System.out.println(address2);System.out.println(district);
        System.out.println(name);
        System.out.println(lastname);
        System.out.println(email);

        return costumerBL.NewCostumer(String.valueOf(costumer.getCountry()),costumer.getAdress(),costumer.getAdress2(), costumer.getDistrito(),costumer.getNombre(), costumer.getApellido(),  costumer.getEmail());
    }
    //login
    @GetMapping(value = "/costumer/login/{correo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int Login(@PathVariable(name = "correo") String correo) {
        return costumerBL.Login(correo);
    }
    // Retornar direccion
    @GetMapping(value = "/costumer/adress/{country}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Costumer> getAdress(@PathVariable(name = "country") String country,@PathVariable(name = "name") String name ) {
        System.out.println("Invocando al metodo GET por actor!!!!!!!!!!!");
        System.out.println("country "+country);
        System.out.println("actor "+name);
        return costumerBL.Address(country,name);
    }
    //Envia email
    @PostMapping (value = "mail",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String>sendEmail(@RequestBody Mail mail){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        javaMailSender.send(message);
        Map<String,String> result= new HashMap<>();
        result.put("succes","ok");
        return result;

    }
    //Modifica la direccion de envio
    @PutMapping(value = "/new_adress/{adress}/{newAdress}")
    public String direccion(
            @PathVariable(name = "adress") String adress, @PathVariable(name = "newAdress") String newAdress)   {
        return costumerBL.UpdateAddress(adress,newAdress);
    }
    // Insertar nuevo pago

}

