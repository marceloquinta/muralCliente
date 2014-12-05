package muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments.inflaters;

import muralufg.fabrica.inf.ufg.br.centralufg.model.Compromisso;

public class CardItemData {
    private String labelNome;
    private String labelData;
    private Compromisso compromisso;

    public CardItemData(Compromisso compromisso) {
        this.compromisso = compromisso;
        labelNome = compromisso.getNome();
        labelData = compromisso.getStringData();
    }

    public String getLabelNome() {
        return labelNome;
    }

    public String getLabelData() {
        return labelData;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setLabelNome(String labelNome) {
        this.labelNome = labelNome;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }
}
