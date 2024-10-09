<%@page import="METODOS.ModelosJs"%>
<%@page import="java.security.KeyPair"%>
<%@page import="METODOS.ValidarCpf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.Inserir" %>
<%@page import="METODOS.ValidarEmail" %>
<%@page import="METODOS.ValidarTelefone" %>
<%@page import="MODEL.Usuario" %>
<%
   try{  
     String nome,senha,email,cpf,telefone,senhaConfirmar;
     int idade;
     boolean cadastrado,cpfValido,emailValido,telefoneValido;
     
     nome=request.getParameter("nome");
     senha=request.getParameter("senha");
     senhaConfirmar=request.getParameter("senha");
     email=request.getParameter("email");
     cpf=request.getParameter("cpf");
     telefone=request.getParameter("telefone");
     idade=Integer.parseInt(request.getParameter("idade"));
     
     cpfValido=new ValidarCpf().validar(cpf);
     emailValido=new ValidarEmail().valideEmail(email);
     telefoneValido=new ValidarTelefone().validarTelefone(telefone);
     ModelosJs javascript=ModelosJs.JAVASCRIPT;
         
     if(cpfValido && emailValido && telefoneValido && senha.length()>=10){   
         Usuario inserirDadosDoUsuario=new Usuario();
         Inserir executarInsercaoNoBanco=new Inserir();
 
         inserirDadosDoUsuario.setCpf(cpf);
         inserirDadosDoUsuario.setEmail(email);
         inserirDadosDoUsuario.setUser(nome);
         inserirDadosDoUsuario.setSenha(senha);
         inserirDadosDoUsuario.setTelefone(telefone);       
         inserirDadosDoUsuario.setIdade(idade);
         cadastrado=executarInsercaoNoBanco.inserirNoBanco(inserirDadosDoUsuario);

         if(cadastrado){
             out.print(javascript.getCadastroEfetuado());
             response.sendRedirect("paginaInicial.jsp");
         }else{
             out.print(javascript.getUsuarioJaCadastrado());
             out.print(javascript.getRedirecionamentoParaIndex());
         }
     }else if(!senha.equals(senhaConfirmar)){
            out.print(javascript.getSenhasNaoBatem());
            out.print(javascript.getRedirecionamentoParaIndex());
     }else if(senha.length()<10){
            out.print(javascript.getNumeroDeCaracteresMenorQueDez());
            out.print(javascript.getRedirecionamentoParaIndex());
     }else{
        out.print(javascript.getDadosInvalidos());
        out.print(javascript.getRedirecionamentoParaIndex()); 
     }   
   }catch(Exception Erro){
      throw new RuntimeException("erro ao inserir dados:"+Erro.getMessage());
   }
%>   
