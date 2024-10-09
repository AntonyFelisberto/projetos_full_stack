<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/paginaCadastro.css">
    <meta name="viewport" content="wth=device-width, initial-scale=1.0">
    <script src="javascript/telaCadastro.js"></script>
    <title>Pagina de cadastro Cinema One</title>
</head>
<body id="fundo">
    <div id="video">
    <video id="toque"  autoplay loop class="bg_video">
        <source src="arquivos/anime.mp4" type="video/mp4">
    </video>
    </div>
   <header>
    <h1>WELCOME TO CINEMA ONE</h1>
    <h2>por favor preencha o formulario a baixo</h2>
   </header>
   <main>
    <form action="inserirCadastro.jsp" method="post">
        <label for="nome">user</label><br>
        <input type="text" name="nome"  placeholder="usuario" required><br>
        <label for="password">senha</label><br>
        <input type="password" name="senha" placeholder="senha" required><br>
        <label for="senhaConfirma">Confirmar senha</label><br>
        <input type="password" name="senhaConfirma" placeholder="senha" required><br>
        <label for="cpf">cpf</label><br>
        <input type="text" name="cpf" placeholder="cpf com ponto e traço" required><br>
        <label for="telefone">telefone</label><br>
        <input type="tel" name="telefone" placeholder="telefone" required><br>
        <label for="idade">idade</label><br>
        <input type="number" min="5" max="100" name="idade" placeholder="idade" required><br>
        <label for="email">email</label><br>
        <input type="email" name="email" placeholder="email" required><br><br>
        <input type="submit" value="enviar"><br>
    </form>
    <br>
    <button><a href="login.jsp">ja tenho cadastro</a></button>
    </main>
</body>
</html>
