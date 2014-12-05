package muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments.inflaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import muralufg.fabrica.inf.ufg.br.centralufg.R;

public class CardInflater implements IAdapterViewInflater<CardItemData> {
    @Override
    public View inflate(final BaseInflaterAdapter<CardItemData> adapter, final int pos, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View newConvertView;

        if (convertView != null) {
            newConvertView = convertView;
            holder = (ViewHolder) convertView.getTag();
        } else  {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            newConvertView = inflater.inflate(R.layout.list_item_card_compromisso, parent, false);
            holder = new ViewHolder(newConvertView);
        }

        final CardItemData item = adapter.getTItem(pos);
        holder.updateDisplay(item);

        return newConvertView;
    }

    private class ViewHolder {
        private View mRootView;
        private TextView labelNome;
        private TextView labelData;

        public ViewHolder(View rootView) {
            mRootView = rootView;
            labelNome = (TextView) mRootView.findViewById(R.id.textNome);
            labelData = (TextView) mRootView.findViewById(R.id.textData);
            rootView.setTag(this);
        }

        public void updateDisplay(CardItemData item) {
            labelNome.setText(item.getLabelNome());
            labelData.setText(item.getLabelData());
        }
    }
}

