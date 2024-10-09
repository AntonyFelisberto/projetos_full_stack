
package telas;

import java.util.Scanner;

public class Principal {
    public static void main (String[] args){
        int escolha;
        Scanner ler=new Scanner(System.in);
        System.out.println("Digite qual tela deseja abrir \n1-Inserir imagem\n2-ver imagem\n");
        escolha=ler.nextInt();
        switch(escolha){
            case 1:
                new EnviarImagem().setVisible(true);
            break;
            case 2:
                new ExibirImagem().setVisible(true);
            break;
            default:
                System.out.println("Sem opções");
            break;
        }
    }
}
