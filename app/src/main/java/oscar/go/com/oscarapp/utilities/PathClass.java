package oscar.go.com.oscarapp.utilities;

import android.content.Context;

import java.io.IOException;

/**
 * Created by Rebeca de Melo on 24/06/2017.
 */
public class PathClass {
    private Context context;

    //constructor
    public PathClass(Context context){
        this.context = context;
    }

    private String getWsBasePath() throws IOException {
        StringBuilder birl = new StringBuilder();
        birl.append(PropertySource.getProperty("oscars.ws.protocol", context));
        birl.append("://");
        birl.append(PropertySource.getProperty("oscars.ws.host", context));
        birl.append(":");
        birl.append(PropertySource.getProperty("oscars.ws.port", context));
        return birl.toString();
    }

    /**
     * Método para pegar caminho completo do WS, por exemplo: http://localhost:8008/server
     *
     * @return
     * @throws IOException
     */
    public String getServerPath() throws IOException {
        StringBuilder birl = new StringBuilder();
        birl.append(this.getWsBasePath());
        birl.append(PropertySource.getProperty("oscars.ws.services", context));
        return birl.toString();
    }

}
