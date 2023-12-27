package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.DAO.SuperDAO;
import com.example.layeredarchitecture.DTO.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends SuperDAO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
