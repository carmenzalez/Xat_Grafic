package ChatGrafic;

import javax.swing.SwingUtilities;

public class MyClientGUI {

    static MyClientFrame client;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                client = new MyClientFrame();
            }
        });
    }
}
