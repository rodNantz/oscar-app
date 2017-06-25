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
        birl.append(PropertySource.getProperty("resysclagem.service.protocol", context));
        birl.append("://");
        birl.append(PropertySource.getProperty("resysclagem.service.host", context));
        birl.append(":");
        birl.append(PropertySource.getProperty("resysclagem.service.port", context));
        return birl.toString();
    }

    public String getServerPath() throws IOException {
        StringBuilder birl = new StringBuilder();
        birl.append(this.getWsBasePath());
        birl.append(PropertySource.getProperty("resysclagem.service.ws", context));
        return birl.toString();
    }

    public String getFilePath() throws IOException {
        StringBuilder birl = new StringBuilder();
        birl.append(this.getWsBasePath());
        birl.append(PropertySource.getProperty("resysclagem.service.file", context));
        return birl.toString();
    }

}
