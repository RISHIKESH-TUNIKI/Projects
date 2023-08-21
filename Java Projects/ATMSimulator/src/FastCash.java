import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b100, b500,b1000,b2000,b5000,b10000,back;
    String id;
    FastCash(String id){
        this.id=id;
        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Select Withdrawl Amount: ");
        head.setFont(new Font("serif",Font.BOLD,15));
        head.setForeground(Color.WHITE);
        head.setBounds(280,360,300,35);
        bg.add(head);

        b100=new JButton("Rs 100");
        b100.setFont(new Font("serif",Font.BOLD,15));
        b100.setBounds(240,425,100,30);
        b100.addActionListener(this);
        bg.add(b100);

        b500=new JButton("Rs 500");
        b500.setFont(new Font("serif",Font.BOLD,15));
        b500.setBounds(400,425,100,30);
        b500.addActionListener(this);
        bg.add(b500);

        b1000=new JButton("Rs 1000");
        b1000.setFont(new Font("serif",Font.BOLD,15));
        b1000.setBounds(240,460,100,30);
        b1000.addActionListener(this);
        bg.add(b1000);

        b2000=new JButton("Rs 2000");
        b2000.setFont(new Font("serif",Font.BOLD,15));
        b2000.setBounds(400,460,100,30);
        b2000.addActionListener(this);
        bg.add(b2000);

        b5000=new JButton("Rs 5000");
        b5000.setFont(new Font("serif",Font.BOLD,15));
        b5000.setBounds(240,495,100,30);
        b5000.addActionListener(this);
        bg.add(b5000);

        b10000=new JButton("Rs 10000");
        b10000.setFont(new Font("serif",Font.BOLD,15));
        b10000.setBounds(400,495,100,30);
        b10000.addActionListener(this);
        bg.add(b10000);

        back=new JButton("Back");
        back.setFont(new Font("serif",Font.BOLD,15));
        back.setBounds(400,530,100,30);
        back.addActionListener(this);
        bg.add(back);

        setUndecorated(true);
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(id);
        } else {
            String number=((JButton)ae.getSource()).getText();
            number=number.substring(3);
            Date date=new Date();
            try {
                conn c=new conn();
                String balancequery="select amount from balance where id='"+id+"'";
                ResultSet rs=c.s.executeQuery(balancequery);
                rs.next();
                int balance=Integer.parseInt( rs.getString(1));
                if(balance>=Integer.parseInt(number)){
                    String query="insert into bank values('"+id+"',  '"+date+"',  'Withdraw', '"+number+"' )";
                    c.s.executeUpdate(query);
                    balance-=Integer.parseInt(number);
                    String updatequery="update balance set amount='"+balance+"' where id='"+id+"'";
                    c.s.executeUpdate(updatequery);
                    JOptionPane.showMessageDialog(null,"Rs"+number+"  Withdraw  Sucessful .\nCurrent Balance: "+balance+"/-");
                    setVisible(false);
                    new Transactions(id);
                }else{
                    JOptionPane.showMessageDialog(null,"You don't have Sufficient Funds.\nPlease check the Balance in your Account no:."+id);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
      }
}
    public static void main(String[] args) {
        new FastCash("1360");
    }
}
