package muralufg.fabrica.inf.ufg.br.centralufg.cardapio.fragments;

/**
 * Created by AIRES on 04/12/2014.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.cardapio.adapter.AdapterPratos;
import muralufg.fabrica.inf.ufg.br.centralufg.main.MainActivity;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Prato;


public class PratosFragment extends Fragment implements AbsListView.OnItemClickListener {


    private ListView mListView;
    private ListAdapter mAdapter;

    public static PratosFragment newInstance() {
        PratosFragment fragment = new PratosFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pratos_list, container, false);

        // Set the adapter
        mListView = (ListView) view.findViewById(R.id.lista_de_pratos);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Prato> pratos = new ArrayList<Prato>();
        pratos.add(new Prato("Pamonha Diet", "Zero açucar", 12.0, R.drawable.pamonha, 1));
        pratos.add(new Prato("Feijao", "Carioquinha", 12.0, R.drawable.feijao, 2));
        pratos.add(new Prato("Arroz", "Branco", 12.0, R.drawable.arroz, 3));


        mAdapter = new AdapterPratos(getActivity(), pratos);
        mListView.setAdapter(mAdapter);
        Log.v("-->>> PratosFragment", "" + mListView.getAdapter().getCount());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Prato pratoSelecionado =  (Prato) parent.getItemAtPosition(position);
        DescricaoPratoFragment.newInstance().setPrato(pratoSelecionado);

    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


}