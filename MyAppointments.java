//GUI for managing appointments 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyAppointments implements ActionListener {
    // Frame and Panels
    JFrame frame = new JFrame();
    JPanel panel, panelAddAppoinment, panelEdit, panelEditProcess, panelDelete;

    // Buttons
    JButton dash, clients, petdet, appoint, pas, alls, pos,logout;
    JButton addAppoint, sAppointment, sEditB, sEditB2, editAppoint, viewAppointments, sDelete, deleteAppointments;

    // Text Fields
    JTextField enClientId, enName, enSpecies, enContact, enEdit, enName1, enSpecies1, enContact1, enDelete, enDate, enDate2,enTime,enTime2;

    // Labels
    JLabel lAppointment, lEditAp, lEditAp2, lDeleteAp;

    // Combo Boxes
    JComboBox enReason, enReason1;

    Appointment appt= null; 

    MyAppointments() {
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        // Side Panel
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        // Add Appointment Panel
        panelAddAppoinment = new JPanel();
        panelAddAppoinment.setBackground(new Color(245, 145, 145));
        panelAddAppoinment.setBounds(475, 25, 450, 425);
        panelAddAppoinment.setLayout(null);

        // Edit Panel
        panelEdit = new JPanel();
        panelEdit.setBackground(new Color(245, 145, 145));
        panelEdit.setBounds(475, 50, 365, 165);
        panelEdit.setLayout(null);

        // Edit Process Panel
        panelEditProcess = new JPanel();
        panelEditProcess.setBackground(new Color(245, 145, 145));
        panelEditProcess.setBounds(475, 25, 450, 425);
        panelEditProcess.setLayout(null);

        // Delete Panel
        panelDelete = new JPanel();
        panelDelete.setBackground(new Color(245, 145, 145));
        panelDelete.setBounds(475, 50, 365, 165);
        panelDelete.setLayout(null);

        // Labels
        lEditAp = new JLabel("Edit Appointment");
        lEditAp.setFont(new Font("Alice", Font.BOLD, 25));
        lEditAp.setBounds(95, 10, 300, 40);

        lEditAp2 = new JLabel("Edit Appointment");
        lEditAp2.setFont(new Font("Alice", Font.BOLD, 25));
        lEditAp2.setBounds(135, 10, 300, 40);

        lDeleteAp = new JLabel("Delete Client");
        lDeleteAp.setFont(new Font("Alice", Font.BOLD, 25));
        lDeleteAp.setBounds(100, 5, 185, 50);

        lAppointment = new JLabel("Add Appointment");
        lAppointment.setFont(new Font("Alice", Font.BOLD, 25));
        lAppointment.setBounds(130, 15, 250, 50);

        // Buttons for panels
        sDelete = new JButton("Delete");
        sDelete.addActionListener(this);
        sDelete.setBounds(57, 115, 100, 40);

        sEditB = new JButton("Edit");
        sEditB.addActionListener(this);
        sEditB.setBounds(57, 100, 75, 40);

        sEditB2 = new JButton("Edit");
        sEditB2.addActionListener(this);
        sEditB2.setBounds(105, 375, 100, 40);

        sAppointment = new JButton("Submit");
        sAppointment.addActionListener(this);
        sAppointment.setBounds(105, 375, 75, 40);
        sAppointment.setFocusable(true);

        // Text Fields
        enEdit = new JTextField();
        enEdit.setText("Enter Client's ID to Edit: ");
        enEdit.setBounds(57, 55, 250, 40);
        enEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enEdit.isEnabled() && enEdit.getText().equals("Enter Client's ID to Edit: ")) {
                enEdit.setText("");
            }
            }
        });

        enClientId = new JTextField("Client ID");
        enClientId.setBounds(105, 75, 250, 40);
        enClientId.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enClientId.isEnabled() && enClientId.getText().equals("Client ID")) {
                enClientId.setText("");
            }
            }
        });

        enName = new JTextField("Name");
        enName.setBounds(105, 75, 250, 40);
        enName.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enName.isEnabled() && enName.getText().equals("Name")) {
                enName.setText("");
            }
            }
        });

        enSpecies = new JTextField("Species");
        enSpecies.setBounds(105, 125, 250, 40);
        enSpecies.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enSpecies.isEnabled() && enSpecies.getText().equals("Species")) {
                enSpecies.setText("");
            }
            }
        });

        enContact = new JTextField("Contact");
        enContact.setBounds(105, 175, 250, 40);
        enContact.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enContact.isEnabled() && enContact.getText().equals("Contact")) {
                enContact.setText("");
            }
            }
        });
        enDate = new JTextField("Date (MM/DD/YYYY)");
        enDate.setBounds(105, 225, 250, 40);
        enDate.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDate.isEnabled() && enDate.getText().equals("Date (MM/DD/YYYY)")) {
                enDate.setText("");
            }
            }
        });
        enTime = new JTextField("Time (HH:MM AM/PM)");
        enTime.setBounds(105, 275, 250, 40);
        enTime.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enTime.isEnabled() && enTime.getText().equals("Time (HH:MM AM/PM)")) {
                enTime.setText("");
            }
            }
        });

        enName1 = new JTextField("Name");
        enName1.setBounds(105, 75, 250, 40);
        enName1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enName1.isEnabled() && enName1.getText().equals("Name")) {
                enName1.setText("");
            }
            }
        });

        enSpecies1 = new JTextField("Species");
        enSpecies1.setBounds(105, 125, 250, 40);
        enSpecies1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enSpecies1.isEnabled() && enSpecies1.getText().equals("Species")) {
                enSpecies1.setText("");
            }
            }
        });

        enContact1 = new JTextField("Contact");
        enContact1.setBounds(105, 175, 250, 40);
        enContact1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enContact1.isEnabled() && enContact1.getText().equals("Contact")) {
                enContact1.setText("");
            }
            }
        });
        enDate2 = new JTextField("Date (MM/DD/YYYY)");
        enDate2.setBounds(105, 225, 250, 40);
        enDate2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDate2.isEnabled() && enDate2.getText().equals("Date (MM/DD/YYYY)")) {
                enDate2.setText("");
            }
            }
        });
        enTime2 = new JTextField("Time (HH:MM AM/PM)");
        enTime2.setBounds(105, 275, 250, 40);
        enTime2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enTime2.isEnabled() && enTime2.getText().equals("Time (HH:MM AM/PM)")) {
                enTime2.setText("");
            }
            }
        });

        enDelete = new JTextField();
        enDelete.setText("Enter Client's ID to Delete: ");
        enDelete.setBounds(57, 55, 250, 40);
        enDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDelete.isEnabled() && enDelete.getText().equals("Enter Client's ID to Delete: ")) {
                enDelete.setText("");
            }
            }
        });

        // Combo Boxes
        enReason = new JComboBox(new String[]{
            "Consultation", "Vaccination", "Laboratory", "Grooming", "Surgery"
        });
        enReason.setBounds(105, 325, 250, 40);

        enReason1 = new JComboBox(new String[]{
            "Consultation", "Vaccination", "Laboratory", "Grooming", "Surgery"
        });
        enReason1.setBounds(105, 325, 250, 40);

        // Navigation Buttons
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
        appoint.setForeground(Color.RED);
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

        // Main Action Buttons
        addAppoint = new JButton("Add Appointment");
        addAppoint.addActionListener(this);
        addAppoint.setBounds(225, 50, 150, 50);

        editAppoint = new JButton("Edit Appointment");
        editAppoint.addActionListener(this);
        editAppoint.setBounds(225, 150, 150, 50);

        viewAppointments = new JButton("View Appointments");
        viewAppointments.addActionListener(this);
        viewAppointments.setBounds(225, 250, 150, 50);

        deleteAppointments = new JButton("Delete Appointment");
        deleteAppointments.addActionListener(this);
        deleteAppointments.setBounds(225, 350, 150, 50);

        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setTitle("Appointments");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setIconImage(icon.getImage());

        // Add panels and buttons to frame
        frame.add(panel);
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);

        frame.add(addAppoint);
        frame.add(editAppoint);
        frame.add(viewAppointments);
        frame.add(deleteAppointments);

        // Add Appointment Panel
        frame.add(panelAddAppoinment);
        panelAddAppoinment.setVisible(false);
        panelAddAppoinment.add(enName);
        panelAddAppoinment.add(enSpecies);
        panelAddAppoinment.add(enContact);
        panelAddAppoinment.add(enDate);
        panelAddAppoinment.add(enTime);
        panelAddAppoinment.add(enReason);
        panelAddAppoinment.add(sAppointment);
        panelAddAppoinment.add(lAppointment);
        

        // Edit Panel
        frame.add(panelEdit);
        panelEdit.add(lEditAp);
        panelEdit.add(enEdit);
        panelEdit.add(sEditB);
        panelEdit.setVisible(false);

        // Edit Process Panel
        frame.add(panelEditProcess);
        panelEditProcess.add(lEditAp2);
        panelEditProcess.add(enName1);
        panelEditProcess.add(enSpecies1);
        panelEditProcess.add(enContact1);
        panelEditProcess.add(enDate2);
        panelEditProcess.add(enTime2);
        panelEditProcess.add(enReason1);
        panelEditProcess.add(sEditB2);
        panelEditProcess.setVisible(false);

        // Delete Panel
        frame.add(panelDelete);
        panelDelete.add(lDeleteAp);
        panelDelete.add(enDelete);
        panelDelete.add(sDelete);
        panelDelete.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Navigation
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
        if (e.getSource() == pas) {
            frame.dispose();
            MyVetServices myVetServices = new MyVetServices();
        }
        if (e.getSource() == alls) {
            frame.dispose();
            MySales mySales = new MySales();
        }
        if (e.getSource() == pos) {
            frame.dispose();
            MyPos myPos = new MyPos();
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame(); // Return to main frame
        }

        // Add Appointment
        if (e.getSource() == addAppoint) {
            panelAddAppoinment.setVisible(true);
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);
            panelDelete.setVisible(false);
        }
        if (e.getSource() == sAppointment) {
            String name = enName.getText().trim();
            String species = enSpecies.getText().trim();
            String contactNum = enContact.getText().trim();
            String reason = enReason.getSelectedItem().toString().trim();
            String date = enDate.getText().trim();
            String time = enTime.getText().trim();
            String clientId = ""; // Placeholder for client ID logic

            // Simulate finding client data by name (replace with actual logic)
            String[] clientData = AppointmentManager.findClientDataByName(name);
            boolean isExistingClient = clientData != null;

            if (isExistingClient) {
            clientId = clientData[0];
            name = clientData[1];
            contactNum = clientData[2];
            // Optionally auto-fill fields in the UI
            enName.setText(name);
            enContact.setText(contactNum);
            JOptionPane.showMessageDialog(frame, "Client found! Auto-filled details:\nID: " + clientId + "\nName: " + name + "\nContact: " + contactNum);
            } else {
            clientId = AppointmentManager.generateAppointmentId();
            JOptionPane.showMessageDialog(frame, "Client not found. Proceeding as new client.\nGenerated ID: " + clientId);
            }

            // Validate date format MM/DD/YYYY
            if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(frame, "Please enter Date in MM/DD/YYYY format.");
            return;
            }
            // Validate time format HH:MM AM/PM
            if (!time.matches("(?i)\\d{2}:\\d{2}\\s?(AM|PM)")) {
            JOptionPane.showMessageDialog(frame, "Please enter Time in HH:MM AM/PM format (e.g., 02:30 PM).");
            return;
            }

            String record = clientId + " | " + name + " | " + species + " | " +
            date + " | " + time.toUpperCase() + " | " + contactNum + " | " + reason;

            try {
            AppointmentManager.appendAppointmentToFile(record);
            JOptionPane.showMessageDialog(frame, "Appointment booked successfully!\n" + record);
            panelAddAppoinment.setVisible(false);
            // Optionally clear fields
            enName.setText("");
            enSpecies.setText("");
            enContact.setText("");
            enReason.setSelectedIndex(0);
            enDate.setText("Date");
            enTime.setText("Time");
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error while booking appointment: " + ex.getMessage());
            }
        }
        if (e.getSource() == editAppoint) {
            panelEdit.setVisible(true);
            panelAddAppoinment.setVisible(false);
            panelEditProcess.setVisible(false);
            panelDelete.setVisible(false);
        }
        if (e.getSource() == sEditB) {
            String idToEdit = enEdit.getText().trim();
            if (idToEdit.isEmpty() || idToEdit.equals("Enter Client's ID to Edit: ")) {
                JOptionPane.showMessageDialog(frame, "Please enter a Client ID or Appointment ID to edit.");
                return;
            }

            // Try to find appointment by Client ID or Appointment ID
            String appointmentRecord = AppointmentManager.findAppointmentByIdOrClientId(idToEdit);
            if (appointmentRecord == null) {
                JOptionPane.showMessageDialog(frame, "No appointment found for ID: " + idToEdit);
                return;
            }

            // Split the record into fields (using " | " as delimiter)
            String[] appointmentData = appointmentRecord.trim().split(" \\| ");
            if (appointmentData.length < 7) {
                JOptionPane.showMessageDialog(frame, "Invalid appointment data format.");
                return;
            }
            // Fill the edit fields with the found data
            enName1.setText(appointmentData[1]);
            enSpecies1.setText(appointmentData[2]);
            enContact1.setText(appointmentData[5]);
            enDate2.setText(appointmentData[3]);
            enTime2.setText(appointmentData[4]);
            enReason1.setSelectedItem(appointmentData[6]);

            // Store the actual appointment ID for editing
            enEdit.setText(appointmentData[0]);

            panelEdit.setVisible(false);
            panelEditProcess.setVisible(true);
        }
        if (e.getSource() == sEditB2) {
            String idToEdit = enEdit.getText().trim();
            String name = enName1.getText().trim();
            String species = enSpecies1.getText().trim();
            String contactNum = enContact1.getText().trim();
            String reason = enReason1.getSelectedItem().toString().trim();
            String date = enDate2.getText().trim();
            String time = enTime2.getText().trim();

            // Validate input
            if (name.isEmpty() || species.isEmpty() || contactNum.isEmpty() || reason.isEmpty() || date.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled out.");
                return;
            }

            // Validate date format MM/DD/YYYY
            if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(frame, "Please enter Date in MM/DD/YYYY format.");
                return;
            }
            // Validate time format HH:MM AM/PM
            if (!time.matches("(?i)\\d{2}:\\d{2}\\s?(AM|PM)")) {
                JOptionPane.showMessageDialog(frame, "Please enter Time in HH:MM AM/PM format (e.g., 02:30 PM).");
                return;
            }

            // Always update the record, even if unchanged
            String updatedRecord = idToEdit + " | " + name + " | " + species + " | " +
                date + " | " + time.toUpperCase() + " | " +
                contactNum + " | " + reason;

            try {
                java.util.List<String> appointments = AppointmentManager.readAppointmentsFromFile();
                boolean found = false;
                for (int i = 0; i < appointments.size(); i++) {
                    String[] fields = appointments.get(i).split(" \\| ");
                    // Match by ID (first field)
                    if (fields.length > 0 && fields[0].trim().equals(idToEdit)) {
                        appointments.set(i, updatedRecord);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    // Write updated list back to file, even if no changes
                    java.util.List<Appointment> appointmentObjects = new java.util.ArrayList<>();
                    for (String line : appointments) {
                        String[] fields = line.split(" \\| ");
                        if (fields.length >= 7) {
                            Appointment appt = new Appointment(
                                fields[0].trim(),
                                fields[1].trim(),
                                fields[2].trim(),
                                fields[3].trim(),
                                fields[4].trim(),
                                fields[5].trim(),
                                fields[6].trim()
                            );
                            appointmentObjects.add(appt);
                        }
                    }
                    AppointmentManager.writeAppointmentsToFile(appointmentObjects);
                    JOptionPane.showMessageDialog(frame, "Appointment updated successfully!\n" + updatedRecord);
                    panelEditProcess.setVisible(false);
                    // Optionally clear fields
                    enName1.setText("Name");
                    enSpecies1.setText("Species");
                    enContact1.setText("Contact");
                    enReason1.setSelectedIndex(0);
                    enDate2.setText("Date");
                    enTime2.setText("Time");
                    enEdit.setText("Enter Client's ID to Edit: ");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update appointment for ID: " + idToEdit);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error while updating appointment: " + ex.getMessage());
            }
        }

        // View Appointments
        if (e.getSource() == viewAppointments) {
            panelAddAppoinment.setVisible(false);
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);
            panelDelete.setVisible(false);

            // Always reload latest data from appointments.txt to avoid duplicates or stale data
            java.util.List<String> appointments = AppointmentManager.readAppointmentsFromFile();
            if (appointments == null || appointments.isEmpty() || (appointments.size() == 1 && appointments.get(0).trim().isEmpty())) {
                JOptionPane.showMessageDialog(frame, "No appointments found.");
                return;
            }

            // Build a plain text table with better alignment
            StringBuilder appointmentList = new StringBuilder();
            String formatHeader = "%-12s %-20s %-15s %-12s %-10s %-18s %-30s%n";
            String formatRow = "%-12s %-20s %-15s %-12s %-10s %-18s %-30s%n";
            appointmentList.append(String.format(formatHeader, "ID", "Name", "Species", "Date", "Time", "Contact", "Reason"));
            appointmentList.append("-----------------------------------------------------------------------------------------------------------------------------\n");
            for (String appointment : appointments) {
                String[] fields = appointment.split(" \\| ");
                if (fields.length < 7) continue;
                appointmentList.append(String.format(formatRow,
                    fields[0].trim(), // ID
                    fields[1].trim(), // Name
                    fields[2].trim(), // Species
                    fields[3].trim(), // Date
                    fields[4].trim(), // Time
                    fields[5].trim(), // Contact
                    fields[6].trim()  // Reason
                ));
            }

            JTextArea textArea = new JTextArea(appointmentList.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            textArea.setCaretPosition(0);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(950, 340));

            JOptionPane.showMessageDialog(frame, scrollPane, "Appointment List (Live from appointments.txt)", JOptionPane.INFORMATION_MESSAGE);
        }

        // Delete Appointment
        if (e.getSource() == deleteAppointments) {
            panelDelete.setVisible(true);
            panelAddAppoinment.setVisible(false);
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);

        }
        if (e.getSource() == sDelete) {
            if (enDelete.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a Client ID or Appointment ID to delete.");
            } else {
                String deleteId = enDelete.getText().trim();
                String appointmentRecord = AppointmentManager.findAppointmentByIdOrClientId(deleteId);

                if (appointmentRecord != null) {
                    String[] fields = appointmentRecord.split(" \\| ");
                    String name = (fields.length > 1) ? fields[1].trim() : "";
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete appointment for " + name + "?\nID: " + deleteId,
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean deleted = AppointmentManager.deleteAppointmentByIdOrClientId(deleteId);
                        if (deleted) {
                            panelDelete.setVisible(false);
                            enDelete.setText("");
                            JOptionPane.showMessageDialog(frame, "Appointment deleted successfully:\nID: " + deleteId + "\nName: " + name);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to delete appointment for ID: " + deleteId);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Deletion cancelled.");
                    }
                } else {
                    panelDelete.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "No appointment found with ID: " + deleteId);
                }
            }
        }

        // Show updated appointment list after add or edit, not after delete
        if ((e.getSource() == sAppointment || e.getSource() == sEditB2)) {
            java.util.List<String> appointments = AppointmentManager.readAppointmentsFromFile();
            if (appointments != null && !appointments.isEmpty() && !(appointments.size() == 1 && appointments.get(0).trim().isEmpty())) {
                StringBuilder appointmentList = new StringBuilder();
                String formatHeader = "%-12s %-20s %-15s %-12s %-10s %-18s %-30s%n";
                String formatRow = "%-12s %-20s %-15s %-12s %-10s %-18s %-30s%n";
                appointmentList.append(String.format(formatHeader, "ID", "Name", "Species", "Date", "Time", "Contact", "Reason"));
                appointmentList.append("-----------------------------------------------------------------------------------------------------------------------------\n");
                for (String appointment : appointments) {
                    String[] fields = appointment.split(" \\| ");
                    if (fields.length < 7) continue;
                    appointmentList.append(String.format(formatRow,
                        fields[0].trim(), // ID
                        fields[1].trim(), // Name
                        fields[2].trim(), // Species
                        fields[3].trim(), // Date
                        fields[4].trim(), // Time
                        fields[5].trim(), // Contact
                        fields[6].trim()  // Reason
                    ));
                }
            }
        }
    }
}
