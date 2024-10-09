<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.Usuario"%>
<%@page import="DAO.Listar"%>
<%@page import="METODOS.ModelosJs" %>
<!DOCTYPE html>
<html>
<%
    try{
        Usuario guardarDadosUsuario=new Usuario();
        Listar verificarCondicao=new Listar();
        String nome,senha;
        boolean usuarioExistente;
        nome=request.getParameter("nome");
        senha=request.getParameter("senha");
        
        guardarDadosUsuario.setUser(nome);
        guardarDadosUsuario.setSenha(senha);
        
        usuarioExistente=verificarCondicao.verificarSeUsuarioEstaCadastrado(guardarDadosUsuario);
        ModelosJs javascript=ModelosJs.JAVASCRIPT;
        if(usuarioExistente){
             out.print(javascript.getVerificarLoginSucesso());
             response.sendRedirect("paginaInicial.jsp");
        }else{
             out.print(javascript.getLoginSemSucesso());
             out.print(javascript.getRetornoPaginaLoginParaPaginaIndex());
        }   
    }catch(Exception erro){
        throw new RuntimeException("erro ao verificar dados"+erro.getMessage());
    }
%>
</html>
