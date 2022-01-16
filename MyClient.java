package ChatGrafic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {

    public static void main(String[] args) {
        
        MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));
        
        // Input thread
        new Thread() {
            public void run() {
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while((line = in.readLine()) != null) {
                        sc.println(line);
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
        }.start();
          
        // Output thread
        new Thread() {
            public void run() {
                String line;
                while((line = sc.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }
}
