package muralufg.fabrica.inf.ufg.br.centralufg.locais.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.locais.adapters.LocaisListAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.locais.services.LocaisService;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Local;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

public class LocaisFragment extends ListFragment implements ServiceCompliant {

    private List<Local> listaDeLocais;
    private LocaisListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaisService service = new LocaisService(this);
        service.execute();
    }

    @Override
    public void readObject(Object object) {
        listaDeLocais = new ArrayList<Local>();

        if (object != null && object instanceof List) {
            listaDeLocais = (List<Local>) object;
        }

        listAdapter = new LocaisListAdapter(getActivity(), listaDeLocais);
        setListAdapter(listAdapter);
    }

    @Override
    public Activity getContextActivity() {
        return this.getActivity();
    }

    @Override
    public void handleError(String error) {
        Crouton.makeText(this.getActivity(), error, Style.ALERT).show();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle bundle = new Bundle();
        bundle.putParcelable("local", listaDeLocais.get(position));

        DetalheLocalFragment detalheLocalFragment = new DetalheLocalFragment();
        detalheLocalFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.replace(R.id.drawer_layout, detalheLocalFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
