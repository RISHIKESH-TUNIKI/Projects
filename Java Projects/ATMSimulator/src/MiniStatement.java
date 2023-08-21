import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {

    JButton back;

    String id;
    MiniStatement(String id){
        this.id=id;

        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        JLabel head=new JLabel("Bank X ");
        head.setFont(new Font("serif",Font.BOLD,12));
        head.setBounds(200,30,50,35);
        add(head);

        JLabel cardnum=new JLabel();
        cardnum.setFont(new Font("serif",Font.BOLD,12));
        cardnum.setBounds(50,80,400,35);
        add(cardnum);

        JLabel body=new JLabel();
        body.setFont(new Font("serif",Font.BOLD,12));
        body.setBounds(50,100,400,500);
        add(body);

        JLabel balancef=new JLabel();
        balancef.setFont(new Font("serif",Font.BOLD,12));
        balancef.setBounds(50,530,400,35);
        add(balancef);

        String cardno;
        String balance = "";
        conn c=new conn();
        String query1="select card_number from login where id='"+id+"'";
        String query2="select * from bank";
        String query3="select amount from balance where id='"+id+"'";
        try{
            ResultSet rs=c.s.executeQuery(query1);
            rs.next();
            cardno=rs.getString(1);
            cardnum.setText("Card No: "+cardno.substring(0,4)+"XXXXXXXXXX"+cardno.substring(12,16));
            ResultSet rsf=c.s.executeQuery(query2);
            while(rsf.next()){
                body.setText(body.getText() + "<html>"+rsf.getString(2)+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rsf.getString(3) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rsf.getString(4) + "<br><html>");
            }
            ResultSet rsb=c.s.executeQuery(query3);
            rsb.next();
            balance=rsb.getString(1);
            balancef.setText("Your Current Account  Balance: "+ balance);
        }catch (Exception e){
            System.out.println(e);
        }





        setLayout(null);
        setSize(500,700);
        setLocation(500,50);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(id);
    }

}
