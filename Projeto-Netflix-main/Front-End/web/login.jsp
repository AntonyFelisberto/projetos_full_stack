<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/paginaCadastro.css">
    <meta name="viewport" content="wth=device-width, initial-scale=1.0">
    <script src="javascript/telaCadastro.js"></script>
    <title>Pagina de login Cinema One</title>
</head>
<body id="fundo" >
    <div id="video">
    <video id="toque"  autoplay loop class="bg_video">
        <source src="arquivos/anime-login.mp4" type="video/mp4">
    </video>
    </div>
   <header>
    <h1>WELCOME TO CINEMA ONE</h1>
    <h2>por favor preencha o formulario a baixo</h2>
   </header>
    <main> 
    <form action="verificarLogin.jsp" method="post">
        <label for="nome">user</label><br>
        <input type="text" name="nome"  placeholder="usuario" required><br>
        <label for="password">senha</label><br>
        <input type="password" name="senha" placeholder="senha" required><br><br>
        <input type="submit" value="enviar"><br>
    </form>
    <br>
    <button><a href="index.jsp">não tenho cadastro</a></button>
    </main>
</body>
</html>