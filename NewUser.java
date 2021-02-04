
package Project;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class NewUser extends JFrame implements ActionListener {
    
    JPanel p1;
    JLabel lbluid,lblpwd,lblconpwd,lblrole;
    JTextField txtuid;
    JPasswordField pwd,conpwd;
    JComboBox drprole;
    JButton btnsignin,btnrefresh;
    Font f=new Font("Baskerville Old Face",Font.BOLD,20);
    
    public NewUser()
    {
        super("New User Form");
        
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
        lblconpwd=new JLabel("Enter the password:");
        lblconpwd.setForeground(c1);
        lblconpwd.setBounds(10, 120, 200, 20);
        lblconpwd.setFont(f);
        
        pwd=new JPasswordField(20);
        pwd.setBounds(200,90,150,20);
        pwd.setEchoChar('*');
        conpwd=new JPasswordField(20);
        conpwd.setBounds(200,120,150,20);
        conpwd.setEchoChar('*');
        
        lblrole=new JLabel("Role:");
        lblrole.setFont(f);
        lblrole.setForeground(c1);
        lblrole.setBounds(10,150,100,20);
        
        String s1[] = { "","head", "student", "staff", "dean","assistant" }; 
  
        // create droplist 
        drprole = new JComboBox(s1);
        drprole.setBounds(80,150,90,20);
        /*drprole=new JComboBox();
        drprole.add("Head");
        drprole.add("Student");
        drprole.add("Staff");
        drprole.add("Dean");*/

        
        btnsignin=new JButton("Signin");
        Color c2=new Color(18, 182, 227);
        btnsignin.setBackground(c2);
        btnsignin.setBounds(175,180,100,20);
        btnrefresh=new JButton("Refresh");
        Color refresh=new Color(65, 209, 70);
        btnrefresh.setBounds(300,180,100,20);
        btnrefresh.setBackground(refresh);
        
        p1=new JPanel(null);
         add(p1);
        
         p1.add(lbluid);p1.add(txtuid);
         p1.add(lblpwd);p1.add(pwd);
         p1.add(lblconpwd);p1.add(conpwd);
         p1.add(lblrole);p1.add(drprole);
         p1.add(btnsignin);p1.add(btnrefresh);
         
         p1.setBackground(c);
         
        btnsignin.addActionListener(this); 
        btnrefresh.addActionListener(this);
         addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                
                evt.getWindow().dispose();
            }
        });
    }
    public void actionPerformed(ActionEvent evt)
     {
        String a,b,r,c;
        a=txtuid.getText();
        b=pwd.getText();
        c=conpwd.getText();
        r=drprole.getSelectedItem().toString();
        Object obj =evt.getSource();
        if(btnsignin==obj)
        {
           try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            Statement stmt=conn.createStatement();
            
            
            //boolean status=rs.next();
            
                
                if(b.equals(c))
                {
                    if(r.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Enter role!!", "Empty Field",3);
                        
                    }
                    else
                    {
                        int rs=stmt.executeUpdate("insert into rolelogin(uid,pwd,role) values('"+a+"','"+b+"','"+r+"')");
                        if(rs>0)
                        {
                                JOptionPane.showMessageDialog(null, "Successful Registration", "New User Created",1);
                        }
                        else 
                        {
                            JOptionPane.showMessageDialog(null, "Wrong info!!", "Confirm Password", 2);

                        }
                        
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Password doesn't match", "Confirm Password",0);
                }
             
            
        }catch(Exception ex){ex.printStackTrace();}
        } 
        else if(obj==btnrefresh)
        {
            txtuid.setText("");
            pwd.setText("");
            conpwd.setText("");
        }
        
        
     }
        
     
     public static void main(String args[])
    {
       NewUser n= new NewUser();
        n.setSize(500,500);
        n.setLocation(800,250);
        n.setVisible(true);
    }

}
