package ChatGrafic;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    
    private static final ConcurrentHashMap<String, MySocket> chm = new ConcurrentHashMap<String, MySocket>();

    public static void main(String[] args) {
        
        MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
        ExecutorService pool = Executors.newFixedThreadPool(50);
        
        while(true) {
            pool.execute(new Task(ss.accept()));
        }
        
    }

    public static class Task implements Runnable {
        
        private MySocket sc;

        private String name;
        private String msg;
        
        public Task(MySocket s) {
            sc = s;
        }
        
        @Override
        public void run() {
            while(true) {
                try {
                    sc.println("Enter username: ");
                    name = sc.readLine();
                    if (!chm.containsKey(name)) {
                        chm.put(name, sc);
                        System.out.println("USER: " + name);
                        for(MySocket bs : chm.values()) {
                            bs.println("--- " + name + " has joined the chat" + " ---");
                        }
                        break;
                    } else {
                        sc.println("Username already taken. Try again with a different one.");
                    }
                } catch (IOException ex) {
                    System.out.println("error");
                }
            }
            while(sc != null) {
                try {
                    while((msg = sc.readLine()) != null) {
                        for(MySocket bs : chm.values()) {
                            if (sc != bs) {
                                bs.println(name + ": " + msg);
                            }
                        }
                    }
                } catch (IOException ex) {

                }
                break;
            }
            System.out.println("DISCONNECTED USER: " + name);
            chm.remove(name);
            try {
                for(MySocket bs : chm.values()) {
                    if (sc != bs) {
                        bs.println("--- " + name + " has left the chat" + " ---");
                    }
                }
            } catch (IOException ex) {

            }
        }
    }

}
