package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.services;

import com.github.kevinsawicki.http.HttpRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models.Ouvidoria;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models.OuvidoriaItemAnexo;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

/**
 * Classe de serviço responsável por enviar mensagem para a Ouvidoria
 */
public class OuvidoriaService extends OuvidoriaConnection {

    /**
     * URL de acesso para envio das mensagem à ouvidoria
     */
    private static final String URL = "http://private-385b-centralufgouvidoria.apiary-mock.com/ouvidoria";

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private Ouvidoria ouvidoria;

    public OuvidoriaService(ServiceCompliant handler, Ouvidoria ouvidoria) {
        super(handler, URL);
        this.ouvidoria = ouvidoria;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            HttpRequest request = HttpRequest.post(getUrl());
            // Adicionar os anexos
            for (OuvidoriaItemAnexo itemAnexo : ouvidoria.getItensAnexo()) {
                request.part("item[id]", new File(itemAnexo.getDiretorio()));
            }
            // Reposta da requisição
            setHttpStatus(request.code());
            setResponse(request.body());
        } catch (Exception e) {
            LOGGER.info("Erro ao realizar requisicao: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        switch (getHttpStatus()) {
            case OK:
                trataHttpStatusOk();
                break;
            case ERROR:
                handler.handleError("Não foi possível enviar mensagem à Ouvidoria");
                break;
            default:
                handler.handleError(handler.getContextActivity().getResources().getString(
                        R.string.alerta_server_error));

        }
    }

    /**
     * Trata a reposta Ok do servidor
     */
    private void trataHttpStatusOk() {
        try {
            JSONObject object = new JSONObject(getResponse());
            String mensagem = object.getString("mensagem");

            handler.readObject(mensagem);
        } catch (JSONException e) {
            handler.handleError("Ocorreu um erro com " + getResponse() + ": " + e.getLocalizedMessage());
            LOGGER.info("Erro no formato do JSON: " + e.getMessage(), e);
        }
    }

}
