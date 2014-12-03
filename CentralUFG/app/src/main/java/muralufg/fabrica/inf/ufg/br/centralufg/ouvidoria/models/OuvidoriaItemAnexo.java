package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models;

/**
 * Classe que representa o arquivo de mídia para ser enviado à ouvidoria
 */
public class OuvidoriaItemAnexo {

    private String nome;
    private String diretorio;
    private Long tamanho;

    public OuvidoriaItemAnexo(String diretorio, String nome, Long tamanho) {
        this.nome = nome;
        this.diretorio = diretorio;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("nome: ").append(getNome());
        builder.append(" tamanho: ").append(getTamanho());
        builder.append(" diretorio: ").append(getDiretorio());
        return builder.toString();
    }
}