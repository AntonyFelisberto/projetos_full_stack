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
public enum Inserts {
    INSERT("INSERT INTO Usuario(nome,senha,cpf,email,telefone,ano) values(?,?,?,?,?,?)");
    
    private String inserirUsuario;

    private Inserts(String inserirUsuario) {
        this.inserirUsuario = inserirUsuario;
    }

    public String getInserirUsuario() {
        return inserirUsuario;
    }
    
    
}
