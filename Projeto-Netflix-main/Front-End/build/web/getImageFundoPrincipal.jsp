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
    try {
        PreparedStatement ps;
        Connection con = new Conexao().conectar();
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
    }catch (Exception e) {
        out.println(e);
        System.out.println(e);
    }
%>