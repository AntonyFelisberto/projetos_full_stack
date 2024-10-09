package UTIL;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

    //Novo método para exibir imagem na tela
    //Recebe o label que queremos exibir E a imagem como array de bytes do banco
public class ExibirImagemEmTela {
    public static void exibiImagemLabel(byte[] minhaimagem, javax.swing.JLabel label){
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO
        if(minhaimagem!=null){
            InputStream input = new ByteArrayInputStream(minhaimagem);
            try {
                BufferedImage imagem = ImageIO.read(input);
                label.setIcon(new ImageIcon(imagem));
            } catch (IOException ex) {
            }
        }else{
            label.setIcon(null);
        }
    }
}
