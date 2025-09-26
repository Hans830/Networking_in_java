package URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

    public static void main(String[] args) throws IOException {

        URL oracle=new URL("https://www.oracle.com/");
        URLConnection oracleConnection= oracle.openConnection();

        BufferedReader in =new BufferedReader(
                new InputStreamReader(
                        oracleConnection.getInputStream()
                )
        );

        String inputLine;

        while ((inputLine = in.readLine())!= null){

            System.out.println(inputLine);

        }

        in.close();
    }
}
