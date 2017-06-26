package oscar.go.com.oscarapp.filme;

import android.content.Context;
import android.content.Intent;
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
 * Created by rodtw on 25/06/2017.
 */
public class FilmeListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Filme> filmesList;
    private Filme filmeItem;

    public FilmeListViewAdapter(Context context, ArrayList<Filme> filmesList) {
        this.context = context;
        this.filmesList = filmesList;
    }

    @Override
    public int getCount() {
        return filmesList.size();
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filme_item, parent, false);

        TextView nome;
        TextView genero;
        ImageView foto;

        nome = (TextView) view.findViewById(R.id.nomeFilmeTV);
        genero = (TextView) view.findViewById(R.id.generoTV);
        foto = (ImageView) view.findViewById(R.id.posterIV);

        filmeItem = filmesList.get(position);
        nome.setText(filmeItem.getNome());
        genero.setText(filmeItem.getGenero());
        Picasso.with(context).load(filmeItem.getFoto()).into(foto);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filmeItem = filmesList.get(position);
                Intent intent = new Intent(context, FilmeActivity.class);
                intent.putExtra("filme", filmeItem);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
