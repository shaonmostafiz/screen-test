package model;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class BaseModel {

    public static final String REQUEST_GET  = "GET";
    public static final String REQUEST_POST = "POST";

    private String REQUEST_URL;

    private URL url;
    private HttpURLConnection connection = null;

    public String webservice(String REQUEST_URL,String REQUEST_TYPE,Map<String,String> KEY_VALUE_PAIR) {

        if(REQUEST_TYPE == this.REQUEST_GET) {
            //build url
            String queryString = new String();
            Iterator<String> keys = KEY_VALUE_PAIR.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                queryString += "&"+key+"="+KEY_VALUE_PAIR.get(key);
            }
            //build request url
            this.REQUEST_URL = REQUEST_URL + "?" + queryString ;
        }

        try {

            this.url = new URL(this.REQUEST_URL);

            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setRequestMethod(REQUEST_TYPE);
            this.connection.setDoInput(true);
            this.connection.setDoOutput(true);

            //catch response
            InputStream inputStream = this.connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer response = new StringBuffer();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append("\r");
            }
            bufferedReader.close();

            return response.toString();

        } catch (Exception e) {
            return "";
        }
    }

}
