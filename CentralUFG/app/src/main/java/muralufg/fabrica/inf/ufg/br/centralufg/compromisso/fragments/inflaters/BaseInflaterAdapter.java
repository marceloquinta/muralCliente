package muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments.inflaters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.compromisso.fragments.ExibeCompromisso;
import muralufg.fabrica.inf.ufg.br.centralufg.model.Compromisso;

public class BaseInflaterAdapter<T> extends BaseAdapter {
    private List<T> mItems = new ArrayList<T>();
    private IAdapterViewInflater<T> mViewInflater;

    public BaseInflaterAdapter(IAdapterViewInflater<T> viewInflater) {
        mViewInflater = viewInflater;
    }

    public BaseInflaterAdapter(List<T> items, IAdapterViewInflater<T> viewInflater) {
        mItems.addAll(items);
        mViewInflater = viewInflater;
    }

    public void setViewInflater(IAdapterViewInflater<T> viewInflater, boolean notifyChange) {
        mViewInflater = viewInflater;

        if (notifyChange) {
            notifyDataSetChanged();
        }
    }

    public void addItem(T item, boolean notifyChange) {
        mItems.add(item);

        if (notifyChange) {
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> items, boolean notifyChange) {
        mItems.addAll(items);

        if (notifyChange) {
            notifyDataSetChanged();
        }
    }

    public void clear(boolean notifyChange) {
        mItems.clear();

        if (notifyChange) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return getTItem(pos);
    }

    public T getTItem(int pos) {
        return mItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                CardItemData cardItemData = (CardItemData) getItem(pos);
                Compromisso compromissoClicado = cardItemData.getCompromisso();
                Intent intent = new Intent(v.getContext(), ExibeCompromisso.class);
                intent.putExtra("data", compromissoClicado.getStringData());
                intent.putExtra("nome", compromissoClicado.getNome());
                intent.putExtra("descricao", compromissoClicado.getDescricao());
                v.getContext().startActivity(intent);
            }
        };

        View view = null;
        if (mViewInflater != null) {
            view = mViewInflater.inflate(this, pos, convertView, parent);
            view.setOnClickListener(onClickListener);
        }

        return view;
    }
}
