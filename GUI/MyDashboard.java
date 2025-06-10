import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDashboard implements ActionListener {
    JFrame frame = new JFrame();
    JButton dash, clients, petdet, appoint, pas, alls, pos, createTransaction, logout;
    JPanel panel, ClientPanel, AppointmentPanel;
    JLabel nTime;
    Appointment appt= null;

    MyDashboard() {
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        // Side panel
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        // Client Panel
        ClientPanel = new JPanel();
        ClientPanel.setBackground(new Color(199, 99, 111));
        ClientPanel.setBounds(250, 50, 250, 150);
        ClientPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        ClientPanel.setLayout(null);

        JLabel clientNumber = new JLabel(String.valueOf(ClientManager.clients.size()));
        clientNumber.setFont(new Font("Tahoma", Font.BOLD, 50));
        clientNumber.setForeground(Color.BLACK);
        clientNumber.setBounds(30, 30, 150, 60);
        ClientPanel.add(clientNumber);

        JLabel clientLabel = new JLabel("Total Clients");
        clientLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        clientLabel.setForeground(Color.BLACK);
        clientLabel.setBounds(30, 80, 200, 40);
        ClientPanel.add(clientLabel);

        // Appointment Panel
        AppointmentPanel = new JPanel();
        AppointmentPanel.setBackground(new Color(153, 255, 255));
        AppointmentPanel.setBounds(550, 50, 250, 150);
        AppointmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        AppointmentPanel.setLayout(null);

        JLabel appointmentNumber = new JLabel(String.valueOf(AppointmentManager.appointments.size()));
        appointmentNumber.setFont(new Font("Tahoma", Font.BOLD, 50));
        appointmentNumber.setForeground(Color.BLACK);
        appointmentNumber.setBounds(30, 30, 150, 60);
        AppointmentPanel.add(appointmentNumber);

        JLabel appointmentLabel = new JLabel("Appointments");
        appointmentLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        appointmentLabel.setForeground(Color.BLACK);
        appointmentLabel.setBounds(30, 80, 200, 40);
        AppointmentPanel.add(appointmentLabel);

        // Time Label
        nTime = new JLabel();
        java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss");
        java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("MM-dd-yyyy");
        nTime.setFont(new Font("Helvetica", Font.BOLD, 20));
        nTime.setBounds(250, 400, 400, 50);

        // Timer to update time label
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = java.time.LocalTime.now().format(timeFormatter);
                String date = java.time.LocalDate.now().format(dateFormatter);
                nTime.setText("Current Time: " + time + " | Date: " + date);
            }
        });
        timer.setRepeats(true);
        timer.start();
        String time = java.time.LocalTime.now().format(timeFormatter);
        String date = java.time.LocalDate.now().format(dateFormatter);
        nTime.setText("Current Time: " + time + " | Date: " + date);


        // Buttons 
        dash = new JButton("Dashboard");
        dash.addActionListener(this);
        dash.setBounds(30, 50, 120, 20);
        dash.setFocusable(false);
        dash.setFont(new Font("Tahoma", Font.BOLD, 12));
        dash.setForeground(Color.RED);
        dash.setContentAreaFilled(false);
        dash.setBorderPainted(false);
        dash.setOpaque(false);
        dash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dash.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(dash);

        JSeparator sep1 = new JSeparator(); //LINE 
        sep1.setBounds(45, 85, 120, 1);
        sep1.setForeground(Color.RED); 
        panel.add(sep1);

        clients = new JButton("Clients");
        clients.addActionListener(this);
        clients.setBounds(30, 100, 120, 20);
        clients.setFocusable(false);
        clients.setFont(new Font("Tahoma", Font.BOLD, 12));
        clients.setForeground(Color.BLACK);
        clients.setContentAreaFilled(false);
        clients.setBorderPainted(false);
        clients.setOpaque(false);
        clients.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clients.setHorizontalAlignment(SwingConstants.LEFT); 


        petdet = new JButton("Pet Details");
        petdet.addActionListener(this);
        petdet.setBounds(30, 130, 140, 20);
        petdet.setFocusable(false);
        petdet.setFont(new Font("Tahoma", Font.BOLD, 12));
        petdet.setForeground(Color.BLACK);
        petdet.setContentAreaFilled(false);
        petdet.setBorderPainted(false);
        petdet.setOpaque(false);
        petdet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        petdet.setHorizontalAlignment(SwingConstants.LEFT); 

        appoint = new JButton("Appointments");
        appoint.addActionListener(this);
        appoint.setBounds(30, 160, 140, 20);
        appoint.setFocusable(false);
        appoint.setFont(new Font("Tahoma", Font.BOLD, 12));
        appoint.setForeground(Color.BLACK);
        appoint.setContentAreaFilled(false);
        appoint.setBorderPainted(false);
        appoint.setOpaque(false);
        appoint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        appoint.setHorizontalAlignment(SwingConstants.LEFT); 

        JSeparator sep2 = new JSeparator(); //LINE 
        sep2.setBounds(45, 195, 120, 1);
        sep2.setForeground(Color.RED); 
        panel.add(sep2);

        pas = new JButton("Services");
        pas.addActionListener(this);
        pas.setBounds(30, 205, 185, 20);
        pas.setFocusable(false);
        pas.setFont(new Font("Tahoma", Font.BOLD, 12));
        pas.setForeground(Color.BLACK);
        pas.setContentAreaFilled(false);
        pas.setBorderPainted(false);
        pas.setOpaque(false);
        pas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pas.setHorizontalAlignment(SwingConstants.LEFT); 

        alls = new JButton("All Sales");
        alls.addActionListener(this);
        alls.setBounds(30, 235, 120, 20);
        alls.setFocusable(false);
        alls.setFont(new Font("Tahoma", Font.BOLD, 12));
        alls.setForeground(Color.BLACK);
        alls.setContentAreaFilled(false);
        alls.setBorderPainted(false);
        alls.setOpaque(false);
        alls.setCursor(new Cursor(Cursor.HAND_CURSOR));
        alls.setHorizontalAlignment(SwingConstants.LEFT); 

        pos = new JButton("POS");
        pos.addActionListener(this);
        pos.setBounds(30, 265, 120, 20);
        pos.setFocusable(false);
        pos.setFont(new Font("Tahoma", Font.BOLD, 12));
        pos.setForeground(Color.BLACK);
        pos.setContentAreaFilled(false);
        pos.setBorderPainted(false);
        pos.setOpaque(false);
        pos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pos.setHorizontalAlignment(SwingConstants.LEFT); 

        JSeparator sep3 = new JSeparator(); //LINE 
        sep3.setBounds(45, 295, 120, 1);
        sep3.setForeground(Color.RED); 
        panel.add(sep3);

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        logout.setBounds(30, 310, 120, 20);
        logout.setFocusable(false);
        logout.setFont(new Font("Tahoma", Font.BOLD, 12));
        logout.setForeground(Color.BLACK);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setOpaque(false);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(logout); 



        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setTitle("Dashboard");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setIconImage(icon.getImage());

        // Add panels and components
        frame.add(panel);
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);
        panel.add(logout);

        frame.add(ClientPanel);
        frame.add(AppointmentPanel);
        frame.add(nTime);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clients) {
            frame.dispose();
            MyClients myClients = new MyClients();
        }
        if (e.getSource() == petdet) {
            frame.dispose();
            MyPetDets myPetDetails = new MyPetDets();
        }
        if (e.getSource() == appoint) {
            frame.dispose();
            MyAppointments myAppointments = new MyAppointments();
        }
        if (e.getSource() == pas) {
            frame.dispose();
            MyProductsServices myProductsServices = new MyProductsServices();
        }
        if (e.getSource() == alls) {
            frame.dispose();
            MySales mySales = new MySales();
        }
        if (e.getSource() == pos) {
            frame.dispose();
            MyPOS myPOS = new MyPOS();
            
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame(); // Return to main frame
        }
    }
}