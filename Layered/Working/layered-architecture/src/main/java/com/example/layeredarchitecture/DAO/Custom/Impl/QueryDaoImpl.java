package com.example.layeredarchitecture.DAO.Custom.Impl;

import com.example.layeredarchitecture.DAO.Custom.QueryDAO;
import com.example.layeredarchitecture.SQLUtill.SQL;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDaoImpl implements QueryDAO {
    @Override
    public void customerOrderDetail(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQL.execute("SELECT c.name, c.address, o.oid FROM Customer c LEFT JOIN Orders o ON c.id = o.customerID WHERE c.id = ? ", customerDTO.getId());
        while(rst.next()){
            System.out.println("Customer name : "+ rst.getString(1)+ "\nAddress : "+rst.getString(2) + "\nOrder IDs : "+rst.getString(3));
        }
    }
}
