package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.DAO.SuperDAO;
import com.example.layeredarchitecture.DTO.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {
    boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
