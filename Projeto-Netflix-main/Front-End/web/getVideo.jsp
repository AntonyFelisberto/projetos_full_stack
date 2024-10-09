<%@page import="DAO.Conexao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    /*
        ADICIONAR response.setContentType("video/mp4"); PARA OUTROS TIPOS E VER SE VAI
    */
    String id = request.getParameter("id");
    try {
        Connection con = new Conexao().conectar();
        PreparedStatement ps = con.prepareStatement("SELECT video FROM videos WHERE idVideo=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("video");
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("video/mp4");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        }
    } catch (Exception e) {
        out.println(e);
    }
%>
