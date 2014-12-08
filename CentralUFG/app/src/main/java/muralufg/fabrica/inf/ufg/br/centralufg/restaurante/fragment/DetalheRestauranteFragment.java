/*
 * ====================================================================
 * Licença da Fábrica de Software (UFG)
 *
 * Copyright (c) 2014 Fábrica de Software
 * Instituto de Informática (Universidade Federal de Goiás)
 * Todos os direitos reservados.
 *
 * Redistribuição e uso, seja dos fontes ou do formato binário
 * correspondente, com ou sem modificação, são permitidos desde que
 * as seguintes condições sejam atendidas:
 *
 * 1. Redistribuição do código fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condições
 *    e a observação sobre garantia abaixo.
 *
 * 2. Redistribuição no formato binário deve reproduzir o conteúdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condições e a observação abaixo na documentação
 *    e/ou materiais fornecidos com a distribuição.
 *
 * 3. A documentação fornecida com a redistribuição,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela Fábrica
 *       de Software do Instituto de Informática (UFG)."
 *
 * 4. Os nomes Fábrica de Software, Instituto de Informática e UFG
 *    não podem ser empregados para endoçar ou promover produtos
 *    derivados do presente software sem a explícita permissão
 *    por escrito.
 *
 * 5. Produtos derivados deste software não podem ser chamados
 *    "Fábrica de Software", "Instituto de Informática", "UFG",
 *    "Universidade Federal de Goiás" ou contê-los em seus nomes,
 *    sem permissão por escrito.
 *
 * ESTE SOFTWARE É FORNECIDO "COMO ESTÁ". NENHUMA GARANTIA É FORNECIDA,
 * EXPLÍCITA OU NÃO. NÃO SE AFIRMA QUE O PRESENTE SOFTWARE
 * É ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÁRIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃO,
 * ESTA AVALIAÇÃO E A CONSEQUÊNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÍDO AO INTERESSADO
 * E/OU USUÁRIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software é resultado do trabalho de voluntários, estudantes e
 * professores, ao realizar atividades no âmbito da Fábrica de Software
 * do Instituto de Informática (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */
package muralufg.fabrica.inf.ufg.br.centralufg.restaurante.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Restaurante;
import muralufg.fabrica.inf.ufg.br.centralufg.util.AppController;

/**
 * Created by Fernando Lopes
 */
public class DetalheRestauranteFragment extends Fragment {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;
    private View view;

    private class ViewHolder {
        TextView textViewNomeRestaurante;
        TextView textViewEnderecoRestaurante;
        TextView textViewTelefoneRestaurante;
        NetworkImageView netImageViewRestaurante;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        final Restaurante restaurante = bundle.getParcelable("restaurante");

        view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        if (imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textViewNomeRestaurante = (TextView) view.findViewById(R.id.textViewNomeRestaurante);
        viewHolder.textViewTelefoneRestaurante = (TextView) view.findViewById(R.id.textViewTelefoneRestaurante);
        viewHolder.textViewEnderecoRestaurante = (TextView) view.findViewById(R.id.textViewEnderecoRestaurante);
        viewHolder.netImageViewRestaurante = (NetworkImageView) view.findViewById(R.id.imagemRestaurante);

        viewHolder.textViewNomeRestaurante.setText(restaurante.getNomeRestaurante());
        viewHolder.textViewTelefoneRestaurante.setText(restaurante.getTelefoneRestaurante());
        viewHolder.textViewEnderecoRestaurante.setText(restaurante.getEnderecoRestaurante());
        viewHolder.netImageViewRestaurante.setImageUrl(restaurante.getImagemRestaurante(), imageLoader);

        Button botaoDiscarRestaurante = (Button) view.findViewById(R.id.botaoDiscarRestaurante);
        botaoDiscarRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefoneRestaurante = restaurante.getTelefoneRestaurante();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telefoneRestaurante));
                startActivity(intent);
            }
        });

        addListenerOnRatingBar();
        addListenerOnButton();


        return view;
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBarRestaurante);
        txtRatingValue = (TextView) view.findViewById(R.id.textViewValorRating);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBarRestaurante);
        btnSubmit = (Button) view.findViewById(R.id.botaoSubmeterRating);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

}
