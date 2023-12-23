package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;

public interface QueryDAO {
    void customerOrderDetail(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
