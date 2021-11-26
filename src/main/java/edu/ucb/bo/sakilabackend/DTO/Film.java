package edu.ucb.bo.sakilabackend.DTO;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Film {
    private Integer filmId;
    private String title;
    private String description;
    private Short releaseYear;
    private double cost;
    private int stock;


    private Integer length;

    private List<String> actors;


    public Film() {
    }

    public int getStock() {
        return stock;
    }



    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId.equals(film.filmId);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                //", description='" + description + '\'' +
                //", lengthLabel='" + lengthLabel + '\'' +
                ", actors='" + actors + '\'' +
                '}';
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }


    public Integer getLength() {
        return length;
    }



    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }




}

