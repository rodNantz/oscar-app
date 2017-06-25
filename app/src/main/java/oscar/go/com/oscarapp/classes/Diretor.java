package oscar.go.com.oscarapp.classes;

import java.io.Serializable;

/**
 * Created by Rebeca de Melo on 25/06/2017.
 */
public class Diretor implements Serializable {
    private int id;
    private String nome;

    public Diretor() {
    }

    public Diretor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
