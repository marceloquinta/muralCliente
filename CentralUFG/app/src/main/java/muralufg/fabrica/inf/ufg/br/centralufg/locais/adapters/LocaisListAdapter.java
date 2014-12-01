package muralufg.fabrica.inf.ufg.br.centralufg.locais.adapters;

import android.app.Activity;
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
import muralufg.fabrica.inf.ufg.br.centralufg.model.Local;
import muralufg.fabrica.inf.ufg.br.centralufg.util.AppController;

public class LocaisListAdapter extends ArrayAdapter<Local> {

    private Context context;
    private List<Local> listaDeLocais;

    @Override
    public Local getItem(int position) {
        return super.getItem(position);
    }

    public LocaisListAdapter(Context context, List<Local> listaDeLocais) {
        super(context, android.R.layout.simple_list_item_1, listaDeLocais);
        this.listaDeLocais = listaDeLocais;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaDeLocais.size();
    }

    private class ViewHolder {
        TextView tvNome;
        TextView tvEndereco;
        TextView tvTelefone;
        NetworkImageView imgLocal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Local local = (Local) getItem(position);
        View view = null;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.locais_list_item, null);

            holder = new ViewHolder();
            holder.tvNome = (TextView) view.findViewById(R.id.tvLocaisNome);
            holder.tvEndereco = (TextView) view.findViewById(R.id.tvLocaisEndereco);
            holder.tvTelefone = (TextView) view.findViewById(R.id.tvLocaisTelefone);
            holder.imgLocal = (NetworkImageView) view.findViewById(R.id.imgLocais);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.tvNome.setText(local.getNome());
        holder.tvEndereco.setText(local.getEndereco());
        holder.tvTelefone.setText(local.getTelefone());
        holder.imgLocal.setImageUrl(local.getImagem(), imageLoader);

        return view;
    }


}
