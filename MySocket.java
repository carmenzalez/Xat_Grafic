package ChatGrafic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySocket {
    
    private Socket sc;
    
    public MySocket(String host, int port) {
        try {
            sc = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MySocket(Socket s) {
        sc = s;
    }
    
    public String readLine() {
        InputStream input = myGetInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        try {
            line = reader.readLine();
            return line;
        } catch (IOException ex) {
            
        }
        return null;
    }
    
    public void println(String line) throws IOException {
        OutputStream output = myGetOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println(line);
    }
    
    public InputStream myGetInputStream() {
        try {
            return sc.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public OutputStream myGetOutputStream() {
        try {
            return sc.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close() {
        try {
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
