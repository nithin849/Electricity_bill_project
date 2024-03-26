import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener{
    JLabel createaccount,MeterNumber,employee,Username,Name,password;
    Choice createacc;
    JTextField MeterNumberText, employeeID,userText,NameText,passText;
    JButton create ,back;

    SignUp(){
        super("SIGUP PAGE");
        getContentPane().setBackground(Color.lightGray); 

        createaccount =new JLabel("Create Account as");
        createaccount.setBounds(30,50,120,20);
        add(createaccount);

        createacc =new Choice();
        createacc.add("admin");
        createacc.add("customer");
        createacc.setBounds(200,50,100,20);
        add(createacc);

        MeterNumber =new JLabel("Meter Number");
        MeterNumber.setBounds(30,90,120,20);
        MeterNumber.setVisible(false);
        add(MeterNumber);

        MeterNumberText =new JTextField();
        MeterNumberText.setBounds(200,90,100,20);
        MeterNumberText.setVisible(false);
        add(MeterNumberText);

        employee =new JLabel("Employee_Id");
        employee.setBounds(30,90,120,20);
        employee.setVisible(true);
        add(employee);

        employeeID =new JTextField();
        employeeID.setBounds(200,90,100,20);
        employeeID.setVisible(true);
        add(employeeID);

        Username =new JLabel("Username");
        Username.setBounds(30,130,100,20);
        add(Username);

        userText =new JTextField();
        userText.setBounds(200,130,100,20);
        add(userText);

        Name=new JLabel("Name");
        Name.setBounds(30,170,100,20);
        add(Name);

        NameText =new JTextField();
        NameText.setBounds(200,170,100,20);
        add(NameText);

        MeterNumberText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){

            }
            @Override
            public void focusLost(FocusEvent e){
                try {
                    Database d=new Database();
                    ResultSet rs=d.statement.executeQuery("select * from signup where meter_no='"+MeterNumberText.getText()+"'  ");
                    if(rs.next()){
                        NameText.setText(rs.getString("name"));
                    }
                } catch (Exception E){
                    E.printStackTrace();
                }
            }
            
        });

        password=new JLabel("Password");
        password.setBounds(30,210,100,20);
        add(password);

        passText=new JTextField();
        passText.setBounds(200,210,100,20);
        add(passText);

        create =new JButton("CREATE");
        create.setBackground(new Color(48, 191, 19));
        create.setBounds(30,260,100,20);
        create.addActionListener(this);
        add(create);

        back =new JButton("BACK");
        back.setBackground(new Color(53, 240, 233));
        back.setBounds(200,260,100,20);
        back.addActionListener(this);
        add(back);
        
        createacc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=createacc.getSelectedItem();
                if(user.equals("customer")){
                    employee.setVisible(false);
                    NameText.setEditable(false);
                    employeeID.setVisible(false);
                    MeterNumber.setVisible(true);
                    MeterNumberText.setVisible(true);
                }
                else{
                    employee.setVisible(true);
                    employeeID.setVisible(true);
                    MeterNumber.setVisible(false);
                    MeterNumberText.setVisible(false);
                }
            }
        });

        ImageIcon imageIcon= new ImageIcon(ClassLoader.getSystemResource("boy.png"));
        Image image=imageIcon.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 =new ImageIcon(image);
        JLabel  label=new JLabel(imageIcon2);
        label.setBounds(5,5,900,330);
        add(label); 

        setSize(640,400);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==create){
            String asloginas=createacc.getSelectedItem();
            String asusername=userText.getText();
            String asname=NameText.getText();
            String aspassword=passText.getText();
            String asmeter=MeterNumberText.getText();
            try{
                Database d=new Database();
                String query=null;
                if(createacc.getSelectedItem().equals("admin")){
                    query="insert into signup values('"+asmeter+"','"+asusername+"','"+asname+"','"+aspassword+"','"+asloginas+"')"; 
                }
                else{
                    query="update  signup set username='"+asusername+"',password='"+aspassword+"',usertype='"+asloginas+"'where meter_no ='"+asmeter+"'";
                }

                d.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created successfully");  // we use to get message
                setVisible(false);
                new Login();
            }
            catch(Exception h){
                h.printStackTrace();
            }
        }
        else if(e.getSource()==back){
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args) {
        new SignUp();
    }    
}
