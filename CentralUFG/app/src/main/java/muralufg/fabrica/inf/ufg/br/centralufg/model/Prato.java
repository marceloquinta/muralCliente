package muralufg.fabrica.inf.ufg.br.centralufg.model;

/**
 * Created by AIRES on 04/12/2014.
 */
public class Prato {
    private long id;
    private String nome;
    private double preco;
    private String descricaoPrato;
    private int image;


    public Prato(String nome, String descricaoPrato, double preco, int image, long id) {
        this.nome = nome;
        this.preco = preco;
        this.descricaoPrato = descricaoPrato;
        this.image = image;
        this.id = id;
    }

    public String getDescricaoPrato() {
        return descricaoPrato;
    }

    public void setDescricaoPrato(String descricaoPrato) {
        this.descricaoPrato = descricaoPrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
