
package Project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class welcome extends JFrame implements ActionListener{
    JLabel l,l1;
    JButton b;
    JPanel p1;
    public welcome() 
    {
        super("Successfull Sigin!");
        Color c=new Color(207, 78, 125);
        Font f= new Font("Arial",Font.BOLD,20);
       
        l=new JLabel("WELCOME");
        l.setBounds(180,40,200,30);
	l.setFont(f);
        l.setForeground(Color.WHITE);
        l1=new JLabel("YOU HAVE SUCESSFULLY LOGGED IN");
        l1.setBounds(60,90,800,20);
	l1.setFont(f);
        l1.setForeground(Color.WHITE);
        b=new JButton("NEXT");
        b.setBounds(180,150,100,30);
        b.setBackground(Color.darkGray);
        b.setForeground(Color.WHITE);
        p1=new JPanel(null);
        add(p1);
        
        p1.add(l);p1.add(l1);
        p1.add(b);
        p1.setBackground(c);
        b.addActionListener(this);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                //System.exit(0);
                evt.getWindow().dispose();
            }
        });
        b.addActionListener(this);
            }
    
    public void actionPerformed(ActionEvent evt)
    {

        Object obj=evt.getSource();
        if(obj==b)
        {
            StudentLogin s=new StudentLogin();
            s.setSize(500,500);
            s.setLocation(500,250);
            s.setVisible(true);
            
        }
    }
    public static void main(String args[])
    {
        welcome w=new welcome();
        w.setSize(500,300);
        w.setLocation(500,250);
        w.setVisible(true);
        
    }
}
   