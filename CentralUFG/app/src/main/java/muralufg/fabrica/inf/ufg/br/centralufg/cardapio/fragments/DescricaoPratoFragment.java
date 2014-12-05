package muralufg.fabrica.inf.ufg.br.centralufg.cardapio.fragments;

/**
 * Created by AIRES on 04/12/2014.
 */
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.FraseDoDia;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Prato;
import muralufg.fabrica.inf.ufg.br.centralufg.util.ServiceCompliant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DescricaoPratoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DescricaoPratoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescricaoPratoFragment extends Fragment{


    private Prato prato;
    private RatingBar pontuacaoPrato;
    public static DescricaoPratoFragment fragment;
    private OnFragmentInteractionListener mListener;

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public static Fragment newInstance() {
        if(fragment == null){
            fragment = new DescricaoPratoFragment();
        }
        return fragment;
    }

    public DescricaoPratoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descricao_prato, container, false);
        pontuacaoPrato = (RatingBar) view.findViewById(R.id.pontuacaoPrato);
        pontuacaoPrato.setDrawingCacheBackgroundColor(Color.YELLOW);
        pontuacaoPrato.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(DescricaoPratoFragment.this.getActivity(), "" + v, Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtDescrixao = (TextView) getView().findViewById(R.id.txt_nome_prato);
        txtDescrixao.setText(prato.getDescricaoPrato());

        TextView txtPreco = (TextView) getView().findViewById(R.id.txt_preco_prato);
        txtPreco.setText(String.format("$1.2",prato.getPreco()));

        LinearLayout imagemPrato = (LinearLayout) getView().findViewById(R.id.imagem_prato);
        imagemPrato.setBackgroundResource(prato.getImage());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
