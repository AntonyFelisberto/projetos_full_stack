package METODOS;

public enum Sql {
    SELECT( "SELECT cpf FROM Usuario WHERE CPF='%s'",
            "SELECT nome,senha FROM Usuario ",
            "SELECT ano FROM Usuario WHERE nome='%s' and senha='%s'",
            "SELECT * FROM imagens WHERE tipo='%s' AND imagem IS NOT NULL",
            "SELECT * FROM imagens WHERE imagem IS NOT NULL",
            "SELECT * FROM imagens WHERE idImagem=%d AND imagem IS NOT NULL",
            "SELECT idImagem,titulo,classificacaoIndicativa FROM imagens WHERE imagem IS NOT NULL",
            "SELECT idImagem,titulo,classificacaoIndicativa FROM imagens WHERE tipo='%s' AND imagem IS NOT NULL",
            "SELECT idImagem,classificacaoIndicativa,titulo FROM imagens WHERE titulo LIKE '%s%s%s' AND imagem IS NOT NULL OR (titulo LIKE '%s%s%s' AND imagem IS NOT NULL)",
            "SELECT classificacaoIndicativa FROM imagens WHERE idImagem=%d AND imagem IS NOT NULL"
           );
    
    private final String codigo;
    private final String codigoSecundario;
    private final String codigoTerciario;
    private final String codigoQuarternario;
    private final String codigoQuintoplo;
    private final String codigoSextoplo;      
    private final String codigoSetimo; 
    private final String codigoOitavo; 
    private final String codigoNono; 
    private final String codigoDecimo; 
    
    private Sql(String codigo, String codigoSecundario, String codigoTerciario, String codigoQuarternario, String codigoQuintoplo, String codigoSextoplo,String codigoSetimo,String codigoOitavo,String codigoNono,String codigoDecimo) {
        this.codigo = codigo;
        this.codigoSecundario = codigoSecundario;
        this.codigoTerciario = codigoTerciario;
        this.codigoQuarternario = codigoQuarternario;
        this.codigoQuintoplo = codigoQuintoplo;
        this.codigoSextoplo = codigoSextoplo;
        this.codigoSetimo=codigoSetimo;
        this.codigoOitavo=codigoOitavo;
        this.codigoNono=codigoNono;
        this.codigoDecimo=codigoDecimo;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public String getCodigoSecundario() {
        return codigoSecundario;
    }
    
    public String getCodigoTerciario() {
        return codigoTerciario;
    }

    public String getCodigoQuarternario() {
        return codigoQuarternario;
    }

    public String getCodigoQuintoplo() {
        return codigoQuintoplo;
    }

    public String getCodigoSextoplo() {
        return codigoSextoplo;
    }
    
    public String getCodigoSetimo() {
        return codigoSetimo;
    }

    public String getCodigoOitavo() {
        return codigoOitavo;
    }

    public String getCodigoNono() {
        return codigoNono;
    }

    public String getCodigoDecimo() {
        return codigoDecimo;
    }
    
    
    
}
