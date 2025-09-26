package URLs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectingWithURLs {

    public static void main(String[] args){

        try {
            URL myURL = new URL("https://example.com/");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
        }
        catch (MalformedURLException m){
            System.out.println("Malformed URL");
        }catch (IOException io){
            System.out.println("IOException");

        }

        System.out.println("Connection was Successful");
    }
}
