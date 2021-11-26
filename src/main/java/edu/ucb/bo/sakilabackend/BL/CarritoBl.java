package edu.ucb.bo.sakilabackend.BL;

import edu.ucb.bo.sakilabackend.DAO.CarritoDao;
import edu.ucb.bo.sakilabackend.DTO.Carrito;
import edu.ucb.bo.sakilabackend.Exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarritoBl {
    private final CarritoDao carritoDao;
    @Autowired
    public CarritoBl(CarritoDao carritoDao) {
        this.carritoDao = carritoDao;
    }
    public List<Carrito> AddCarrito(String country, String id) {
        if (country == null || country.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return carritoDao.AddMovie(country,id);
    }
}
