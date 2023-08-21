import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;

    String id;
    Withdrawl(String id){
        this.id=id;
        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Enter the amount you want to Withdraw: ");
        head.setFont(new Font("serif",Font.BOLD,15));
        head.setForeground(Color.WHITE);
        head.setBounds(250,360,300,35);
        bg.add(head);

        amount=new JTextField();
        amount.setFont(new Font("serif",Font.BOLD,15));
        amount.setBounds(250,400,250,35);
        bg.add(amount);

        withdraw=new JButton("Withdraw");
        withdraw.setBounds(400,495,100,30);
        withdraw.addActionListener(this);
        bg.add(withdraw);

        back=new JButton("Back");
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
        if(ae.getSource()==withdraw){
            String number=amount.getText();
            Date date=new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdraw");
            } else{
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
                        JOptionPane.showMessageDialog(null,"Rs"+number+"Withdraw  Sucessful .\nCurrent Balance: "+balance+"\\-");
                        setVisible(false);
                        new Transactions(id);
                    }else{
                        JOptionPane.showMessageDialog(null,"You don't have Sufficient Funds.\nPlease check the Balance in your Account no:."+id);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (ae.getSource()==back) {
            setVisible(false);
            new Transactions(id);
        }
    }
}
