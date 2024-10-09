package DAO;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/*IMPORTAR
  DRIVER DO JAVA DB
  MYSQL CONNECTOR
  TESTAR DUAS CONEXOES
*/


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


//--  QUERIES CRIAÇÃO DO BANCO EM JDBC
//
//-- create table imagens(
//--  idImagem int primary key generated always as identity (start with 1,increment by 1),
//--  imagem blob,
//--  tipo varchar(20),
//--  titulo varchar(100),
//--  classificacaoIndicativa int,
//--  descricao varchar(500)
//-- );
//-- 
//
//-- create table audio(
//--   id int primary key generated always as identity(start with 1, increment by 1),
//--   som blob,
//--   comentario varchar(500),
//--   idDaImagem int,
//--   CONSTRAINT idDaImagem FOREIGN KEY (idDaImagem) REFERENCES imagens(idImagem)
//-- );
//
//-- create table videos(
//--     idVideo int primary key generated always as identity (start with 1,increment by 1), 
//--     video blob,
//--     titulo varchar(100),
//--     tipoConteudo varchar(50),--TESTAR response.setContentType("video/mp4");
//--     videoEstilizado varchar(500),
//--     idImagem int,
//--     constraint idImagem foreign key(idImagem) references imagens(idImagem)
//-- );
//
//-- create table Usuario(
//--     id int primary key generated always as identity (start with 1,increment by 1), 
//--     nome varchar(50),
//--     senha varchar(50),
//--     cpf varchar(15),
//--     email varchar(50),
//--     telefone varchar(50),
//--     ano DATE
//-- );

//--  QUERIES CRIAÇÃO DO BANCO EM MYSQL
//
//  create database CinemaOne;
//
//-- create table imagens(
//--  idImagem int primary key AUTO_INCREMENT,
//--  imagem longblob,
//--  tipo varchar(20),
//--  titulo varchar(100),
//--  classificacaoIndicativa int,
//--  descricao varchar(500)
//-- );
//-- 
//
//-- create table audio(
//--   id int primary key AUTO_INCREMENT,
//--   som longblob,
//--   comentario varchar(500),
//--   idDaImagem int,
//--   CONSTRAINT idDaImagem FOREIGN KEY (idDaImagem) REFERENCES imagens(idImagem)
//-- );
//
//-- create table videos(
//--     idVideo int primary key AUTO_INCREMENT, 
//--     video longblob,
//--     titulo varchar(100),
//==     tipoConteudo varchar(50),
//--     videoEstilizado varchar(500),
//--     idImagem int,
//--     constraint idImagem foreign key(idImagem) references imagens(idImagem)
//-- );
//
//-- create table Usuario(
//--     id int primary key AUTO_INCREMENT, 
//--     nome varchar(50),
//--     senha varchar(50),
//--     cpf varchar(15),
//--     email varchar(50),
//--     telefone varchar(50),
//       ano DATE
//-- );

//   QUERIES VISIBILIDADE E EXTRAÇÃO
//-- select * from VIDEOS inner join IMAGENS on VIDEOS.IDIMAGEM=IMAGENS.IDIMAGEM;
//-- select * from VIDEOS; 
//-- select * from USUARIO;
//-- select * from IMAGENS;
//
//-- drop table USUARIO;
//-- drop table IMAGENS;
//-- drop table VIDEOS;