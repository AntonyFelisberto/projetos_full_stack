package MODEL;

public class Arquivos {
    private int id,classificacao;
    private byte[] imagem; 
    private String comentario,titulo,tipo;
    private byte[] videos;  

    
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
    
    public int getClassificacao() {
        return classificacao;
    }

    public byte[] getVideos() {
        return videos;
    }

    public void setVideos(byte[] videos) {
        this.videos = videos;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
