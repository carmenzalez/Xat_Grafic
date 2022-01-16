package ChatGrafic;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.awt.event.WindowEvent;

public class MyClientFrame extends JFrame implements ActionListener {

    private JPanel chatPane;
    private JPanel writePane;
    private JButton button;
    private JTextField writeArea;
    private JList usersList;
    private JScrollPane messageArea;
    private JScrollPane userArea;
    private JTextArea messages;
    private String line, name;
    private MySocket sc;

    private int key;

    public MyClientFrame(String adress, int port) {

        sc = new MySocket(adress, port);

        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        addKeyListener(new Key());

        chatPane = new JPanel();
        writePane = new JPanel();

        chatPane.setLayout(new BoxLayout(chatPane, BoxLayout.LINE_AXIS));
        writePane.setLayout(new BoxLayout(writePane, BoxLayout.LINE_AXIS));

        // CHAT PANE

        usersList = new JList();
        messages = new JTextArea(26,47);
        messages.setFont(messages.getFont().deriveFont(12f));
        messages.setEditable(false);

        userArea = new JScrollPane(usersList);
        messageArea = new JScrollPane(messages);
        
        chatPane.add(userArea);
        chatPane.add(messageArea);

        // WRITE PANE

        writeArea = new JTextField();
        writeArea.addKeyListener(new Key());
        button = new JButton("Send");

        button.addActionListener(this);

        writePane.add(writeArea, Component.CENTER_ALIGNMENT);
        writePane.add(Box.createRigidArea(new Dimension(5,0)));
        writePane.add(button, Component.CENTER_ALIGNMENT);

        Container contentPane = getContentPane();
        contentPane.add(chatPane, BorderLayout.CENTER);
        contentPane.add(writePane, BorderLayout.PAGE_END);

        setSize(700,500);
        setVisible(true);

        // Ventana pop-up para introducir username
        name = JOptionPane.showInputDialog(this, "Enter username: ", null);
        if (name == null) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        try {
            sc.println(name);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        // Para que empiece el thread de escuchar mensajes
        button.doClick();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            try {
                if((line = writeArea.getText()) != null && !writeArea.getText().isEmpty()) {
                    sc.println(line);
                    messages.setText(messages.getText() + "\n  " + line);
                    writeArea.setText("");
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }

        new Thread(new Runnable() {
            public void run() {
                while((line = sc.readLine()) != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (!line.equals("Enter username: ")) {
                                messages.setText(messages.getText() + "\n  " + line);
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public class Key extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
                button.doClick();
            }
        }
    }

}
