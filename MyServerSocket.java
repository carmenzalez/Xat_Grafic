package ChatGrafic;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerSocket {
    
    private MySocket s;
    private ServerSocket ss;
    
    public MyServerSocket(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MySocket accept() {
        try {
            s = new MySocket(ss.accept());
            return s;
        } catch (IOException ex) {
            
        }
        return null;
    }
    
    
}
