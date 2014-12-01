package muralufg.fabrica.inf.ufg.br.centralufg.locais.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Local;
import muralufg.fabrica.inf.ufg.br.centralufg.util.AppController;

public class DetalheLocalFragment extends Fragment {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private class ViewHolder {
        TextView tvNome;
        TextView tvEndereco;
        TextView tvTelefone;
        NetworkImageView imgLocal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Local local = (Local) bundle.getParcelable("local");
        ViewHolder holder = new ViewHolder();

        View view = inflater.inflate(R.layout.fragment_locais, container, false);

        if (imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }

        holder.tvNome = (TextView) view.findViewById(R.id.tvLocalNome);
        holder.tvEndereco = (TextView) view.findViewById(R.id.tvLocalEndereco);
        holder.tvTelefone = (TextView) view.findViewById(R.id.tvLocalTelefone);
        holder.imgLocal = (NetworkImageView) view.findViewById(R.id.imgLocal);

        holder.tvNome.setText(local.getNome());
        holder.tvTelefone.setText(local.getTelefone());
        holder.tvEndereco.setText(local.getEndereco());
        holder.imgLocal.setImageUrl(local.getImagem(), imageLoader);

        return view;
    }
}

