import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame{
    Splash(){
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("download.jpeg"));           // here we took ClassLoader static variable 
        Image image=imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);   // to fit the image in the frame we used image class
        ImageIcon imageIcon2=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon2);
        add(label); 
        setSize(500,500);
        setLocation(600,200);
        setVisible(true);
        try{
            Thread.sleep(3000);    // we kept image for 5 sec in the frame
            setVisible(false);
            new Login();                  // this makes to redirect from splash frame to login frame

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Splash();                     // here we created object
    }
}