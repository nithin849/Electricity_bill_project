import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Payment extends JFrame implements ActionListener {
    String meter_number;
    JButton back;

    Payment(String meter_number){
        super("PAYMENT PAGE");
        this.meter_number=meter_number;

        setLayout(null);
        JEditorPane j=new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://pay.google.com/about/");
            j.setBounds(300,150,800,600);
        } catch (Exception e) {
            e.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html> ERROR! ERROR !  ERROR  ! </html>");
        }

        JScrollPane p=new JScrollPane(j);
        add(p);

        back =new JButton("BACK");
        back.setBounds(600,40,100,20);
        back.setFont(new Font("serif", Font.BOLD,20));
        back.addActionListener(this);
        add(back);


        setBounds(300,150,800,600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setVisible(false);
        new PayBill(meter_number);
    }
    public static void main(String[] args) {
        new Payment("");
    }
}
