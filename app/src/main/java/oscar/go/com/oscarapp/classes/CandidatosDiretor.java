package oscar.go.com.oscarapp.classes;

import java.util.ArrayList;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class CandidatosDiretor {
    private ArrayList<Diretor> diretor;

    public CandidatosDiretor(ArrayList<Diretor> diretor) {
        this.diretor = diretor;
    }

    public ArrayList<Diretor> getDiretor() {
        return diretor;
    }

    public void setDiretor(ArrayList<Diretor> diretor) {
        this.diretor = diretor;
    }
}
