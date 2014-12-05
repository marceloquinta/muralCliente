package muralufg.fabrica.inf.ufg.br.centralufg.locais.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.mapa.MapaFragment;
import muralufg.fabrica.inf.ufg.br.centralufg.mapa.Ponto;
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

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Ponto ponto = new Ponto();
        ponto.setDescricao(local.getNome());
        ponto.setLatitude(-16.6032416);
        ponto.setLongitude(-49.2657075);

        MapaFragment mapaFragment = new MapaFragment();

        fragmentTransaction.replace(R.id.flContainer, mapaFragment);
        fragmentTransaction.commit();


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

