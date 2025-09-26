package URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class URLReverser {

    public static void main(String[] args) throws IOException {

       /* if(args.length != 2){
            System.err.println("Usage:  java Reverse "
                    + "http://<location of your servlet/script>"
                    + " string_to_reverse");
            System.exit(1);
        }*/

        String stringToReverse = URLEncoder.encode("String to Reverse", "UTF-8");

        URL url = new URL("https://example.com/servlet/ReverseServlet");
        URLConnection connection= url.openConnection();
        connection.setDoOutput(true);

        connection.setRequestProperty("User-Agent","Mozilla/5.0");
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream()
        );

        out.write("String=" + stringToReverse);
        out.flush();
        out.close();

        BufferedReader in =new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()
                )
        );

        String decodedString;

        while(( decodedString = in.readLine()) != null){
            System.out.println(decodedString);
        }

        out.close();
        in.close();
    }
}
