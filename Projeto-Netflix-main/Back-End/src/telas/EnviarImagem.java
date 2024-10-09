package telas;

import dao.Inserir;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Dados;

public class EnviarImagem extends javax.swing.JFrame {

    BufferedImage imagem;//,img;
    File caminhoVideo;
    byte[] videoBytes,audioBytes,img;
    public EnviarImagem() {
        initComponents();
    }
           
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImagem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblImagem = new javax.swing.JLabel();
        blnEnviar = new javax.swing.JButton();
        comentarios = new javax.swing.JTextField();
        titulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnImagem1 = new javax.swing.JButton();
        classificacao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoEstilizado = new javax.swing.JTextArea();
        idImagem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnImagem2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        comentario = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImagem.setText("Selecionar Imagem");
        btnImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagemActionPerformed(evt);
            }
        });

        jLabel1.setText("IMAGEM");

        blnEnviar.setText("Enviar");
        blnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blnEnviarActionPerformed(evt);
            }
        });

        jLabel2.setText("COMENTARIO");

        jLabel3.setText("TITULO");

        jLabel4.setText("TIPO FILME");

        btnImagem1.setText("Selecionar video");
        btnImagem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagem1ActionPerformed(evt);
            }
        });

        jLabel5.setText("IDADE MINIMA");

        jLabel6.setText("   VIDEO ESTILIZADO");

        campoEstilizado.setColumns(20);
        campoEstilizado.setRows(5);
        jScrollPane1.setViewportView(campoEstilizado);

        idImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idImagemActionPerformed(evt);
            }
        });

        jLabel8.setText("ID IMAGEM FOREIGN");

        btnImagem2.setText("Selecionar audio");
        btnImagem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagem2ActionPerformed(evt);
            }
        });

        jLabel9.setText("COMENTARIO AUDIO");

        comentario.setColumns(20);
        comentario.setRows(5);
        jScrollPane2.setViewportView(comentario);

        jLabel10.setText("tipos de filme:M-maiores F-filme A-anime D-desenho P-aulas | comentario: comentario sobre o filme | idade:minimo de idade que precisa ter| id imagem: com que imagem tem conexao (colocar numero) | estilizado: filme que pegou de outro site ou drive ");

        jLabel11.setText("caso a imagem não for selecionada ele não vai inserir nenhum dado na tabela dela (isso serve para fazer a foreign key de series e temporadas dos animes), caso deixe sem imagem insira a foreign key para conectar os videos");

        jLabel12.setText("VIDEO");

        jLabel13.setText("AUDIO");

        jLabel14.setText("quando for utilizar videos estilizados voce tem que ir no drive ou youtube pegar o link de incorporação e copiar apenas o que esta dentro do set ex:<iframe src=\"https://www.youtube.com/embed/z0L37D7-wfk\" COPIE SÓ src=\"....\"");

        jLabel15.setText("quando for utilizar a questão das imagens por foreign  key não coloque a imagen, a aplicação vai setala como null.");

        jLabel16.setText("Caso não consiga baixar o filme ou ele seja muito pesado utilize o video estilizado ");

        jLabel17.setText("AVISOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comentarios)
                            .addComponent(tipo)
                            .addComponent(classificacao)
                            .addComponent(titulo)
                            .addComponent(idImagem)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(lblImagem))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(blnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(156, 156, 156))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(131, 131, 131))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnImagem2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(169, 169, 169))))))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(classificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(13, 13, 13)
                        .addComponent(idImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnImagem2)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblImagem, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnImagem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnImagem1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(blnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(jLabel17)
                .addGap(8, 8, 8)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagemActionPerformed

        JFileChooser selecionarImagem = new JFileChooser();     //instanciando o selecionador
        int res = selecionarImagem.showOpenDialog(null);        //abrindo seleção
        if (res == JFileChooser.APPROVE_OPTION) {               //se opção correta
            File arquivo = selecionarImagem.getSelectedFile();  //pega arquivo
            File caminhoImagem = new File(arquivo.getAbsolutePath());   //pega o caminho do audio e armazena  
            String  caminhoDoArquivoSelecionado=caminhoImagem.toString();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
          try {
                FileInputStream fis = new FileInputStream(new File(caminhoDoArquivoSelecionado));
                byte[] buf = new byte[100000000];
                int n;
                while (-1 != (n = fis.read(buf))){
                    baos.write(buf, 0, n);
                }
                img = baos.toByteArray();
                 JOptionPane.showMessageDialog(rootPane, img);
            }catch (FileNotFoundException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "imagem não armazenado");
            }catch (IOException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "imagem não armazenado");
            }
        }
    }//GEN-LAST:event_btnImagemActionPerformed




    private void blnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blnEnviarActionPerformed
         try {
             Dados pegarDados = new Dados();
             Inserir inserirDados = new Inserir();
             
            //imagem - modo novo
             pegarDados.setImagem(img);              
             
             if(titulo.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Digite um titulo para a imagem/videos"); 
                 throw new RuntimeException("Digite um titulo para o imagem/videos");
             }else{
                pegarDados.setTitulo(titulo.getText());
             }
             
             pegarDados.setComentario(comentarios.getText());
             
             if(tipo.getText().isEmpty()){
                 JOptionPane.showMessageDialog(rootPane, "coloque o tipo para continuar");
                 throw new RuntimeException("coloque o tipo para continuar");
             }else{
                 pegarDados.setTipo(tipo.getText());
             }
             
             if(classificacao.getText().isEmpty()){
                 JOptionPane.showMessageDialog(rootPane, "coloque 0 ou maior na classificação indicativa para continuar");
                 throw new RuntimeException("coloque 0 ou maior na classificação indicativa para continuar");
             }else{
                 pegarDados.setClassificacao(Integer.parseInt(classificacao.getText()));
             }
             
             
             //video
             if(titulo.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Digite um titulo para o imagem/videos"); 
                 throw new RuntimeException("Digite um titulo para o imagem/videos");
             }else{
                pegarDados.setTituloVideo(titulo.getText());
             }
             
             pegarDados.setVideos(videoBytes);
             
             if(img==null && idImagem.getText().isEmpty()){
                  JOptionPane.showMessageDialog(rootPane, "ERRO INSIRA PELO MENOS A IMAGEM (PRA UM NOVO FILME OU SERIE)\n OU A ID IMAGEM FOREIGN KEY(PRA COLOCAR O FILME EM UMA IMAGEM QUE JA EXISTE)");
                  throw new RuntimeException("ERRO INSIRA PELO MENOS A IMAGEM (PRA UM NOVO FILME OU SERIE)\n OU A ID IMAGEM FOREIGN KEY(PRA COLOCAR O FILME EM UMA IMAGEM QUE JA EXISTE)");
             }else if(idImagem.getText().isEmpty()){
                  pegarDados.setIdImagemVideo(0);
             }else{
                 pegarDados.setIdImagemVideo(Integer.parseInt(idImagem.getText()));
             }
             
             pegarDados.setUrl(campoEstilizado.getText());
             
             //audio
             pegarDados.setcomentarioDoAudio(comentario.getText());
             pegarDados.setAudio(audioBytes);
             
             Boolean armazenado = inserirDados.inserir(pegarDados);  //armazena no banco
             if(armazenado){
                 JOptionPane.showMessageDialog(rootPane, "dados armazenados com sucesso");
                 img=null;
                 videoBytes=null;
                 campoEstilizado.setText("");
             }
             else{
                JOptionPane.showMessageDialog(rootPane, "dados não armazenados");
             }   
         } catch (HeadlessException ex) {
             Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_blnEnviarActionPerformed

    private void btnImagem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagem1ActionPerformed
        //ATENÇÃO PARA ARMAZENAR UM VIDEO MUITO GRANDE COMO DE UM GIGA É EXIGIDA MEMÓRIA NO COMPUTADOR SENÃO ACONTECERA O ERRO java.lang.OutOfMemoryError: Java heap space, ISSO PODE SER UM ERRO DA MAQUINA E NAO CODIGO // existem videos que mostram como corrigi lo eis um : https://www.youtube.com/watch?v=vmI8rdV9EOo
        JFileChooser selecionarVideo = new JFileChooser();     //instanciando o selecionador
        int res = selecionarVideo.showOpenDialog(null);        //abrindo seleção
        if (res == JFileChooser.APPROVE_OPTION) {               //se opção correta
            File arquivo = selecionarVideo.getSelectedFile();  //pega arquivo
            File caminhoImagem = new File(arquivo.getAbsolutePath());   //pega o caminho do video e armazena  
            String  caminhoDoArquivoSelecionado=caminhoImagem.toString();
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          try {
                FileInputStream fis = new FileInputStream(new File(caminhoDoArquivoSelecionado));
                byte[] buf = new byte[100000000];
                
                int n;
                while (-1 != (n = fis.read(buf))){
                    baos.write(buf, 0, n);
                }
                videoBytes = baos.toByteArray();
                JOptionPane.showMessageDialog(rootPane, videoBytes);
            }catch (FileNotFoundException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "video não armazenado");
            }catch (IOException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "video não armazenado");
            }
        }
    }//GEN-LAST:event_btnImagem1ActionPerformed

    private void idImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idImagemActionPerformed
       
    }//GEN-LAST:event_idImagemActionPerformed

    private void btnImagem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagem2ActionPerformed
        //ATENÇÃO PARA ARMAZENAR UM AUDIO MUITO GRANDE COMO DE UM GIGA É EXIGIDA MEMÓRIA NO COMPUTADOR SENÃO ACONTECERA O ERRO java.lang.OutOfMemoryError: Java heap space, ISSO PODE SER UM ERRO DA MAQUINA E NAO CODIGO
        JFileChooser selecionarAudio = new JFileChooser();     //instanciando o selecionador
        int res = selecionarAudio.showOpenDialog(null);        //abrindo seleção
        if (res == JFileChooser.APPROVE_OPTION) {               //se opção correta
            File arquivo = selecionarAudio.getSelectedFile();  //pega arquivo
            File caminhoImagem = new File(arquivo.getAbsolutePath());   //pega o caminho do audio e armazena  
            String  caminhoDoArquivoSelecionado=caminhoImagem.toString();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
          try {
                FileInputStream fis = new FileInputStream(new File(caminhoDoArquivoSelecionado));
                byte[] buf = new byte[100000000];
                
                int n;
                while (-1 != (n = fis.read(buf))){
                    baos.write(buf, 0, n);
                }
                audioBytes = baos.toByteArray();
                 JOptionPane.showMessageDialog(rootPane, audioBytes);
            }catch (FileNotFoundException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "audio não armazenado");
            }catch (IOException ex) {
                Logger.getLogger(EnviarImagem.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "audio não armazenado");
            }
        }
    }//GEN-LAST:event_btnImagem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blnEnviar;
    private javax.swing.JButton btnImagem;
    private javax.swing.JButton btnImagem1;
    private javax.swing.JButton btnImagem2;
    private javax.swing.JTextArea campoEstilizado;
    private javax.swing.JTextField classificacao;
    private javax.swing.JTextArea comentario;
    private javax.swing.JTextField comentarios;
    private javax.swing.JTextField idImagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField titulo;
    // End of variables declaration//GEN-END:variables
}
