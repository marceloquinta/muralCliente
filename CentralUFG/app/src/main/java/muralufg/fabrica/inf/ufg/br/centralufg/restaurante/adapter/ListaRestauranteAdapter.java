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
package muralufg.fabrica.inf.ufg.br.centralufg.restaurante.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Restaurante;
import muralufg.fabrica.inf.ufg.br.centralufg.util.AppController;

/**
 * Created by Fernando Lopes
 */
public class ListaRestauranteAdapter extends ArrayAdapter<Restaurante> {
    private Context context;
    private List<Restaurante> listaDeRestaurantes;

    @Override
    public Restaurante getItem(int position) {
        return super.getItem(position);
    }

    public ListaRestauranteAdapter(Context context, List<Restaurante> listaDeRestaurantes) {
        super(context, android.R.layout.simple_list_item_1, listaDeRestaurantes);
        this.context = context;
        this.listaDeRestaurantes = listaDeRestaurantes;
    }

    @Override
    public int getCount() {
        return listaDeRestaurantes.size();
    }

    private class ViewHolder {
        TextView textViewNomeRestaurante;
        TextView textViewTelefoneRestaurante;
        NetworkImageView netImageViewImagemRestaurante;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Restaurante restaurante = (Restaurante) getItem(position);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder = null;
        View view = null;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.row_restaurante_lista, null);

            holder = new ViewHolder();
            holder.textViewNomeRestaurante = (TextView) view.findViewById(R.id.textViewNomeRestaurante);
            holder.textViewTelefoneRestaurante = (TextView) view.findViewById(R.id.textViewTelefoneRestaurante);
            holder.netImageViewImagemRestaurante = (NetworkImageView) view.findViewById(R.id.imagemRestaurantes);

            view.setTag(holder);
        } else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }


        holder.textViewNomeRestaurante.setText(restaurante.getNomeRestaurante());
        holder.textViewTelefoneRestaurante.setText(restaurante.getTelefoneRestaurante());
        holder.netImageViewImagemRestaurante.setImageUrl(restaurante.getImagemRestaurante(), imageLoader);

        return view;
    }
}
