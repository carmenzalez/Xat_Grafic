package ChatGrafic;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class MyClientFrame extends JFrame implements ActionListener {

    private JPanel chatPane;
    private JPanel writePane;
    private JButton button;
    private JTextField writeArea;
    private JList usersList;
    private JScrollPane messageArea;
    private JScrollPane userArea;
    private JTextArea messages;

    public MyClientFrame() {

        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        chatPane = new JPanel();
        writePane = new JPanel();

        chatPane.setLayout(new BoxLayout(chatPane, BoxLayout.LINE_AXIS));
        writePane.setLayout(new BoxLayout(writePane, BoxLayout.LINE_AXIS));

        // CHAT PANE

        usersList = new JList();
        messages = new JTextArea(26,47);

        userArea = new JScrollPane(usersList);
        messageArea = new JScrollPane(messages);
        
        chatPane.add(userArea);
        chatPane.add(messageArea);

        // WRITE PANE

        writeArea = new JTextField();
        button = new JButton("Send");

        writePane.add(writeArea, Component.CENTER_ALIGNMENT);
        writePane.add(Box.createRigidArea(new Dimension(5,0)));
        writePane.add(button, Component.CENTER_ALIGNMENT);

        //buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        Container contentPane = getContentPane();
        contentPane.add(chatPane, BorderLayout.CENTER);
        contentPane.add(writePane, BorderLayout.PAGE_END);

        setSize(700,500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
