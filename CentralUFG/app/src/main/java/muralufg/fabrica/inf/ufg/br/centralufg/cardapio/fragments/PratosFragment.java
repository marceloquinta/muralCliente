package muralufg.fabrica.inf.ufg.br.centralufg.cardapio.fragments;

/**
 * Created by AIRES on 04/12/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.cardapio.adapter.AdapterPratos;
import muralufg.fabrica.inf.ufg.br.centralufg.main.MainActivity;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Prato;


public class PratosFragment extends Fragment {


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
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prato pratoSelecionado = (Prato) mAdapter.getItem(position);
                DescricaoPratoFragment fragment = (DescricaoPratoFragment) DescricaoPratoFragment.newInstance();
                Integer fragmentId = (Integer) view.getTag();
                fragment.setPrato(pratoSelecionado);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(fragmentId, fragment);
                fragmentTransaction.commit();

            }


        });
        Log.v("-->>> PratosFragment", "" + mListView.getAdapter().getCount());
    }

    @Override
    public void onDetach() {
        super.onDetach();
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


    public FragmentManager getSupportFragmentManager() {
        return new FragmentManager() {
            @Override
            public FragmentTransaction beginTransaction() {
                return null;
            }

            @Override
            public boolean executePendingTransactions() {
                return false;
            }

            @Override
            public Fragment findFragmentById(int i) {
                return null;
            }

            @Override
            public Fragment findFragmentByTag(String s) {
                return null;
            }

            @Override
            public void popBackStack() {

            }

            @Override
            public boolean popBackStackImmediate() {
                return false;
            }

            @Override
            public void popBackStack(String s, int i) {

            }

            @Override
            public boolean popBackStackImmediate(String s, int i) {
                return false;
            }

            @Override
            public void popBackStack(int i, int i2) {

            }

            @Override
            public boolean popBackStackImmediate(int i, int i2) {
                return false;
            }

            @Override
            public int getBackStackEntryCount() {
                return 0;
            }

            @Override
            public BackStackEntry getBackStackEntryAt(int i) {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(OnBackStackChangedListener onBackStackChangedListener) {

            }

            @Override
            public void removeOnBackStackChangedListener(OnBackStackChangedListener onBackStackChangedListener) {

            }

            @Override
            public void putFragment(Bundle bundle, String s, Fragment fragment) {

            }

            @Override
            public Fragment getFragment(Bundle bundle, String s) {
                return null;
            }

            @Override
            public List<Fragment> getFragments() {
                return null;
            }

            @Override
            public SavedState saveFragmentInstanceState(Fragment fragment) {
                return null;
            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public void dump(String s, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strings) {

            }
        };
    }
}