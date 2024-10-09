<%@page import="METODOS.Drive"%>
<%@page import="java.awt.Dimension"%>
<%@page import="java.awt.Toolkit"%>
<%@page import="DAO.Conexao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.management.RuntimeErrorException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Random"%>
<%@page import="MODEL.Registro"%>
<%@page import="MODEL.Arquivos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.Listar"%>
<%@page import="METODOS.Sql" %>


<%
    ResultSet realizarBusca;
    Statement realizarQuery;
    Connection conectar;
    Sql queries=Sql.SELECT;
    String tipo,sqlTipo,sql,campoDePesquisa;
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/main.css">
    
    <!--responsividade-->
    <link rel="stylesheet" href="style/responsive.css">
    
    <!--owl css-->
    <link rel="stylesheet" href="style/owl/owl.carousel.min.css">
    <link rel="stylesheet" href="style/owl/owl.theme.default.min.css">
    <link href="css/botao.css" rel="stylesheet" type="text/css"/>
    <link href="css/botao.css" rel="stylesheet" type="text/css"/>
    <title>Cinema One</title>
    <style>
        
                
        [style*="--aspect-ratio"] > :first-child {
            width: 100%;
        }
        [style*="--aspect-ratio"] > img {  
          height: auto;
        }
        @supports (--custom:property) {
          [style*="--aspect-ratio"] {
            position: relative;
          }
          [style*="--aspect-ratio"]::before {
            content: "";
            display: block;
            padding-bottom: calc(100% / (var(--aspect-ratio)));
          }
          [style*="--aspect-ratio"] > :first-child {
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
          }  
        }
        
        .search-icon{
            height: 30px;
        }
        .search{
            width: 300px;
            height: 30px;
            top: -30px;
        } 
        h1 {
            display: flex;
            flex-direction: row;
            justify-content: center;
            text-align: center;
            font-size: 2.0em; 
            background-color: blue; 
            color: #FFF; 
        }
        h1:before, h1:after {
            background-color: #ddd;
            content: '\a0';
            flex-grow: 1;
            height: 1px;
            position: relative;
            top: 0.5em;
        }
        h1:before {
            margin-right:10px;
        }
        h1:after {
            margin-left:10px;
        }
    </style>
</head>
<body>
    <header>
        <div class="container">
            <h2 class="logo">Cinema One</h2>
            <nav>
                <form style="margin-right: 10px; display: inline" action="paginaInicial.jsp" method="POST">
                    <input type="text" name="pesquisa" class="search">
                    <button type="submit" value="buscar" class="search-icon">Buscar</button>
                </form>
               <%
                
                out.print("<a href='paginaInicial.jsp'>Inicio</a>");
                if(Registro.getIdade()>=18){
                    out.print("<a href='paginaInicial.jsp?tipo=M&task="+Registro.getIdade()+"'>+18</a>");
                    //CAMPO ESPECIAL DAS AULAS
                    out.print("<a href='paginaInicial.jsp?tipo=P&task="+Registro.getIdade()+"'>Aulas</a>");
                }
                out.print("<a href='paginaInicial.jsp?tipo=F&task="+Registro.getIdade()+"'>Filme</a>");
                out.print("<a href='paginaInicial.jsp?tipo=A&task="+Registro.getIdade()+"'>Anime</a>");
                out.print("<a href='paginaInicial.jsp?tipo=D&task="+Registro.getIdade()+"'>Desenho</a>");
               %>
            </nav>
        </div>
    </header>
    <section>
        <div style="text-align: center">
                    <%
                      try{
                        conectar=new Conexao().conectar();
                        sql="SELECT * FROM videos WHERE idImagem="+request.getParameter("id");
                        realizarQuery=conectar.createStatement();
                        realizarBusca=realizarQuery.executeQuery(sql);

                        while(realizarBusca.next()){
                            Toolkit tk = Toolkit.getDefaultToolkit();
                            Dimension d = tk.getScreenSize();
                            if(realizarBusca.getBlob("video")==null){
                                out.print("<br>");
                                out.print("<h1>"+realizarBusca.getString("titulo")+"</h1>");
                                out.print("<div style='--aspect-ratio: 16/9;'>");
                                Drive drive=Drive.VIDEODRIVE;
                                String driver=String.format(drive.getUrl(),realizarBusca.getString("videoEstilizado"));
                                out.print(driver);  
                                out.print("</div>");
                            }else{
                                out.print("<br>");
                                out.print("<h1>"+realizarBusca.getString("titulo")+"</h1>");
                                out.print("<video class='box-filme' width='"+d.width+"' height='"+d.height+"' controls  frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen>");
                                out.print("<source src='getVideo.jsp?id="+realizarBusca.getInt("idVideo")+"' type='video/mp4'>");
                                out.print("</video>");  
                            }    
                        }
                      }catch(Exception erro){
                          throw new RuntimeException(erro);
                      }
                    %>       
            </div>  
        </section>
    <div class="carrosel-filmes">
        <div class="owl-carousel owl-theme">
            <%     
              campoDePesquisa=request.getParameter("pesquisa");
              if(campoDePesquisa!=null){
                conectar=new Conexao().conectar();
                sqlTipo=String.format(queries.getCodigoNono(),"%",campoDePesquisa.toUpperCase(),"%","%",campoDePesquisa.toLowerCase(),"%");
                realizarQuery=conectar.createStatement();
                realizarBusca=realizarQuery.executeQuery(sqlTipo);
                
                while(realizarBusca.next()){
                  if(Registro.getIdade()>=realizarBusca.getInt("classificacaoIndicativa")){
                    out.print("<div class='item'>");
                    out.print("<h3>"+realizarBusca.getString("titulo")+"</h3>");
                    out.print("<img class='box-filme' style='height:400px;' src='getImage.jsp?idade="+Registro.getIdade()+"&id="+realizarBusca.getInt("idImagem")+"' alt='imagem'/>");
                          out.print("<div style='text-align: center; object-fit: cover;'><br>");
                              out.print("<button role='button' class='botao'><i class='fas fa-play'></i><a href='assistirFilme.jsp?id="+realizarBusca.getInt("idImagem")+"'>ASSISTIR</a></button>");
                          out.print("</div>");
                    out.print("</div>");
                  }
                }
              }else if(request.getParameter("tipo")==null){
                conectar=new Conexao().conectar();
                
                //S� � POSSIVEL UTILIZAR TRAZER CERTOS ATRIBUTOS NO RESULTSET SE COLOCALOS NA QUERY, NO CASO S� TROUXE idImagem,classificacaoIndicativa CASO PRECISASE USAR ALGO MAIS TERIA QUE COLOCALOS NA QUERY
                sql=queries.getCodigoSetimo();
                realizarQuery=conectar.createStatement();
                realizarBusca=realizarQuery.executeQuery(sql);
                
                while(realizarBusca.next()){
                  if(Registro.getIdade()>=realizarBusca.getInt("classificacaoIndicativa")){
                    out.print("<div class='item'>");
                    out.print("<h3>"+realizarBusca.getString("titulo")+"</h3>");
                    out.print("<img class='box-filme' style='height:400px;' src='getImage.jsp?idade="+Registro.getIdade()+"&id="+realizarBusca.getInt("idImagem")+"' alt='imagem'/>");
                          out.print("<div style='text-align: center; object-fit: cover;'><br>");
                              out.print("<button role='button' class='botao'><i class='fas fa-play'></i><a href='assistirFilme.jsp?id="+realizarBusca.getInt("idImagem")+"'>ASSISTIR</a></button>");
                          out.print("</div>");
                     out.print("</div>");
                  }
                }
              }else{
                tipo=request.getParameter("tipo");
                
                conectar=new Conexao().conectar();
                sqlTipo=String.format(queries.getCodigoOitavo(),tipo);
                realizarQuery=conectar.createStatement();
                realizarBusca=realizarQuery.executeQuery(sqlTipo);
                
                while(realizarBusca.next()){
                  if(Registro.getIdade()>=realizarBusca.getInt("classificacaoIndicativa")){
                    out.print("<div class='item'>");
                    out.print("<h3>"+realizarBusca.getString("titulo")+"</h3>");
                    out.print("<img class='box-filme' style='height:400px;' src='getImage.jsp?idade="+Registro.getIdade()+"&id="+realizarBusca.getInt("idImagem")+"' alt='imagem'/>");
                          out.print("<div style='text-align: center; object-fit: cover;'><br>");
                              out.print("<button role='button' class='botao'><i class='fas fa-play'></i><a href='assistirFilme.jsp?id="+realizarBusca.getInt("idImagem")+"'>ASSISTIR</a></button>");
                          out.print("</div>");
                    out.print("</div>");
                  }
                }
              }
            %>
        </div>
    </div>
    <script src="https://kit.fontawesome.com/2c36e9b7b1.js"></script>
    <script src="js/owl/jquery.min.js"></script>
    <script src="js/owl/owl.carousel.min.js"></script>
    <script src="js/owl/setup.js"></script>
</body>
</html>