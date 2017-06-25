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
        birl.append(PropertySource.getProperty("oscars.launch.protocol", context));
        birl.append("://");
        birl.append(PropertySource.getProperty("oscars.launch.host", context));
        birl.append(":");
        birl.append(PropertySource.getProperty("oscars.launch.port", context));
        return birl.toString();
    }

    public String getServerPath() throws IOException {
        StringBuilder birl = new StringBuilder();
        birl.append(this.getWsBasePath());
        birl.append(PropertySource.getProperty("oscars.service.path", context));
        return birl.toString();
    }

}
