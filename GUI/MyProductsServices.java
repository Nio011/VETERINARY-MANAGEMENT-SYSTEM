import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyProductsServices implements ActionListener {

    
    
    // Frame and Panels
    JFrame frame = new JFrame();
    JPanel panel, panelAddServices, panelEdit, panelEditProcess, panelDelete;
    JButton logout;

    // Sidebar Buttons
    JButton dash, clients, petdet, appoint, pas, alls, pos;

    // Main Area Buttons
    JButton addServices, viewServices, editServices, deleteServices;
    JButton sServices, sEdit, sEdit2, sDelete;

    // TextFields
    JTextField enServiceName, enPrice;
    JTextField enEdit, enServiceName1, enPrice1, enDelete;

    // Labels
    JLabel label2, label3, label5, label6;

    Appointment appt = null; 

    MyProductsServices() {
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        // Labels
        label2 = new JLabel("Add Services");
        label2.setFont(new Font("Alice", Font.BOLD, 25));
        label2.setBounds(160, 5, 185, 50);

        label3 = new JLabel("Delete Service");
        label3.setFont(new Font("Alice", Font.BOLD, 25));
        label3.setBounds(100, 5, 185, 50);

        label5 = new JLabel("Edit Services");
        label5.setFont(new Font("Alice", Font.BOLD, 25));
        label5.setBounds(105, 8, 200, 50);

        label6 = new JLabel("Edit Services");
        label6.setFont(new Font("Alice", Font.BOLD, 25));
        label6.setBounds(150, 5, 250, 50);

        // Panels
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        panelAddServices = new JPanel();
        panelAddServices.setBackground(new Color(245, 145, 145));
        panelAddServices.setBounds(475, 50, 450, 250);
        panelAddServices.setLayout(null);

        panelEdit = new JPanel();
        panelEdit.setBackground(new Color(245, 145, 145));
        panelEdit.setBounds(475, 50, 365, 165);
        panelEdit.setLayout(null);

        panelEditProcess = new JPanel();
        panelEditProcess.setBackground(new Color(245, 145, 145));
        panelEditProcess.setBounds(475, 50, 450, 300);
        panelEditProcess.setLayout(null);

        panelDelete = new JPanel();
        panelDelete.setBackground(new Color(245, 145, 145));
        panelDelete.setBounds(475, 50, 365, 165);
        panelDelete.setLayout(null);

        // Sidebar Buttons
        dash = new JButton("Dashboard");
        dash.addActionListener(this);
        dash.setBounds(30, 50, 120, 20);
        dash.setFocusable(false);
        dash.setFont(new Font("Tahoma", Font.BOLD, 12));
        dash.setForeground(Color.BLACK);
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
        clients.setForeground(Color.RED);
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

        // Main Area Buttons
        addServices = new JButton("Add Services");
        addServices.addActionListener(this);
        addServices.setBounds(250, 100, 150, 50);

        viewServices = new JButton("View Services");
        viewServices.addActionListener(this);
        viewServices.setBounds(250, 180, 150, 50);

        editServices = new JButton("Edit Services");
        editServices.addActionListener(this);
        editServices.setBounds(250, 260, 150, 50);

        deleteServices = new JButton("Delete Services");
        deleteServices.addActionListener(this);
        deleteServices.setBounds(250, 340, 150, 50);

        sServices = new JButton("Submit");
        sServices.addActionListener(this);
        sServices.setBounds(105, 175, 100, 40);

        sEdit = new JButton("Edit");
        sEdit.addActionListener(this);
        sEdit.setBounds(57, 115, 100, 40);

        sEdit2 = new JButton("Edit");
        sEdit2.addActionListener(this);
        sEdit2.setBounds(105, 175, 100, 40);

        sDelete = new JButton("Delete");
        sDelete.addActionListener(this);
        sDelete.setBounds(57, 115, 100, 40);

        // TextFields for service

        enServiceName = new JTextField();
        enServiceName.setText("Service Name");
        enServiceName.setBounds(105, 75, 250, 40);
        enServiceName.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enServiceName.isEnabled() && enServiceName.getText().equals("Service Name")) {
                enServiceName.setText("");
            }
            }
        });

        enPrice = new JTextField();
        enPrice.setText("Price");
        enPrice.setBounds(105, 125, 250, 40);
        enPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enPrice.isEnabled() && enPrice.getText().equals("Price")) {
                enPrice.setText("");
            }
            }
        });

        enEdit = new JTextField();
        enEdit.setText("Enter Services number to Edit: ");
        enEdit.setBounds(57, 55, 250, 40);
        enEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enEdit.isEnabled() && enEdit.getText().equals("Enter Services number to Edit: ")) {
                enEdit.setText("");
            }
            }
        });

       

        enServiceName1 = new JTextField();
        enServiceName1.setText("Service Name");
        enServiceName1.setBounds(105, 75, 250, 40);
        enServiceName1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enServiceName1.isEnabled() && enServiceName1.getText().equals("Service Name")) {
                enServiceName1.setText("");
            }
            }
        });

        enPrice1 = new JTextField();
        enPrice1.setText("Price");
        enPrice1.setBounds(105, 125, 250, 40);
        enPrice1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enPrice1.isEnabled() && enPrice1.getText().equals("Price")) {
                enPrice1.setText("");
            }
            }
        });

        enDelete = new JTextField();
        enDelete.setText("Enter Service # to Delete: ");
        enDelete.setBounds(57, 55, 250, 40);
        enDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDelete.isEnabled() && enDelete.getText().equals("Enter Service # to Delete: ")) {
                enDelete.setText("");
            }
            }
        });

        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setTitle("Services");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setIconImage(icon.getImage());

        // Add components to panel and frame
        frame.add(panel);
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);

        frame.add(addServices);
        frame.add(viewServices);
        frame.add(editServices);
        frame.add(deleteServices);

        frame.add(panelAddServices);
        panelAddServices.setVisible(false);
        panelAddServices.add(label2);
        panelAddServices.add(sServices);
        panelAddServices.add(enServiceName);
        panelAddServices.add(enPrice);

        frame.add(panelEdit);
        panelEdit.setVisible(false);
        panelEdit.add(label5);
        panelEdit.add(enEdit);
        panelEdit.add(sEdit);

        frame.add(panelEditProcess);
        panelEditProcess.setVisible(false);
        panelEditProcess.add(label6);
        panelEditProcess.add(enServiceName1);
        panelEditProcess.add(enPrice1);
        panelEditProcess.add(sEdit2);

        frame.add(panelDelete);
        panelDelete.setVisible(false);
        panelDelete.add(label3);
        panelDelete.add(enDelete);
        panelDelete.add(sDelete);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dash) {
            frame.dispose();
            MyDashboard mydashboard = new MyDashboard();
        }
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
        if (e.getSource() == alls) {
            frame.dispose();
            MySales mySales = new MySales();
        }
        if (e.getSource() == pos) {
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame(); // Return to main frame
        }
        if (e.getSource() == addServices) {
            panelAddServices.setVisible(true);
            enServiceName.setText(" Service Name");
            enPrice.setText(" Price");
        }
        if (e.getSource() == sServices) {
            String serviceName = enServiceName.getText().trim();
            String priceText = enPrice.getText().trim();
            double price;
            if (serviceName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            try {
            price = Double.parseDouble(priceText);
            // Save to services.txt
            java.io.FileWriter fw = new java.io.FileWriter("services.txt", true);
            fw.write(serviceName + "," + price + System.lineSeparator());
            fw.close();
            JOptionPane.showMessageDialog(frame, "Service added successfully.");
            panelAddServices.setVisible(false);
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid price entered.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving service.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == viewServices) {
            panelAddServices.setVisible(false);
            java.util.List<String> services = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("services.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    services.add(line);
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error reading services.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (services.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No services available.", "Services", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            StringBuilder servicesList = new StringBuilder("Available Services:\n");
            for (int i = 0; i < services.size(); i++) {
                String[] parts = services.get(i).split(",");
                if (parts.length == 3) {
                    servicesList.append(String.format("[%d] [%s] %s - ₱%.2f%n", i + 1, parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
            JOptionPane.showMessageDialog(frame, servicesList.toString(), "Services", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == editServices) {
            panelEdit.setVisible(true);
            panelAddServices.setVisible(false);
            panelEditProcess.setVisible(false);
            panelDelete.setVisible(false);

            enEdit.setText("Enter Services number to Edit: ");
            enServiceName1.setText("Service Name");
            enPrice1.setText("Price");
        }
        if (e.getSource() == sEdit) {
            String serviceNumberText = enEdit.getText().trim();
            int serviceNumber;
            try {
            serviceNumber = Integer.parseInt(serviceNumberText);
            if (serviceNumber < 1) {
                throw new NumberFormatException();
            }
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid service number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            java.util.List<String> services = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("services.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                services.add(line);
            }
            } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading services.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            if (serviceNumber > services.size()) {
            JOptionPane.showMessageDialog(frame, "Service number does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            String[] parts = services.get(serviceNumber - 1).split(",");
            if (parts.length != 3) {
            JOptionPane.showMessageDialog(frame, "Invalid service format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            enServiceName1.setText(parts[1]);
            enPrice1.setText(parts[2]);
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(true);
        }
        if (e.getSource() == sEdit2) {
            String serviceNumberText = enEdit.getText().trim();
            int serviceNumber;
            try {
            serviceNumber = Integer.parseInt(serviceNumberText);
            if (serviceNumber < 1) {
                throw new NumberFormatException();
            }
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid service number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            java.util.List<String> services = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("services.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                services.add(line);
            }
            } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading services.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            if (serviceNumber > services.size()) {
            JOptionPane.showMessageDialog(frame, "Service number does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            String[] oldParts = services.get(serviceNumber - 1).split(",");
            if (oldParts.length != 3) {
            JOptionPane.showMessageDialog(frame, "Invalid service format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            String newServiceName = enServiceName1.getText().trim();
            String newPriceText = enPrice1.getText().trim();
            double newPrice;
            if (newServiceName.isEmpty() || newPriceText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            try {
            newPrice = Double.parseDouble(newPriceText);
            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid price entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            // Keep the original pet type (parts[0])
            services.set(serviceNumber - 1, oldParts[0] + "," + newServiceName + "," + newPrice);
            // Write back to file
            try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("services.txt"))) {
            for (String s : services) {
                bw.write(s);
                bw.newLine();
            }
            } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving service.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            JOptionPane.showMessageDialog(frame, "Service updated successfully.");
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);
        }
        if (e.getSource() == deleteServices) {
            panelDelete.setVisible(true);
            panelAddServices.setVisible(false);
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);

            enDelete.setText(" Enter Service # to Delete: ");
        }
        if (e.getSource() == sDelete) {
            String serviceNumberText = enDelete.getText().trim();
            int serviceNumber;
            try {
                serviceNumber = Integer.parseInt(serviceNumberText);
                if (serviceNumber < 1) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid service number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.util.List<String> services = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("services.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    services.add(line);
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error reading services.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Use the logic from deleteService(int index)
            if (serviceNumber < 1 || serviceNumber > services.size()) {
                JOptionPane.showMessageDialog(frame, "Invalid service number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            services.remove(serviceNumber - 1);
            // Write back to file
            try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("services.txt"))) {
                for (String s : services) {
                    bw.write(s);
                    bw.newLine();
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving service.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame, "Service deleted successfully.");
            panelDelete.setVisible(false);
        }
    }
}
