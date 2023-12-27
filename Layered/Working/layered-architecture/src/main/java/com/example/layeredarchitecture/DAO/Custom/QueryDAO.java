package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.DAO.SuperDAO;
import com.example.layeredarchitecture.DTO.CustomerDTO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    void customerOrderDetail(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
