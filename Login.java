import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class Login extends JFrame implements ActionListener {
    JTextField usertext;                                    // we have declared here because we wnat to use this in jdbc connection
    JTextField passtext;
    Choice loginChoice;                                     // its like dropdown 
    JButton loginbutton;
    JButton cancel;
    JButton signup;
    Login() {                                                 // login class constructer

        super("Login Page");                           // super
        getContentPane().setBackground(Color.lightGray); 

        JLabel username=new JLabel("Username");        // username label
        username.setBounds(300,60,100,20);
        add(username);

        usertext=new JTextField();                          //usertext field
        usertext.setBounds(400,60,150,20);
        add(usertext);

        JLabel password=new JLabel("Password");        // password label
        password.setBounds(300,90,100,20);
        add(password);

        passtext=new JTextField();                          // password textfield
        passtext.setBounds(400,90,150,20);
        add(passtext);

        JLabel login=new JLabel("Login in as");        // login label
        login.setBounds(300,120,100,20);
        add(login);

        loginChoice = new Choice();                         // dropdown
        loginChoice.add("admin");
        loginChoice.add("customer");
        loginChoice.setBounds(400,120,150,20);
        add(loginChoice);
        
        loginbutton =new JButton("login");             // login button
        loginbutton.setBounds(300,150,90,20);
        loginbutton.addActionListener(this);
        add(loginbutton);
 
        cancel =new JButton("Cancel");                // cancel button
        cancel.setBounds(400,150,90,20);
        cancel.addActionListener(this);
        add(cancel);

        signup =new JButton("Signup");                // signup button
        signup.setBounds(350,190,90,20);
        signup.addActionListener(this);
        add(signup);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("personal.png"));
        Image image=imageIcon.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        label.setBounds(5,5,250,250);
        add(label);
        
        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==loginbutton){
            String asusername=usertext.getText();
            String aspassword=passtext.getText();
            String astype=loginChoice.getSelectedItem();
            try{
                Database d= new Database();
                String query="select * from signup where username='"+asusername+"'and password='"+aspassword+"'and  usertype='"+astype+"'";
                ResultSet resultSet=d.statement.executeQuery(query);
                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    JOptionPane.showMessageDialog(null, "Login Succesfull  !!");
                    setVisible(false);
                    new Main(astype,meter);
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Invalid Login credentials");
                }
            }
            catch(Exception h){
                h.printStackTrace();
            }
        }
        else if(e.getSource()==signup){
            setVisible(false);
            new SignUp();
        }
        else if(e.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
