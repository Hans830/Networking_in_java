package URLs;

import java.io.*;
import java.net.*;

public class URLReverserGPT {


        public static void main(String[] args) {
            try {
                String stringToReverse = URLEncoder.encode("String to Reverse", "UTF-8");

                URL url = new URL("https://postman-echo.com/post");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                    out.write("String=" + stringToReverse);
                    out.flush();
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
