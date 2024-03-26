import javax.swing.*;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {
    Choice searchmeterChoice,searchnameChoice;
    JButton search,print,close;
    JTable table;
    public CustomerDetails() {
        super("CUSTOMER DETAILS");
        getContentPane().setBackground(new Color(184, 93, 245));
        setSize(750, 500);
        setLocation(400, 200);
        
        JLabel searchmeter=new JLabel("Search Meterno.");
        searchmeter.setBounds(40, 20, 120, 20);
        searchmeter.setFont(new Font("monospace", Font.PLAIN, 15));
        add(searchmeter);

        searchmeterChoice=new Choice();
        searchmeterChoice.setBounds(200, 20, 150, 20);
        searchmeterChoice.setFont(new Font("monospace", Font.PLAIN, 15));
        add(searchmeterChoice);

        try {
           Database d=new Database();
           ResultSet rs=d.statement.executeQuery("select * from newcustomer");
           while(rs.next()){
            searchmeterChoice.add(rs.getString("meterno"));
           } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel searchname=new JLabel("Search Name");
        searchname.setBounds(400, 20, 120, 20);
        searchname.setFont(new Font("monospace", Font.PLAIN, 15));
        add(searchname);

        searchnameChoice=new Choice();
        searchnameChoice.setBounds(550, 20, 150, 20);
        searchnameChoice.setFont(new Font("monospace", Font.PLAIN, 15));
        add(searchnameChoice);

        try {
            Database d=new Database();
            ResultSet rs=d.statement.executeQuery("select * from newcustomer");
            while(rs.next()){
             searchnameChoice.add(rs.getString("name"));
            } 
         } catch (Exception e) {
             e.printStackTrace();
         }
         table=new JTable();
         //table.setBounds()
         try {
            Database d=new Database();
            ResultSet rs=d.statement.executeQuery("select * from newcustomer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
            e.printStackTrace();
         }

         JScrollPane js=new JScrollPane(table);
         js.setBounds(0,100,750,500);
         js.setBackground(Color.LIGHT_GRAY);
         add(js);

         search =new JButton("Search");
         search.setBackground(Color.YELLOW);
         search.setForeground(Color.BLACK);
         search.setBounds(40,70,80,20);
         search.addActionListener(this);
         add(search);

         print =new JButton("Print");
         print.setBackground(Color.GREEN);
         print.setForeground(Color.BLACK);
         print.setBounds(140,70,80,20);
         print.addActionListener(this);
         add(print);

         close =new JButton("close");
         close.setBackground(Color.RED);
         close.setForeground(Color.BLACK);
         close.setBounds(600,70,80,20);
         close.addActionListener(this);
         add(close);


        setLayout(new BorderLayout());
        setVisible(true);
    }
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==search){
            String query="select  * from newcustomer where meterno='"+searchmeterChoice.getSelectedItem()+"' and name ='"+searchnameChoice.getSelectedItem()+"' ";
            try {
                Database d=new Database();
                ResultSet rsa=d.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rsa));
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else if(e.getSource()==print){
            try {
                table.print();
            } catch (Exception h) {
                h.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
        
    }
    public static void main(String[] args) {
        new CustomerDetails();
    }
}

