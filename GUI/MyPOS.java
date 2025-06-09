import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

// ===================== CLASS DEFINITION =====================
public class MyPOS implements ActionListener {

    // ===================== FIELDS =====================
    JFrame frame = new JFrame();
    JButton dash, clients, petdet, appoint, pas, alls, pos, add, submit3, logout;
    JPanel panel, panelViewPOS;
    JTextField enPOS;
    JLabel lPOS;

    // ===================== CONSTRUCTOR =====================
    MyPOS() {

        // ----- Label -----
        lPOS = new JLabel("View POS");
        lPOS.setFont(new Font("Alice", Font.BOLD, 25));
        lPOS.setBounds(95, 10, 300, 40);

        // ----- Text Field -----
        enPOS = new JTextField();
        enPOS.setText("Enter Client's ID to view POS: ");
        enPOS.setBounds(57, 55, 250, 40);
        enPOS.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enPOS.isEnabled() && enPOS.getText().equals("Enter Client's ID to view POS: ")) {
                enPOS.setText("");
            }
            }
        });

        // ----- Panel for View POS -----
        panelViewPOS = new JPanel();
        panelViewPOS.setBackground(new Color(245, 145, 145));
        panelViewPOS.setBounds(475, 50, 365, 165);
        panelViewPOS.setLayout(null);

        // ----- Submit Button -----
        submit3 = new JButton("submit");
        submit3.addActionListener(this);
        submit3.setBounds(57, 115, 100, 40);

        // ----- Side Panel -----
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        // ----- Navigation Buttons -----
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

        

        // ----- Frame Setup -----
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setTitle("Dashboard");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setVisible(true);
        frame.add(panel);
        

        // ----- Add Buttons to Panel -----
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);

        // ----- Add Components to View POS Panel -----
        panelViewPOS.add(enPOS);
        panelViewPOS.add(lPOS);
        panelViewPOS.add(submit3);
        panelViewPOS.setVisible(true);
        frame.add(panelViewPOS);
    }

    // ===================== EVENT HANDLING =====================
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
        if (e.getSource() == pas) {
            frame.dispose();
            MyProductsServices myProductsServices = new MyProductsServices();
        }
        if (e.getSource() == alls) {
            frame.dispose();
            MySales mySales = new MySales();
        }
        if (e.getSource() == submit3) {
            String clientId = enPOS.getText().trim();
            if (clientId.isEmpty() || clientId.equals("Enter Client's ID to view POS: ")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Client ID.");
            return;
            }

            Client selectedClient = ClientManager.findClientbyId(clientId);
            if (selectedClient == null) {
            JOptionPane.showMessageDialog(frame, "Client ID not found.");
            return;
            }

            List<MyServices.Services> availableServices = ServicesManager.getServices();
            if (availableServices.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No services available.");
            return;
            }

            List<MyServices.Services> selectedServices = new ArrayList<>();
            double total = 0;
            boolean adding = true;

            while (adding) {
            // Allow multiple service selection using JList
            String[] serviceNames = new String[availableServices.size()];
            for (int i = 0; i < availableServices.size(); i++) {
                serviceNames[i] = availableServices.get(i).getName() + " - ₱" + String.format("%.2f", availableServices.get(i).getPrice());
            }
            JList<String> serviceList = new JList<>(serviceNames);
            serviceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(serviceList);
            scrollPane.setPreferredSize(new Dimension(300, 150));
            int option = JOptionPane.showConfirmDialog(
                frame,
                scrollPane,
                "Select service(s):",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            if (option != JOptionPane.OK_OPTION || serviceList.getSelectedIndices().length == 0) {
                if (selectedServices.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Transaction cancelled or no service selected.");
                return;
                } else {
                break;
                }
            }

            int[] selectedIndices = serviceList.getSelectedIndices();
            StringBuilder addedNames = new StringBuilder();
            for (int idx : selectedIndices) {
                MyServices.Services s = availableServices.get(idx);
                selectedServices.add(s);
                total += s.getPrice();
                addedNames.append(s.getName()).append(" (₱").append(String.format("%.2f", s.getPrice())).append("), ");
            }

            int addMore = JOptionPane.showConfirmDialog(
                frame,
                "Added: " + (addedNames.length() > 2 ? addedNames.substring(0, addedNames.length() - 2) : "None") +
                "\nCurrent total: ₱" + String.format("%.2f", total) +
                "\nDo you want to add more services?",
                "Add More Services",
                JOptionPane.YES_NO_OPTION
            );
            if (addMore != JOptionPane.YES_OPTION) {
                adding = false;
            }
            }

            // Prepare services string for receipt (each service on a new line if more than one)
            StringBuilder servicesStr = new StringBuilder();
            for (int i = 0; i < selectedServices.size(); i++) {
            MyServices.Services s = selectedServices.get(i);
            servicesStr.append(s.getName()).append(" (₱").append(String.format("%.2f", s.getPrice())).append(")");
            if (i < selectedServices.size() - 1) {
                servicesStr.append("\n");
            }
            }

            // Payment input
            double payment = 0;
            while (true) {
            String payStr = JOptionPane.showInputDialog(frame, "Total: ₱" + String.format("%.2f", total) + "\nEnter payment amount:");
            if (payStr == null) {
                JOptionPane.showMessageDialog(frame, "Transaction cancelled.");
                return;
            }
            try {
                payment = Double.parseDouble(payStr.trim());
                if (payment < total) {
                JOptionPane.showMessageDialog(frame, "Insufficient payment. Please enter at least ₱" + String.format("%.2f", total));
                } else if (payment < 0) {
                JOptionPane.showMessageDialog(frame, "Negative payment not allowed.");
                } else {
                break;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number.");
            }
            }
            double change = payment - total;

            // Receipt
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            String dateTime = now.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            StringBuilder receipt = new StringBuilder();
            receipt.append("Date & Time: ").append(dateTime).append("\n");
            receipt.append("Client ID: ").append(selectedClient.getId()).append("\n");
            receipt.append("Client Name: ").append(selectedClient.getName()).append("\n");
            receipt.append("Services:\n").append(servicesStr).append("\n");
            receipt.append("Total: ₱").append(String.format("%.2f", total)).append("\n");
            receipt.append("Payment: ₱").append(String.format("%.2f", payment)).append("\n");
            receipt.append("Change: ₱").append(String.format("%.2f", change)).append("\n");

            JOptionPane.showMessageDialog(frame, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);

            // Save to AllSales.txt in format: Client ID | Name | Purchased Item(s) | Price | Date/Time
            try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("AllSales.txt", true))) {
            // Write the first line with all columns
            if (selectedServices.size() > 0) {
                MyServices.Services firstService = selectedServices.get(0);
                String line = String.format("%-15s| %-15s| %-30s| %-10s| %s",
                selectedClient.getId(),
                selectedClient.getName(),
                firstService.getName() + " (₱" + String.format("%.2f", firstService.getPrice()) + ")",
                String.format("₱%.2f", total),
                dateTime);
                bw.write(line);
                bw.newLine();

                // Write additional services (if any) on new lines, aligned to Services column
                for (int i = 1; i < selectedServices.size(); i++) {
                MyServices.Services s = selectedServices.get(i);
                // Add client ID for each extra service, leave name/price/date empty
                String serviceLine = String.format("%-15s| %-15s| %-30s| %-10s| %s",
                    selectedClient.getId(),
                    "",
                    s.getName() + " (₱" + String.format("%.2f", s.getPrice()) + ")",
                    "",
                    ""
                );
                bw.write(serviceLine);
                bw.newLine();
                }
            }
            } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error writing to AllSales.txt");
            }
        }
    }

    }

