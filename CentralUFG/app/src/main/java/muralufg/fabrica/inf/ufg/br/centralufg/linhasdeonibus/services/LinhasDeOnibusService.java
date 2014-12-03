package muralufg.fabrica.inf.ufg.br.centralufg.linhasdeonibus.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.LinhaDeOnibus;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;
import muralufg.fabrica.inf.ufg.br.centralufg.util.SimpleConnection;

public class LinhasDeOnibusService extends SimpleConnection {

    private static final String URL = "http://invisiblerails.com/rmtc/api/v1/bus/point/";
    private String param;

    public LinhasDeOnibusService(ServiceCompliant handler, String param) {
        super(handler, URL + param);
        this.param = param;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return super.doInBackground();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        switch (getHttpStatus()) {
            case OK:
                try {
                    JSONObject jsonResponse = new JSONObject(getResponse());
                    if (jsonResponse.has("error") && jsonResponse.getBoolean("error")) {
                        if (jsonResponse.has("exist") && !jsonResponse.getBoolean("exist")) {
                            handler.handleError("Ponto " + param + " possívelmente não existe.");
                        }
                    } else {
                        if (jsonResponse.isNull("bus-lines")) {
                            handler.handleError("Não há linhas disponíveis no momento para o ponto " + param + ", tente novamente mais tarde.");
                        } else {
                            JSONArray linhasDeOnibusJson = jsonResponse.getJSONArray("bus-lines");
                            ArrayList<LinhaDeOnibus> linhasDeOnibus = LinhaDeOnibus.fromJson(linhasDeOnibusJson);
                            handler.readObject(linhasDeOnibus);
                        }
                    }
                } catch (JSONException e) {
                    handler.handleError("Ocorreu um erro com " + getResponse() + ": " + e.getLocalizedMessage());
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
