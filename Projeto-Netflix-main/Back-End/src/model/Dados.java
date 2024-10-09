package model;

public class Dados {
    
    private int id,classificacao,idImagemVideo;
    private byte[] imagem;  //define o numero de bytes como vazio para que o proprio sistema insira o tamanho
    private byte[] videos;  //define o numero de bytes como vazio para que o proprio sistema insira o tamanho
    private byte[] audio;  //define o numero de bytes como vazio para que o proprio sistema insira o tamanho
    private String comentario,titulo,tipo,url,comentarioDoAudio,tituloVideo;

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
    
    
    public String getcomentarioDoAudio() {
        return comentarioDoAudio;
    }

    public void setcomentarioDoAudio(String comentarioDoAudio) {
        this.comentarioDoAudio = comentarioDoAudio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdImagemVideo() {
        return idImagemVideo;
    }

    public void setIdImagemVideo(int idImagemVideo) {
        this.idImagemVideo = idImagemVideo;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
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

    public String getComentarioDoAudio() {
        return comentarioDoAudio;
    }

    public void setComentarioDoAudio(String comentarioDoAudio) {
        this.comentarioDoAudio = comentarioDoAudio;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public void setTituloVideo(String tituloVideo) {
        this.tituloVideo = tituloVideo;
    }
    
    
    
}
