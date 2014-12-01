package muralufg.fabrica.inf.ufg.br.centralufg.locais.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Local;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;
import muralufg.fabrica.inf.ufg.br.centralufg.util.SimpleConnection;

public class LocaisService extends SimpleConnection {

    private static final String URL = "http://private-82161-muralcliente1.apiary-mock.com/locais";

    public LocaisService(ServiceCompliant handler) {
        super(handler,URL);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return super.doInBackground();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        switch (getHttpStatus()){
            case OK:
                try {
                    JSONArray list = new JSONArray(getResponse());

                    List<Local> listaDeLocais = new ArrayList<Local>();

                    for (int i = 0; i < list.length(); i++) {
                        Local local = new Local();
                        JSONObject obj = (JSONObject) list.get(i);

                        local.setId(obj.getInt("id"));
                        local.setNome(obj.getString("nome"));
                        local.setEndereco(obj.getString("endereco"));
                        local.setTelefone(obj.getString("telefone"));
                        local.setImagem(obj.getString("imagem"));
                        local.setLocalizacaoGeografica(obj.getString("localizacaoGeografica"));
                        listaDeLocais.add(local);
                    }

                    handler.readObject(listaDeLocais);
                } catch (JSONException e) {
                    handler.handleError("Ocorreu um erro com "+ getResponse() + ": " + e.getLocalizedMessage());
                }
                break;
            case ERROR:
                handler.handleError("Ocorreu um erro");
                break;

            default:
                handler.handleError(handler.getContextActivity().getResources().getString(
                        R.string.alerta_server_error));
                break;
        }

    }
}
