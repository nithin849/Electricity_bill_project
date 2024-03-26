import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class BillDetails extends JFrame implements ActionListener{
    String meterno;
    JButton cancel;
    BillDetails(String meterno){
        super("BILL DETAILS");
        this.meterno=meterno;
        getContentPane().setBackground(Color.lightGray); 

        JLabel heading=new JLabel("Bill Details");            // heading
        heading.setBounds(300,20,100,20);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        setBounds(300,50,800,500);
        setLayout(null);

        JTable table =new JTable();
        try {
            Database d=new Database();
            ResultSet rs=d.statement.executeQuery("select * from bills where meter_no='"+meterno+"'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane js=new JScrollPane(table);
        js.setBounds(0,50,800,400);
        js.setBackground(Color.LIGHT_GRAY);
        add(js);

        cancel =new JButton("Cancel");                                 // cancel button
        cancel.setBounds(650,20,100,25);
        cancel.setBackground(new Color(30, 232, 40));
        cancel.setForeground(new Color(228, 229, 235));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new BillDetails("");
    }
}
