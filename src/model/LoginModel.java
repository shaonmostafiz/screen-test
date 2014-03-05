package model;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginModel {
    public String username;
    public String password;
    String url = "http://localhost/screen-test";

    public boolean login() {

        String username = this.username;
        String password = getPassword(username);

        if(password == null) {
            //LoginController failed - user not exists
            return  false;
        }

        if (this.password.equals(password)) {
            return true;
        }

        return false;
    }

    private String getPassword(String username) {

        URL url;
        HttpURLConnection connection = null;

        try{

            this.url += "?username="+username;

            url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            // send request
            /*DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            wr.writeBytes("username=" + URLEncoder.encode(username,"UTF-8"));
            wr.flush();
            wr.close();*/

            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer response = new StringBuffer();

            String line;

            while ((line = br.readLine())!= null){
                response.append(line);
                response.append("\r");
            }
            br.close();

            System.out.println(response.toString());
            return response.toString();

        }
        catch (Exception e) {

        }


        return "Password";
    }

}
