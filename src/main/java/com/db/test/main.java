import com.ecommerce.dao.DAOFactory;
import com.ecommerce.dao.ProduitDaoImpl;
import com.ecommerce.dao.UserDaoImp;
import com.ecommerce.model.User;
import com.ecommerce.model.produit;

import java.sql.SQLException;

public static void main(String[] args) throws SQLException {
    DAOFactory daoFactory = DAOFactory.getInstance();
    daoFactory.testConnection();
    ProduitDaoImpl produitDao=new ProduitDaoImpl(daoFactory);
    UserDaoImp userDao=new UserDaoImp(daoFactory);
    User l=new User(1 , "hazem" , "password");
    User u=userDao.getUser(l);
    System.out.println(u.getId());
    //produit prod=new produit(4, "Gaming Mouse" , 55 , "reaches up to 5000 dpi!!", null);
    //produitDao.insert(prod);
    //produit p = produitDao.getById(3);
    //System.out.println(p.getName());




}
