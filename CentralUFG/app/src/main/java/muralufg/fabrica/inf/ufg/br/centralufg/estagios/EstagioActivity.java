package muralufg.fabrica.inf.ufg.br.centralufg.estagios;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import muralufg.fabrica.inf.ufg.br.centralufg.R;

public class EstagioActivity extends Activity {

    public static final String ESTAGIO = "chaveEstagio";

    TextView NomeEstagio;
    TextView DescricaoEstagio;
    TextView LocalEstagio;
    TextView LinkEstagio;

    Estagio estagio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estagio);
        criaView();

        estagio = (Estagio) getIntent().getSerializableExtra(ESTAGIO);
        NomeEstagio.setText(estagio.getNome());
        DescricaoEstagio.setText(estagio.getDescricao());
        LocalEstagio.setText(estagio.getLocal());
        LinkEstagio.setText(estagio.getLink());
    }

    private void criaView(){
        NomeEstagio = (TextView) findViewById(R.id.textView_nomeEstagio);
        DescricaoEstagio = (TextView) findViewById(R.id.textView_descricaoEstagio);
        LocalEstagio = (TextView) findViewById(R.id.textView_localEstagio);
        LinkEstagio = (TextView) findViewById(R.id.textView_linkEstagio);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.estagio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
