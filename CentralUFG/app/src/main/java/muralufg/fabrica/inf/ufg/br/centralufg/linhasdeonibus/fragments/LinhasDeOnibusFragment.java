package muralufg.fabrica.inf.ufg.br.centralufg.linhasdeonibus.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.linhasdeonibus.adapters.LinhasDeOnibusAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.linhasdeonibus.services.LinhasDeOnibusService;
import muralufg.fabrica.inf.ufg.br.centralufg.model.LinhaDeOnibus;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

public class LinhasDeOnibusFragment extends Fragment implements ServiceCompliant {

    private Button buttonPesquisar;
    private EditText editTextNumeroPonto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linhasdeonibus, container, false);
        buttonPesquisar = (Button) view.findViewById(R.id.buttonPesquisar);
        editTextNumeroPonto = (EditText) view.findViewById(R.id.editTextNumeroPonto);
        editTextNumeroPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumeroPonto.setText("");
            }
        });
        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroPonto = editTextNumeroPonto.getText().toString();
                if (numeroPonto == null || ("").equals(numeroPonto)) {
                    createInfo("Por favor, insira o número do ponto desejado.");
                } else {
                    LinhasDeOnibusService service = new LinhasDeOnibusService(LinhasDeOnibusFragment.this, numeroPonto);
                    service.execute();
                    InputMethodManager imm = (InputMethodManager) getContextActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextNumeroPonto.getWindowToken(), 0);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleError(String error) {
        Crouton.makeText(this.getActivity(), error, Style.ALERT).show();
    }

    private void createInfo(String info) {
        Crouton.makeText(this.getActivity(), info, Style.INFO).show();
    }

    @Override
    public void readObject(Object object) {
        List<LinhaDeOnibus> linhasDeOnibus = (ArrayList<LinhaDeOnibus>) object;
        LinhasDeOnibusAdapter linhaDeOnibusAdapter = new LinhasDeOnibusAdapter(getContextActivity(), linhasDeOnibus);
        ListView listView = (ListView) getView().findViewById(R.id.listViewLinhasDeOnibus);
        listView.setAdapter(linhaDeOnibusAdapter);
    }

    public Activity getContextActivity() {
        return this.getActivity();
    }

}
