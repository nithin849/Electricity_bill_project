import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MeterInfo extends JFrame implements ActionListener {
    JLabel meterNumText;
    Choice meterChoice,meterTypChoice,phasecodeChoice,billtypeChoice;
    JButton submit;
    String m;
    MeterInfo(String m){
        super("METER INFO");
        this.m=m;
        setSize(700,500);
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(200, 133, 242));
        add(panel);

        JLabel heading =new JLabel("Meter Information");
        heading.setBounds(100,20,200,40);
        heading.setFont(new Font("calibri",Font.BOLD,20));
        panel.add(heading);

        JLabel meternumber=new JLabel("Meter Number");
        meternumber.setBounds(40,70,100,20);
        meternumber.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(meternumber);

        meterNumText=new JLabel(m);
        meterNumText.setBounds(200,70,100,20);
        panel.add(meterNumText); 

        JLabel meternumber1=new JLabel("Meter Location");
        meternumber1.setBounds(40,110,100,20);
        meternumber1.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(meternumber1);

        meterChoice =new Choice();
        meterChoice.add("outside");
        meterChoice.add("inside");
        meterChoice.setBounds(200,110,100,20);
        meterChoice.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(meterChoice);

        JLabel metertype=new JLabel("Meter Type");
        metertype.setBounds(40,150,100,20);
        metertype.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(metertype);

        meterTypChoice =new Choice();
        meterTypChoice.add("electric meter");
        meterTypChoice.add("solar meter");
        meterTypChoice.add("smart meter");
        meterTypChoice.setBounds(200,150,100,20);
        meterTypChoice.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(meterTypChoice);

        JLabel phasecode=new JLabel("Phase Code");
        phasecode.setBounds(40,190,100,20);
        phasecode.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(phasecode);

        phasecodeChoice =new Choice();
        phasecodeChoice.add("011");
        phasecodeChoice.add("022");
        phasecodeChoice.add("033");
        phasecodeChoice.add("044");
        phasecodeChoice.add("055");
        phasecodeChoice.add("066");
        phasecodeChoice.add("077");
        phasecodeChoice.add("088");
        phasecodeChoice.add("099");
        phasecodeChoice.setBounds(200,190,100,20);
        phasecodeChoice.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(phasecodeChoice);

        JLabel billtype=new JLabel("Bill Type");
        billtype.setBounds(40,230,100,20);
        billtype.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(billtype);

        billtypeChoice =new Choice();
        billtypeChoice.add("Normal");
        billtypeChoice.add("Industrial");
        billtypeChoice.setBounds(200,230,100,20);
        billtypeChoice.setFont(new Font("serif",Font.PLAIN,15));
        panel.add(billtypeChoice);

        JLabel day=new JLabel("30 day billing here");
        day.setBounds(40,270,160,20);
        day.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(day);

        JLabel note=new JLabel("Note -->");
        note.setBounds(40,310,250,20);
        note.setFont(new Font("monospace",Font.BOLD,15));
        panel.add(note);

        JLabel days=new JLabel("By default bill is calculated for 30days only");
        days.setBounds(40,340,290,20);
        days.setFont(new Font("monospace",Font.PLAIN,15));
        panel.add(days);

        submit=new JButton("SUBMIT");
        submit.setBounds(80,390,100,20);
        submit.setBackground(new Color(23, 22, 23));
        submit.setForeground(new Color(214, 206, 214));
        submit.addActionListener(this);
        submit.setFont(new Font("monospace",Font.BOLD,15));
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("meter.png"));
        Image image=imageIcon.getImage().getScaledInstance(320, 250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        add(label,"East");


        setLocation(400,200);
        setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==submit){
                String smeter=m;
                String smeterLocation=meterChoice.getSelectedItem();
                String smeterTypeChoice=meterTypChoice.getSelectedItem();
                String sphasecodechoice=phasecodeChoice.getSelectedItem();
                String sbilltype=billtypeChoice.getSelectedItem();
                String sday="30";
                String query="insert into meter values('"+smeter+"','"+smeterLocation+"','"+smeterTypeChoice+"','"+sphasecodechoice+"','"+sbilltype+"','"+sday+"')";
                try {
                    Database d=new Database();
                    d.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "successfully meter information added ");
                    setVisible(false);
                    
                } catch (Exception E) {
                    E.printStackTrace();
                    System.out.println(E);
                }
            }
            else{
                setVisible(false);
            }

        }
    public static void main(String[] args) {
        new MeterInfo("");
    }
}
