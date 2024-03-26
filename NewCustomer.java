import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NewCustomer extends JFrame implements ActionListener{
    JLabel newcustomer,Meternumber,meternumbertext,address,city,state,email,phone;
    JTextField newcustomertext, addresstext,citytext,statetext,emailtext,phonetext;
    JButton next,cancel;
    NewCustomer(){
        super("New Customer ");
        setSize(800,600);
        setLocation(400,100);
        JPanel panel =new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(70, 235, 103));
        add(panel);

        JLabel heading =new JLabel("New Customer");
        heading.setBounds(150,10,200,20);
        heading.setFont(new Font("monospace",Font.BOLD,15));
        panel.add(heading);

        newcustomer =new JLabel("New Customer");
        newcustomer.setBounds(40,60,200,20);
        newcustomer.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(newcustomer);

        newcustomertext =new JTextField();
        newcustomertext.setBounds(180,60,140,20);
        panel.add(newcustomertext);


        Meternumber =new JLabel("Meter Number");
        Meternumber.setBounds(40,120,200,20);
        Meternumber.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(Meternumber);

        meternumbertext =new JLabel("");
        meternumbertext.setBounds(180,120,140,20); 
        panel.add(meternumbertext);

        Random ran=new Random();
        long number=ran.nextLong()%1000000;
        meternumbertext.setText(""+Math.abs(number));

        address =new JLabel("Address");
        address.setBounds(40,180,200,20);
        address.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(address);

        addresstext =new JTextField();
        addresstext.setBounds(180,180,140,20);
        panel.add(addresstext);


        city =new JLabel("City");
        city.setBounds(40,240,200,20);
        city.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(city);

        citytext =new JTextField();
        citytext.setBounds(180,240,140,20);
        panel.add(citytext);


        state =new JLabel("State");
        state.setBounds(40,300,200,20);
        state.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(state);

        statetext =new JTextField();
        statetext.setBounds(180,300,140,20);
        panel.add(statetext);


        email =new JLabel("Email");
        email.setBounds(40,360,200,20);
        email.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(email);

        emailtext =new JTextField();
        emailtext.setBounds(180,360,140,20);
        panel.add(emailtext);


        phone =new JLabel("Phone");
        phone.setBounds(40,420,200,20);
        phone.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(phone);

        phonetext =new JTextField();
        phonetext.setBounds(180,420,140,20);
        panel.add(phonetext);

        next = new JButton("Next");
        next.setBounds(40, 480, 80, 20);
        next.setFont(new Font("calibri", Font.PLAIN, 15));
        next.setBackground(new Color(9, 15, 11)); 
        next.setForeground(new Color(200, 207, 202)); 
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 480, 80, 20);
        cancel.setFont(new Font("calibri", Font.PLAIN, 15));
        cancel.setBackground(new Color(9, 15, 11)); 
        cancel.setForeground(new Color(200, 207, 202)); 
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");


        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("newcustomer.png"));
        Image image=imageIcon.getImage().getScaledInstance(380, 300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        add(label,"East");

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==next){
            String sname=newcustomertext.getText();
            String smeter=meternumbertext.getText();
            String saddress=addresstext.getText();
            String scity=citytext.getText();
            String sstate=statetext.getText();
            String eemail=emailtext.getText();
            String sphone=phonetext.getText();
            String query_customer = "insert into newcustomer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup="insert into signup values('"+smeter+"','','"+sname+"','','')";
            try{
                Database d=new Database();
                d.statement.executeUpdate(query_customer);
                d.statement.executeUpdate(query_signup);
                JOptionPane.showMessageDialog(null,"Customer details succesfully added");
                setVisible(false);
                new MeterInfo(smeter);
            }
            catch(Exception E){
                E.printStackTrace();
                System.out.println(E);
            }
        }
        else if(e.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new NewCustomer();
    }
    
}
