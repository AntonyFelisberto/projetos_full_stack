package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Dados;
import java.sql.Statement;
/**
 *
 * @author Antony
 */
public class Buscar {
    private ResultSet realizarProcura;
    private Statement realizarQuery;
    private Connection conectar;
    public Buscar(){
        conectar=new Conexao().conectar();
    }
    
    public Dados buscar(int id){
        Dados retorno = null;
        
        try {
            String sql = "SELECT idImagem,imagem FROM imagens WHERE idImagem="+id;
            realizarQuery = conectar.createStatement();
            realizarProcura = realizarQuery.executeQuery(sql);
            if(realizarProcura.next()){
                retorno = new Dados();
                retorno.setId(realizarProcura.getInt("idImagem"));
                retorno.setImagem(realizarProcura.getBytes("imagem"));
            }
        } catch (SQLException e) {
            retorno = null;
        }
        return retorno;
    }
    
}
