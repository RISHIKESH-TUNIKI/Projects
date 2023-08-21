import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    JButton depositb, cashb,fastb,minib,pincb,balanceb,exitb;
    String id;
    Transactions(String id){
        this.id=id;
        ImageIcon imglogo =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imglogo.getImage());

        ImageIcon imgpath=new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image resize=imgpath.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon img=new ImageIcon(resize);
        JLabel bg=new JLabel(img);
        bg.setBounds(0,0,900,900);
        add(bg);

        JLabel head=new JLabel("Please select your Transaction: ");
        head.setFont(new Font("serif",Font.BOLD,15));
        head.setForeground(Color.WHITE);
        head.setBounds(280,360,300,35);
        bg.add(head);

        depositb=new JButton("Deposit");
        depositb.setFont(new Font("serif",Font.BOLD,12));
        depositb.setBounds(240,425,120,30);
        depositb.addActionListener(this);
        bg.add(depositb);

        cashb=new JButton("Cash Withdrawl");
        cashb.setFont(new Font("serif",Font.BOLD,12));
        cashb.setBounds(380,425,120,30);
        cashb.addActionListener(this);
        bg.add(cashb);

        fastb=new JButton("Fast Cash");
        fastb.setFont(new Font("serif",Font.BOLD,12));
        fastb.setBounds(240,460,120,30);
        fastb.addActionListener(this);
        bg.add(fastb);

        minib=new JButton("Min Statement");
        minib.setFont(new Font("serif",Font.BOLD,12));
        minib.setBounds(380,460,120,30);
        minib.addActionListener(this);
        bg.add(minib);

        pincb=new JButton("Pin Change");
        pincb.setFont(new Font("serif",Font.BOLD,12));
        pincb.setBounds(240,495,120,30);
        pincb.addActionListener(this);
        bg.add(pincb);

        balanceb=new JButton("Balance Enquiry");
        balanceb.setFont(new Font("serif",Font.BOLD,12));
        balanceb.setBounds(380,495,120,30);
        balanceb.addActionListener(this);
        bg.add(balanceb);

        exitb=new JButton("Exit");
        exitb.setFont(new Font("serif",Font.BOLD,12));
        exitb.setBounds(380,530,120,30);
        exitb.addActionListener(this);
        bg.add(exitb);

        setUndecorated(true);
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==exitb){
            System.exit(0);
        } else if (ae.getSource()==depositb) {
            setVisible(false);
            new Deposit(id);
        }else if(ae.getSource()==cashb){
            setVisible(false);
            new Withdrawl(id);
        } else if (ae.getSource()==fastb) {
            setVisible(false);
            new FastCash(id);
        }else if(ae.getSource()==pincb){
            setVisible(false);
            new PinChange(id);
        } else if (ae.getSource()==minib) {
            new MiniStatement(id);
        } else if (ae.getSource()==balanceb) {
            setVisible(false);
            new BalanceEnquiry(id);
        }
    }
}
