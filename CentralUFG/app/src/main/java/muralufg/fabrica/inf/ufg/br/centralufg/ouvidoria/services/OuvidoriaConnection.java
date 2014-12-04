package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.services;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

/**
 * Classe responsável de definir a lógica de comunicação com a Ouvidoria.
 */
public class OuvidoriaConnection extends AsyncTask<Void, Void, Void> {

    /**
     * Sucesso na solicitação HTTP
     */
    protected static final int OK = 200;
    /**
     * Erro na solicitação HTTP
     */
    protected static final int ERROR = 400;

    private int httpStatus;
    private String response;
    protected ServiceCompliant handler;
    private ProgressDialog dialog;
    private String url;

    public OuvidoriaConnection(ServiceCompliant handler, String url) {
        this.handler = handler;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(handler.getContextActivity());
        dialog.setMessage(handler.getContextActivity().getResources().getString(
                R.string.label_aguarde));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        dialog.dismiss();
    }

    /**
     * Código de status HTTP
     *
     * @return
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * Setar o status HTTP
     *
     * @param status
     */
    public void setHttpStatus(int status) {
        this.httpStatus = status;
    }

    /**
     * Obter a reposta da requisição
     *
     * @return
     */
    public String getResponse() {
        return response;
    }

    /**
     * Setar a reposta da requisição
     *
     * @param response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Obter a url de conexão
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setar a url de conexão
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
