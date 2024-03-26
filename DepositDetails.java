import javax.swing.*;

import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {
    Choice searchmeterChoice,searchmonthChoice;
    JButton search,print,close;
    JTable table;
    public DepositDetails() {
        super("DEPOSIT DETAILS");
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
           ResultSet rs=d.statement.executeQuery("select * from bills");
           while(rs.next()){
            searchmeterChoice.add(rs.getString("meter_no"));
           } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel searchmonth=new JLabel("Search Month");
        searchmonth.setBounds(400, 20, 120, 20);
        searchmonth.setFont(new Font("monospace", Font.PLAIN, 15));
        add(searchmonth);

        searchmonthChoice=new Choice();
        searchmonthChoice.setBounds(550, 20, 150, 20);
        searchmonthChoice.setFont(new Font("monospace", Font.PLAIN, 15));
        searchmonthChoice.add("JANUARY");
        searchmonthChoice.add("FEBRUARY");
        searchmonthChoice.add("MARCH");
        searchmonthChoice.add("APRIL");
        searchmonthChoice.add("MAY");
        searchmonthChoice.add("JUNE");
        searchmonthChoice.add("JULY");
        searchmonthChoice.add("AUGUST");
        searchmonthChoice.add("SEPTEMBER");
        searchmonthChoice.add("OCTOBER");
        searchmonthChoice.add("NOVEMBER");
        searchmonthChoice.add("DECEMBER");
        add(searchmonthChoice);

        
         table=new JTable();
         try {
            Database d=new Database();
            ResultSet rs=d.statement.executeQuery("select * from bills");
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
            String query="select  * from bills where meter_no='"+searchmeterChoice.getSelectedItem()+"' and month ='"+searchmonthChoice.getSelectedItem()+"' ";
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
        new DepositDetails();
    }
}

