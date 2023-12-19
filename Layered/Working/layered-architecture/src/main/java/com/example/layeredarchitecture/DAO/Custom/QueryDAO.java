package com.example.layeredarchitecture.DAO.Custom;

import com.example.layeredarchitecture.model.CustomerDTO;

public interface QueryDAO {
    void customerOrderDetail(CustomerDTO customerDTO);
}
