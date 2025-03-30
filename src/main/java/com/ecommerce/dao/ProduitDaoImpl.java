package com.ecommerce.dao;

import com.ecommerce.model.produit;

import java.awt.image.BufferedImage;
import java.io.File;
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
        String query="INSERT INTO produit(Nom,Prix,Info,ProductId , imagePath)VALUES(?,?,?,? , ?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,produit.getName());
        preparedStatement.setDouble(2,produit.getPrice());
        preparedStatement.setString(3,produit.getDescription());
        preparedStatement.setInt(4,produit.getId());
        preparedStatement.setString(5, produit.getImagePath());
        preparedStatement.executeUpdate();

    };
    @Override
    public void update(produit produit ,int id) throws SQLException{
        Connection connection=daoFactory.getConnection();
        String query="UPDATE  produit  SET Nom= ? ,Prix= ? ,Info= ? , imagePath=? WHERE ProductId = ?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setString(1,produit.getName());
        ps.setDouble(2,produit.getPrice());
        ps.setString(3,produit.getDescription());
        ps.setInt(5,id);
        ps.setString(4,produit.getImagePath());

        ps.executeUpdate();









    };
    @Override
    public void delete(produit produit) throws SQLException{
        Connection connection=daoFactory.getConnection();
        String getImageQuery = "SELECT imagePath FROM produit WHERE ProductId=?";
        PreparedStatement getImagePs = connection.prepareStatement(getImageQuery);
        getImagePs.setInt(1, produit.getId());
        ResultSet rs = getImagePs.executeQuery();

        String path = null;
        if (rs.next()) {
            path = rs.getString("imagePath");
        }


        if (path != null) {
            File imageFile = new File( path);
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }
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
                    rs.getString("imagePath"));
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
                    rs.getString("imagePath"));
            list.add(produit);

        }
        return list;


    }

}
