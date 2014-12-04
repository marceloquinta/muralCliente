package muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.models.OuvidoriaItemAnexo;
import muralufg.fabrica.inf.ufg.br.centralufg.ouvidoria.utils.OuvidoriaUtil;

public class AnexoImagensAdapter extends ArrayAdapter<OuvidoriaItemAnexo> {

    static int layout = R.layout.adapter_ouvidoria_item_image;

    private Activity mContext;
    private List<OuvidoriaItemAnexo> mItens;

    public AnexoImagensAdapter(Activity context, List<OuvidoriaItemAnexo> itens) {
        super(context, layout, itens);
        mContext = context;
        mItens = itens;
    }

    @Override
    public void add(OuvidoriaItemAnexo item) {
        mItens.add(item);
    }

    public List<OuvidoriaItemAnexo> getAll() {
        return mItens;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            final LayoutInflater inflater = mContext.getLayoutInflater();
            view = inflater.inflate(layout, null);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final OuvidoriaItemAnexo item = mItens.get(position);

        viewHolder.setImagem(item.getDiretorio());
        viewHolder.setNome(item.getNome());
        final String tamanho = OuvidoriaUtil.bytesParaFormatoLegivel(item.getTamanho(), true);
        viewHolder.setTamanho(tamanho);
        viewHolder.removerAnexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(item);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    /**
     * Padrão para acessar cada elemento que está sendo visualizado na lista
     */
    static class ViewHolder {
        ImageView itemImagem;
        TextView itemNome;
        TextView itemTamanho;
        ImageButton removerAnexo;
        private Context mContext;

        ViewHolder(View view) {
            itemImagem = (ImageView) view.findViewById(R.id.ouvidoria_item_image);
            itemNome = (TextView) view.findViewById(R.id.ouvidoria_item_nome);
            itemTamanho = (TextView) view.findViewById(R.id.ouvidoria_item_tamanho);
            removerAnexo = (ImageButton) view.findViewById(R.id.ouvidoria_item_remover);

            mContext = view.getContext();
        }

        public void setNome(String nome) {
            this.itemNome.setText(nome);
        }

        public void setTamanho(String tamanho) {
            this.itemTamanho.setText(tamanho);
        }

        public void setImagem(String diretorio) {
            Picasso.with(mContext).load(new File(Uri.decode(diretorio))).resize(200, 200).centerCrop().into(this.itemImagem);
        }
    }

}
