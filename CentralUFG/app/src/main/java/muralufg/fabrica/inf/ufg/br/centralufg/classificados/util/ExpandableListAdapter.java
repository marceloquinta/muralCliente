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

package muralufg.fabrica.inf.ufg.br.centralufg.classificados.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.pojos.CabecalhoClassificado;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.pojos.Classificado;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.pojos.CorpoClassificado;

/**
 * Classe que representa o adapter da lista de classificados.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<CabecalhoClassificado> listaCabecalhosClassificados = new ArrayList<CabecalhoClassificado>();
    private LayoutInflater inflater;
    private static final int QUANTIDADE_DE_CORPOS_POR_CABECALHO = 1;
    private ImageLoader imageLoader;

    public ExpandableListAdapter(Context context) {
        super();
        imageLoader = new ImageLoader(VolleySingleton.getInstance().getRequestQueue(), new BitmapLruCache());
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Método que separa os vários classificados da lista e os coloca cada um em um cabeçalho,
     * vinculando cada corpo ao seu cabeçalho.
     * @param listaClassificados    lista de todos os classificados
     */
    public void separarClassificados(List<Classificado> listaClassificados) {
        listaCabecalhosClassificados.clear();
        for (Classificado classificado : listaClassificados) {
            CabecalhoClassificado cabecalho = new CabecalhoClassificado(classificado);
            CorpoClassificado corpo = new CorpoClassificado(classificado.getDescricao(), classificado.getImagemUrl(), classificado.getEmail());
            cabecalho.setCorpoClassificado(corpo);
            listaCabecalhosClassificados.add(cabecalho);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return listaCabecalhosClassificados.size();
    }

    @Override
    public int getChildrenCount(int posicaoCabecalho) {
        return QUANTIDADE_DE_CORPOS_POR_CABECALHO;
    }

    @Override
    public Object getGroup(int posicaoCabecalho) {
        return listaCabecalhosClassificados.get(posicaoCabecalho);
    }

    @Override
    public Object getChild(int posicaoCabecalho, int posicaoCorpo) {
        return listaCabecalhosClassificados.get(posicaoCabecalho).getCorpoClassificado();
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int posicaoCabecalho, int posicaoCorpo) {
        return posicaoCorpo;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int posicaoCabecalho, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cabecalho_classificado, null);
        }

        CabecalhoClassificado cabecalho = (CabecalhoClassificado) getGroup(posicaoCabecalho);

        NetworkImageView imagem = (NetworkImageView) convertView.findViewById(R.id.cabecalho_imagem_classificado);
        TextView titulo = (TextView) convertView.findViewById(R.id.cabecalho_titulo_classificado);
        TextView autor = (TextView) convertView.findViewById(R.id.cabecalho_autor_classificado);
        TextView dataCriacao = (TextView) convertView.findViewById(R.id.cabecalho_data_classificado);

        titulo.setText(cabecalho.getClassificado().getTitulo());
        autor.setText(cabecalho.getClassificado().getAutor());
        imagem.setImageUrl(cabecalho.getClassificado().getImagemUrl(), imageLoader);
        dataCriacao.setText(cabecalho.getClassificado().getDataCriacao());

        return convertView;
    }

    @Override
    public View getChildView(int posicaoCabecalho, int posicaoCorpo, boolean isUltimoItemCorpo, View convertView, ViewGroup parent) {
        final CorpoClassificado corpo = (CorpoClassificado) getChild(posicaoCabecalho, posicaoCorpo);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.corpo_classificado, null);
        }

        TextView descricao = (TextView) convertView.findViewById(R.id.corpo_descricao_classificado);
        NetworkImageView imagem = (NetworkImageView) convertView.findViewById(R.id.corpo_imagem_classificado);
        TextView email = (TextView) convertView.findViewById(R.id.corpo_email_classificado);

        descricao.setText(corpo.getDescricao());
        imagem.setImageUrl(corpo.getImagemUrl(), imageLoader);
        email.setText(corpo.getEmail());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}