package oscar.go.com.oscarapp.diretor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import oscar.go.com.oscarapp.R;
import oscar.go.com.oscarapp.classes.Diretor;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class DiretorListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Diretor> diretoresList = new ArrayList<>();

    public DiretorListViewAdapter(Context context, ArrayList<Diretor> diretoresList) {
        this.context = context;
        this.diretoresList = diretoresList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diretor_item, parent, false);

        return null;
    }
}
