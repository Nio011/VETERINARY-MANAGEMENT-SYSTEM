//This is the part where you can view, edit, and manage pet details in the veterinary system. It allows you to view all pets, edit their details, and manage records.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPetDets implements ActionListener {
    JFrame frame = new JFrame();
    JButton dash,clients,petdet,appoint,pas,alls,pos, logout, view, add,submit2,sEditP,sEditP2,edit,submit3;
    JPanel panel,panelAddPet, panelEdit, panelEditProcess,panelRecord;
    JTextField on2,enEdit, enPetName, enSpecies, enBreed, enDateofBirth, enWeight,enClientRecord;
    JLabel label2, printPetDetails, lEditP, lEditP2, lRecord;
    Appointment appt= null; 

    MyPetDets(){
        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        panel = new JPanel();
        panel.setBackground(new Color(	245, 245, 224));
        panel.setBounds(0, 0, 200,500);
        panel.setLayout(null);

        panelEdit = new JPanel();
        panelEdit.setBackground(new Color(	245, 145, 145));
        panelEdit.setBounds(475, 50, 365,165);
        panelEdit.setLayout(null);

        panelEditProcess = new JPanel();
        panelEditProcess.setBackground(new Color(	245, 145, 145));
        panelEditProcess.setBounds(475, 50, 450,350);
        panelEditProcess.setLayout(null);

        panelRecord = new JPanel();
        panelRecord.setBackground(new Color(	104, 77, 244));
        panelRecord.setBounds(475, 50, 365,165);
        panelRecord.setLayout(null);

        lEditP = new JLabel("Edit Pet Details");
        lEditP.setFont(new Font("Alice", Font.BOLD, 25));
        lEditP.setBounds(95, 10, 300, 40);

        lRecord = new JLabel("View Record");
        lRecord.setFont(new Font("Alice", Font.BOLD, 25));
        lRecord.setBounds(95, 10, 300, 40);

        lEditP2 = new JLabel("Edit Pet Details");
        lEditP2.setFont(new Font("Alice", Font.BOLD, 25));
        lEditP2.setBounds(135, 10, 300, 40);

        sEditP = new JButton("Edit");
        sEditP.addActionListener(this);
        sEditP.setBounds(57,115,100,40);

        submit3 = new JButton("submit");
        submit3.addActionListener(this);
        submit3.setBounds(57,115,100,40);

        sEditP2 = new JButton("Edit");
        sEditP2.addActionListener(this);
        sEditP2.setBounds(100,300,100,40);

        enEdit = new JTextField();
        enEdit.setText("Enter Client's ID to Edit: ");
        enEdit.setBounds(57,55,250,40);
        enEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enEdit.isEnabled() && enEdit.getText().equals("Enter Client's ID to Edit: ")) {
                enEdit.setText("");
            }
            }
        });

        enClientRecord = new JTextField();
        enClientRecord.setText("Enter Client's ID to Check Records: ");
        enClientRecord.setBounds(57,55,250,40);
        enClientRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enClientRecord.isEnabled() && enClientRecord.getText().equals("Enter Client's ID to Check Records: ")) {
                enClientRecord.setText("");
            }
            }
        });

        enPetName=new JTextField();
        enPetName.setText("Pet Name");
        enPetName.setBounds(100,55,250,40);
        enPetName.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enPetName.isEnabled() && enPetName.getText().equals("Pet Name")) {
                enPetName.setText("");
            }
            }
        });

        enSpecies=new JTextField();
        enSpecies.setText("Species(Dog/Cat)");
        enSpecies.setBounds(100,105,250,40);
        enSpecies.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enSpecies.isEnabled() && enSpecies.getText().equals("Species(Dog/Cat)")) {
                enSpecies.setText("");
            }
            }
        });

        enBreed=new JTextField();
        enBreed.setText("Breed");
        enBreed.setBounds(100,155,250,40);
        enBreed.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enBreed.isEnabled() && enBreed.getText().equals("Breed")) {
                enBreed.setText("");
            }
            }
        });

        enDateofBirth=new JTextField();
        enDateofBirth.setText("Date of Birth");
        enDateofBirth.setBounds(100,205,250,40);
        enDateofBirth.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enDateofBirth.isEnabled() && enDateofBirth.getText().equals("Date of Birth")) {
                enDateofBirth.setText("");
            }
            }
        });

        enWeight=new JTextField();
        enWeight.setText("Weight in kg");
        enWeight.setBounds(100,255,250,40);
        enWeight.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            if (enWeight.isEnabled() && enWeight.getText().equals("Weight in kg")) {
                enWeight.setText("");
            }
            }
        });

        

        // Side Bar buttons
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
        petdet.setForeground(Color.RED);
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

        // Main buttons for pet details
        view = new JButton("View Pet Details");
        view.addActionListener(this);
        view.setBounds(225,50,125,50);

        edit = new JButton("Edit Pet Details");
        edit.addActionListener(this);
        edit.setBounds(225,125,125,50);

        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(236, 236, 211));
        frame.setIconImage(icon.getImage());

        frame.setTitle("Pet Details");


        frame.add(panel);
        panel.add(dash);
        panel.add(clients);
        panel.add(petdet);
        panel.add(appoint);
        panel.add(pas);
        panel.add(alls);
        panel.add(pos);
        panel.add(logout);
        frame.add(view);
        frame.add(edit);
        frame.add(panelEdit);

        panelEdit.add(lEditP);
        panelEdit.add(enEdit);
        panelEdit.add(sEditP);
        panelEdit.setVisible(false);

        frame.add(panelEditProcess);
        panelEditProcess.add(lEditP2);
        panelEditProcess.add(sEditP2);
        panelEditProcess.add(enPetName);
        panelEditProcess.add(enSpecies);
        panelEditProcess.add(enBreed);
        panelEditProcess.add(enDateofBirth);
        panelEditProcess.add(enWeight);
        panelEditProcess.setVisible(false);

        frame.add(panelRecord);
        panelRecord.add(enClientRecord);
        panelRecord.add(submit3);
        panelRecord.add(lRecord);
        panelRecord.setVisible(false);


        



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==dash){
            frame.dispose();
            MyDashboard mydashboard=new MyDashboard();
        }
        if(e.getSource()==clients){
            frame.dispose();
            MyClients myClients = new MyClients();
        }
        if(e.getSource()==appoint){
            frame.dispose();
            MyAppointments myAppointments=new MyAppointments();
        }
        if(e.getSource()==pas){
            frame.dispose();
            MyVetServices myVetServices = new MyVetServices();
        }
        if(e.getSource()==alls){
            frame.dispose();
            MySales mySales=new MySales();
        }
        if (e.getSource() == pos) {
            frame.dispose();
            MyPos myPos = new MyPos();
        }

        if (e.getSource() == logout) {
            frame.dispose();
            MyFrame myFrame = new MyFrame(); // Return to main frame
        }

        if(e.getSource()==logout){
            frame.dispose();
            MyFrame myFrame=new MyFrame();
        }
        if(e.getSource()==view){
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(false);
            panelRecord.setVisible(false);

            // Only show one dialog at a time, and don't append repeatedly
            // Clear the animals list before loading to avoid duplicates
            if (AnimalManager.animals != null) {
                AnimalManager.animals.clear();
            }
            AnimalManager.loadFromFile(); // Reload latest data

            java.util.List<Animal> animals = AnimalManager.animals;
            if (animals == null || animals.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No animals found.");
                return;
            }

            // Build a plain text table with better alignment
            StringBuilder animalList = new StringBuilder();
            String format = "%-10s %-15s %-15s %-15s %-10s %-12s %-8s%n";
            animalList.append(String.format(format, "ID", "Owner Name", "Pet Name", "Breed", "Type", "DOB", "Weight"));
            animalList.append("-------------------------------------------------------------------------------\n");
            for (Animal a : animals) {
                animalList.append(String.format(format,
                    a.getId(),
                    a.getName(),
                    a.getPetName(),
                    a.getBreed(),
                    a.getSpecies(),
                    a.getDateOfBirth(),
                    a.getWeight()
                ));
            }

            // Always create a new JTextArea for each view
            JTextArea textArea = new JTextArea(animalList.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            textArea.setCaretPosition(0);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 300));

            JOptionPane.showMessageDialog(frame, scrollPane, "Animal List", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==edit){
            panelEdit.setVisible(true);
            panelEditProcess.setVisible(false);
            
        }
        if(e.getSource()==sEditP){

            // Try to find the animal by client ID entered in enEdit
            String clientId = enEdit.getText().trim();
            Animal animal = null;

            // Always reload animals from file to ensure up-to-date data
            AnimalManager.animals.clear();
            AnimalManager.loadFromFile();
            java.util.List<Animal> animals = AnimalManager.animals;

            if (animals != null) {
            for (Animal a : animals) {
                if (a.getId().equalsIgnoreCase(clientId)) {
                animal = a;
                break;
                }
            }
            }

            if (animal == null) {
            JOptionPane.showMessageDialog(frame, "Client ID not found in animals.txt.");
            panelEditProcess.setVisible(false);
            return;
            }

            // Pre-fill fields with current animal data
            enPetName.setText(animal.getPetName());
            enSpecies.setText(animal.getSpecies());
            enBreed.setText(animal.getBreed());
            enDateofBirth.setText(animal.getDateOfBirth().toString());
            enWeight.setText(String.valueOf(animal.getWeight()));
            panelEdit.setVisible(false);
            panelEditProcess.setVisible(true);
        }
        if(e.getSource()==sEditP2){
            // Save the edited animal details
            String clientId = enEdit.getText().trim();
            Animal animal = null;

            // Always reload animals from file to ensure up-to-date data
            AnimalManager.animals.clear();
            AnimalManager.loadFromFile();
            java.util.List<Animal> animals = AnimalManager.animals;

            if (animals != null) {
            for (Animal a : animals) {
                if (a.getId().equalsIgnoreCase(clientId)) {
                animal = a;
                break;
                }
            }
            }

            if (animal == null) {
            JOptionPane.showMessageDialog(frame, "Client ID not found.");
            return;
            }

            // Update fields if not empty
            String newPetName = enPetName.getText().trim();
            if (!newPetName.isEmpty()) {
            animal.setPetName(newPetName);
            }

            String newSpecies = enSpecies.getText().trim();
            if (!newSpecies.isEmpty()) {
            animal.setSpecies(newSpecies);
            }

            String newBreed = enBreed.getText().trim();
            if (!newBreed.isEmpty()) {
            animal.setBreed(newBreed);
            }

            String dobStr = enDateofBirth.getText().trim();
            if (!dobStr.isEmpty()) {
            try {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                java.time.LocalDate dob = java.time.LocalDate.parse(dobStr, formatter);
                animal.setDateOfBirth(dob);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Use yyyy-MM-dd.");
            }
            }

            String weightStr = enWeight.getText().trim();
            if (!weightStr.isEmpty()) {
            try {
                double weight = Double.parseDouble(weightStr);
                animal.setWeight(weight);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid weight format.");
            }
            }

            JOptionPane.showMessageDialog(frame, "Animal updated.");
            AnimalManager.saveToFile();
            panelEditProcess.setVisible(false);
            panelEdit.setVisible(false);
        }
        
    }   
}
