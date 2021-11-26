package edu.ucb.bo.sakilabackend.BL;

import edu.ucb.bo.sakilabackend.DAO.CostumerDAO;
import edu.ucb.bo.sakilabackend.Exception.SakilaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostumerBL {
    private  CostumerDAO costumerDAO;

    @Autowired
    public CostumerBL(CostumerDAO costumerDAO) {
        this.costumerDAO = costumerDAO;
    }
    public String NewCostumer(String country,String address,String address2, String district,String name,String lastname, String email) {
        if (country == null || country.trim().equals("")) {
            throw new SakilaException(403, "Bad request: The title parameter is mandatory and can't be null or empty");
        }
        return costumerDAO.AddCostumerAdress(country,address,address2,district,name,lastname,email);
    }

}
