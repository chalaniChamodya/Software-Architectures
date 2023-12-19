package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
