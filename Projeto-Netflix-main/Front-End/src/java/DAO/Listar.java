package DAO;

import METODOS.Sql;
import MODEL.Usuario;
import MODEL.Arquivos;
import MODEL.Registro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
public class Listar {
    private Connection conectar;
    private ResultSet realizarBusca;
    private Statement realizarQuery;
    private ArrayList<Arquivos> listaDeDados=new ArrayList<>();
    private HashMap<String,String> descricao=new HashMap<>();
    
    public Listar(){
        conectar=new Conexao().conectar();
    }

    public boolean verificarSeUsuarioEstaCadastrado(Usuario dadosUsuario){
        Integer anoAtual,anoDeNascimento;
        Calendar c = Calendar.getInstance();
        anoAtual=c.get(Calendar.YEAR);
        
        try{
            Sql queryBanco=Sql.SELECT;
            String sql=String.format(queryBanco.getCodigoTerciario(),dadosUsuario.getUser(),dadosUsuario.getSenha());
            realizarQuery=conectar.createStatement();
            realizarBusca=realizarQuery.executeQuery(sql);
            if(realizarBusca.next()){
                Date dbSqlDate = realizarBusca.getDate("ano");
                Date dbSqlDateConverted = new Date(dbSqlDate.getTime());
                String dataDoBanco=String.valueOf(dbSqlDateConverted);
                String anoDoBanco = dataDoBanco.substring(0,4);
                anoDeNascimento=Integer.parseInt(anoDoBanco);
                Registro.setIdade(anoAtual-anoDeNascimento);
                return true;
            }else{
                return false;
            }
        }catch(NumberFormatException | SQLException erro){
            throw new RuntimeException("erro ao listar"+erro.getMessage());
        }
    }

    public ArrayList retornarDadosDoBanco(Arquivos dadosArquivos){
        try{
          Sql queryBanco=Sql.SELECT;
          String sql=queryBanco.getCodigoQuintoplo();
          realizarQuery=conectar.createStatement();
          realizarBusca=realizarQuery.executeQuery(sql);
          while(realizarBusca.next()){
              dadosArquivos.setId(realizarBusca.getInt("idImagem"));
              dadosArquivos.setImagem(realizarBusca.getBytes("imagem"));
              dadosArquivos.setTipo(realizarBusca.getString("tipo"));
              dadosArquivos.setTitulo(realizarBusca.getString("titulo"));
              dadosArquivos.setComentario(realizarBusca.getString("descricao"));
              dadosArquivos.setClassificacao(realizarBusca.getInt("classificacaoIndicativa"));
              listaDeDados.add(dadosArquivos);
          }
        }catch(SQLException erro){
            throw new RuntimeException("erro ao listar tamanho"+erro.getMessage());
        }
      return listaDeDados;
    }

    public int retornarNumeroDeDadosNoBanco(){
        int totalDeDadosNoBanco=0;
        try{
          Sql queryBanco=Sql.SELECT;
          String sql=queryBanco.getCodigoQuintoplo();
          realizarQuery=conectar.createStatement();
          realizarBusca=realizarQuery.executeQuery(sql);
          while(realizarBusca.next()){
              totalDeDadosNoBanco=totalDeDadosNoBanco+1;
          }
        }catch(SQLException erro){
            throw new RuntimeException("erro ao listar tamanho"+erro.getMessage());
        }
        return totalDeDadosNoBanco;
    }
      
    public boolean retornarVerificacaoIdade(int numeroRandomico,int idade){
        boolean verificacao=false;
        try{
          Sql queryBanco=Sql.SELECT;
          String sql=String.format(queryBanco.getCodigoDecimo(),numeroRandomico);
          realizarQuery=conectar.createStatement();
          realizarBusca=realizarQuery.executeQuery(sql);
          if(realizarBusca.next()){
            if(idade>=realizarBusca.getInt("classificacaoIndicativa")){
                verificacao=true;
            }  
          }
        }catch(SQLException erro){
            throw new RuntimeException("erro ao listar tamanho"+erro.getMessage());
        }
        return verificacao;
    }
    
    public HashMap retornarDescricao(int id){
        try{
          Sql queryBanco=Sql.SELECT;
          String sql=String.format(queryBanco.getCodigoSextoplo(), id);
          realizarQuery=conectar.createStatement();
          realizarBusca=realizarQuery.executeQuery(sql);
          while(realizarBusca.next()){
              descricao.put(realizarBusca.getString("titulo"),realizarBusca.getString("descricao"));
          }
        }catch(SQLException erro){
            throw new RuntimeException("erro ao listar tamanho"+erro.getMessage());
        }
      return descricao;
    }
    
    public int retornarNumeroDeDadosComFiltro(String tipo){
        int totalDeDadosNoBanco=0;
        try{
          Sql queryBanco=Sql.SELECT;
          String sql=String.format(queryBanco.getCodigoQuarternario(), tipo);
          realizarQuery=conectar.createStatement();
          realizarBusca=realizarQuery.executeQuery(sql);
          while(realizarBusca.next()){
             totalDeDadosNoBanco=totalDeDadosNoBanco+1;
          }
        }catch(SQLException erro){
            throw new RuntimeException("erro ao listar tamanho"+erro.getMessage());
        }
      return totalDeDadosNoBanco;
    }
        
}
