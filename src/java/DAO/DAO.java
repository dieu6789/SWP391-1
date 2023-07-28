/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utility.BirdDTO;
import Utility.FavoriteDTO;
import Utility.FeedbackDTO;
import Utility.GetProductToFeedbackDTO;
import Utility.OrderDTO;
import Utility.OrderDetailDTO;
import Utility.ProductListDTO;
import Utility.RefundDTO;
import Utility.RevenueDTO;
import Utility.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class DAO {

    private List<BirdDTO> listBird;
    private List<FavoriteDTO> listFavorite;
    private List<UserDTO> listAccount;
    private List<ProductListDTO> listProduct;
    private List<OrderDTO> listOrder;
    private List<FeedbackDTO> listFeedback;
    private List<OrderDetailDTO> listOrderDetail;
    private List<RevenueDTO> listRevenue;
    private List<GetProductToFeedbackDTO> listGetProductToFeedbackDTO;
    private List<RefundDTO> listRefund;

    public List<RefundDTO> getListRefund() {
        return listRefund;
    }

    public List<GetProductToFeedbackDTO> getListGetProductToFeedbackDTO() {
        return listGetProductToFeedbackDTO;
    }

    public List<RevenueDTO> getListRevenue() {
        return listRevenue;
    }

    public List<OrderDetailDTO> getListOrderDetail() {
        return listOrderDetail;
    }

    public List<UserDTO> getListAccount() {
        return listAccount;
    }

    public List<OrderDTO> getListOrder() {
        return listOrder;
    }

    public List<FavoriteDTO> getListFavorite() {
        return listFavorite;
    }

    public List<ProductListDTO> getListProduct() {
        return listProduct;
    }

    public List<BirdDTO> getListBird() {
        return listBird;
    }

    public List<FeedbackDTO> getListFeedback() {
        return listFeedback;
    }

    public static boolean checkLogin(String mail, String password) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * From dbo.Account_User Where mail = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mail);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertAccount(int user_id, String username, String password, String mail) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Account_User (user_id,username,password,mail) Values (?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setString(2, username);
                stm.setString(3, password);
                stm.setString(4, mail);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertProfile(int user_id, String username, String mail, String address, String phoneNumber, String role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.ProfileUser (user_id,username,mail,address,phone_number,role) Values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setString(2, username);
                stm.setString(3, mail);
                stm.setString(4, address);
                stm.setString(5, phoneNumber);
                stm.setString(6, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String getRole(String mail) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String Role = "";
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select role from dbo.ProfileUser where mail like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mail);
                ResultSet row = stm.executeQuery();
                if (row.next()) {
                    Role = row.getString("role");
                    return Role;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return Role;
    }

    public boolean updatePassword(String mail, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Update dbo.Account_User set password = ? where mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, mail);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchUser(String mail) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select * from dbo.ProfileUser where mail like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mail);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    mail = rs.getString("mail");
                    String address = rs.getString("address");
                    String phone_number = rs.getString("phone_number");
                    String role = rs.getString("role");

                    UserDTO dto = new UserDTO(user_id, username, mail, address, phone_number, role);
                    if (this.listAccount == null) {
                        this.listAccount = new ArrayList<UserDTO>();
                    }
                    this.listAccount.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean uniqueID(int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select user_id from dbo.Account_User where user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public boolean uniqueMail(String mail) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select mail from dbo.Account_User where mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mail);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public void orderHistory(int user_id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select top 10 * from dbo.OrderList where user_id = ? order by order_date desc";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    user_id = rs.getInt("user_id");
                    Date date = rs.getDate("order_date");
                    int totalPrice = rs.getInt("total_price");
                    String status = rs.getString("status");

                    OrderDTO orderDTO = new OrderDTO(order_id, user_id, date, totalPrice, status);
                    if (this.listOrder == null) {
                        this.listOrder = new ArrayList<OrderDTO>();
                    }
                    this.listOrder.add(orderDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateInformation(String mail, String phone_number, String address) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Update dbo.ProfileUser set phone_number = ?,address = ? where mail = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, phone_number);
                stm.setString(2, address);
                stm.setString(3, mail);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchFavoriteProduct(int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "SELECT Product_list.product_id, product_name,price\n"
                        + "FROM Product_list\n"
                        + "JOIN favorite\n"
                        + "ON Product_list.product_id = dbo.Favorite.product_id\n"
                        + "WHERE dbo.Favorite.user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");

                    FavoriteDTO favoriteDTO = new FavoriteDTO(product_id, product_name, price);
                    if (this.listFavorite == null) {
                        this.listFavorite = new ArrayList<FavoriteDTO>();
                    }
                    this.listFavorite.add(favoriteDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean removeFavoriteProduct(int user_id, int product_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Delete from dbo.Favorite where user_id = ? and product_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setInt(2, product_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchProduct(String product_name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List where product_name like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + product_name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void sortProductByPrice() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List order by price";
                stm = con.prepareStatement(sql);
     
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void sortProductByQuantity() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List  order by quantity";
                stm = con.prepareStatement(sql);
  
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void sortProductByName() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List order by product_name";
                stm = con.prepareStatement(sql);
    
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int getID(String mail) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int id = 0;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select user_id from dbo.ProfileUser where mail like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mail);
                ResultSet row = stm.executeQuery();
                if (row.next()) {
                    id = row.getInt("user_id");
                    return id;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }

    public boolean addToFavorite(int code, int user_id, int product_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Favorite (id,user_id,product_id) Values (?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, code);
                stm.setInt(2, user_id);
                stm.setInt(3, product_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchBird(String bird_name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.BIRD where bird_name like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + bird_name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bird_id = rs.getInt("bird_id");
                    bird_name = rs.getString("bird_name");
                    String product_name = rs.getString("product_name");
                    int product_id = rs.getInt("product_id");
                    String species = rs.getString("species");
                    String image_url = rs.getString("Images");
                    String desc_order = rs.getString("description_order");
                    BirdDTO birdList = new BirdDTO(bird_id, bird_name, product_id, product_name, desc_order, species, image_url);
                    if (this.listBird == null) {
                        this.listBird = new ArrayList<BirdDTO>();
                    }
                    this.listBird.add(birdList);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void showFeedback(String product_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "SELECT account_user.username, feedback.date_feedback, feedback.comment\n"
                        + "FROM feedback\n"
                        + "INNER JOIN account_user ON feedback.user_id = account_user.user_id WHERE feedback.product_id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, product_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    Date date_feedback = rs.getDate("date_feedback");
                    String comment = rs.getString("comment");

                    FeedbackDTO feedbackDTO = new FeedbackDTO(username, date_feedback, comment);
                    if (this.listFeedback == null) {
                        this.listFeedback = new ArrayList<FeedbackDTO>();
                    }
                    this.listFeedback.add(feedbackDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getProductDetail(String product_id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List where product_name = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, product_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getBirdDetail(int bird_id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.BIRD where bird_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, bird_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    bird_id = rs.getInt("bird_id");
                    String bird_name = rs.getString("bird_name");
                    String product_name = rs.getString("product_name");
                    int product_id = rs.getInt("product_id");
                    String species = rs.getString("species");
                    String image_url = rs.getString("Images");
                    String desc_order = rs.getString("description_order");
                    BirdDTO birdList = new BirdDTO(bird_id, bird_name, product_id, product_name, desc_order, species, image_url);
                    if (this.listBird == null) {
                        this.listBird = new ArrayList<BirdDTO>();
                    }
                    this.listBird.add(birdList);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertOrderToDB(int order_id, int user_id, String order_date, float total_price, String status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "insert dbo.OrderList (order_id,user_id,order_date,total_price,status) values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, order_id);
                stm.setInt(2, user_id);
                stm.setString(3, order_date);
                stm.setFloat(4, total_price);
                stm.setString(5, status);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertOrderDetail(int order_item_id, int order_id, String product_list, float total_price, String phone_number, String address, String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "insert dbo.OrderDetail (order_item_id,order_id,product_list,price,phone_number,address,username) values (?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, order_item_id);
                stm.setInt(2, order_id);
                stm.setString(3, product_list);
                stm.setFloat(4, total_price);
                stm.setString(5, phone_number);
                stm.setString(6, address);
                stm.setString(7, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean cancelOrder(int Order_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql
                        = "Update dbo.OrderList set status = 'CANCELED' WHERE order_id = ?;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Order_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean addProduct(int product_id, String product_name, int price, int quantity, String desc, String birds, String image_url) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Product_List (product_id,product_name,price,quantity,images,description,birds) Values (?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, product_id);
                stm.setString(2, product_name);
                stm.setInt(3, price);
                stm.setInt(4, quantity);
                stm.setString(5, image_url);
                stm.setString(6, desc);
                stm.setString(7, birds);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteProduct(int product_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Delete from dbo.Product_List where product_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, product_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateProduct(int product_id, String product_name, int price, int quantity, String desc, String bird) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Update dbo.Product_List set product_name = ? , price = ?, quantity = ? , description = ? , birds = ? where product_id = ? ";
                stm = con.prepareStatement(sql);

                stm.setString(1, product_name);
                stm.setInt(2, price);
                stm.setInt(3, quantity);
                stm.setString(4, desc);
                stm.setString(5, bird);
                stm.setInt(6, product_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void showOrderList(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select d.*,l.status from dbo.OrderDetail d, dbo.OrderList l where d.order_id = l.order_id and username like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + username + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    username = rs.getString("username");
                    int price = rs.getInt("price");
                    String address = rs.getString("address");
                    String phone_number = rs.getString("phone_number");
                    String product_list = rs.getString("product_list");
                    String status = rs.getString("status");

                    OrderDetailDTO dto = new OrderDetailDTO(order_id, username, phone_number, address, price, product_list, status);
                    if (this.listOrderDetail == null) {
                        this.listOrderDetail = new ArrayList<OrderDetailDTO>();
                    }
                    this.listOrderDetail.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchAllUser(String mail) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select * from dbo.ProfileUser where mail like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + mail + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    mail = rs.getString("mail");
                    String address = rs.getString("address");
                    String phone_number = rs.getString("phone_number");
                    String role = rs.getString("role");

                    UserDTO dto = new UserDTO(user_id, username, mail, address, phone_number, role);
                    if (this.listAccount == null) {
                        this.listAccount = new ArrayList<UserDTO>();
                    }
                    this.listAccount.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean DeleteStaff(int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Delete from dbo.ProfileUser where user_id = ? \n"
                        +    "Delete from dbo.Account_User where user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setInt(2, user_id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    public boolean addStaff(int user_id, String username, String password, String mail, String address, String phoneNumber, String role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Account_User (user_id,username,password,mail) values (?,?,?,?) \n"
                        + "Insert into dbo.ProfileUser (user_id,username,mail,address,phone_number,role) values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setString(2, username);
                stm.setString(3, password);
                stm.setString(4, mail);
                stm.setInt(5, user_id);
                stm.setString(6, username);
                stm.setString(7, mail);
                stm.setString(8, address);
                stm.setString(9, phoneNumber);
                stm.setString(10, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateOrder(int orderID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "update dbo.OrderList set status = 'DELIVERING' where order_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void revenuelist(int year) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "SELECT months.month AS month, COALESCE(SUM(orderList.total_price), 0) AS revenue\n"
                        + "FROM (\n"
                        + "    SELECT '01' AS month UNION ALL SELECT '02' UNION ALL SELECT '03' UNION ALL SELECT '04' UNION ALL SELECT '05' UNION ALL\n"
                        + "    SELECT '06' UNION ALL SELECT '07' UNION ALL SELECT '08' UNION ALL SELECT '09' UNION ALL SELECT '10' UNION ALL\n"
                        + "    SELECT '11' UNION ALL SELECT '12'\n"
                        + ") months\n"
                        + "LEFT JOIN orderList ON months.month = FORMAT(orderList.order_date, 'MM')\n"
                        + "    AND YEAR(orderList.order_date) = ? \n"
                        + "    AND orderList.status = 'CONFIRMED'\n"
                        + "GROUP BY months.month";
                stm = con.prepareStatement(sql);
                stm.setInt(1, year);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String month = rs.getString("month");
                    int revenue = rs.getInt("revenue");

                    RevenueDTO revenueList = new RevenueDTO(month, revenue);
                    if (this.listRevenue == null) {
                        this.listRevenue = new ArrayList<RevenueDTO>();
                    }
                    this.listRevenue.add(revenueList);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void updateQuantity(int quantity, String product_name) throws SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "UPDATE Product_List\n"
                        + "SET Quantity = Quantity - ? \n"
                        + "WHERE product_name = ?;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, product_name);
                int row = stm.executeUpdate();
                if (row > 0) {
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean uniqueFavorite(int user_id, int product_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select id from dbo.Favorite where user_id = ? and product_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setInt(2, product_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public void getFeedback(int order_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "SELECT \n"
                        + "    pl.product_id,\n"
                        + "    pl.Images\n"
                        + "FROM \n"
                        + "    orderDetail AS od\n"
                        + "CROSS APPLY \n"
                        + "    STRING_SPLIT(od.product_list, '|') AS t\n"
                        + "INNER JOIN \n"
                        + "    Product_List AS pl ON LTRIM(RTRIM(REPLACE(t.value, CONCAT(SUBSTRING(t.value, 1, PATINDEX('%[a-zA-Z]%', t.value)-1), ''), ''))) = pl.product_name\n"
                        + "WHERE \n"
                        + "    od.order_id = ?;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, order_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_int = rs.getInt("product_id");
                    String images_url = rs.getString("Images");

                    GetProductToFeedbackDTO dto = new GetProductToFeedbackDTO(product_int, images_url);
                    if (this.listGetProductToFeedbackDTO == null) {
                        this.listGetProductToFeedbackDTO = new ArrayList<GetProductToFeedbackDTO>();
                    }
                    this.listGetProductToFeedbackDTO.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void Feedback(int id, int user_id, int product_id, String date, String feedback) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Feedback (id,user_id,product_id,Date_feedback,comment) values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setInt(2, user_id);
                stm.setInt(3, product_id);
                stm.setString(4, date);
                stm.setString(5, feedback);
                int row = stm.executeUpdate();
                if (row > 0) {
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void setFeedbackOrder(int Order_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql
                        = "Update dbo.OrderList set status = 'FEEDBACKED' WHERE order_id = ?;";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Order_id);

                int row = stm.executeUpdate();
                if (row > 0) {

                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void insertRequest(int requestID, int order_id, String mail, String date, String reason) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Insert into dbo.Request (request_id,mail,order_id,Date_request,request_text) Values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, requestID);
                stm.setString(2, mail);
                stm.setInt(3, order_id);
                stm.setString(4, date);
                stm.setString(5, reason);
                int row = stm.executeUpdate();
                if (row > 0) {
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public String getOrderStatus(int order_id, int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String status = "";
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select status from dbo.OrderList where order_id = ? and user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, order_id);
                stm.setInt(2, user_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return status;
    }

    public boolean getRefundList(String mail) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select * from dbo.Request where mail like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + mail + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int refundID = rs.getInt("request_id");
                    mail = rs.getString("mail");
                    int order_ID = rs.getInt("order_id");
                    Date date = rs.getDate("Date_request");
                    String reason = rs.getString("request_text");
                    RefundDTO dto = new RefundDTO(refundID, order_ID, mail, reason, date);
                    if (this.listRefund == null) {
                        this.listRefund = new ArrayList<RefundDTO>();
                    }
                    this.listRefund.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    public String getMailbyRequest(int requestID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String mail = "";
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "Select mail from dbo.Request where request_id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, requestID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    mail = rs.getString("mail");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return mail;
    }

    public void HandleRequest(int requestID) throws SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "update dbo.OrderList set status = 'REFUND' \n"
                        + "where order_id IN (\n"
                        + "	select order_id\n"
                        + "	from Request\n"
                        + "	where request_id = ?\n"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setInt(1, requestID);
                int row = stm.executeUpdate();
                if (row > 0) {
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void DeleteRequest(int requestID) throws SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = " delete from dbo.request where request_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, requestID);
                int row = stm.executeUpdate();
                if (row > 0) {
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean confirmOrder(int orderID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "update dbo.OrderList set status = 'CONFIRMED' where order_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String checkRole(int user_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        String Role = "";
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select role from dbo.ProfileUser where user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                ResultSet row = stm.executeQuery();
                if (row.next()) {
                    Role = row.getString("role");
                    return Role;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return Role;
    }
    public int getProductID(String product_name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int id = 0;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select product_id from dbo.Product_List where product_name = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, product_name);
                ResultSet row = stm.executeQuery();
                if (row.next()) {
                    id = row.getInt("product_id");
                    return id;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }
     public void sortProductByPrice2() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List order by price desc";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void sortProductByQuantity2() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List order by quantity desc";
                stm = con.prepareStatement(sql);
 
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void sortProductByName2() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select * from dbo.Product_List order by product_name desc";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int product_id = rs.getInt("product_id");
                    String product_name = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String desc = rs.getString("description");
                    String image_url = rs.getString("Images");
                    String birds = rs.getString("birds");
                    ProductListDTO productListDTO = new ProductListDTO(product_id, product_name, price, quantity, desc, image_url, birds);
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductListDTO>();
                    }
                    this.listProduct.add(productListDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
     public int getProductQuantity(String product_name) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int id = 0;
        try {
            con = DBUtility.makeConnection();
            if (con != null) {
                String sql = "select quantity from dbo.Product_List where product_name = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, product_name);
                ResultSet row = stm.executeQuery();
                if (row.next()) {
                    id = row.getInt("quantity");
                    return id;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return id;
    }
}
