
package Project;

import Project.welcome;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener,MouseListener {
    
    JPanel p1;
    JLabel lbluid,lblpwd,lblhead,lblnewuser,lblrole;
    JTextField txtuid;
    JPasswordField pwd;
    JComboBox drprole;
    JButton btnlogin,btncancel,btnnewuser;
    Font f=new Font("Baskerville Old Face",Font.BOLD,20);
    
    public Project()
    {
        super("Login Form");
        
        Color c=new Color(9, 57, 97);
        Color c1=new Color(250, 250, 250);
        lbluid=new JLabel("Enter the username:");
        lbluid.setBounds(10, 50, 200, 20);
        lbluid.setForeground(c1);
        lbluid.setFont(f);
        txtuid=new JTextField(20);
        txtuid.setBounds(200,50,150,20);
        lblpwd=new JLabel("Enter the password:");
        lblpwd.setForeground(c1);
        lblpwd.setBounds(10, 90, 200, 20);
        lblpwd.setFont(f);
        pwd=new JPasswordField(20);
        pwd.setBounds(200,90,150,20);
        pwd.setEchoChar('*');
        
        lblrole=new JLabel("Role:");
        lblrole.setFont(f);
        lblrole.setForeground(c1);
        lblrole.setBounds(10,130,100,20);
        
        String s1[] = { "","head", "student", "staff", "dean","assistant" }; 
  
        // create droplist 
        drprole = new JComboBox(s1);
        drprole.setBounds(80,130,90,20);
        /*drprole=new JComboBox();
        drprole.addItem("Head");
        drprole.addItem("Student");
        drprole.addItem("Staff");
        drprole.addItem("Dean");*/

        
        btnlogin=new JButton("Login");
        Color c2=new Color(18, 182, 227);
        btnlogin.setBackground(c2);
        btnlogin.setBounds(175,180,100,20);
        btncancel=new JButton("Cancel");
        Color cancel=new Color(245, 56, 63);
        btncancel.setBounds(300,180,100,20);
        btncancel.setBackground(cancel);
        //btncancel.setBackground(Color.red);
        lblnewuser=new JLabel("New User? Click Here");
        lblnewuser.setForeground(c1);
        lblnewuser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblnewuser.setBounds(180, 220, 150, 20);
        
        /*btnnewuser=new JButton("click here");
        btnnewuser.setOpaque(true);
        btnnewuser.setBounds(250,220,90, 20);*/
        
        
         p1=new JPanel(null);
         add(p1);
        
         p1.add(lbluid);p1.add(txtuid);
         p1.add(lblpwd);p1.add(pwd);
         p1.add(lblrole);p1.add(drprole);
         p1.add(btnlogin);p1.add(btncancel);
         p1.add(lblnewuser); //p1.add(btnnewuser);
         p1.setBackground(c);
         
        btnlogin.addActionListener(this); 
        btncancel.addActionListener(this);
         addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                
                evt.getWindow().dispose();
            }
        });
         
         
         lblnewuser.addMouseListener(this);
        
     }
     public void actionPerformed(ActionEvent evt)
     {
        String a,b,r;
        a=txtuid.getText();
        b=pwd.getText();
        r=drprole.getSelectedItem().toString();
        Object obj =evt.getSource(); //returns object
        if(btnlogin==obj)
        {
           try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select role from rolelogin where uid='"+a+"'and pwd='"+b+"'");
            boolean status=rs.next();
            if(status)
            {
                String role1=rs.getString("role");
                if(role1.equals(r))
                {
                    welcome s=new welcome();
                    s.setSize(500,300);
                    s.setLocation(500,250);
                    s.setVisible(true);
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Role doesn't match", "Confirm Role",2);
                }
                
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Password doesn't match", "Confirm Password", 2);

            }
        }catch(Exception ex){ex.printStackTrace();}
        } 
        else if(obj==btncancel)
        {
            txtuid.setText("");
            pwd.setText("");
        }
        
        
     }
     public static void main(String args[])
    {
       Project p= new Project();
        p.setSize(500,300);
        p.setLocation(500,250);
        p.setVisible(true);
    }

   @Override
    public void mouseClicked(MouseEvent e) {
        
        NewUser n=new NewUser();
        n.setSize(500,500);
        n.setLocation(800,250);
        n.setVisible(true);
               
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lblnewuser.setText("<html><a href=''>" + "Register here!!" + "</a></html>");
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lblnewuser.setText("New User? Click Here");
        
    }

}


