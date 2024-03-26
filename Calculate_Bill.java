import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Calculate_Bill extends JFrame implements ActionListener, ItemListener {
    JLabel meternumber, name, nameText, address, addressText, unitconsumed, month;
    JTextField unitText;
    Choice meterText, monthChoice;
    JButton submit, cancel;

    public Calculate_Bill() {
        super("Calculate Bill");
        getContentPane().setBackground(Color.lightGray);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(200, 133, 242));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 15, 300, 20);
        heading.setFont(new Font("calibri", Font.BOLD, 20));
        panel.add(heading);

        meternumber = new JLabel("Meter Number");
        meternumber.setBounds(40, 70, 100, 20);
        meternumber.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(meternumber);

        meterText = new Choice();
        try {
            Database d = new Database();
            ResultSet rs = d.statement.executeQuery("select * from newcustomer");
            while (rs.next()) {
                meterText.add(rs.getString("meterno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        meterText.setBounds(180, 70, 100, 20);
        panel.add(meterText);

        name = new JLabel("Name");
        name.setBounds(40, 110, 100, 20);
        name.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180, 110, 100, 20);
        nameText.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(nameText);

        address = new JLabel("Address");
        address.setBounds(40, 150, 100, 20);
        address.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180, 150, 100, 20);
        addressText.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(addressText);

        meterText.addItemListener(this);

        try {
            Database d = new Database();
            ResultSet rs = d.statement.executeQuery("select * from newcustomer where meterno ='" + meterText.getSelectedItem() + "' ");
            if (rs.next()) {
                nameText.setText(rs.getString("name"));
                addressText.setText(rs.getString("address"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        unitconsumed = new JLabel("Units Consumed");
        unitconsumed.setBounds(40, 190, 150, 20);
        unitconsumed.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(unitconsumed);

        unitText = new JTextField();
        unitText.setBounds(180, 190, 150, 20);
        unitText.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(unitText);

        month = new JLabel("Month");
        month.setBounds(40, 230, 100, 20);
        month.setFont(new Font("monospace", Font.PLAIN, 15));
        panel.add(month);

        monthChoice = new Choice();
        monthChoice.add("JANUARY");
        monthChoice.add("FEBRUARY");
        monthChoice.add("MARCH");
        monthChoice.add("APRIL");
        monthChoice.add("MAY");
        monthChoice.add("JUNE");
        monthChoice.add("JULY");
        monthChoice.add("AUGUST");
        monthChoice.add("SEPTEMBER");
        monthChoice.add("OCTOBER");
        monthChoice.add("NOVEMBER");
        monthChoice.add("DECEMBER");
        monthChoice.setBounds(180, 230, 150, 20);
        panel.add(monthChoice);

        submit = new JButton("SUBMIT");
        submit.setBounds(40, 300, 100, 20);
        submit.setFont(new Font("calibri", Font.PLAIN, 15));
        submit.setBackground(new Color(9, 15, 11));
        submit.setForeground(new Color(200, 207, 202));
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(180, 300, 100, 20);
        cancel.setFont(new Font("calibri", Font.PLAIN, 15));
        cancel.setBackground(new Color(9, 15, 11));
        cancel.setForeground(new Color(200, 207, 202));
        cancel.addActionListener(this);
        panel.add(cancel);

        setSize(750, 400);
        setLocation(300, 200);
        setLayout(new BorderLayout());
        add(panel,"Center");


        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("calculatebill.png"));
        Image image=imageIcon.getImage().getScaledInstance(380, 360,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        add(label,"East");
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == meterText) {
            String selectedMeter = meterText.getSelectedItem();
            try {
                Database d = new Database();
                ResultSet rs = d.statement.executeQuery("select * from newcustomer where meterno ='" + meterText.getSelectedItem() + "' ");
                if (rs.next()) {
                    nameText.setText(rs.getString("name"));
                    addressText.setText(rs.getString("address"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterno=meterText.getSelectedItem();
            String sunit=unitText.getText();
            String smonth=monthChoice.getSelectedItem();

            int total=0;
            int units=Integer.parseInt(sunit);
            String query="select * from tax";
            try {
                Database c=new Database();
                ResultSet rs=c.statement.executeQuery(query);
                while(rs.next()){
                    total+=units*Integer.parseInt(rs.getString("tax_unit"));
                    total+=Integer.parseInt(rs.getString("meter_rent"));
                    total+=Integer.parseInt(rs.getString("service_tax"));
                }
            } catch (Exception p) {
                p.printStackTrace();
            }

            String query_total="insert into bills values('"+smeterno+"','"+smonth+"','"+sunit+"','"+total+"','Not Paid')";
            try {
                Database d=new Database();
                d.statement.executeUpdate(query_total);
                JOptionPane.showMessageDialog(null,"Customer bill updated succesfully ");
                setVisible(false);
            } catch (Exception h) {
                h.printStackTrace();
            }
        } 
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Calculate_Bill();
    }
}
