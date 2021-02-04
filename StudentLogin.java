
package Project;


import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class StudentLogin extends Frame implements ActionListener {
    
    Label lblname,lblrno;
    TextField txtname,txtrno;
    Button btnin,btnup,btndel,btnview,btnprev;
    Panel p1;
    Font f=new Font("Baskerville Old Face",Font.BOLD,16);
    public StudentLogin()
    {
        super("User Update Form");
        Color c=new Color(9, 57, 97);
        lblname=new Label("Enter the name:");
        lblname.setForeground(Color.white);
        lblname.setBounds(10, 50, 150, 20);
        lblname.setFont(f);
        
        lblrno=new Label("Enter the roll number:");
        lblrno.setForeground(Color.white);
        lblrno.setFont(f);
        lblrno.setBounds(10, 80, 180, 20);
        
        txtname=new TextField(20);
        txtname.setBounds(200,50,150,20);
        txtrno=new TextField(20);
        txtrno.setBounds(200,80,150,20);
        
        btnin=new Button("Insert");
        btnin.setBounds(175,120,100,20);
        btnup=new Button("Update");
        btnup.setBounds(280,120,100,20);
        btndel=new Button("Delete");
        btndel.setBounds(175,160,100,20);
        btnview=new Button("View");
        btnview.setBounds(280,160,100,20);
        
        btnprev=new Button("Previous  <=");
        btnprev.setBounds(220,200,100,20);
        
        p1=new Panel(null);
        add(p1);
        p1.add(lblname);p1.add(txtname);
        p1.add(lblrno);p1.add(txtrno);
        p1.add(btnin);p1.add(btnup);p1.add(btndel);p1.add(btnview);
        p1.add(btnprev);
        p1.setBackground(c);
        btnin.addActionListener(this); //3-register the event
        btnup.addActionListener(this);
        btndel.addActionListener(this); 
        btnview.addActionListener(this);
        btnprev.addActionListener(this);
       addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                //System.exit(0);
                evt.getWindow().dispose();
            }
        });
    }
    public void actionPerformed(ActionEvent evt) //4-override the method
    {
        String a;
        int b;
        a=txtname.getText();
        b=Integer.parseInt(txtrno.getText());
        Object obj =evt.getSource(); //returns object
        if(btnin==obj)
        {
            try
            {
                
                String query="insert into studentlog(name,rollno) values('"+a+"',"+b+")";
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                Statement stmt=conn.createStatement();
                int i=stmt.executeUpdate(query);
                if(i>0)
                {
                    System.out.println("record added sucessfully!");
                }
                else
                {
                    System.out.println("error");
                }
                conn.close();
        }catch(Exception ex){ex.printStackTrace();}
        }
        else if(btnup==obj)
        {
            try
        {
           
            String query="update studentlog set name='"+b+"' where rollno="+a+"";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            Statement stmt=conn.createStatement();
            int i=stmt.executeUpdate(query);
            if(i>0)
            {
                System.out.println("record updated sucessfully!");
            }
            else
            {
                System.out.println("error");
            }
            conn.close();
        }catch(Exception ex){ex.printStackTrace();}
        }
        else if(btndel==obj)
        {
            try
        {
            
            String query="delete from studentlog where rollno=("+b+")";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            Statement stmt=conn.createStatement();
            int i=stmt.executeUpdate(query);
            if(i>0)
            {
                System.out.println("record deleted sucessfully!");
            }
            else
            {
                System.out.println("error");
            }
            conn.close();
        }catch(Exception ex){ex.printStackTrace();}
        }
        else if(btnview==obj)
        {
            try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from studentlog");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
            }
            conn.close();
        }catch(Exception ex){ex.printStackTrace();}
        }
        /*else if(btnprev==obj)// optional if you want to do a previous page so you can skip it I did this because I did a small project
        {
            Project p= new Project();
            p.setSize(500,300);
            p.setLocation(500,250);
            p.setVisible(true);
        }*/
    }
    public static void main(String args[])
    {
        StudentLogin s= new StudentLogin();
        s.setSize(500,400);
        s.setLocation(500,250);
        s.setVisible(true);
    }

  
}
