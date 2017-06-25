package oscar.go.com.oscarapp.classes;

import java.util.ArrayList;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class CandidatosDiretor {
    private ArrayList<Diretor> diretores;

    public CandidatosDiretor(ArrayList<Diretor> diretores) {
        this.diretores = diretores;
    }

    public ArrayList<Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(ArrayList<Diretor> diretores) {
        this.diretores = diretores;
    }
}
