package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.SQLUtill.SQL;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDAO{
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        Statement stm = connection.createStatement();
//        ResultSet resultSet = stm.executeQuery("SELECT * FROM Customer");
//
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ResultSet resultSet = SQL.execute("SELECT * FROM Customer");

        while (resultSet.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address")
            );
            allCustomer.add(customerDTO);
        }
        return allCustomer;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        return SQL.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", dto.getId(),dto.getName(), dto.getAddress());
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        return SQL.execute("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(), dto.getAddress(), dto.getId());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
//        pstm.setString(1, id);
//
//        return pstm.executeUpdate() > 0;
        return SQL.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        ResultSet rst = SQL.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
//        pstm.setString(1, id);
//        return pstm.executeQuery().next();
        ResultSet resultSet = SQL.execute("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id = ? ");
//        pstm.setString(1,id);
//        ResultSet resultSet = pstm.executeQuery();
//
//        CustomerDTO dto = new CustomerDTO();
//
//        while (resultSet.next()){
//            dto.setId(id);
//            dto.setName(resultSet.getString("name"));
//            dto.setAddress(resultSet.getString("address"));
//        }
//        return dto;
        return SQL.execute("SELECT * FROM Customer WHERE id = ? ", id);
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
//
        ArrayList<String> customerId = new ArrayList<>();
        ResultSet rst = SQL.execute("SELECT * FROM Customer");
//
        while (rst.next()){
            customerId.add(rst.getString("id"));
        }
        return customerId;

    }
}
