
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaineb
 */
public class NewJSFManagedBean {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    private String message;
    
    public void sendNotif(){
        try {
   String jsonResponse;
   
   URL url = new URL("https://onesignal.com/api/v1/notifications");
   HttpURLConnection con = (HttpURLConnection)url.openConnection();
   con.setUseCaches(false);
   con.setDoOutput(true);
   con.setDoInput(true);

   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
   con.setRequestProperty("Authorization", "Basic ZmU5NzZhNGYtMjc3Yi00MzM3LTgwMDctNGNmOTMwNWJmYzAy");
   con.setRequestMethod("POST");

   String strJsonBody = "{"
                      +   "\"app_id\": \"da7bc528-f33e-4c80-9865-b13d0d5f0038\","
                      +   "\"included_segments\": [\"All\"],"
                      +   "\"data\": {\"foo\": \"bar\"},"
                      +   "\"contents\": {\"en\": \""+message+"\"}"
                      + "}";
         
   
   System.out.println("strJsonBody:\n" + strJsonBody);

   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
   con.setFixedLengthStreamingMode(sendBytes.length);

            java.io.OutputStream outputStream = con.getOutputStream();
   outputStream.write(sendBytes);

   int httpResponse = con.getResponseCode();
   System.out.println("httpResponse: " + httpResponse);

   if (  httpResponse >= HttpURLConnection.HTTP_OK
      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
      scanner.close();
   }
   else {
      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
      scanner.close();
   }
   System.out.println("jsonResponse:\n" + jsonResponse);
   
} catch(Throwable t) {
   t.printStackTrace();
}

    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
