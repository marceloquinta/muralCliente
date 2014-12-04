package muralufg.fabrica.inf.ufg.br.centralufg.cardapio.adapter;

/**
 * Created by AIRES on 04/12/2014.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Prato;


public class AdapterPratos extends ArrayAdapter <Prato>
{
    private static final int layout = R.layout.item_prato;
    Context mContext;
    private List<Prato> mPratos;

    static class ViewHolder  {
        public TextView txtNomePrato;
        public TextView txtPrecoPrato;
        public LinearLayout imagemPrato ;
    }

    @Override
    public int getCount() {
        if(mPratos != null) {
            return mPratos.size();
        }
        return super.getCount();
    }

    public AdapterPratos(Context context, List<Prato> pratos) {
        super(context, layout);
        mContext = context;
        mPratos = pratos;
    }

    public View getView(int position, View convertView,
                        ViewGroup viewGroup) {
        View listItemView = convertView;
        if(convertView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtNomePrato = (TextView) listItemView.findViewById(R.id.prato_nome);
            viewHolder.txtPrecoPrato = (TextView) listItemView.findViewById(R.id.prato_preco);
            viewHolder.imagemPrato = (LinearLayout) listItemView.findViewById(R.id.prato_imagem);
            listItemView.setTag(viewHolder);
        }

        Prato prato = mPratos.get(position);

        ViewHolder holder = (ViewHolder) listItemView.getTag();

        holder.txtNomePrato.setText(prato.getNome());
        holder.txtPrecoPrato.setText(String.format("$1.2", prato.getPreco()));
        holder.imagemPrato.setBackgroundResource(prato.getImage());
        // TODO: Recurso local -> alterar para uma lib
        //  holder.imagemPrato.setBackgroundResource(prato.getImage());

        return listItemView;
    }
}
