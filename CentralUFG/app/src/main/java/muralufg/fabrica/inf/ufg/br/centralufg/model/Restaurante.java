package muralufg.fabrica.inf.ufg.br.centralufg.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fernando Lopes
 */
public class Restaurante implements Parcelable {

    private int id;
    private String nomeRestaurante;
    private String telefoneRestaurante;
    private String enderecoRestaurante;
    private String localizacaoGeograficaRestaurante;
    private String imagemRestaurante;

    public Restaurante(){}

    public Restaurante (Parcel parcel){
        this.nomeRestaurante = parcel.readString();
        this.telefoneRestaurante = parcel.readString();
        this.enderecoRestaurante = parcel.readString();
        this.localizacaoGeograficaRestaurante = parcel.readString();
        this.imagemRestaurante = parcel.readString();
    }

    public Restaurante(int id, String nomeRestaurante, String imagemRestaurante,
                       String enderecoRestaurante, String telefoneRestaurante,
                       String localizacaoGeograficaRestaurante){
        this.id = id;
        this.nomeRestaurante = nomeRestaurante;
        this.imagemRestaurante = imagemRestaurante;
        this.enderecoRestaurante = enderecoRestaurante;
        this.telefoneRestaurante = telefoneRestaurante;
        this.localizacaoGeograficaRestaurante = localizacaoGeograficaRestaurante;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getTelefoneRestaurante() {
        return telefoneRestaurante;
    }

    public void setTelefoneRestaurante(String telefoneRestaurante) {
        this.telefoneRestaurante = telefoneRestaurante;
    }

    public String getEnderecoRestaurante() {
        return enderecoRestaurante;
    }

    public void setEnderecoRestaurante(String enderecoRestaurante) {
        this.enderecoRestaurante = enderecoRestaurante;
    }

    public String getLocalizacaoGeograficaRestaurante() {
        return localizacaoGeograficaRestaurante;
    }

    public void setLocalizacaoGeograficaRestaurante(String localizacaoGeograficaRestaurante) {
        this.localizacaoGeograficaRestaurante = localizacaoGeograficaRestaurante;
    }

    public String getImagemRestaurante() {
        return imagemRestaurante;
    }

    public void setImagemRestaurante(String imagemRestaurante) {
        this.imagemRestaurante = imagemRestaurante;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nomeRestaurante);
        parcel.writeString(telefoneRestaurante);
        parcel.writeString(enderecoRestaurante);
        parcel.writeString(localizacaoGeograficaRestaurante);
        parcel.writeValue(imagemRestaurante);
    }

    public static final Parcelable.Creator<Restaurante> CREATOR = new Parcelable.Creator<Restaurante>(){
        @Override
        public Restaurante createFromParcel(Parcel parcel) {
            return new Restaurante(parcel);
        }

        @Override
        public Restaurante[] newArray(int i) {
            return new Restaurante[i];
        }
    };
}

