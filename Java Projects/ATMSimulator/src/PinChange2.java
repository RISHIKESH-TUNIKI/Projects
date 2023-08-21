import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange2 extends JFrame implements ActionListener {
    JTextField pinen,pinren;
    JButton submit,back;

    String id;
    PinChange2(String id){
        this.id=id;
        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Change your  PIN: ");
        head.setFont(new Font("serif",Font.BOLD,15));
        head.setForeground(Color.WHITE);
        head.setBounds(280,375,300,35);
        bg.add(head);

        JLabel pine=new JLabel("Enter the new PIN: ");
        pine.setFont(new Font("serif",Font.BOLD,14));
        pine.setForeground(Color.WHITE);
        pine.setBounds(250,425,150,30);
        bg.add(pine);

        pinen=new JTextField();
        pinen.setFont(new Font("serif",Font.BOLD,14));
        pinen.setBounds(400,425,100,30);
        bg.add(pinen);

        JLabel pinre=new JLabel("RE-Enter the new PIN: ");
        pinre.setFont(new Font("serif",Font.BOLD,14));
        pinre.setForeground(Color.WHITE);
        pinre.setBounds(250,460,150,30);
        bg.add(pinre);

        pinren=new JTextField();
        pinren.setFont(new Font("serif",Font.BOLD,14));
        pinren.setBounds(400,460,100,30);
        bg.add(pinren);

        submit=new JButton("Submit");
        submit.setFont(new Font("serif",Font.BOLD,12));
        submit.setBounds(400,495,100,30);
        submit.addActionListener(this);

        bg.add(submit);

        back=new JButton("Back");
        back.setFont(new Font("serif",Font.BOLD,12));
        back.setBounds(400,530,100,30);
        back.addActionListener(this);

        bg.add(back);

        JOptionPane.showMessageDialog(null,"T");


        setUndecorated(true);
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    public static void main(String[] args) {
        new PinChange("1360");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
