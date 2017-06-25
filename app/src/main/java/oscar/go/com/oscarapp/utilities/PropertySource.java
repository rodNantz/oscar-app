package oscar.go.com.oscarapp.utilities;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Rebeca de Melo on 02/05/2017.
 */
public class PropertySource {
    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("mobile.properties");
        properties.load(inputStream);
        return properties.getProperty(key);

    }
}
