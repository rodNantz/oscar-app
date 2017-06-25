package oscar.go.com.oscarapp.classes;

/**
 * Created by Rebeca de Melo on 24/06/2017.
 */
public class User {
    private long codU;
    private String user;
    private String pass;
    private String token;

    public User() {
    }

    public User(long codU, String user, String pass, String token){
        this.setCodU(codU);
        this.setUser(user);
        this.setPass(pass);
        this.setToken(token);
    }

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String residuo) {
        this.pass = residuo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCodU() {
        return codU;
    }

    public void setCodU(long codU) {
        this.codU = codU;
    }
}