import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import java.awt.*;

public class PayBill extends JFrame implements ActionListener {
    String meter_number;
    JLabel nameText,meterText,unitText,totalText,statusText;
    Choice monthText;
    JButton pay,cancel; 
    PayBill(String meter_number){
        super("Pay Bill");
        this.meter_number=meter_number;
        getContentPane().setBackground(Color.lightGray); 

        JLabel heading=new JLabel("Pay Bill");                                  // heading
        heading.setBounds(300,20,100,20);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel name=new JLabel("Name");                                         // customer data that is name
        name.setFont(new Font("serif", Font.PLAIN,18));
        name.setBounds(40,70,80,20);
        add(name);
  
        JLabel nameText=new JLabel("");                                         // name field
        nameText.setFont(new Font("serif", Font.PLAIN,18));
        nameText.setBounds(200,70,80,20);
        add(nameText);

        JLabel meter=new JLabel("Meter Number");                                // customer data 
        meter.setFont(new Font("serif", Font.PLAIN,18));
        meter.setBounds(40,120,120,20);
        add(meter);

        meterText=new JLabel("");                                               //meter  field
        meterText.setFont(new Font("serif", Font.PLAIN,18));
        meterText.setBounds(200,120,120,20);
        add(meterText);

        JLabel month=new JLabel("Month");                                      // customer data 
        month.setFont(new Font("serif", Font.PLAIN,18));
        month.setBounds(40,170,120,20);
        add(month);

        monthText=new Choice();                                                    // choice months
        monthText.setBounds(200, 170, 150, 20);
        monthText.setFont(new Font("monospace", Font.PLAIN, 15));
        monthText.add("JANUARY");
        monthText.add("FEBRUARY");
        monthText.add("MARCH");
        monthText.add("APRIL");
        monthText.add("MAY");
        monthText.add("JUNE");
        monthText.add("JULY");
        monthText.add("AUGUST");
        monthText.add("SEPTEMBER");
        monthText.add("OCTOBER");
        monthText.add("NOVEMBER");
        monthText.add("DECEMBER");
        add(monthText);

        JLabel unit=new JLabel("Units");                                       // customer data 
        unit.setFont(new Font("serif", Font.PLAIN,18));
        unit.setBounds(40,220,120,20);
        add(unit);

        unitText=new JLabel("");                                               //units  field
        unitText.setFont(new Font("serif", Font.PLAIN,18));
        unitText.setBounds(200,220,120,20);
        add(unitText);

        JLabel total=new JLabel("Total Amt");                                       // customer data 
        total.setFont(new Font("serif", Font.PLAIN,18));
        total.setBounds(40,270,120,20);
        add(total);

        totalText=new JLabel("");                                               //total bill field
        totalText.setFont(new Font("serif", Font.PLAIN,18));
        totalText.setBounds(200,270,120,20);
        add(totalText);

        JLabel status=new JLabel("Status");                                       // customer data 
        status.setFont(new Font("serif", Font.PLAIN,18));
        status.setBounds(40,320,120,20);
        add(status);

        statusText=new JLabel("");                                               //total bill field
        statusText.setFont(new Font("serif", Font.PLAIN,18));
        statusText.setBounds(200,320,120,20);
        statusText.setForeground(new Color(217, 58, 43));
        add(statusText);

        try {
            Database d=new Database();
            ResultSet rs=d.statement.executeQuery("select * from newcustomer where meterno='"+meter_number+"' ");
            while(rs.next()){
                meterText.setText(meter_number);
                nameText.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        monthText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                Database d=new Database();
                try {
                    ResultSet rs=d.statement.executeQuery("select * from bills where meter_no='"+meter_number+"'and month='"+monthText.getSelectedItem()+"'");
                    while(rs.next()){
                        unitText.setText(rs.getString("unit"));
                        totalText.setText(rs.getString("total_bill"));
                        statusText.setText(rs.getString("status"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        pay =new JButton("PAYMENT");
        pay.setBounds(40,390,150,25);
        pay.setFont(new Font("serif", Font.BOLD,20));
        pay.setBackground(new Color(9, 15, 11)); 
        pay.setForeground(new Color(200, 207, 202)); 
        pay.addActionListener(this);
        add(pay);

        cancel =new JButton("CANCEL");
        cancel.setBounds(240,390,150,25);
        cancel.setFont(new Font("serif", Font.BOLD,20));
        cancel.setBackground(new Color(9, 15, 11)); 
        cancel.setForeground(new Color(200, 207, 202)); 
        cancel.addActionListener(this);
        add(cancel);




        setBounds(200,50,800,600);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==pay){
            try {
                Database d=new Database();
                d.statement.executeUpdate("update bills set status='paid' where meter_no='"+meter_number+"' and month='"+meterText+"' ");
            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new Payment(meter_number);
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new PayBill("");
    }
}
