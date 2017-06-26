package oscar.go.com.oscarapp.diretor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;

/**
 * Created by rodtw on 25/06/2017.
 */
public class DiretorListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Diretor> diretoresList;
    private Diretor diretorItem;

    public DiretorListViewAdapter(Context context, ArrayList<Diretor> diretoresList) {
        this.context = context;
        this.diretoresList = diretoresList;
    }

    @Override
    public int getCount() {
        return diretoresList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diretor_item, parent, false);

        TextView nome;

        nome = (TextView) view.findViewById(R.id.nomeDiretorTV);

        diretorItem = diretoresList.get(position);
        nome.setText(diretorItem.getNome());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diretorItem = diretoresList.get(position);
                Intent intent = new Intent(context, DiretorActivity.class);
                intent.putExtra("diretor", diretorItem);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
