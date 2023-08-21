import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    JTextField cardnum;
    JPasswordField pinfield;
    JButton signin,clear,signup;

    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        setResizable(false);

            ImageIcon imgpath =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
            setIconImage(imgpath.getImage());
        Image imgsize=imgpath.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(imgsize);

        JLabel logo=new JLabel(img);
        logo.setBounds(40,10,150,150);
        add(logo);

        JLabel head=new JLabel("Welcome to ATM Simulator");
        head.setFont(new Font("serif",Font.BOLD,40));
        head.setBounds(280,80,500,60);
        add(head);


        JLabel card=new JLabel("CARD NO: ");
        card.setFont(new Font("serif",Font.BOLD,28));
        card.setBounds(100,250,150,30);
        add(card);

        cardnum=new JTextField();
        cardnum.setFont(new Font("serif",Font.BOLD,18));
        cardnum.setBounds(300,250, 450,30);
        add(cardnum);

        JLabel pin=new JLabel("PIN NO: ");
        pin.setFont(new Font("serif",Font.BOLD,28));
        pin.setBounds(100,320,150,30);
        add(pin);

         pinfield=new JPasswordField();
        pinfield.setFont(new Font("serif",Font.BOLD,22));
        pinfield.setBounds(300,320, 450,30);
        add(pinfield);

        signin=new JButton("SIGN IN ");
        signin.setFont(new Font("serif",Font.BOLD,18));
        signin.setBounds(300,390,200,30);
        signin.setBackground(Color.BLACK);
        signin.setForeground(Color.WHITE);
        signin.addActionListener(this);
        add(signin);

        clear=new JButton(" CLEAR ");
        clear.setFont(new Font("serif",Font.BOLD,18));
        clear.setBounds(550,390,200,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

       signup=new JButton(" SIGN UP ");
        signup.setFont(new Font("serif",Font.BOLD,18));
        signup.setBounds(300,450,450,30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        setSize(900,600);
        setVisible(true);
        setLocation(300,100);
        getContentPane().setBackground(Color.WHITE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==clear){
            cardnum.setText("");
            pinfield.setText("");
        } else if (e.getSource()==signup) {
            setVisible(false);
            new Signup();
        }
        else if(e.getSource()==signin){
            String cardno=cardnum.getText();
            Long pinno=Long.valueOf(new String(pinfield.getPassword()));
            System.out.println(pinno);
            try {
                conn c =new conn();
                String query="select id from login where card_number='"+cardno+"' and pin_number='"+pinno+"'";
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(rs.getString(1));
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Card Number Or PIN");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
