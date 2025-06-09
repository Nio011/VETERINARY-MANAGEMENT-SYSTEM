import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{

    JButton button;
    JButton bk;

    JLabel title;
    JLabel image;
    MyFrame(){

        ImageIcon icon = new ImageIcon("LOGO.png");
        Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(40, 1, 400, 400); // Centered horizontally, above the button

//        label.setVisible(false);

        button = new JButton();
        button.setBounds(145,345,200,50);
        button.addActionListener(this);
        button.setText("LOG IN");
        button.setFocusable(false);
//        button.setHorizontalAlignment(JButton.CENTER);
//        button.setVerticalAlignment(JButton.BOTTOM);
        button.setFont(new Font("Times New Roman",Font.BOLD, 25));
        button.setIconTextGap(0);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.lightGray);
        button.setBorder(BorderFactory.createEtchedBorder());

        


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(new FlowLayout(FlowLayout.CENTER,60,10));
        this.setLayout(null);
        this.setSize(500,500);
        this.setResizable(false);
        this.add(image);
        this.add(button);
        this.getContentPane().setBackground(new Color(236, 236, 211));



        this.setTitle("VETERINARY SYSTEM");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            this.dispose();
            MyLogin myLogin=new MyLogin();
        }
    }
}
