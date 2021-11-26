package edu.ucb.bo.sakilabackend.DAO;

import edu.ucb.bo.sakilabackend.DTO.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDao {

   private DataSource dataSource;

    public FilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> findByCountry (String country) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT  fl.film_id, " +
                "fl.title, " +
                " fl.description, " +
                " fl.replacement_cost, " +
                " fl.release_year, " +
                " COUNT(n.film_id) " +
                " from film  fl INNER JOIN inventory n on (fl.film_id=n.film_id) " +
                " INNER JOIN store s on (n.store_id=s.store_id) " +

                " where s.store_id LIKE (?) GROUP by (fl.title);" ;
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setCost(rs.getDouble("replacement_cost"));
                film.setStock(rs.getInt("COUNT(n.film_id)"));
                result.add(film);

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }


    public List<Film> News (String country) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT  f.film_id, " +
                "f.title, " +
                " f.description, " +
                " f.replacement_cost, " +
                " f.release_year, " +
                " COUNT(i.film_id)" +
                " FROM film f INNER JOIN inventory i ON f.film_id = i.film_id" +
                " INNER JOIN store s on (i.store_id=s.store_id) where i.store_id like (?) GROUP BY f.title ORDER BY MAX(f.last_update) DESC ";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setCost(rs.getDouble("replacement_cost"));
                film.setStock(rs.getInt("COUNT(i.film_id)"));
                result.add(film);

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public List<Film> MostRental (String country) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT  f.film_id, " +
                "f.title, " +
                " f.description, " +
                " f.replacement_cost, " +
                " f.release_year, " +
                " COUNT(i.film_id)" +
                " FROM film f INNER JOIN inventory i ON f.film_id = i.film_id INNER JOIN rental r ON i.inventory_id = r.inventory_id " +
                " INNER JOIN store s on (i.store_id=s.store_id) where i.store_id like (?) GROUP BY f.title ORDER BY MAX(r.rental_date) DESC LIMIT 10 ";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setCost(rs.getDouble("replacement_cost"));
                film.setStock(rs.getInt("COUNT(i.film_id)"));
                result.add(film);

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public List<Film> findByTitle(String country,String title) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.replacement_cost, " +
                "  f.release_year , " +
                "  COUNT(n.film_id)  " +
                " FROM film f " +
                "INNER JOIN inventory n on (f.film_id=n.film_id) " +
                "INNER JOIN store s on (n.store_id=s.store_id) "+
                " WHERE " +
                "s.store_id LIKE (?) and UPPER(f.title) LIKE(?) GROUP by (f.title)" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            pstmt.setString(2, "%"+title.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setStock(rs.getInt( "replacement_cost"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setStock(rs.getInt("COUNT(n.film_id)"));
                result.add(film);

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public  List<Film> findByActor(String country, String actor_name, String actor_lastname ) {
        List<Film> result1 = new ArrayList<>();
        String query = "SELECT f.film_id, f.title, " +
                "  ol.first_name ,  " +
                "   ol.last_name, " +
                "f.replacement_cost,"+
                "f.description,"+
                "  COUNT(n.film_id)  " +
                " FROM film f " +
                "    LEFT JOIN film_actor l ON ( f.film_id = l.film_id) " +
                "    LEFT JOIN actor ol ON ( l.actor_id = ol.actor_id) " +
                "INNER JOIN inventory n on (f.film_id=n.film_id)"+
                "INNER JOIN store s on (n.store_id=s.store_id)"+
                " WHERE " +
                " s.store_id like (?) and UPPER(ol.first_name) LIKE (?) and UPPER(ol.last_name) LIKE (?)  GROUP by (f.title)" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            pstmt.setString(2, "%"+actor_name.toUpperCase()+ "%");
            pstmt.setString(3, "%"+actor_lastname.toUpperCase()+ "%");



            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                List<String> actors = new ArrayList<>();
                String name=rs.getString("first_name");
                String lastname=rs.getString("last_name");
                actors.add(name);actors.add(lastname);
                film.setActors(actors);
                film.setCost(rs.getDouble("replacement_cost"));
                film.setStock(rs.getInt("COUNT(n.film_id)"));
                result1.add(film);

            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result1;
    }

}
