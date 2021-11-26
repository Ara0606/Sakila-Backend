package edu.ucb.bo.sakilabackend.DAO;

import edu.ucb.bo.sakilabackend.DTO.Costumer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostumerDAO {
    private DataSource dataSource;
    private Costumer costumer;


    public CostumerDAO(DataSource dataSource) {

        this.dataSource = dataSource;
    }
    public String AddCostumerAdress (String country, String address, String address2, String district, String name, String lastname, String email) {
        List<Costumer> result = new ArrayList<>();
        String query = "insert into address (address, address2, district) values (?,?,?) ";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {

            pstmt.setString(1, "%"+address.toUpperCase()+ "%");
            pstmt.setString(2, "%"+address2.toUpperCase()+ "%");
            pstmt.setString(3, "%"+district.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        String query2 = "select  address_id from address where address like (?) and  address2 like (?)  and district like (?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt2 =  conn.prepareStatement(query2);
        ) {

            pstmt2.setString(1, "%"+address.toUpperCase()+ "%");
            pstmt2.setString(2, "%"+address2.toUpperCase()+ "%");
            pstmt2.setString(3, "%"+district.toUpperCase()+ "%");
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()) {
                Costumer costumer = new Costumer();
                costumer.setId(rs.getInt("address_id"));
                result.add(costumer);
            }
            String query3 = "insert into customer (store_id, first_name, last_name, email, address_id) values (?,?,?,?,?);  ";
            PreparedStatement pstmt3 =  conn.prepareStatement(query3);
            pstmt3.setString(1, "%"+country.toUpperCase()+ "%");
            pstmt3.setString(2, "%"+name.toUpperCase()+ "%");
            pstmt3.setString(3, "%"+lastname.toUpperCase()+ "%");
            pstmt3.setString(4, "%"+email.toUpperCase()+ "%");
            String id=String.valueOf(costumer.getId());
            pstmt3.setString(4, "%"+id.toUpperCase()+ "%");
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }

        return null;
    }

}
