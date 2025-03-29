package com.ecommerce.dao;

import com.ecommerce.model.produit;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements ProduitDao {
    private DAOFactory          daoFactory;

    public ProduitDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void insert(produit produit) throws SQLException {


        Connection connection=daoFactory.getConnection();
        String query="INSERT INTO produit(Nom,Prix,Info,ProductId)VALUES(?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,produit.getName());
        preparedStatement.setDouble(2,produit.getPrice());
        preparedStatement.setString(3,produit.getDescription());
        preparedStatement.setInt(4,produit.getId());
        preparedStatement.executeUpdate();

    };
    @Override
    public void update(produit produit) throws SQLException{
        Connection connection=daoFactory.getConnection();
        String query="UPDATE  produit  SET Nom= ? ,Prix= ? ,Info= ? WHERE ProductId = ?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setString(1,produit.getName());
        ps.setDouble(2,produit.getPrice());
        ps.setString(3,produit.getDescription());
        ps.setInt(4,produit.getId());
        ps.executeUpdate();



        int id=produit.getId();





    };
    @Override
    public void delete(produit produit) throws SQLException{
        Connection connection=daoFactory.getConnection();
        String query="DELETE FROM produit WHERE ProductId=?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setInt(1,produit.getId());
        ps.executeUpdate();
    };
    @Override
    public produit getById(int id) throws SQLException{
        Connection connection=daoFactory.getConnection();
        String query="select * from produit where ProductId=?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setInt(1 , id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()){
                 produit produit=new produit(
                    rs.getInt("ProductId"),
                    rs.getString("Nom"),
                    rs.getDouble("Prix"),
                    rs.getString("Info"),
                    null);
            return produit;

        }

        return null;



    };
    @Override
    public produit getByName (String name) throws SQLException{
        return null;
    };
    public List<produit> getAll() throws SQLException{
        Connection connection=daoFactory.getConnection();
        String query="select * from produit";
        PreparedStatement ps=connection.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        List<produit> list=new ArrayList<>();
        while (rs.next()){
            produit produit=new produit(rs.getInt("ProductId"),
                    rs.getString("Nom"),
                    rs.getDouble("Prix"),
            rs.getString("info"),
                    null);
            list.add(produit);

        }
        return list;


    }

}
