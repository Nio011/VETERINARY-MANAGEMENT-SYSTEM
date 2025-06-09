import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyClients implements ActionListener {
    // Frame and Panels
    JFrame frame = new JFrame();
    JPanel panel, panad, panelSearch, panelDelete, panelEdit, panelEditProcess, panelAddPanel, panelAddPet;

    // Buttons
    JButton dash, clients, petdet, appoint, pas, alls, pos, logout, add, search, delete, edit, view, submit, submit2, sSearch, sDelete, sEdit, sEdit2;

    // Labels
    JLabel label, label2, label3, label4, label5, label6, label7;

    // TextFields
    JTextField on, ea, cn, on1, ea1, cn1, enSearch, enDelete, enEdit, enPetName, enSpecies, enBreed, enDateofBirth, enWeight;

    Appointment appt = null; 

    public MyClients() {
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        // Labels
        label = new JLabel("Add a Client");
        label.setFont(new Font("Alice", Font.BOLD, 25));
        label.setBounds(160, 15, 150, 50);

        label2 = new JLabel("Search Client");
        label2.setFont(new Font("Alice", Font.BOLD, 25));
        label2.setBounds(100, 5, 185, 50);

        label3 = new JLabel("Delete Client");
        label3.setFont(new Font("Alice", Font.BOLD, 25));
        label3.setBounds(100, 5, 185, 50);

        label4 = new JLabel("Edit Client");
        label4.setFont(new Font("Alice", Font.BOLD, 25));
        label4.setBounds(120, 5, 150, 50);

        label5 = new JLabel("Edit Client");
        label5.setFont(new Font("Alice", Font.BOLD, 25));
        label5.setBounds(120, 5, 150, 50);

        label6 = new JLabel("Edit Client");
        label6.setFont(new Font("Alice", Font.BOLD, 25));
        label6.setBounds(120, 5, 500, 500);

        label7 = new JLabel("Add Pet");
        label7.setFont(new Font("Alice", Font.BOLD, 25));
        label7.setBounds(185, 5, 150, 50);

        // TextFields for Add Client
        on = new JTextField();
        on.setText("Owner Name");
        on.setBounds(105, 75, 250, 40);
        on.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (on.isEnabled() && on.getText().equals("Owner Name")) {
                on.setText("");
            }
            }
        });

        ea = new JTextField();
        ea.setText("Email Address");
        ea.setBounds(105, 125, 250, 40);
        ea.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (ea.isEnabled() && ea.getText().equals("Email Address")) {
                ea.setText("");
            }
            }
        });

        cn = new JTextField();
        cn.setText("Contact Number");
        cn.setBounds(105, 175, 250, 40);
        cn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (cn.isEnabled() && cn.getText().equals("Contact Number")) {
                cn.setText("");
            }
            }
        });

        // TextFields for Edit Client
        on1 = new JTextField();
        on1.setText("Owner Name");
        on1.setBounds(105, 75, 250, 40);
        on1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (on1.isEnabled() && on1.getText().equals("Owner Name")) {
                on1.setText("");
            }
            }
        });

        ea1 = new JTextField();
        ea1.setText("Email Address");
        ea1.setBounds(105, 125, 250, 40);
        ea1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (ea1.isEnabled() && ea1.getText().equals("Email Address")) {
                ea1.setText("");
            }
            }
        });

        cn1 = new JTextField();
        cn1.setText("Contact Number");
        cn1.setBounds(105, 175, 250, 40);
        cn1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (cn1.isEnabled() && cn1.getText().equals("Contact Number")) {
                cn1.setText("");
            }
            }
        });

        // Buttons for Submit/Edit/Delete/Search
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(105, 225, 100, 40);

        sSearch = new JButton("Submit");
        sSearch.addActionListener(this);
        sSearch.setBounds(57, 115, 100, 40);

        sDelete = new JButton("Delete");
        sDelete.addActionListener(this);
        sDelete.setBounds(57, 115, 100, 40);

        sEdit = new JButton("Edit");
        sEdit.addActionListener(this);
        sEdit.setBounds(57, 115, 100, 40);

        sEdit2 = new JButton("Edit");
        sEdit2.addActionListener(this);
        sEdit2.setBounds(105, 225, 100, 40);

        // Panels
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 224));
        panel.setBounds(0, 0, 200, 500);
        panel.setLayout(null);

        panad = new JPanel();
        panad.setBackground(new Color(245, 145, 145));
        panad.setBounds(475, 50, 450, 350);
        panad.setLayout(null);

        panelSearch = new JPanel();
        panelSearch.setBackground(new Color(245, 145, 145));
        panelSearch.setBounds(475, 50, 365, 165);
        panelSearch.setLayout(null);

        panelDelete = new JPanel();
        panelDelete.setBackground(new Color(245, 145, 145));
        panelDelete.setBounds(475, 50, 365, 165);
        panelDelete.setLayout(null);

        panelEdit = new JPanel();
        panelEdit.setBackground(new Color(245, 145, 145));
        panelEdit.setBounds(475, 50, 365, 165);
        panelEdit.setLayout(null);

        panelEditProcess = new JPanel();
        panelEditProcess.setBackground(new Color(245, 145, 145));
        panelEditProcess.setBounds(475, 50, 450, 350);
        panelEditProcess.setLayout(null);

        panelAddPet = new JPanel();
        panelAddPet.setBackground(new Color(245, 145, 145));
        panelAddPet.setBounds(475, 50, 450, 350);
        panelAddPet.setLayout(null);

        // TextFields for Search/Delete/Edit
        enSearch = new JTextField();
        enSearch.setText("Enter Client's ID to search: ");
        enSearch.setBounds(57, 55, 250, 40);
        enSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enSearch.isEnabled() && enSearch.getText().equals("Enter Client's ID to search: ")) {
                enSearch.setText("");
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

        // TextFields for Add Pet
        enPetName = new JTextField();
        enPetName.setText("Pet Name");
        enPetName.setBounds(100, 55, 250, 40);
        enPetName.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enPetName.isEnabled() && enPetName.getText().equals("Pet Name")) {
                enPetName.setText("");
            }
            }
        });

        enSpecies = new JTextField();
        enSpecies.setText("Species(Dog/Cat)");
        enSpecies.setBounds(100, 105, 250, 40);
        enSpecies.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enSpecies.isEnabled() && enSpecies.getText().equals("Species(Dog/Cat)")) {
                enSpecies.setText("");
            }
            }
        });

        enBreed = new JTextField();
        enBreed.setText("Breed");
        enBreed.setBounds(100, 155, 250, 40);
        enBreed.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enBreed.isEnabled() && enBreed.getText().equals("Breed")) {
                enBreed.setText("");
            }
            }
        });

        enDateofBirth = new JTextField();
        enDateofBirth.setText("Date of Birth");
        enDateofBirth.setBounds(100, 205, 250, 40);
        enDateofBirth.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDateofBirth.isEnabled() && enDateofBirth.getText().equals("Date of Birth")) {
                enDateofBirth.setText("");
            }
            }
        });

        enWeight = new JTextField();
        enWeight.setText("Weight in kg");
        enWeight.setBounds(100, 255, 250, 40);
        enWeight.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enWeight.isEnabled() && enWeight.getText().equals("Weight in kg")) {
                enWeight.setText("");
            }
            }
        });

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

        // Main Action Buttons
        add = new JButton("Add Client");
        add.addActionListener(this);
        add.setBounds(225, 50, 100, 50);

        search = new JButton("Search Client");
        search.addActionListener(this);
        search.setBounds(225, 125, 150, 50);

        delete = new JButton("Delete Client");
        delete.addActionListener(this);
        delete.setBounds(225, 200, 150, 50);

        edit = new JButton("Edit Client");
        edit.addActionListener(this);
        edit.setBounds(225, 275, 150, 50);

        view = new JButton("View Clients");
        view.addActionListener(this);
        view.setBounds(225, 350, 150, 50);

        submit2 = new JButton("Submit");
        submit2.addActionListener(this);
        submit2.setBounds(100, 300, 100, 40);

        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setTitle("Clients");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setIconImage(icon.getImage());

        // Add panels and components to frame
        frame.add(panel);
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);

        frame.add(add);
        frame.add(search);
        frame.add(delete);
        frame.add(edit);
        frame.add(view);

        frame.add(panad);
        panad.add(label);
        panad.add(on);
        panad.add(ea);
        panad.add(cn);
        panad.add(submit);
        panad.setVisible(false);

        frame.add(panelSearch);
        panelSearch.add(enSearch);
        panelSearch.setVisible(false);
        panelSearch.add(sSearch);
        panelSearch.add(label2);

        frame.add(panelDelete);
        panelDelete.setVisible(false);
        panelDelete.add(enDelete);
        panelDelete.add(sDelete);
        panelDelete.add(label3);

        frame.add(panelEdit);
        panelEdit.setVisible(false);
        panelEdit.add(enEdit);
        panelEdit.add(sEdit);
        panelEdit.add(label4);

        frame.add(panelEditProcess);
        panelEditProcess.setVisible(false);
        panelEditProcess.add(label5);
        panelEditProcess.add(on1);
        panelEditProcess.add(ea1);
        panelEditProcess.add(cn1);
        panelEditProcess.add(sEdit2);

        frame.add(panelAddPet);
        panelAddPet.setVisible(false);
        panelAddPet.add(label7);
        panelAddPet.add(submit2);
        panelAddPet.add(enPetName);
        panelAddPet.add(enDateofBirth);
        panelAddPet.add(enBreed);
        panelAddPet.add(enSpecies);
        panelAddPet.add(enWeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Navigation
        if (e.getSource() == dash) {
            frame.dispose();
            MyDashboard myDashboard = new MyDashboard();
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
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame(); // Return to main frame
        }
        if (e.getSource() == logout) {
            frame.dispose();
            new MyFrame();
        }   

        // Show/Hide Panels
        if (e.getSource() == add) {
            panelSearch.setVisible(false);
            panelDelete.setVisible(false);
            panelEdit.setVisible(false);
            panelAddPet.setVisible(false);
            panad.setVisible(true);
        }
        if (e.getSource() == search) {
            panad.setVisible(false);
            panelDelete.setVisible(false);
            panelEdit.setVisible(false);
            panelAddPet.setVisible(false);
            panelSearch.setVisible(true);
        }
        if (e.getSource() == delete) {
            panad.setVisible(false);
            panelSearch.setVisible(false);
            panelEdit.setVisible(false);
            panelAddPet.setVisible(false);
            panelDelete.setVisible(true);
        }
        if (e.getSource() == edit) {
            panad.setVisible(false);
            panelSearch.setVisible(false);
            panelDelete.setVisible(false);
            panelAddPet.setVisible(false);
            panelEdit.setVisible(true);
        }

        // Add Client
        if (e.getSource() == submit) {
            String name = on.getText().trim();
            String email = ea.getText().trim();
            String contactNum = cn.getText().trim();

            if (name.isEmpty() || email.isEmpty() || contactNum.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
            } else {
                String id = ClientManager.generateClientId();
                Client client = new Client(id, name, email, contactNum);

                // Add to list and save
                ClientManager.clients.add(client);
                ClientManager.saveClient(client);

                ClientManager.lastAddedClient = client;

                JOptionPane.showMessageDialog(frame, "Client added successfully:\nName: " + name + "\nEmail: " + email + "\nContact: " + contactNum + "\nClient ID: " + id);
                panad.setVisible(false);

                
                panelAddPet.setVisible(true);
            }
        }

        // Add Pet
        if (e.getSource() == submit2) {
            String petName = enPetName.getText().trim();
            String species = enSpecies.getText().trim();
            String breed = enBreed.getText().trim();
            String dateOfBirth = enDateofBirth.getText().trim();
            String weightStr = enWeight.getText().trim();

            // Validate fields
            if (petName.isEmpty() || species.isEmpty() || breed.isEmpty() || dateOfBirth.isEmpty() || weightStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
                return;
            }

            // Validate date format
            java.time.LocalDate dob;
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy");
                dob = java.time.LocalDate.parse(dateOfBirth, formatter);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use MM/dd/yyyy.");
                return;
            }

            // Validate weight
            double weight;
            try {
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid weight format.");
                return;
            }

            // Get last added client
            Client client = ClientManager.lastAddedClient;
            if (client == null) {
                JOptionPane.showMessageDialog(frame, "No client found to add pet. Please add a client first.");
                return;
            }

            Animal animal;
            if (species.equalsIgnoreCase("Dog")) {
                animal = new Dog(client.getId(), client.getName(), petName, breed, dob, weight);
            } else if (species.equalsIgnoreCase("Cat")) {
                animal = new Cat(client.getId(), client.getName(), petName, breed, dob, weight);
            } else {
                JOptionPane.showMessageDialog(frame, "Species not supported. Please enter Dog or Cat.");
                return;
            }

            // Add animal to the list and save
            if (AnimalManager.animals == null) {
                AnimalManager.animals = new java.util.ArrayList<>();
            }
            AnimalManager.animals.add(animal);
            try {
                AnimalManager.saveToFile();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving pet: " + ex.getMessage());
            }

            JOptionPane.showMessageDialog(frame, "Pet added successfully:\nName: " + petName + "\nSpecies: " + species + "\nBreed: " + breed + "\nDOB: " + dateOfBirth + "\nWeight: " + weight);
            panelAddPet.setVisible(false);

            // Optionally reset fields
            enPetName.setText("Pet Name");
            enSpecies.setText("Species(Dog/Cat)");
            enBreed.setText("Breed");
            enDateofBirth.setText("Date of Birth");
            enWeight.setText("Weight in kg");
        }

        // Search Client
        if (e.getSource() == search) {
            panelSearch.setVisible(true);
        }
        if (e.getSource() == sSearch) {
            if (enSearch.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a Client ID to search");
            } else {
                String searchId = enSearch.getText().trim();
                Client foundClient = ClientManager.findClientbyId(searchId);

                if (foundClient != null) {
                    panelSearch.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Client found:\nID: " + foundClient.getId() + "\nName: " + foundClient.getName() + "\nEmail: " + foundClient.getEmail() + "\nContact: " + foundClient.getContactNum());
                } else {
                    panelSearch.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "No client found with ID: " + searchId);
                }
            }
        }

        // Delete Client
        if (e.getSource() == delete) {
            panelDelete.setVisible(true);
        }
        if (e.getSource() == sDelete) {
            if (enDelete.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a Client ID to delete");
            } else {
            String deleteId = enDelete.getText().trim();
            Client foundClient = ClientManager.findClientbyId(deleteId);

            if (foundClient != null) {
                int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete client " + foundClient.getName() + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                // Delete client
                ClientManager.deleteClientById(deleteId);

                // Remove all animals belonging to this client and update animals.txt
                if (AnimalManager.animals != null) {
                    AnimalManager.animals.removeIf(animal -> animal.getOwnerId().equals(deleteId));
                }
                try {
                    AnimalManager.saveToFile();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating animals.txt: " + ex.getMessage());
                }

                // Also use the new deleteAnimalbyClientid logic
                String animalDeleteMsg = AnimalManager.deleteAnimalbyClientid(deleteId);

                panelDelete.setVisible(false);
                JOptionPane.showMessageDialog(frame, "Client and their pets deleted successfully:\nID: " + foundClient.getId() + "\nName: " + foundClient.getName() + "\n" + animalDeleteMsg);
                } else {
                JOptionPane.showMessageDialog(frame, "Deletion cancelled.");
                }
            } else {
                panelDelete.setVisible(false);
                JOptionPane.showMessageDialog(frame, "No client found with ID: " + deleteId);
            }
            }
        }

        // Edit Client
        if (e.getSource() == edit) {
            panelEdit.setVisible(true);
        }
        if (e.getSource() == sEdit) {
            if (enEdit.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a Client ID to edit");
            } else {
                String editId = enEdit.getText().trim();
                Client foundClient = ClientManager.findClientbyId(editId);

                if (foundClient != null) {
                    panelEdit.setVisible(false);
                    panelEditProcess.setVisible(true);
                    // Pre-fill the fields with current client data
                    on1.setText(foundClient.getName());
                    ea1.setText(foundClient.getEmail());
                    cn1.setText(foundClient.getContactNum());
                } else {
                    JOptionPane.showMessageDialog(frame, "No client found with ID: " + editId);
                }
            }
        }
        if (e.getSource() == sEdit2) {
            String newName = on1.getText().trim();
            String newEmail = ea1.getText().trim();
            String newContactNum = cn1.getText().trim();
            String editId = enEdit.getText().trim();

            if (newName.isEmpty() || newEmail.isEmpty() || newContactNum.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
            } else {
                // Find the client and update its fields directly
                Client foundClient = ClientManager.findClientbyId(editId);
                if (foundClient != null) {
                    foundClient.setName(newName);
                    foundClient.setEmail(newEmail);
                    foundClient.setContactNum(newContactNum);
                    // Save all clients to file
                    ClientManager.saveAllClientsToFile();
                    panelEditProcess.setVisible(false);
                    // Optionally reset fields
                    on1.setText("Owner Name");
                    ea1.setText("Email Address");
                    cn1.setText("Contact Number");
                    JOptionPane.showMessageDialog(frame, "Client updated successfully:\nID: " + editId + "\nName: " + newName + "\nEmail: " + newEmail + "\nContact: " + newContactNum);
                } else {
                    JOptionPane.showMessageDialog(frame, "No client found with ID: " + editId);
                }
            }
        }

        // View Clients
        if (e.getSource() == view) {
            panelSearch.setVisible(false);
            panelDelete.setVisible(false);
            panelEdit.setVisible(false);
            panelAddPet.setVisible(false);
            panad.setVisible(false);

            // Always reload the client list from file before displaying
            ClientManager.reloadClientsFromFile();

            java.util.List<Client> clients = ClientManager.clients;
            if (clients == null || clients.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No clients found.");
                return;
            }

            // Build a plain text table with better alignment
            StringBuilder clientList = new StringBuilder();
            String format = "%-12s %-20s %-30s %-15s%n";
            clientList.append(String.format(format, "ID", "Name", "Email", "Contact No."));
            clientList.append("-------------------------------------------------------------------------------\n");
            for (Client c : clients) {
                clientList.append(String.format(format,
                        c.getId(),
                        c.getName(),
                        c.getEmail(),
                        c.getContactNum()
                ));
            }

            JTextArea textArea = new JTextArea(clientList.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            textArea.setCaretPosition(0);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 300));

            JOptionPane.showMessageDialog(frame, scrollPane, "Client List", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
