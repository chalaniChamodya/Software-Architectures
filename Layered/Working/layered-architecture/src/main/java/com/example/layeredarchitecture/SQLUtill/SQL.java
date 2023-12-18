package com.example.layeredarchitecture.SQLUtill;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    public static <T> T execute(String sql , Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i+1,args[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }



//        CustomerDTO dto = new CustomerDTO();

//        while (resultSet.next()){
//            //dto.setId(id);
//            dto.setName(resultSet.getString("name"));
//            dto.setAddress(resultSet.getString("address"));
//        }
    }
}
