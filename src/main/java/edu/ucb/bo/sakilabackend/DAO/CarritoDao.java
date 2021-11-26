package edu.ucb.bo.sakilabackend.DAO;

import edu.ucb.bo.sakilabackend.DTO.Carrito;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class CarritoDao {
    private DataSource dataSource;

    public CarritoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Carrito> AddMovie (String country, String idmovie) {
        List<Carrito> result = new ArrayList<>();
        String query = "SELECT  fl.film_id, " +
                "fl.title, " +
                " fl.description, " +
                " fl.replacement_cost, " +
                " fl.release_year, " +
                " COUNT(n.film_id) " +
                " from film  fl INNER JOIN inventory n on (fl.film_id=n.film_id) " +
                " INNER JOIN store s on (n.store_id=s.store_id) " +

                " where s.store_id LIKE (?) and fl.film_id like (?) GROUP by (fl.title);" ;
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setString(1, "%"+country.toUpperCase()+ "%");
            pstmt.setString(2, "%"+idmovie.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Carrito carrito = new Carrito();

                carrito.setFilmId(rs.getInt("film_id"));
                carrito.setTitle(rs.getString("title"));
                carrito.setDescription(rs.getString("description"));
                carrito.setReleaseYear(rs.getShort("release_year"));
                carrito.setCost(rs.getDouble("replacement_cost"));
                carrito.setStock(rs.getInt("COUNT(n.film_id)"));
                result.add(carrito);
                System.out.println(result.toString());
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }

        return result;
    }
}
