package muralufg.fabrica.inf.ufg.br.centralufg.estagios;

import java.io.Serializable;
/**
 * Created by Felipe on 04/12/2014.
 */
public class Estagio implements Serializable {

    private String nome;
    private String descricao;
    private String local;
    private String link;

    public Estagio(String nome, String descricao, String local, String link){
        this.nome= nome;
        this.descricao= descricao;
        this.local= local;
        this.link= link;
    }

    @Override
    public String toString(){
        return nome+" - "+local;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


}
