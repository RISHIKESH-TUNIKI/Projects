import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

class SignupThree extends JFrame implements ActionListener{

    JRadioButton saving, fixed, current, recurring;
    JCheckBox  atm, internet, mobile, email, cheque, estate, declare;
    JButton submit, cancel;
    long cardno,pinno;
    String id;
    SignupThree(String id){
        this.id=id;
        ImageIcon imgpath =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imgpath.getImage());

        setTitle("Account Details");
        JLabel head=new JLabel("Page 3: Account Details ");
        head.setFont(new Font("serif",Font.BOLD,22));
        head.setBounds(230,10,400,30);
        add(head);

        JLabel acctype=new JLabel("Account type:  ");
        acctype.setFont(new Font("serif",Font.BOLD,22));
        acctype.setBounds(100,50,400,30);
        add(acctype);

        saving=new JRadioButton("Saving Account");
        saving.setBackground(Color.WHITE);
        saving.setFont(new Font("serif",Font.BOLD,15));
        saving.setBounds(100,90,250,30);
        add(saving);

        fixed=new JRadioButton("Fixed Deposit Account");
        fixed.setBackground(Color.WHITE);
        fixed.setFont(new Font("serif",Font.BOLD,15));
        fixed.setBounds(370,90,250,30);
        add(fixed);

        current=new JRadioButton("Current Account");
        current.setBackground(Color.WHITE);
        current.setFont(new Font("serif",Font.BOLD,15));
        current.setBounds(100,140,250,30);
        add(current);

        recurring=new JRadioButton("Recurring Deposit Account");
        recurring.setBackground(Color.WHITE);
        recurring.setFont(new Font("serif",Font.BOLD,15));
        recurring.setBounds(370,140,250,30);
        add(recurring);

        ButtonGroup account=new ButtonGroup();
        account.add(saving);
        account.add(current);
        account.add(recurring);
        account.add(fixed);

        JLabel card=new JLabel("Card No: ");
        card.setFont(new Font("serif",Font.BOLD,22));
        card.setBounds(100,220,100,30);
        add(card);

        boolean cardgen=false;
        Random random=new Random();
        conn c=new conn();
        ResultSet rs;
        while (!cardgen){
            try{
                cardno=Math.abs(random.nextLong()%90000000L+1009032700000000L);
                String query="select * from login where id="+id;
                rs=c.s.executeQuery(query);
                if(!rs.next()){
                    cardgen=true;
                    break;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        pinno=Math.abs(random.nextLong()%9000+1000L);

        JLabel cardn=new JLabel("XXXX - XXXX - XXXX -  "+cardno%10000);
        cardn.setFont(new Font("serif",Font.BOLD,22));
        cardn.setBounds(220,220,400,30);
        add(cardn);

        JLabel cardmsg=new JLabel("This is your 16 digit Card no. ");
        cardmsg.setFont(new Font("serif",Font.BOLD,15));
        cardmsg.setBounds(100,250,200,30);
        add(cardmsg);

        JLabel pin=new JLabel("PIN No: ");
        pin.setFont(new Font("serif",Font.BOLD,22));
        pin.setBounds(100,280,100,30);
        add(pin);

        JLabel pinn=new JLabel("XXXX");
        pinn.setFont(new Font("serif",Font.BOLD,22));
        pinn.setBounds(220,280,400,30);
        add(pinn);

        JLabel pinmsg=new JLabel("This is your 4 digit Password. ");
        pinmsg.setFont(new Font("serif",Font.BOLD,15));
        pinmsg.setBounds(100,310,200,30);
        add(pinmsg);

        JLabel sertype=new JLabel("Services Required:  ");
        sertype.setFont(new Font("serif",Font.BOLD,22));
        sertype.setBounds(100,380,400,30);
        add(sertype);

        atm=new JCheckBox("ATM Card");
        atm.setBackground(Color.WHITE);
        atm.setFont(new Font("serif",Font.BOLD,15));
        atm.setBounds(100,420,250,30);
        add(atm);

        internet=new JCheckBox("Intenet Banking");
        internet.setBackground(Color.WHITE);
        internet.setFont(new Font("serif",Font.BOLD,15));
        internet.setBounds(400,420,250,30);
        add(internet);

        mobile=new JCheckBox("Mobile Banking");
        mobile.setBackground(Color.WHITE);
        mobile.setFont(new Font("serif",Font.BOLD,15));
        mobile.setBounds(100,460,250,30);
        add(mobile);

        email=new JCheckBox("Email & SMS Alerts");
        email.setBackground(Color.WHITE);
        email.setFont(new Font("serif",Font.BOLD,15));
        email.setBounds(400,460,250,30);
        add(email);

        cheque=new JCheckBox("Cheque Book");
        cheque.setBackground(Color.WHITE);
        cheque.setFont(new Font("serif",Font.BOLD,15));
        cheque.setBounds(100,500,250,30);
        add(cheque);

        estate=new JCheckBox("E-Statements");
        estate.setBackground(Color.WHITE);
        estate.setFont(new Font("serif",Font.BOLD,15));
        estate.setBounds(400,500,250,30);
        add(estate);

        declare=new JCheckBox("I here by declare that above entered details are correct to the best of my knowledge");
        declare.setBackground(Color.WHITE);
        declare.setFont(new Font("serif",Font.BOLD,12));
        declare.setBounds(100,560,500,15);
        add(declare);

        submit=new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("serif",Font.BOLD,12));
        submit.setBounds(280,600,100,30);
        submit.addActionListener(this);
        add(submit);

        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif",Font.BOLD,12));
        cancel.setBounds(400,600,100,30);
        cancel.addActionListener(this);
        add(cancel);



        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(750,700);
        setLocation(380,15);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cardnumber=""+cardno;
        if((ae.getSource()==submit)){
            String accounttype=null;
            if(saving.isSelected()){
                accounttype="Saving Account";
            } else if (fixed.isSelected()) {
                accounttype="Fixed Deposit Account";
            } else if (current.isSelected()) {
                accounttype="Current Account";
            }else{
                accounttype="Recurring Deposit Account";
            }

            String services="";
            if(atm.isSelected()){
                services+="ATM Card";
            }else if(internet.isSelected()) {
                services += "Internet Banking";
            }else if(mobile.isSelected()){
                services+="Mobile Banking";
            }else if(email.isSelected()){
                services+="Email & SMS Alerts";
            }else if(cheque.isSelected()){
                services+="Cheque Book";
            }else if(estate.isSelected()){
                services+="E-Statements";
            }
            boolean isdeclare=false;
            if(declare.isSelected()){
                isdeclare=true;
            }

            try {
                if(accounttype==null || cardnumber.equals("")  || services.equals("Null") || !isdeclare){
                    JOptionPane.showMessageDialog(null, "Fill all the required fields");
                }
                else{
                    conn c=new conn();
                    String query="insert into clientaccountdetails values('"+id+"', '"+accounttype+"','"+cardnumber+"','"+pinno+"','"+services+"')";
                    String queryupdate="insert into login values('"+id+"','"+cardnumber+"','"+pinno+"')";
                    c.s.executeUpdate(query);
                    c.s.executeUpdate(queryupdate);
                    JOptionPane.showMessageDialog(null,"Card No: "+cardnumber+"\nPIN No: "+pinno);
                    setVisible(false);
                    new Deposit(id);
                }
            }
            catch (Exception e){
                System.out.println(e.fillInStackTrace());
            }
        } else if(ae.getSource()==cancel){
            setVisible(false);
            new Login();
        }
    }
}
class SignupTwo extends  JFrame implements ActionListener{
    JTextField pannofield,adharnofield;
    JComboBox religionbox, categorybox, incomebox, edubox, occupbox;
    JRadioButton senioryes, seniorno, exyes, exno;
    JButton next, cancel;
    String id;

    SignupTwo(String id){
        this.id=id;
        ImageIcon imgpath =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imgpath.getImage());
        setTitle("Account Application Form");


        JLabel  addtionaldetails=new JLabel(" Page 2: Additional Details ");
        addtionaldetails.setFont(new Font("serif",Font.BOLD,22));
        addtionaldetails.setBounds(200,80,500,30);
        add(addtionaldetails);

        JLabel religion=new JLabel("Religion: ");
        religion.setFont(new Font("serif",Font.BOLD,22));
        religion.setBounds(80,140,100,30);
        add(religion);

        String [] relegionlist={"Null","Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionbox=new JComboBox(relegionlist);
        religionbox.setFont(new Font("serif",Font.BOLD,18));
        religionbox.setBounds(280,140,350,30);
        religionbox.setBackground(Color.WHITE);
        add(religionbox);

        JLabel category=new JLabel("Category: ");
        category.setFont(new Font("serif",Font.BOLD,22));
        category.setBounds(80,190,200,30);
        add(category);

        String [] categorylist={"Null","General","OBC","SC","ST","Other"};
        categorybox=new JComboBox(categorylist);
        categorybox.setFont(new Font("serif",Font.BOLD,18));
        categorybox.setBounds(280,190,350,30);
        categorybox.setBackground(Color.WHITE);
        add(categorybox);

        JLabel income=new JLabel("Income: ");
        income.setFont(new Font("serif",Font.BOLD,22));
        income.setBounds(80,240,200,30);
        add(income);

        String [] incomelist={"Null","No Income Source", "<1,50,000 per year", "<2,50,000 per year", "<5,00,000 per year","Upto 10,00,000 per year"};
        incomebox=new JComboBox(incomelist);
        incomebox.setFont(new Font("serif",Font.BOLD,18));
        incomebox.setBounds(280,240,350,30);
        incomebox.setBackground(Color.WHITE);
        add(incomebox);


        JLabel eductional=new JLabel("Educational: ");
        eductional.setFont(new Font("serif",Font.BOLD,22));
        eductional.setBounds(80,290,200,30);
        add(eductional);

        JLabel qualification=new JLabel("Qualification: ");
        qualification.setFont(new Font("serif",Font.BOLD,22));
        qualification.setBounds(80,320,200,30);
        add(qualification);

        String [] edulist={"Null", "Not Graduate", "Graduate", "Post-Graduate","Doctrate"};
        edubox=new JComboBox(edulist);
        edubox.setFont(new Font("serif",Font.BOLD,18));
        edubox.setBounds(280,340,350,30);
        edubox.setBackground(Color.WHITE);
        add(edubox);

        JLabel occupation=new JLabel("Occupation: ");
        occupation.setFont(new Font("serif",Font.BOLD,22));
        occupation.setBounds(80,390,200,30);
        add(occupation);

        String [] occuplist={"Null", "Salaried", "Self-Employed", "Student","Home-Maker","Retired", "Other"};
        occupbox=new JComboBox(occuplist);
        occupbox.setFont(new Font("serif",Font.BOLD,18));
        occupbox.setBounds(280,390,350,30);
        occupbox.setBackground(Color.WHITE);
        add(occupbox);


        JLabel panno=new JLabel("PAN Number: ");
        panno.setFont(new Font("serif",Font.BOLD,22));
        panno.setBounds(80,440,200,30);
        add(panno);

        pannofield=new JTextField();
        pannofield.setFont(new Font("serif",Font.BOLD,22));
        pannofield.setBounds(280,440,350,30);
        add(pannofield);

        JLabel adharno=new JLabel("Adhar Number: ");
        adharno.setFont(new Font("serif",Font.BOLD,22));
        adharno.setBounds(80,490,200,30);
        add(adharno);

        adharnofield=new JTextField();
        adharnofield.setFont(new Font("serif",Font.BOLD,22));
        adharnofield.setBounds(280,490,350,30);
        add(adharnofield);

        JLabel seniorcitizen=new JLabel("Senior Citizen: ");
        seniorcitizen.setFont(new Font("serif",Font.BOLD,22));
        seniorcitizen.setBounds(80,540,200,30);
        add(seniorcitizen);

        senioryes=new JRadioButton("Yes");
        senioryes.setBackground(Color.WHITE);
        senioryes.setFont(new Font("serif",Font.BOLD,22));
        senioryes.setBounds(310,540,100,30);
        add(senioryes);

        seniorno=new JRadioButton("No");
        seniorno.setBackground(Color.WHITE);
        seniorno.setFont(new Font("serif",Font.BOLD,22));
        seniorno.setBounds(450,540,100,30);
        add(seniorno);

        ButtonGroup sbg=new ButtonGroup();
        sbg.add(senioryes);
        sbg.add(seniorno);

        JLabel exaccount=new JLabel("Existing Account:");
        exaccount.setFont(new Font("serif",Font.BOLD,22));
        exaccount.setBounds(80,590,200,30);
        add(exaccount);

        exyes=new JRadioButton("Yes");
        exyes.setBackground(Color.WHITE);
        exyes.setFont(new Font("serif",Font.BOLD,22));
        exyes.setBounds(310,590,100,30);
        add(exyes);

        exno=new JRadioButton("No");
        exno.setBackground(Color.WHITE);
        exno.setFont(new Font("serif",Font.BOLD,22));
        exno.setBounds(450,590,100,30);
        add(exno);

        ButtonGroup exbg=new ButtonGroup();
        exbg.add(exyes);
        exbg.add(exno);

        next=new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("serif",Font.BOLD,18));
        next.setBounds(530,655,100,30);
        next.addActionListener(this);
        add(next);

        cancel=new JButton("Next");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif",Font.BOLD,18));
        cancel.setBounds(530,655,100,30);
        cancel.addActionListener(this);
        add(cancel);


        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(750,800);
        setLocation(380,15);
        setVisible(true);

    }

    static boolean adharValidation(String s){
        if(s.length()!=12){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    static boolean panValidation(String s){
        if(s.length()!=10){
            return false;
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==next){
            String religion=(String) religionbox.getSelectedItem();
            String category=(String)categorybox.getSelectedItem();
            String income=(String)incomebox.getSelectedItem();
            String education=(String)edubox.getSelectedItem();
            String occupation=(String)occupbox.getSelectedItem();
            String pano=pannofield.getText();
            String adharno=adharnofield.getText();
            String senior=null;
            if(senioryes.isSelected()){
                senior="Yes";
            }else{
                senior="No";
            }
            String exacc=null;
            if(exyes.isSelected()){
                exacc="Yes";
            }else{
                exacc="No";
            }

            try {
                if(religion.equals("Null") || category.equals("Null") || income.equals("Null") || education.equals("Null")  || occupation.equals("Null") || !panValidation(pano) || !adharValidation(adharno) || senior==null  || exacc==null ){
                    JOptionPane.showMessageDialog(null, "Fill all the required fields");
                }
                else{
                    conn c=new conn();
                    String query="insert into clientadditionaldetails values('"+id+"', '"+religion+"', '"+category+"', '"+income+"', '"+education+"', '"+occupation+"','"+pano+"','"+adharno+"','"+senior+"','"+exacc+"')";
                    c.s.executeUpdate(query);
                    setVisible((false));
                    new SignupThree(id);
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        } else if (ae.getSource()==cancel) {
            System.exit(0);
        }
    }
}
class SignupOne extends JFrame implements ActionListener {

    JTextField namefield, fnamefield, emailfield, addfield, cityfield, statefield, pinfield;
    JRadioButton male, female, married, notmarried, other;
    JDateChooser dobfield;
    JButton next;
    String id;
    SignupOne(){
        ImageIcon imgpath =new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        setIconImage(imgpath.getImage());

        boolean idgen=false;
        Random ran=new Random();
        conn c=new conn();
        ResultSet rs;
        while (!idgen){
            try{
                id=""+Math.abs(ran.nextLong(100,1000)%900L+1000L);
                String query="select * from login where id='"+id+"'";
                rs=c.s.executeQuery(query);
                if(!rs.next()){
                    idgen=true;
                    break;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        JLabel  formno=new JLabel("APPLICATION FORM NO:  "+id);
        formno.setFont(new Font("serif",Font.BOLD,30));
        formno.setBounds(150,30,500,30);
        add(formno);

        JLabel  personaldetails=new JLabel(" Page 1: Personal Details ");
        personaldetails.setFont(new Font("serif",Font.BOLD,22));
        personaldetails.setBounds(200,80,500,30);
        add(personaldetails);

        JLabel name=new JLabel("Name: ");
        name.setFont(new Font("serif",Font.BOLD,22));
        name.setBounds(80,140,100,30);
        add(name);

       namefield=new JTextField();
        namefield.setFont(new Font("serif",Font.BOLD,22));
        namefield.setBounds(280,140,350,30);
        add(namefield);

        JLabel fname=new JLabel("Father's Name: ");
        fname.setFont(new Font("serif",Font.BOLD,22));
        fname.setBounds(80,190,200,30);
        add(fname);

       fnamefield=new JTextField();
        fnamefield.setFont(new Font("serif",Font.BOLD,22));
        fnamefield.setBounds(280,190,350,30);
        add(fnamefield);

        JLabel dob=new JLabel("Date of Birth: ");
        dob.setFont(new Font("serif",Font.BOLD,22));
        dob.setBounds(80,240,200,30);
        add(dob);

        dobfield=new JDateChooser();
        dobfield.setBackground(Color.WHITE);
        dobfield.setBounds(280,240,350,30);
        add(dobfield);

        JLabel gender=new JLabel("Gender: ");
        gender.setFont(new Font("serif",Font.BOLD,22));
        gender.setBounds(80,290,200,30);
        add(gender);

       male=new JRadioButton("Male");
        male.setBackground(Color.WHITE);
        male.setFont(new Font("serif",Font.BOLD,18));
        male.setBounds(320,290,150,30);
        add(male);

       female=new JRadioButton("FeMale");
        female.setBackground(Color.WHITE);
        female.setFont(new Font("serif",Font.BOLD,18));
        female.setBounds(470,290,150,30);
        add(female);

        ButtonGroup genderbg=new ButtonGroup();
        genderbg.add(male);
        genderbg.add(female);

        JLabel email=new JLabel("E-mail: ");
        email.setFont(new Font("serif",Font.BOLD,22));
        email.setBounds(80,340,200,30);
        add(email);

        emailfield=new JTextField();
        emailfield.setFont(new Font("serif",Font.BOLD,22));
        emailfield.setBounds(280,340,350,30);
        add(emailfield);

        JLabel maritalstatus=new JLabel("Marital Status: ");
        maritalstatus.setFont(new Font("serif",Font.BOLD,22));
        maritalstatus.setBounds(80,390,200,30);
        add(maritalstatus);

        married=new JRadioButton("Married");
        married.setBackground(Color.WHITE);
        married.setFont(new Font("serif",Font.BOLD,18));
        married.setBounds(280,390,120,30);
        add(married);

        notmarried=new JRadioButton("Not Married");
        notmarried.setBackground(Color.WHITE);
        notmarried.setFont(new Font("serif",Font.BOLD,18));
        notmarried.setBounds(400,390,150,30);
        add(notmarried);

        other=new JRadioButton("Other");
        other.setBackground(Color.WHITE);
        other.setFont(new Font("serif",Font.BOLD,18));
        other.setBounds(550,390,100,30);
        add(other);

        ButtonGroup martialbg=new ButtonGroup();
        martialbg.add(male);
        martialbg.add(female);
        martialbg.add(other);


        JLabel add=new JLabel("Address: ");
        add.setFont(new Font("serif",Font.BOLD,22));
        add.setBounds(80,440,200,30);
        add(add);

        addfield=new JTextField();
        addfield.setFont(new Font("serif",Font.BOLD,22));
        addfield.setBounds(280,440,350,30);
        add(addfield);

        JLabel city=new JLabel("City: ");
        city.setFont(new Font("serif",Font.BOLD,22));
        city.setBounds(80,490,200,30);
        add(city);

        cityfield=new JTextField();
        cityfield.setFont(new Font("serif",Font.BOLD,22));
        cityfield.setBounds(280,490,350,30);
        add(cityfield);

        JLabel state=new JLabel("State: ");
        state.setFont(new Font("serif",Font.BOLD,22));
        state.setBounds(80,540,200,30);
        add(state);

        statefield=new JTextField();
        statefield.setFont(new Font("serif",Font.BOLD,22));
        statefield.setBounds(280,540,350,30);
        add(statefield);

        JLabel pincode=new JLabel("Pin Code:");
        pincode.setFont(new Font("serif",Font.BOLD,22));
        pincode.setBounds(80,590,200,30);
        add(pincode);

       pinfield=new JTextField();
        pinfield.setFont(new Font("serif",Font.BOLD,22));
        pinfield.setBounds(280,590,350,30);
        add(pinfield);

        next=new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("serif",Font.BOLD,18));
        next.setBounds(530,655,100,30);
        next.addActionListener(this);
        add(next);


        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(750,800);
        setLocation(380,15);
        setVisible(true);

    }

    static boolean nameValidation(String s){
        if(s.isEmpty()){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    static boolean pincodeValidation(String s){
        if(s.isEmpty()){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String name=namefield.getText();
        String fname=fnamefield.getText();
        String dob=((JTextField)dobfield.getDateEditor().getUiComponent()).getText();
        String gender=null;
        if(male.isSelected()){
            gender="Male";
        }
        else{
            gender="Female";
        }
        String email=emailfield.getText();
        String marital=null;
        if(married.isSelected()){
            marital="married";
        }
        else if(notmarried.isSelected()){
            marital="not married";
        }
        else{
            marital="other";
        }
        String address = addfield.getText();
        String city = cityfield.getText();
        String pincode = pinfield.getText();
        String state = statefield.getText();
        try {
            if(!nameValidation(name)|| !nameValidation(fname)|| dob.equals("") || gender==null || email.equals("") || marital==null || address.equals("") || city.equals("") || !pincodeValidation(pincode) || state.equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            }
            else{
                conn c=new conn();
                System.out.println("insert into clientpersonaldetails values('"+id+"', '"+name+"','"+fname+"','"+dob+"','"+gender+"', '"+email+"','"+marital+"','"+address+"','"+city+"','"+pincode+"','"+state+"');");
                String query="insert into clientpersonaldetails values('"+id+"', '"+name+"','"+fname+"','"+dob+"','"+gender+"', '"+email+"','"+marital+"','"+address+"','"+city+"','"+pincode+"','"+state+"')";
                c.s.executeUpdate(query);
                setVisible(false);
                new SignupTwo(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
public class Signup {
   Signup() {
       new SignupOne();
    }
}
