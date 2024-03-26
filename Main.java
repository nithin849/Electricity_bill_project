import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener{
    String acctype,meter_number;
    Main(String acctype,String meter_number){
        super("MAIN PAGE");
        this.acctype=acctype;
        this.meter_number=meter_number;
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("main1.jpg"));   // here we set the image 
        Image image=imageIcon.getImage().getScaledInstance(1530,830 ,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        add(label);

        JMenuBar menuBar=new JMenuBar();                                   // created JMenuBar
        setJMenuBar(menuBar);

        JMenu menu=new JMenu("Menu");                                    // created menu option in JMenuBar
        menu.setFont(new Font("serif",Font.PLAIN,16));

        JMenuItem newcustomer=new JMenuItem("New Customer");           // here set added the options in memu 
        newcustomer.setFont(new Font("italic",Font.PLAIN,15));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);

        JMenuItem customerdetail=new JMenuItem("Customer Details");    // here set added the options in memu 
        customerdetail.setFont(new Font("italic",Font.PLAIN,15));
        customerdetail.addActionListener(this);
        menu.add(customerdetail);

        JMenuItem depositdetails=new JMenuItem("Deposit Details");     // here set added the options in memu 
        depositdetails.setFont(new Font("italic",Font.PLAIN,15));
        depositdetails.addActionListener(this);
        menu.add(depositdetails);

        JMenuItem calculatebill=new JMenuItem("Calculate Bill");        // here set added the options in memu 
        calculatebill.setFont(new Font("italic",Font.PLAIN,15));
        calculatebill.addActionListener(this);
        menu.add(calculatebill);

        JMenu information=new JMenu("Information");                        // creating another menu option that is information
        information.setFont(new Font("serif",Font.PLAIN,16));

        JMenuItem updateinformation=new JMenuItem("Update Information");  // here added  updateinformation in infromation memu
        updateinformation.setFont(new Font("italic",Font.PLAIN,15));
        updateinformation.addActionListener(this);
        information.add(updateinformation);

        JMenuItem viewinformation=new JMenuItem("viewinformation");       // here added  viewinformation in infromation memu
        updateinformation.setFont(new Font("italic",Font.PLAIN,15));
        viewinformation.setFont(new Font("italic",Font.PLAIN,15));
        viewinformation.addActionListener(this);
        information.add(viewinformation);

        JMenu user=new JMenu("User");                                      // created another menu named user
        user.setFont(new Font("serif",Font.PLAIN,16));

        JMenuItem paybill=new JMenuItem("Pay Bill");                    // added paybill to user 
        paybill.setFont(new Font("italic",Font.PLAIN,15));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails=new JMenuItem("Bill Details");            // added billdetails to user 
        billdetails.setFont(new Font("italic",Font.PLAIN,15));
        billdetails.addActionListener(this);
        user.add(billdetails);

        JMenu bill=new JMenu("Bill");                                      // created bill menu
        bill.setFont(new Font("serif",Font.PLAIN,16));

        JMenuItem generatebill=new JMenuItem("Generate Bill");          // added generatebill to bill 
        generatebill.setFont(new Font("italic",Font.PLAIN,15));
        generatebill.addActionListener(this);
        bill.add(generatebill);

        JMenu utility=new JMenu("Utility");                               // created utility menu
        utility.setFont(new Font("serif",Font.PLAIN,16));
        utility.addActionListener(this);

        JMenuItem notepad=new JMenuItem("NotePad");                     // added note pad to utilty 
        notepad.setFont(new Font("italic",Font.PLAIN,15));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator=new JMenuItem("Calculator");               // added generatebill to bill 
        calculator.setFont(new Font("italic",Font.PLAIN,15));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit=new JMenu("Exit");                                    //created  exit menu 
        exit.setFont(new Font("serif",Font.PLAIN,16));

        JMenuItem exits=new JMenuItem("exit");                       // added exit option  to  exit mennu
        exits.setFont(new Font("italic",Font.PLAIN,15));
        exits.addActionListener(this);
        exit.add(exits);

        if(acctype.equals("customer")){
            menuBar.add(information);
            menuBar.add(user);
            menuBar.add(bill);
        }
        else{
            menuBar.add(menu);
        }
        menuBar.add(utility);
        menuBar.add(exit);


        setLayout(new FlowLayout());
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent E){
        String msg=E.getActionCommand();
        if(msg.equals("New Customer")){
            new NewCustomer();
        }
        else if(msg.equals("Customer Details")){
            new CustomerDetails();
        }
        else if(msg.equals("Deposit Details")){
            new DepositDetails();
        }
        else if(msg.equals("Calculate Bill")){
            new Calculate_Bill();
        }
        else if(msg.equals("Update Information")){
            new UpdateInfo(meter_number);
        }
        else if(msg.equals("viewinformation")){
            new ViewInfo(meter_number);
        }
        else if(msg.equals("Pay Bill")){
            new PayBill(meter_number);
        }
        else if(msg.equals("Bill Details")){
            new BillDetails(meter_number);
        }
        else if(msg.equals("Generate Bill")){
            new GenerateBill(meter_number);
        }
        else if(msg.equals("Calculator")){
            try {
                Runtime.getRuntime().exec("Calc");
            } catch (Exception n) {
                n.printStackTrace();
            }
        }
        else if(msg.equals("NotePad")){
            try {
                Runtime.getRuntime().exec("notepad");
            } catch (Exception n){
                n.printStackTrace();
            }
        }
        else {
            setVisible(false);
            new Login();
        }

    }  
    public static void main(String[] args) {
        new Main("","");
    }
}
