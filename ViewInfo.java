import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ViewInfo extends JFrame implements ActionListener{
    JButton cancel;
    String views;
    ViewInfo(String views){
        super("VIEW INFOMATION");
        this.views=views;
        getContentPane().setBackground(Color.lightGray); 
        setBounds(300,50,800,600);
        setLayout(null);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("infos.png"));
        Image image=imageIcon.getImage().getScaledInstance(300,300 , Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        label.setBounds(400,120,300,300);
        add(label);

        JLabel view=new JLabel("View Customer Information");    //heading 
        view.setFont(new Font("serif", Font.BOLD, 20));
        view.setBounds(250,10,250,20);
        add(view);

        JLabel name=new JLabel("Name");                        // customer data that is name
        name.setFont(new Font("serif", Font.PLAIN,18));
        name.setBounds(40,70,80,20);
        add(name);
  
        JLabel nameText=new JLabel("");                       // name field
        nameText.setFont(new Font("serif", Font.PLAIN,18));
        nameText.setBounds(200,70,80,20);
        add(nameText);

        JLabel meter=new JLabel("Meter Number");                        // customer data 
        meter.setFont(new Font("serif", Font.PLAIN,18));
        meter.setBounds(40,120,120,20);
        add(meter);

        JLabel meterText=new JLabel("");                       // name field
        meterText.setFont(new Font("serif", Font.PLAIN,18));
        meterText.setBounds(200,120,120,20);
        add(meterText);

        JLabel address=new JLabel("Address");                        // customer data 
        address.setFont(new Font("serif", Font.PLAIN,18));
        address.setBounds(40,170,120,20);
        add(address);

        JLabel addressText=new JLabel("");                           // address field
        addressText.setFont(new Font("serif", Font.PLAIN,18));
        addressText.setBounds(200,170,120,20);
        add(addressText);

        JLabel city=new JLabel("City");                             // customer data 
        city.setFont(new Font("serif", Font.PLAIN,18));
        city.setBounds(40,220,120,20);
        add(city);

        JLabel cityText=new JLabel("");                              // address field
        cityText.setFont(new Font("serif", Font.PLAIN,18));
        cityText.setBounds(200,220,120,20);
        add(cityText);

        JLabel state=new JLabel("State");                             // customer data 
        state.setFont(new Font("serif", Font.PLAIN,18));
        state.setBounds(40,270,120,20);
        add(state);

        JLabel stateText=new JLabel("");                              // state field
        stateText.setFont(new Font("serif", Font.PLAIN,18));
        stateText.setBounds(200,270,120,20);
        add(stateText);

        JLabel email=new JLabel("Email");                             // customer data 
        email.setFont(new Font("serif", Font.PLAIN,18));
        email.setBounds(40,320,120,20);
        add(email);

        JLabel emailText=new JLabel("");                              // email field
        emailText.setFont(new Font("serif", Font.PLAIN,18));
        emailText.setBounds(200,320,120,20);
        add(emailText);

        JLabel phone=new JLabel("Phone");                             // customer data 
        phone.setFont(new Font("serif", Font.PLAIN,18));
        phone.setBounds(40,370,120,20);
        add(phone);

        JLabel phoneText=new JLabel("");                              // email field
        phoneText.setFont(new Font("serif", Font.PLAIN,18));
        phoneText.setBounds(200,370,120,20);
        add(phoneText);

        cancel =new JButton("Cancel");                                // cancel button
        cancel.setBounds(40,430,100,25);
        cancel.setBackground(new Color(30, 232, 40));
        cancel.setForeground(new Color(228, 229, 235));
        cancel.addActionListener(this);
        add(cancel);


        Database d=new Database();

        String query="select * from newcustomer where meterno='"+views+"'";
        try{
            ResultSet rs=d.statement.executeQuery(query);
            while(rs.next()){
                nameText.setText(rs.getString("name"));
                meterText.setText(rs.getString("meterno"));
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
    public void actionPerformed(ActionEvent E){
        if(E.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new ViewInfo("");
    }
}
