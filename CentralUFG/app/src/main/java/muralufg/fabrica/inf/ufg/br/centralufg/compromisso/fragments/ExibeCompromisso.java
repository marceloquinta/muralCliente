package muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Compromisso;

public class ExibeCompromisso extends Activity {

    private Compromisso compromisso = new Compromisso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_compromisso);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        TextView textViewData = (TextView) findViewById(R.id.textViewData);
        textViewData.setText(data);

        String nome = intent.getStringExtra("nome");
        TextView textViewNome = (TextView) findViewById(R.id.textViewNome);
        textViewNome.setText(nome);
        textViewNome.setMovementMethod(new ScrollingMovementMethod());

        String descricao = intent.getStringExtra("descricao");
        TextView textViewDescricao = (TextView) findViewById(R.id.textViewDescricao);
        textViewDescricao.setText(descricao);
        textViewDescricao.setMovementMethod(new ScrollingMovementMethod());

        compromisso.setStringData(data);
        compromisso.setNome(nome);
        compromisso.setDescricao(descricao);
    }

    public void salvarCompromissoNaAgenda(View view) {
        Calendar beginTime = compromisso.getData();
        Calendar endTime = compromisso.getData();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.Events.TITLE, compromisso.getNome())
                .putExtra(CalendarContract.Events.DESCRIPTION, compromisso.getDescricao())
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        startActivity(intent);
        Log.i("view", view.toString());
    }
}
