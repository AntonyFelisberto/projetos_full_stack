<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.Conexao"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String id=request.getParameter("id");
    int idade = Integer.parseInt(request.getParameter("idade"));
    try {
     PreparedStatement ps;
     Connection con = new Conexao().conectar();
     if(idade>=18){
        ps = con.prepareStatement("SELECT imagem FROM imagens WHERE idImagem=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("imagem");
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("imagem/jpg");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        }
     }else{
        ps = con.prepareStatement("SELECT imagem FROM imagens WHERE idImagem=? AND classificacaoIndicativa<18");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("imagem");
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("imagem/jpg");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        }
      }
    }catch (Exception e) {
        out.println(e);
        System.out.println(e);
    }
%>