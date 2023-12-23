package com.example.layeredarchitecture.BO.Impl;

import com.example.layeredarchitecture.BO.PlaceOrderBO;
import com.example.layeredarchitecture.DAO.Custom.*;
import com.example.layeredarchitecture.DAO.Custom.Impl.*;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBO {
    OrderDAO orderDAO = new OrderDaoImpl();
    ItemDAO itemDAO = new ItemDaoImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDaoImpl();

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;

            connection = DBConnection.getDbConnection().getConnection();
//            PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
//            stm.setString(1, orderId);
//            /*if order id already exist*/
//            if (stm.executeQuery().next()) {
//
//            }
            boolean isOrderExist = orderDAO.existOrder(orderId);
            if(isOrderExist){
                return false;
            }

            connection.setAutoCommit(false);
//            stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
//            stm.setString(1, orderId);
//            stm.setDate(2, Date.valueOf(orderDate));
//            stm.setString(3, customerId);

            boolean isOrderSaved = orderDAO.saveOrder(new OrderDTO(orderId, orderDate,customerId));

            if(!isOrderSaved){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
//            if (stm.executeUpdate() != 1) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                return false;
//            }

//            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
//
//            for (OrderDetailDTO detail : orderDetails) {
//                stm.setString(1, orderId);
//                stm.setString(2, detail.getItemCode());
//                stm.setBigDecimal(3, detail.getUnitPrice());
//                stm.setInt(4, detail.getQty());
//
//                if (stm.executeUpdate() != 1) {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                    return false;
//                }
            for (OrderDetailDTO detail : orderDetails){
                boolean isSavedOrderDetails = orderDetailDAO.saveOrderDetails(detail);
                if(!isSavedOrderDetails){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                boolean isUpdateItem = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
                if(!isUpdateItem){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
//
////                //Search & Update Item
//                ItemDTO item = findItem(detail.getItemCode());
//                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
//
//                PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
//                pstm.setString(1, item.getDescription());
//                pstm.setBigDecimal(2, item.getUnitPrice());
//                pstm.setInt(3, item.getQtyOnHand());
//                pstm.setString(4, item.getCode());
//
//                if (!(pstm.executeUpdate() > 0)) {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                    return false;
//                }
//            }
//
            connection.commit();
            connection.setAutoCommit(true);
            return true;

    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    public ItemDTO findItem(String code) {
        try {
//            Connection connection = DBConnection.getDbConnection().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
//            pstm.setString(1, code);
//            ResultSet rst = pstm.executeQuery();
//            rst.next();
//            return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
