package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conectar(){
        try{
            int i=0;
            switch(i){
                case 1:
                    Class.forName("com.mysql.jdbc.Driver");
                    return DriverManager.getConnection("jdbc:mysql://localhost/CinemaOne","","");
                default:
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    return DriverManager.getConnection("jdbc:derby://localhost/CinemaOne","root","123");
            }
        }catch(ClassNotFoundException | SQLException Erro){
            throw new RuntimeException("erro ao conectar com o banco:"+Erro.getMessage());
        }
    }   
}

