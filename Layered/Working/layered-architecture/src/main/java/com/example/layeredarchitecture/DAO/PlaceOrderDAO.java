package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderDAO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    boolean saveOrder(String orderId, OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    String getCustomerName(String id) throws SQLException, ClassNotFoundException;
}
