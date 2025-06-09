import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLogin implements ActionListener {

    ImageIcon eyeIcon = new ImageIcon("C:\\Users\\HP\\Desktop\\VETERINARY MANAGEMENT SYSTEM\\Visibility.png");
    ImageIcon invisibleIcon = new ImageIcon("C:\\Users\\HP\\Desktop\\VETERINARY MANAGEMENT SYSTEM\\Hide.png");


    JFrame frame=new JFrame();
    JLabel label=new JLabel("Username:");
    JLabel logIn=new JLabel();
    JTextField Username, Pass;
    JButton submit;
    JPanel panel;

    int attempts = 0;

    MyLogin(){
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        

        panel = new JPanel();
        panel.setBackground(new Color(	236, 236, 211));
        panel.setBounds(-7, -20, 400,300);
        panel.setLayout(null);

        Username = new JTextField();
        Username.setBounds(50, 75, 250, 40);
        Username.setText("admin");
        Username.setVisible(true);

        Pass = new JPasswordField();
        Pass.setBounds(50, 130, 250, 40);
        Pass.setText("admin123");
        Pass.setVisible(true);

        //Password 
        ImageIcon eyeIcon = new ImageIcon(
        new ImageIcon("Visibility.png")
            .getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)
        );
        ImageIcon invisibleIcon = new ImageIcon(
        new ImageIcon("Hide.png")
            .getImage().getScaledInstance(24, 24, Image.SCALE_AREA_AVERAGING)
        );

        JButton toggleVisibility = new JButton(eyeIcon); 
        toggleVisibility.setBounds(290, 130, 40, 40); 
        toggleVisibility.setFocusable(false);
        toggleVisibility.setContentAreaFilled(false);
        toggleVisibility.setBorderPainted(false);
        panel.add(toggleVisibility);

        // Set the initial echo character for the password field
        ((JPasswordField) Pass).setEchoChar('*'); 
        char defaultEchoChar = ((JPasswordField) Pass).getEchoChar();

        // Add action listener to toggle visibility
        toggleVisibility.addActionListener(new ActionListener() {
            private boolean visible = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                visible = !visible;
                if (visible) {
                    ((JPasswordField) Pass).setEchoChar((char) 0); // Show password
                    toggleVisibility.setIcon(invisibleIcon); // Change icon/text if you want
                } else {
                    ((JPasswordField) Pass).setEchoChar(defaultEchoChar); // Hide password
                    toggleVisibility.setIcon(eyeIcon);
                }
            }
        });

        logIn = new JLabel("LOG IN");
        logIn.setForeground(Color.RED);
        logIn.setFont(new Font("Helvetica" , Font.BOLD, 20));
        logIn.setBounds(135,30,250,40);

        submit = new JButton("Log In");
        submit.addActionListener(this);
        submit.setBounds(50, 180, 100, 40);

//        label.setBounds(0,0,200,50);
//        label.setFont(new Font("Alice",Font.PLAIN,25));
//
//        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(350, 260);
        frame.setResizable(false);
        frame.setLocationRelativeTo(toggleVisibility);
        frame.setVisible(true);
        frame.setTitle("Log-in");
        frame.setIconImage(icon.getImage());
        frame.add(panel);
        panel.add(Username);
        panel.add(Pass);
        panel.add(logIn);
        panel.add(submit);
//
//        frame.add(button);
//        frame.add(Username);
//        frame.add(Pass);
//        frame.pack();

//        frame.setLayout(null);
//        frame.setSize(500,500);
//        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String username = Username.getText();
        String password = new String(((JPasswordField) Pass).getPassword());
        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            frame.dispose();
            MyDashboard myDashboard = new MyDashboard();
            attempts = 0; // reset on success
        } else {
            attempts++;
            if (attempts >= 3) {
                JOptionPane.showMessageDialog(frame, "Too many failed attempts. Exiting.");
                frame.dispose();
                new MyFrame(); // returning to main frame
                attempts = 0; // reset counter
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password. Please try again.");
                Username.setText("admin");
                Pass.setText("admin123");
            }
        }
        return;
        }

        }

    }

