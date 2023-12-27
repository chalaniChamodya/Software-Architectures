package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.BO.Custom.Impl.CustomerBoImpl;
import com.example.layeredarchitecture.BO.Custom.Impl.ItemBoImpl;
import com.example.layeredarchitecture.BO.Custom.Impl.PlaceOrderBoImpl;
import com.example.layeredarchitecture.BO.SuperBO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER, ITEM, ORDER
    }

    public SuperBO getBO(BOFactory.BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case ORDER:
                return new PlaceOrderBoImpl();
        }
        return null;
    }
}
