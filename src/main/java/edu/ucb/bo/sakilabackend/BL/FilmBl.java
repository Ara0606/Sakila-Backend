package edu.ucb.bo.sakilabackend.BL;


import edu.ucb.bo.sakilabackend.DAO.FilmDao;
import edu.ucb.bo.sakilabackend.DTO.Film;
import edu.ucb.bo.sakilabackend.Exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmBl {

    private final FilmDao filmDao;

    @Autowired
    public FilmBl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public List<Film> findByCountry(String country) {
        if (country == null || country.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByCountry(country);
    }
    public List<Film> findByTitle(String country,String title) {
        if (title == null || title.trim().equals("") || country == null || country.trim().equals("") ) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByTitle(country,title);
    }

    public List<Film> findByActor(String country, String actor_name, String actor_lastname) {
        if (actor_name  == null || actor_name.trim().equals("") || country == null || country.trim().equals("") || actor_lastname  == null || actor_lastname.trim().equals("") ) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.findByActor(country,actor_name,actor_lastname);
    }

    public List<Film> News(String country) {
        if (country == null || country.trim().equals("") ) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.News(country);
    }
    public List<Film> MostRentals  (String country) {
        if (country == null || country.trim().equals("") ) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return filmDao.MostRental(country);
    }
}
