package muralufg.fabrica.inf.ufg.br.centralufg.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Created by Laerte on 27/11/2014.
 */
public class LinhaDeOnibus {

    private static final Logger LOGGER = Logger.getLogger(LinhaDeOnibus.class.getName());

    private String nome;

    private int numero;
    private int proximo;

    public LinhaDeOnibus(JSONObject object) {
        try {
            this.nome = object.getString("name");
            this.numero = object.getInt("number");
            this.proximo = object.getInt("next");
        } catch (JSONException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProximo() {
        return proximo;
    }

    public void setProximo(int proximo) {
        this.proximo = proximo;
    }

    public static List<LinhaDeOnibus> fromJson(JSONArray jsonObjects) {
        List<LinhaDeOnibus> linhasDeOnibus = new ArrayList<LinhaDeOnibus>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                linhasDeOnibus.add(new LinhaDeOnibus(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return linhasDeOnibus;
    }
}
