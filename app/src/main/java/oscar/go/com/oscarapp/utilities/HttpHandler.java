package oscar.go.com.oscarapp.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodtw on 25/06/2017.
 */
public class HttpHandler {

    // faz a conexão, o request e a captura da resposta
    public String makeServiceCall(String reqURL, String method, String params){
        String response = null;
        BufferedReader br = null;
        try {
            // faz o request
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(method.equals("GET")){
                conn.setRequestMethod("GET");
                // pega a resposta
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    // lê a InputStream e monta a string de resposta que contem os dados
    private String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    //
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = null;
        result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if(first){
                first = false;
            }else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
