/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package METODOS;

/**
 *
 * @author Antony
 */
public enum ModelosJs {
    JAVASCRIPT("<script>setTimeout(function(){window.alert('logando')},10)</script>"
            ,"<script>setTimeout(function(){window.alert('voce não esta cadastrado')},10)</script>",
            "<script>setTimeout(function(){window.location.replace('index.jsp')},20)</script>",
            "<script>window.alert('cadastrado')</script>",
            "<script>setTimeout(function(){window.alert('voce ja esta cadastrado')},10)</script>",
            "<script>setTimeout(function(){window.location.replace('index.jsp')},20)</script>",
            "<script>setTimeout(function(){window.alert('senhas não coincidem')},10)</script>",
            "<script>setTimeout(function(){window.alert('senha menor que 10 caracteres')},10)</script>",
            "<script>setTimeout(function(){window.alert('dados invalidos tente novamente')},10)</script>");
    
    private String verificarLoginSucesso;
    private String loginSemSucesso;
    private String retornoPaginaLoginParaPaginaIndex;
    private String cadastroEfetuado;
    private String usuarioJaCadastrado;
    private String redirecionamentoParaIndex;
    private String senhasNaoBatem;
    private String numeroDeCaracteresMenorQueDez;
    private String dadosInvalidos;

    private ModelosJs(String verificarLoginSucesso, String loginSemSucesso, String retornoPaginaLoginParaPaginaIndex, String cadastroEfetuado, String usuarioJaCadastrado, String redirecionamentoParaIndex, String senhasNaoBatem, String numeroDeCaracteresMenorQueDez, String dadosInvalidos) {
        this.verificarLoginSucesso = verificarLoginSucesso;
        this.loginSemSucesso = loginSemSucesso;
        this.retornoPaginaLoginParaPaginaIndex = retornoPaginaLoginParaPaginaIndex;
        this.cadastroEfetuado = cadastroEfetuado;
        this.usuarioJaCadastrado = usuarioJaCadastrado;
        this.redirecionamentoParaIndex = redirecionamentoParaIndex;
        this.senhasNaoBatem = senhasNaoBatem;
        this.numeroDeCaracteresMenorQueDez = numeroDeCaracteresMenorQueDez;
        this.dadosInvalidos = dadosInvalidos;
    }

    public String getVerificarLoginSucesso() {
        return verificarLoginSucesso;
    }

    public String getLoginSemSucesso() {
        return loginSemSucesso;
    }

    public String getRetornoPaginaLoginParaPaginaIndex() {
        return retornoPaginaLoginParaPaginaIndex;
    }

    public String getCadastroEfetuado() {
        return cadastroEfetuado;
    }

    public String getUsuarioJaCadastrado() {
        return usuarioJaCadastrado;
    }

    public String getRedirecionamentoParaIndex() {
        return redirecionamentoParaIndex;
    }

    public String getSenhasNaoBatem() {
        return senhasNaoBatem;
    }

    public String getNumeroDeCaracteresMenorQueDez() {
        return numeroDeCaracteresMenorQueDez;
    }

    public String getDadosInvalidos() {
        return dadosInvalidos;
    }  
}
