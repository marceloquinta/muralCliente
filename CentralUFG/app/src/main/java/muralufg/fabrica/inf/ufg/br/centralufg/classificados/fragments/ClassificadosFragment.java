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

package muralufg.fabrica.inf.ufg.br.centralufg.classificados.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.pojos.Classificado;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.util.ExpandableListAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.util.VolleySingleton;

/**
 * Fragment contendo a lista dos classificados.
 */
public class ClassificadosFragment extends Fragment {
    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    private SwipeRefreshLayout swipeLayout;
    private static String URL_SERVIDOR_UFG = "http://invisiblerails.com/web/tacs/jsonClassificados.json";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classificados, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buscarJsonClassificados();

        listAdapter = new ExpandableListAdapter(getActivity());
        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        expandableListView = (ExpandableListView) getView().findViewById(R.id.expandableListView);
        expandableListView.setAdapter(listAdapter);

//        /* Serve para acionar o refresh apenas se o primeiro item da lista estiver totalmente visível */
//        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(AbsListView absListView, int i) {
////
////            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (firstVisibleItem == 0)
//                    swipeLayout.setEnabled(true);
//                else
//                    swipeLayout.setEnabled(false);
//            }
//        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        buscarJsonClassificados();
                        swipeLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light);

        /* Desaparece com as setas indicadoras de abrir e fechar cabecalho */
        expandableListView.setGroupIndicator(null);

        expandableListView.setAdapter(listAdapter);
    }

    /**
     * Método que busca o Json dos classificados no servidor.
     */
    private void buscarJsonClassificados() {
        JsonObjectRequest request = new JsonObjectRequest(
                URL_SERVIDOR_UFG,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            List<Classificado> classificados = analisarJsonClassificados(jsonObject);
                            listAdapter.separarClassificados(classificados);
                        } catch(JSONException e) {
                            Log.e(e.getMessage(), e.getMessage());
                            Crouton.makeText(getActivity(), getResources().getString(R.string.erro_obter_json),
                                    Style.ALERT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Crouton.makeText(getActivity(), getResources().getString(R.string.erro_obter_json),
                                Style.ALERT).show();
                    }
                });

        VolleySingleton.getInstance().getRequestQueue().add(request);
    }

    /**
     * Método que interpreta o Json de classificados recebido.
     * @param json  Json de classificados
     * @return      Lista de classificados
     * @throws JSONException
     */
    private List<Classificado> analisarJsonClassificados(JSONObject json) throws JSONException {
        List<Classificado> records = new ArrayList<Classificado>();

        JSONArray jsonListaClassificados = json.getJSONArray("ListaClassificados");

        for(int i = 0; i < jsonListaClassificados.length(); i++) {
            JSONObject jsonClassificado = jsonListaClassificados.getJSONObject(i);
            int id = jsonClassificado.getInt("id");
            String imagemUrl = jsonClassificado.getString("imagem");
            String titulo = jsonClassificado.getString("titulo");
            String autor = jsonClassificado.getString("autor");
            String data = jsonClassificado.getString("data");
            String descricao = jsonClassificado.getString("descricao");
            String email = jsonClassificado.getString("email");

            Classificado classificado = new Classificado(id, titulo, autor, data, descricao, imagemUrl, email);

            records.add(classificado);
        }

        return records;
    }

}
