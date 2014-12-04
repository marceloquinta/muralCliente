package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Ouvidoria {

    private String titulo;

    private String data;

    private String descricao;

    private List<OuvidoriaItemAnexo> itensAnexo;

    /**
     * @param titulo
     * @param data
     * @param descricao
     */
    public Ouvidoria(String titulo, String data, String descricao) {
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.itensAnexo = new ArrayList<OuvidoriaItemAnexo>();
    }

    /**
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return
     */
    public List<OuvidoriaItemAnexo> getItensAnexo() {
        return itensAnexo;
    }

    /**
     * @param itensAnexo
     */
    public void setItensAnexo(List<OuvidoriaItemAnexo> itensAnexo) {
        this.itensAnexo = itensAnexo;
    }

    /**
     * @param itemAnexo
     */
    public void addItemAnexo(OuvidoriaItemAnexo itemAnexo) {
        this.itensAnexo.add(itemAnexo);
    }

    /**
     * @param itensAnexo
     */
    public void addAllItensAnexo(List<OuvidoriaItemAnexo> itensAnexo) {
        this.itensAnexo.addAll(itensAnexo);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("titulo: ").append(getTitulo());
        builder.append("\n");
        builder.append("data: ").append(getData());
        builder.append("\n");
        builder.append("descricao: ").append(getDescricao());
        builder.append("\n");
        builder.append("total de anexos: ").append(getItensAnexo().size());

        return builder.toString();
    }
}
