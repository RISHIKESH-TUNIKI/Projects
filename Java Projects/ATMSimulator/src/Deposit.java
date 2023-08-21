import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;

   String id;
    Deposit(String id){
        this.id=id;
        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Enter the amount you want to deposit: ");
        head.setFont(new Font("serif",Font.BOLD,15));
        head.setForeground(Color.WHITE);
        head.setBounds(250,360,300,35);
        bg.add(head);

        amount=new JTextField();
        amount.setFont(new Font("serif",Font.BOLD,15));
        amount.setBounds(250,400,250,35);
        bg.add(amount);

        deposit=new JButton("Deposit");
        deposit.setBounds(400,495,100,30);
        deposit.addActionListener(this);
        bg.add(deposit);

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
        if(ae.getSource()==deposit){
            String number=amount.getText();
            Date date=new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
            } else{
                try {
                    conn c=new conn();
                    String balancequery="select amount from balance where id='"+id+"'";
                    String query="insert into bank values('"+id+"',  '"+date+"',  'Deposit', '"+number+"' )";
                    c.s.executeUpdate(query);
                    ResultSet rs=c.s.executeQuery(balancequery);
                    if(!rs.next()){
                        String createquery="insert into balance values('"+id+"','"+number+"')";;
                        c.s.executeUpdate(createquery);
                        JOptionPane.showMessageDialog(null,"Rs"+number+"Deposited Sucessfully.");
                    }else {
                        int balance=Integer.parseInt( rs.getString(1));
                        balance+=Integer.parseInt(number);
                        String updatequery="update balance set amount='"+balance+"' where id='"+id+"'";
                        c.s.executeUpdate(updatequery);
                        JOptionPane.showMessageDialog(null,"Rs"+number+"Deposited Sucessfully.\nCurrent Balance: "+balance+"/-");
                    }
                    setVisible(false);
                    new Transactions(id);
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
