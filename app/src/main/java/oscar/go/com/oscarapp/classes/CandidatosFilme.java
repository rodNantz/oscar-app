package oscar.go.com.oscarapp.classes;

import java.util.ArrayList;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class CandidatosFilme {
    private ArrayList<Filme> filme;

    public CandidatosFilme(ArrayList<Filme> filme) {
        this.filme = filme;
    }

    public ArrayList<Filme> getFilme() {
        return filme;
    }

    public void setFilme(ArrayList<Filme> filme) {
        this.filme = filme;
    }
}
