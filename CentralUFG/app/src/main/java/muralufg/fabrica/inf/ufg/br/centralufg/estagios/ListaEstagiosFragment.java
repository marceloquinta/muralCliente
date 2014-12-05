package muralufg.fabrica.inf.ufg.br.centralufg.estagios;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;

public class ListaEstagiosFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    ListView listView;
    List<Estagio> listaDeEstagios;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Estagio estagio1= new Estagio("Nome 1","Descrição 1","Local 1","Link 1");
        Estagio estagio2= new Estagio("Nome 2","Descrição 2","Local 2","Link 2");
        Estagio estagio3= new Estagio("Nome 3","Descrição 3","Local 3","Link 3");

        listaDeEstagios= new ArrayList<Estagio>();
        listaDeEstagios.add(estagio1);
        listaDeEstagios.add(estagio2);
        listaDeEstagios.add(estagio3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_listaestagios, container, false);

        listView= (ListView) view.findViewById(R.id.listView);


        ArrayAdapter<Estagio> arrayAdapter;
        arrayAdapter = new ArrayAdapter<Estagio>( getActivity(), android.R.layout.simple_list_item_1, listaDeEstagios);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int localTocado, long l) {
        Estagio estagio= listaDeEstagios.get(localTocado);

        Intent intent = new Intent(getActivity().getApplicationContext(), EstagioActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable(EstagioActivity.ESTAGIO, estagio);
        intent.putExtras(extras);

        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}