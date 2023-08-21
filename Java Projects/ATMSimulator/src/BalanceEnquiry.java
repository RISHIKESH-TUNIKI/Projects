import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;

    String id;
    BalanceEnquiry(String id){
        this.id=id;

        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Your Balance: ");
        head.setFont(new Font("serif",Font.BOLD,25));
        head.setForeground(Color.WHITE);
        head.setBounds(280,360,300,35);
        bg.add(head);

        JLabel balance=new JLabel() ;
        balance.setFont(new Font("serif",Font.BOLD,30));
        balance.setForeground(Color.WHITE);
        balance.setBounds(300,430,300,35);
        bg.add(balance);

        conn c=new conn();
        String query="select amount from balance where id='"+id+"'";
        try{
            ResultSet rs=c.s.executeQuery(query);
            rs.next();
            balance.setText("Rs "+rs.getString(1)+" /-");
        }catch (Exception e){
            System.out.println(e);
        }

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
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(id);
    }
}
