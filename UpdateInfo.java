import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
public class UpdateInfo extends JFrame implements ActionListener{
    String meter_number;
    JButton update,cancel;
    JTextField nameText,meterText,addressText,cityText,stateText,emailText,phoneText;
    UpdateInfo(String meter_number){
        super("UPDATE INFORMATION");
        this.meter_number=meter_number;
        setBounds(300,50,800,600);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null); 


        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("update.png"));         // image on update frame
        Image image=imageIcon.getImage().getScaledInstance(300,300 , Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        label.setBounds(400,120,300,300);
        add(label);


        JLabel heading=new JLabel("UPDATE CUSTOMER INFORMATION");        // heading
        heading.setBounds(200,10,380,20);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel name=new JLabel("Name");                                 // customer data that is name
        name.setFont(new Font("serif", Font.PLAIN,18));
        name.setBounds(40,70,80,20);
        add(name);

        nameText=new JTextField();                                           // name Text field
        nameText.setFont(new Font("serif", Font.PLAIN,18));
        nameText.setBounds(200,70,120,20);
        add(nameText);

        JLabel meter=new JLabel("Meter Number");                        // customer data 
        meter.setFont(new Font("serif", Font.PLAIN,18));
        meter.setBounds(40,120,120,20);
        add(meter);

        meterText=new JTextField();                                         // meter Textfield field
        meterText.setFont(new Font("serif", Font.PLAIN,18));
        meterText.setBounds(200,120,120,20);
        add(meterText);

        JLabel address=new JLabel("Address");                          // customer data 
        address.setFont(new Font("serif", Font.PLAIN,18));
        address.setBounds(40,170,120,20);
        add(address);

        addressText=new JTextField();                                       // address Text field
        addressText.setFont(new Font("serif", Font.PLAIN,18));
        addressText.setBounds(200,170,120,20);
        add(addressText);

        JLabel city=new JLabel("City");                               // customer data 
        city.setFont(new Font("serif", Font.PLAIN,18));
        city.setBounds(40,220,120,20);
        add(city);

        cityText=new JTextField();                                         // address Text field
        cityText.setFont(new Font("serif", Font.PLAIN,18));
        cityText.setBounds(200,220,120,20);
        add(cityText);

        JLabel state=new JLabel("State");                             // customer data 
        state.setFont(new Font("serif", Font.PLAIN,18));
        state.setBounds(40,270,120,20);
        add(state);

        stateText=new JTextField();                                       // state Text field
        stateText.setFont(new Font("serif", Font.PLAIN,18));
        stateText.setBounds(200,270,120,20);
        add(stateText);

        JLabel email=new JLabel("Email");                             // customer data 
        email.setFont(new Font("serif", Font.PLAIN,18));
        email.setBounds(40,320,120,20);
        add(email);

        emailText=new JTextField();                                        // email Text field
        emailText.setFont(new Font("serif", Font.PLAIN,18));
        emailText.setBounds(200,320,120,20);
        add(emailText);

        JLabel phone=new JLabel("Phone");                              // customer data 
        phone.setFont(new Font("serif", Font.PLAIN,18));
        phone.setBounds(40,370,120,20);
        add(phone);

        phoneText=new JTextField();                                         // phone Text field
        phoneText.setFont(new Font("serif", Font.PLAIN,18));
        phoneText.setBounds(200,370,120,20);
        add(phoneText);

        update =new JButton("Update");                                 // Update button
        update.setBounds(40,430,100,25);
        update.setBackground(new Color(30, 232, 40));
        update.setForeground(new Color(228, 229, 235));
        update.addActionListener(this);
        add(update);

        cancel =new JButton("Cancel");                                 // cancel button
        cancel.setBounds(200,430,100,25);
        cancel.setBackground(new Color(30, 232, 40));
        cancel.setForeground(new Color(228, 229, 235));
        cancel.addActionListener(this);
        add(cancel);

        Database d=new Database();

        String query="select * from newcustomer where meterno='"+meter_number+"'";
        try{
            ResultSet rs=d.statement.executeQuery(query);
            while(rs.next()){
                nameText.setText(rs.getString("name"));
                nameText.setEditable(false);
                meterText.setText(rs.getString("meterno"));
                meterText.setEditable(false);
                addressText.setText(rs.getString("address"));
                cityText.setText(rs.getString("city"));
                stateText.setText(rs.getString("state"));
                emailText.setText(rs.getString("email"));
                phoneText.setText(rs.getString("phone"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==update){
            String scity=cityText.getText();
            String saddress=addressText.getText();
            String sphone=phoneText.getText();
            String semail=emailText.getText();
            String sstate=stateText.getText();
            try {
                Database d=new Database();
                d.statement.executeUpdate("update newcustomer set address='"+saddress+"',city='"+scity+"',state='"+sstate+"',email='"+semail+"',phone='"+sphone+"' where meterno ='"+meter_number+"'");
                JOptionPane.showMessageDialog(null, "User information updated succesfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }

        }
        else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new UpdateInfo("");
    }
}
