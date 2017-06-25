package oscar.go.com.oscarapp.filme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Filme;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class FilmeListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Filme> filmesList = new ArrayList<>();

    public FilmeListViewAdapter(Context context, ArrayList<Filme> filmesList) {
        this.context = context;
        this.filmesList = filmesList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filme_item, parent, false);

        TextView nome;
        TextView genero;
        ImageView foto;

        nome = (TextView) view.findViewById(R.id.nomeFilmeTV);
        genero = (TextView) view.findViewById(R.id.generoTV);
        foto = (ImageView) view.findViewById(R.id.posterIV);

        Filme filmeItem = filmesList.get(position);
        nome.setText(filmeItem.getNome());
        genero.setText(filmeItem.getGenero());
        Picasso.with(context).load(filmeItem.getFoto()).into(foto);

        return view;
    }
}
