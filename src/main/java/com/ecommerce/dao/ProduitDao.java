package com.ecommerce.dao;
import com.ecommerce.model.produit;

import java.sql.SQLException;

public interface ProduitDao {
    public void insert(produit produit) throws SQLException;
    public void update(produit produit)throws SQLException;
    public void delete(produit produit)throws SQLException;
    public produit getById(int id)throws SQLException;
    public produit getByName(String name) throws SQLException;

}
