import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySales implements ActionListener {
    // Frame and Panels
    JFrame frame = new JFrame();
    JPanel panel, panelSearch, panelRecord;

    // Buttons
    JButton dash, clients, petdet, appoint, pas, alls, pos, logout, searchSales;
    JButton add, sAdd, viewSales, submit3;

    // Labels
    JLabel displaySales, label, label1, label2, label3, lRecord;

    // TextFields
    JTextField enClientName, enServices, enTotal, enDate;
    JTextField enClientName1, enServices1, enTotal1, enDate1, enEdit2, enDeleteSales, enClientRecord;

    Appointment appt = null;

    // Constructor
    MySales() {
        // --- Icon ---
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        // --- Edit Panel TextField ---
        enEdit2 = new JTextField();
        enEdit2.setText("Enter Client's Name to Edit Sales: ");
        enEdit2.setBounds(103, 55, 250, 40);
        enEdit2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enEdit2.isEnabled() && enEdit2.getText().equals("Enter Client's Name to Edit Sales: ")) {
                    enEdit2.setText("");
                }
            }
        });

        // --- Panels ---
        panelRecord = new JPanel();
        panelRecord.setBackground(new Color(245, 145, 145));
        panelRecord.setBounds(475, 50, 365, 165);
        panelRecord.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        // --- Labels ---
        label = new JLabel("Add Sales");
        label.setFont(new Font("Alice", Font.BOLD, 25));
        label.setBounds(160, 15, 150, 50);

        label1 = new JLabel("Edit Sales");
        label1.setFont(new Font("Alice", Font.BOLD, 25));
        label1.setBounds(165, 5, 150, 50);

        label2 = new JLabel("Edit Sales");
        label2.setFont(new Font("Alice", Font.BOLD, 25));
        label2.setBounds(165, 5, 150, 50);

        label3 = new JLabel("Delete Sales");
        label3.setFont(new Font("Alice", Font.BOLD, 25));
        label3.setBounds(160, 15, 150, 50);

        lRecord = new JLabel("View Sales");
        lRecord.setFont(new Font("Alice", Font.BOLD, 25));
        lRecord.setBounds(95, 10, 300, 40);

        // --- Add Sales TextFields ---
        enClientName = new JTextField();
        enClientName.setText("Enter Client Name");
        enClientName.setBounds(100, 55, 250, 40);
        enClientName.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enClientName.isEnabled() && enClientName.getText().equals("Enter Client Name")) {
                    enClientName.setText("");
                }
            }
        });

        enDate = new JTextField();
        enDate.setText("Enter Date (YYYY-MM-DD)");
        enDate.setBounds(100, 155, 250, 40);
        enDate.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enDate.isEnabled() && enDate.getText().equals("Enter Date (YYYY-MM-DD)")) {
                    enDate.setText("");
                }
            }
        });

        enTotal = new JTextField();
        enTotal.setText("enter Total");
        enTotal.setBounds(100, 105, 250, 40);
        enTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enTotal.isEnabled() && enTotal.getText().equals("enter Total")) {
                    enTotal.setText("");
                }
            }
        });

        // --- Edit Sales TextFields ---
        enClientName1 = new JTextField();
        enClientName1.setText("Enter Client Name");
        enClientName1.setBounds(100, 55, 250, 40);
        enClientName1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enClientName1.isEnabled() && enClientName1.getText().equals("Enter Client Name")) {
                    enClientName1.setText("");
                }
            }
        });

        enDate1 = new JTextField();
        enDate1.setText("Enter Date (YYYY-MM-DD)");
        enDate1.setBounds(100, 155, 250, 40);
        enDate1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enDate1.isEnabled() && enDate1.getText().equals("Enter Date (YYYY-MM-DD)")) {
                    enDate1.setText("");
                }
            }
        });

        enTotal1 = new JTextField();
        enTotal1.setText("enter Total");
        enTotal1.setBounds(100, 105, 250, 40);
        enTotal1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enTotal1.isEnabled() && enTotal1.getText().equals("enter Total")) {
                    enTotal1.setText("");
                }
            }
        });

        // --- Delete Sales TextField ---
        enDeleteSales = new JTextField();
        enDeleteSales.setText("Enter Sales index to Delete Sales: ");
        enDeleteSales.setBounds(103, 55, 250, 40);
        enDeleteSales.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enDeleteSales.isEnabled() && enDeleteSales.getText().equals("Enter Sales index to Delete Sales: ")) {
                    enDeleteSales.setText("");
                }
            }
        });

        enClientRecord = new JTextField();
        enClientRecord.setText("Enter Client's ID to Check Records: ");
        enClientRecord.setBounds(57, 55, 250, 40);
        enClientRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (enClientRecord.isEnabled() && enClientRecord.getText().equals("Enter Client's ID to Check Records: ")) {
                    enClientRecord.setText("");
                }
            }
        });

        // --- Buttons for Sales Actions ---
        sAdd = new JButton("Submit");
        sAdd.addActionListener(this);
        sAdd.setBounds(57, 205, 100, 40);

        // --- Navigation Buttons ---
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

        JSeparator sep1 = new JSeparator();
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

        JSeparator sep2 = new JSeparator();
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

        JSeparator sep3 = new JSeparator();
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

        // --- Main Sales Buttons ---
        viewSales = new JButton("View Sales");
        viewSales.addActionListener(this);
        viewSales.setBounds(225, 50, 125, 50);

        searchSales = new JButton("Search Sales");
        searchSales.addActionListener(this);
        searchSales.setBounds(225, 150, 125, 50);

        // --- Frame setup ---
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setTitle("Sales");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.add(panel);
        frame.setIconImage(icon.getImage());

        // --- Navigation Panel ---
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);

        // --- Main Sales Buttons ---
        frame.add(viewSales);
        frame.add(panelRecord);
        frame.add(searchSales);
        panelRecord.setVisible(false);
        panelRecord.add(lRecord);
        panelRecord.add(enClientRecord);
        panelRecord.add(submit3 = new JButton("Submit"));
        submit3.addActionListener(this);
        submit3.setBounds(100, 105, 100, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // --- Navigation ---
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
        if (e.getSource() == pas) {
            frame.dispose();
            MyProductsServices myProductsServices = new MyProductsServices();
        }
        if (e.getSource() == pos) {
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame();
        }

        // --- View Sales ---
        if (e.getSource() == viewSales) {
            panelRecord.setVisible(false);

            // Read AllSales.txt and show all sales records
            java.util.List<String> records = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("AllSales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not read AllSales.txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            if (records.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No sales records found.");
            return;
            }

            // Build a formatted record display
            StringBuilder sb = new StringBuilder();
            sb.append("========================================\n");
            sb.append("              ALL SALES RECORDS\n");
            sb.append("========================================\n");
            sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n", "Client ID", "Name", "Purchased Item(s)", "Price", "Date/Time"));
            sb.append("----------------------------------------------------------------------------------------------------------\n");
            for (String rec : records) {
            String clientID = "", name = "", items = "", price = "", date = "";
            if (rec.contains("|")) {
                String[] parts = rec.split("\\|");
                if (parts.length == 5) {
                clientID = parts[0].trim();
                name = parts[1].trim();
                items = parts[2].trim();
                price = parts[3].trim();
                date = parts[4].trim();
                } else if (parts.length == 4) {
                date = parts[0].trim();
                price = parts[1].trim();
                name = parts[2].trim();
                items = parts[3].trim();
                clientID = "";
                } else if (parts.length == 3) {
                date = parts[0].trim();
                price = parts[1].trim();
                name = parts[2].trim();
                items = "(POS Sale)";
                clientID = "";
                }
            } else if (rec.contains("Client:")) {
                String[] parts = rec.split(",");
                for (String part : parts) {
                if (part.trim().startsWith("Client:")) {
                    name = part.replace("Client:", "").trim();
                } else if (part.trim().startsWith("Total:")) {
                    price = part.replace("Total:", "").trim();
                } else if (part.trim().startsWith("Date:")) {
                    date = part.replace("Date:", "").trim();
                } else if (part.trim().startsWith("Items:")) {
                    items = part.replace("Items:", "").trim();
                } else if (part.trim().startsWith("ClientID:")) {
                    clientID = part.replace("ClientID:", "").trim();
                }
                }
                if (items.isEmpty()) items = "(Manual Sale)";
                if (clientID.isEmpty()) clientID = "";
            } else {
                name = rec;
            }

            // Split items by comma and print each service on a new line under the first
            String[] services = items.split(",");
            for (int i = 0; i < services.length; i++) {
                if (i == 0) {
                sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n",
                    clientID, name, services[i].trim(), price, date));
                } else {
                sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n",
                    "", "", services[i].trim(), "", ""));
                }
            }
            }
            sb.append("========================================");

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            textArea.setCaretPosition(0);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 300));

            JOptionPane.showMessageDialog(frame, scrollPane, "All Sales Records", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == searchSales) {
            panelRecord.setVisible(true);
        }

        if (e.getSource() == submit3) {
            String clientId = enClientRecord.getText().trim();
            if (clientId.isEmpty() || clientId.equals("Enter Client's ID to Check Records: ")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Client ID.");
            return;
            }

            // Read AllSales.txt and filter by clientId
            java.util.List<String> records = new java.util.ArrayList<>();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("AllSales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String clientID = "";
                if (line.contains("|")) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    clientID = parts[0].trim();
                    if (clientID.equalsIgnoreCase(clientId)) {
                    records.add(line);
                    }
                }
                } else if (line.contains("ClientID:")) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    if (part.trim().startsWith("ClientID:")) {
                    clientID = part.replace("ClientID:", "").trim();
                    break;
                    }
                }
                if (clientID.equalsIgnoreCase(clientId)) {
                    records.add(line);
                }
                }
            }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Could not read AllSales.txt", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            if (records.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No sales records found for Client ID: " + clientId);
            return;
            }

            // Build a formatted record display
            StringBuilder sb = new StringBuilder();
            sb.append("========================================\n");
            sb.append("              ALL SALES RECORDS\n");
            sb.append("========================================\n");
            sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n", "Client ID", "Name", "Purchased Item(s)", "Price", "Date/Time"));
            sb.append("----------------------------------------------------------------------------------------------------------\n");
            for (String rec : records) {
            String clientID = "", name = "", items = "", price = "", date = "";
            if (rec.contains("|")) {
                String[] parts = rec.split("\\|");
                if (parts.length == 5) {
                clientID = parts[0].trim();
                name = parts[1].trim();
                items = parts[2].trim();
                price = parts[3].trim();
                date = parts[4].trim();
                } else if (parts.length == 4) {
                date = parts[0].trim();
                price = parts[1].trim();
                name = parts[2].trim();
                items = parts[3].trim();
                clientID = "";
                } else if (parts.length == 3) {
                date = parts[0].trim();
                price = parts[1].trim();
                name = parts[2].trim();
                items = "(POS Sale)";
                clientID = "";
                }
            } else if (rec.contains("Client:")) {
                String[] parts = rec.split(",");
                for (String part : parts) {
                if (part.trim().startsWith("Client:")) {
                    name = part.replace("Client:", "").trim();
                } else if (part.trim().startsWith("Total:")) {
                    price = part.replace("Total:", "").trim();
                } else if (part.trim().startsWith("Date:")) {
                    date = part.replace("Date:", "").trim();
                } else if (part.trim().startsWith("Items:")) {
                    items = part.replace("Items:", "").trim();
                } else if (part.trim().startsWith("ClientID:")) {
                    clientID = part.replace("ClientID:", "").trim();
                }
                }
                if (items.isEmpty()) items = "(Manual Sale)";
                if (clientID.isEmpty()) clientID = "";
            } else {
                name = rec;
            }

            // Split items by comma and print each service on a new line under the first
            String[] services = items.split(",");
            for (int i = 0; i < services.length; i++) {
                if (i == 0) {
                sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n",
                    clientID, name, services[i].trim(), price, date));
                } else {
                sb.append(String.format("%-15s | %-15s | %-30s | %-10s | %-20s\n",
                    "", "", services[i].trim(), "", ""));
                }
            }
            }
            sb.append("========================================");

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            textArea.setCaretPosition(0);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 300));

            JOptionPane.showMessageDialog(frame, scrollPane, "All Sales Records", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
