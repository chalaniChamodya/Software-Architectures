package com.example.layeredarchitecture.BO.Impl;

import com.example.layeredarchitecture.BO.ItemBO;
import com.example.layeredarchitecture.DAO.Custom.Impl.ItemDaoImpl;
import com.example.layeredarchitecture.DAO.Custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDaoImpl();

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(dto);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }
}
