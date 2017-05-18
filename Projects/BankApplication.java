import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.math.*; // for BigInteger format
public class mybank implements ActionListener
{
	JFrame jf,jf1,jf2;
	JButton jb1,jb2,jb3,sb1,sb2,sb3,sbv,login,forg,fb0,fb1,fb2,fb3,fbk;
	JTextField jtf,stf1,stf2,stf3,stf4,stf5,stfa,stfm,ftf,ftf1,ftf2;
	JPasswordField jpf,spf1;
	JLabel l1,l2,sl1,sl2,sl3,sl4,sl5,sla,sls,slm,slad,fla,fld,flm;
	JPanel jpn,jps,jp1,jp2,jp,jpl,jpl1,fp,fp0,fp1,fp2,fp3,fp4,ftp;
	JRadioButton jr1,jr2;
	ButtonGroup bg;
	CardLayout cl,cl1;
	FlowLayout fl;
	JTextArea jta,jtd,fta;
	JComboBox com;
	Connection con; // database variables starts here
	PreparedStatement ps,ps1;
	Statement st;
	ResultSet rs,rsf;
	
	JLabel image,image1,image2;
	ImageIcon i1,i2,i3;
	
	
	String name,email,pswd,dob,address,sex;
	long uid;
	//BigInteger
	long mobile;
	//after login 
	JTextField ltf;
	JButton add,withdraw,check,transaction,logout,cancel,proceed,done;
	JTextArea details;
	JLabel lla;
	long k,ac; // to save uid for further use
	JScrollPane scroll;
	
	int count;
	String em;
	long mo;
	
	mybank()
	{
		jf = new JFrame("Welcome Page");
	//	jf.setLayout(null);
		jf.setSize(500,600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpn = new JPanel(); // fixed North panel
		jb1 = new JButton("Login");
		jb2 = new JButton("SignUp");
		jb3 = new JButton("Home");
		jb1.setBounds(400,200,100,80);
		jb2.setBounds(600,200,100,80);
		jb3.setBounds(800,200,100,80);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jpn.add(jb1);
		jpn.add(jb2);
		jpn.add(jb3);
	//jf.add(jb1);
	//jf.add(jb2);
		jpn.setBackground(Color.GRAY);
		jf.add(jpn,BorderLayout.NORTH);
		//jf.add(jpn);
		/*------------LOGIN PANEL----------------*/
		jp1 = new JPanel();
		l1 = new JLabel("Account No.");
		l2 = new JLabel("Password");
		l1.setBounds(100,150,150,30);
		l2.setBounds(100,190,150,30);
		jtf = new JTextField(30); // Account 
		jpf = new JPasswordField(30); // Password
		jtf.setText("");
		jpf.setText("");
		jtf.setBounds(200,150,180,30);
		jpf.setBounds(200,190,180,30);
		login = new JButton("Login");
		forg = new JButton("Forgot Password ??");
		login.setBounds(160,230,100,40);
		forg.setBounds(270,230,160,40);
		login.addActionListener(this);
		forg.addActionListener(this);
		jp1.add(l1);
		jp1.add(l2);
		jp1.add(jtf);
		jp1.add(jpf);
		jp1.add(login);
		jp1.add(forg);
		jp1.setLayout(null);
		/*--------------------SIGNUP PANEL--------------*/
		jp2 = new JPanel();
		jp2.setLayout(null);
		sl1 = new JLabel("First Name");
		sl2 = new JLabel("Last Name");
		sl3 = new JLabel("Password");
		sl4 = new JLabel("Date Of Birth");
		sl5 = new JLabel("Email ID");
		sla = new JLabel("Account/UID");
		slm = new JLabel("Mobile No.");
		sls = new JLabel("I am..");
		slad = new JLabel("Address");
		stf1 = new JTextField(50); //fname
		stf2 = new JTextField(50); //lname
		stf5 = new JTextField(100);//email
		stfa = new JTextField(30); //Account Number asf UID
		spf1 = new JPasswordField(50); //pswd
		stfm = new JTextField(10); // mobile number
		stf3 = new JTextField(2); //dob day
		stf4 = new JTextField(4); // dob year
		jta = new JTextArea(); // text area
	
		//jta.setText("Terms and Conditions");
		//jta.setEditable(false);
		jp2.add(jta); 
		
		String [] a ={"January","February","March","April","May","June","July","August","September","October","November","December"}; //dob month
		com = new JComboBox(a); // dob month
		sb1 = new JButton("Submit");
		sbv = new JButton("Validate");
		sb1.addActionListener(this);
		sbv.addActionListener(this);
		jr1 = new JRadioButton("Male",true);
		jr2 = new JRadioButton("Female");
		jr1.addActionListener(this);
		jr2.addActionListener(this);
		bg = new ButtonGroup();
		bg.add(jr1);
		bg.add(jr2);
		sl1.setBounds(100,150,150,30);
		sl2.setBounds(100,190,150,30);
		sl5.setBounds(100,230,150,30);
		sl3.setBounds(100,310,150,30);
		slm.setBounds(100,350,150,30);
		sl4.setBounds(100,390,150,30);
		sla.setBounds(100,270,150,30);
		slad.setBounds(100,470,150,30);
		sls.setBounds(100,430,150,30);
		stf1.setBounds(200,150,200,30);
		stf2.setBounds(200,190,200,30);
		stf5.setBounds(200,230,200,30);
		stfa.setBounds(200,270,200,30);
		stf3.setBounds(200,390,50,30);
		com.setBounds(250,390,80,30);
		stf4.setBounds(331,390,70,30);
		spf1.setBounds(200,310,200,30);
		stfm.setBounds(200,350,200,30);
		jr1.setBounds(200,430,90,30);
		jr2.setBounds(300,430,90,30);
		jta.setBounds(200,470,300,100);
		

		sb1.setBounds(300,610,100,40);
		sbv.setBounds(200,610,100,40);
		/*stf1.setText("");
		stf2.setText("");
		spf1.setText("");
		//spf1.setText("");
		//jtf.setText(""); */ //it doesn't work here but only in actionPerformed
		//stf4.setText("");
		//t =Integer.parseInt(stf4.getText());
		//System.out.println(t);
		jp2.add(sl1);
		jp2.add(sl2);
		jp2.add(sl3);
		jp2.add(sl4);
		jp2.add(sl5);
		jp2.add(sla);
		jp2.add(sls);
		jp2.add(slad);
		jp2.add(stf1);
		jp2.add(stf2);
		jp2.add(stf3);
		jp2.add(stf5);
		jp2.add(com);
		jp2.add(stf4);
		jp2.add(stfa);
		jp2.add(spf1);
		jp2.add(sb1);
		jp2.add(sbv);
		jp2.add(jr1);
		jp2.add(jr2);
		jp2.add(slm);
		jp2.add(stfm);
		jf.add(jp2);
		/*------------------After Login --------------------*/
		jf1 = new JFrame("User Account");
		jf1.setSize(600,600);
	//	jf1.setLayout(null);
		jf1.setLocationRelativeTo(null);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpl1 = new JPanel(); // fixed north
		//jpl1.setLayout(null);
		jpl = new JPanel(); //center panel
		jpl.setLayout(null);
		logout = new JButton("Logout");
		jpl.add(logout);
		lla = new JLabel("Enter Money");
		ltf = new JTextField(40);
		add = new JButton("Deposit");
		withdraw = new JButton("Withdraw");
		check = new JButton("Balance");
		transaction = new JButton("Tranfer Amount");
		details = new JTextArea();
		lla.setBounds(20,150,120,30);
		ltf.setBounds(100,150,120,30);
		add.setBounds(100,200,120,30);
		withdraw.setBounds(100,240,120,30);
		check.setBounds(100,280,120,30);
		transaction.setBounds(100,320,120,30);
		details.setBounds(250,100,400,300);
		details.setEditable(false);
		/*jpl.add(add);
		jpl.add(withdraw);
		jpl.add(check);
		jpl.add(details);
		jpl.add(lla);
		jpl.add(ltf); 
		jpl.add(transaction);*/
		jpl1.add(logout); 
		jf1.add(add);
		jf1.add(withdraw);
		jf1.add(check);
		jf1.add(details);
	//	jf1.add(transaction);
		jf1.add(lla);
		jf1.add(ltf); 
		add.addActionListener(this);
		withdraw.addActionListener(this);
		check.addActionListener(this);
		logout.addActionListener(this);
		transaction.addActionListener(this);
		jpl1.setBackground(Color.GRAY);
		jf1.add(jpl1,BorderLayout.NORTH);
		jf1.add(jpl,BorderLayout.CENTER);
	//	ltf.setLineWrap(true);
    //textArea.setEditable(false);
		jf1.setVisible(false);
		/*---------------Forgot Password---------------*/
		jf2 = new JFrame();
		jf2.setSize(600,500);
		jf2.setLocationRelativeTo(null);
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fp = new JPanel();
		cl1 = new CardLayout();
		fp.setLayout(cl1);
		
		fp0 = new JPanel();
		fp0.setLayout(null);
		fp0.setBackground(Color.GREEN);
		fb0 = new JButton("Proceed");
		fb0.setBounds(200,200,100,30);
		//fb0.setOpaque(true);
		fp0.add(fb0);
		fb0.addActionListener(this);
		
		fp1 = new JPanel();
		fp1.setLayout(null);
		fla = new JLabel("Account No.");
		ftf = new JTextField(30);
		fb1 = new JButton("Next");
		fla.setBounds(100,200,150,30);
		ftf.setBounds(200,200,200,30);
		fb1.setBounds(250,250,100,30);
		fp1.add(fla);
		fp1.add(ftf);
		fp1.add(fb1);
		fb1.addActionListener(this);
		
		fp2 = new JPanel();
		fp2.setLayout(null);
		fld = new JLabel("Email");
		ftf1 = new JTextField(30);
		fb2 = new JButton("Next");
		fld.setBounds(100,200,150,30);
		ftf1.setBounds(200,200,200,30);
		fb2.setBounds(250,250,100,30);
		fp2.add(fld);
		fp2.add(ftf1);
		fp2.add(fb2);
		fb2.addActionListener(this);
		
		fp3 = new JPanel();
		fp3.setLayout(null);
		flm = new JLabel("Mobile No.");
		ftf2 = new JTextField(30);
		fb3 = new JButton("Next");
		flm.setBounds(100,200,150,30);
		ftf2.setBounds(200,200,200,30);
		fb3.setBounds(250,250,100,30);
		fp3.add(flm);
		fp3.add(ftf2);
		fp3.add(fb3);
		fb3.addActionListener(this);
		
		fp4 = new JPanel();
		fp4.setLayout(null);
		fta = new JTextArea();
		fta.setEditable(false);
		fbk = new JButton("Ok,Got it.");
		fta.setBounds(200,50,250,250);
		fbk.setBounds(300,350,100,30);
		fp4.add(fta);
		fp4.add(fbk);
		fbk.addActionListener(this);
		
		
		fp.add(fp0,"Note");
		fp.add(fp1,"Account");
		fp.add(fp2,"Email");
		fp.add(fp3,"Mobile");
		fp.add(fp4,"Password");
		
		
		jf2.add(fp);
		jf2.setVisible(false);
		
		/*---------------------CARD LAYOUT----------------*/
		jp = new JPanel();
		cl = new CardLayout();
		jp.setLayout(cl);
		jps = new JPanel();
		//jps.setLayout(new BorderLayout());
		//fl = new FlowLayout();
		//jps.setLayout(null);
		//jf.setBackground(Color.BLUE);
		i1 = new ImageIcon("Logged.jpg");
		i2 = new ImageIcon("bit.jpg");
		image = new JLabel();
		image.setBackground(Color.BLUE);
		image.setOpaque(true);
		//jp.setIconImage(i1);
		image.setIcon(i1);
		//image.setBounds(1,1,1366,768);
		//jps.add(image,BorderLayout.CENTER);
		//jps.setContentPane(new JLabel(new ImageIcon("bit.jpg")));
		jps.add(image);
		jf.setIconImage(i2.getImage());
		jp.add(jps,"Home");
		jp.add(jp1,"Login");
		jp.add(jp2,"SignUp");
		// Logged In frame
		jp.add(jpl,"LoggedIn");
		jf.add(jp);
		jf.setVisible(true);
		database();
		//ctable();
		//mtable();
		
	}
	/*-----------------BANK DATABASE--------------------*/
	public void database()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded");
	  //con = DriverManager.getConnection("jdbc:/mysql://localhost/bank","root","password"); wrong not slash here
		con = DriverManager.getConnection("jdbc:mysql://localhost/bank","root","password");
		System.out.println("Connection Made");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	/*----Create users table if doesn't Exist--------------*/
	public void ctable()
	{ try
		{
		st = con.createStatement();
		System.out.println("Cart Created");
		String s = "create table users (name varchar(100),email varchar(70), uid long, password varchar(16),mobile long,dob varchar(30),gender varchar(10),address varchar(200), Year int(6))";
		st.executeUpdate(s);
		System.out.println("Users Table Created");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void mtable()
	{
		try
		{
			st=con.createStatement();
			String s="create table mtable(uid long,password varchar(16),Amount long)";
			st.executeUpdate(s);
			System.out.println("Money Table Created");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	/*--------------Insert Data into table-----------*/
	public void insert()
	{
		try
		{
		java.util.Date dt = new java.util.Date();
		int y = 1900+dt.getYear();
		ps = con.prepareStatement("insert into users(name,email,uid,password,mobile,dob,gender,address,Year) values(?,?,?,?,?,?,?,?,?)");
		ps.setString(1,name);
		ps.setString(2,email);
		ps.setLong(3,uid);
		ps.setString(4,pswd);
		ps.setLong(5,mobile);
		ps.setString(6,dob);
		ps.setString(7,sex);
		ps.setString(8,address);
		ps.setInt(9,y);
		ps.executeUpdate();
		ps1 = con.prepareStatement("insert into mtable(uid,password,Amount) values(?,?,0)");
		ps1.setLong(1,uid);
		ps1.setString(2,pswd);
		//ps1.setLong()
		ps1.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	} 
	/*--------------MAIN PROGRAM--------------*/
	public static void main(String [] r)
	{
		new mybank();
		java.util.Date date = new java.util.Date();
		System.out.println(date);
		int i=1900+date.getYear();
		System.out.println(i);
		Scanner sc = new Scanner(System.in);
	//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss a");
	//System.out.println(sdf);	//System.out.println(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	/*	String ch = sc.next();
		if(Character.isLetter(ch))
		{
			System.out.println("abcd");
		} */
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==jb1)
		{
			cl.show(jp,"Login");
			//jf.setVisible(false);
		}
		else if(ae.getSource()==jb2)
		{
			cl.show(jp,"SignUp");
		}
		else if(ae.getSource()==jb3)
		{
			cl.show(jp,"Home");
		}
		else if(ae.getSource()==sb1)
		{
			try
			{//validation here
			String st =stf1.getText();
			String st1 = stf2.getText();
			String st2 = stf5.getText();
			String st3 = stfa.getText();
			int a =Integer.parseInt(stf3.getText());
			int b =Integer.parseInt(stf4.getText());
			//System.out.println(a);
			char [] stk =st.toCharArray();
			char [] st1k = st1.toCharArray();
			int c1=0,c2=0; // count values for numbers found in string
			for(int o =0;o<st.length();o++)
			{
			if(Character.isDigit(stk[o]))
			{
				c1++;
			}
			}
			if(c1>0)
			{
				JOptionPane.showMessageDialog(jf,"First Name Shouldn't contain numberic values","Name Error",JOptionPane.ERROR_MESSAGE);
			}
			for(int o =0;o<st1.length();o++)
			{
			if(Character.isDigit(st1k[o]))
			{
				c2++;
			}
			}
			if(c2>0)
			{
				JOptionPane.showMessageDialog(jf,"Last Name Shouldn't contain numberic values","Name Error",JOptionPane.ERROR_MESSAGE);
			}
			if(a>31 ||a==0)
			{
			JOptionPane.showMessageDialog(jf,"Please Check the day entered","Day Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("DAY ERROR");
			}  
			java.util.Date d = new java.util.Date();
			int i=1900+d.getYear();
			//System.out.println(i);
			if(b<1950 || b>i)
			{
			JOptionPane.showMessageDialog(jf,"Please Check the year entered","Year Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("Year ERROR");
			} 
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(jf,"Please Check all values entered or not","Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("ERROR");
			}
			try{
			//String s =stf1.getText()+" "+stf2.getText()+"\t"+spf1.getText();
			//System.out.println(s);
			name = stf1.getText()+" "+stf2.getText();
			email = stf5.getText();
			uid = Long.parseLong(stfa.getText());
			pswd = spf1.getText();
			long l = Long.parseLong(stfm.getText());
			System.out.println(l);
			mobile=l;
			dob = stf3.getText()+"/"+com.getSelectedItem()+"/"+stf4.getText();
			address = jta.getText();
			//String sex;
	
			if(jr1.isSelected())
			{
				sex=jr1.getActionCommand();
			}
			else
			{
				sex=jr2.getActionCommand();
			}
			insert();
			stf1.setText("");
			stf2.setText("");
			stf3.setText("");
			stf4.setText("");
			stfa.setText("");
			stfm.setText("");
			stf5.setText("");
			spf1.setText(""); 
			jta.setText("");
		//	System.out.println(jta.getText());
			System.out.println(sex);
			}
			catch(Exception e)
			{
			System.out.println(e);
			}
			
			
			
		}
		else if(ae.getSource()==sbv)
		{
			
			try
			{//validation here
			String st =stf1.getText();
			String st1 = stf2.getText();
			String st2 = stf5.getText();
			String st3 = stfa.getText();
			int a =Integer.parseInt(stf3.getText());
			int b =Integer.parseInt(stf4.getText());
			//System.out.println(a);
			char [] stk =st.toCharArray();
			char [] st1k = st1.toCharArray();
			int c1=0,c2=0; // count values for numbers found in string
			for(int o =0;o<st.length();o++)
			{
			if(Character.isDigit(stk[o]))
			{
				c1++;
			}
			}
			if(c1>0)
			{
				JOptionPane.showMessageDialog(jf,"First Name Shouldn't contain numberic values","Name Error",JOptionPane.ERROR_MESSAGE);
			}
			for(int o =0;o<st1.length();o++)
			{
			if(Character.isDigit(st1k[o]))
			{
				c2++;
			}
			}
			if(c2>0)
			{
				JOptionPane.showMessageDialog(jf,"Last Name Shouldn't contain numberic values","Name Error",JOptionPane.ERROR_MESSAGE);
			}
			if(a>31 ||a==0)
			{
			JOptionPane.showMessageDialog(jf,"Please Check the day entered","Day Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("DAY ERROR");
			}  
			java.util.Date d = new java.util.Date();
			int i=1900+d.getYear();
			//System.out.println(i);
			if(b<1950 || b>i)
			{
			JOptionPane.showMessageDialog(jf,"Please Check the year entered","Year Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("Year ERROR");
			} 
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(jf,"Please Check all values entered or not","Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("ERROR");
			}
			//catch()
		}
		else if(ae.getSource()==login)
		{
			
			try
			{
				k = Long.parseLong(jtf.getText());
				ps = con.prepareStatement("select *from users where uid =?");
				ps.setLong(1,k);
				rs=ps.executeQuery();
				int county =0;
			//	st =con.createStatement();
			//	String s = "Select *from users";
				//rs=st.executeQuery(s);
			//	rs=ps.executeQuery();
				while(rs.next())
				{ 
					county++;
					if(Long.parseLong(rs.getString("uid"))==k)
					{
					if(rs.getString("password").equals(jpf.getText()))
					{
						System.out.println("Working Credentials");
						System.out.println("Logged in");
					//	cl.show(jp,"LoggedIn");
						jf.setVisible(false);
						jf1.setVisible(true);
						details.setText("Account Name :"+rs.getString("name")+"\n"+"Account No. :"+rs.getString("uid")+"\n"+"Mobile No. :"+rs.getString("mobile")+"\n"+"e-Mail :"+rs.getString("email"));
					}
					else
					{
					JOptionPane.showMessageDialog(jf,"MisMatch Credentials, try again","Login Error",JOptionPane.ERROR_MESSAGE);	
					} 
					}
					if(Long.parseLong(rs.getString("uid"))!=k)
					{
						System.out.println(" Not Logged in");
					JOptionPane.showMessageDialog(jf,"Wrong Credentials. Account Doesn't Exist","Login Error",JOptionPane.ERROR_MESSAGE);	
					}
				}
				
				jtf.setText("");
				jpf.setText("");
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(jf,"Please Enter the Values and try again","Data not Entered",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==forg)
		{
			System.out.println("Forgot Password");
			jf.setVisible(false);
			jf2.setVisible(true);
		}
		/*---After Login Actions----*/
		else if(ae.getSource()==add)
		{
			java.util.Date dt = new java.util.Date();
			try
			{
				ps =con.prepareStatement("update mtable set Amount = Amount+? where uid =?");
				long m = Long.parseLong(ltf.getText());
				ps.setLong(1,m);
				ps.setLong(2,k);
				ps.executeUpdate();
				ltf.setText("");
				ps1 = con.prepareStatement("select *from mtable,users where mtable.uid =? AND users.uid=?");
				ps1.setLong(1,k);
				ps1.setLong(2,k);
				rs = ps1.executeQuery();
				while(rs.next())
				{	details.setText("Account Name :"+rs.getString("name")+"\n"+"Account No. :"+rs.getString("uid")+"\n"+"Mobile No. :"+rs.getString("mobile")+"\n"+"e-Mail :"+rs.getString("email"));
					details.append("\n\n\t"+"----------------Transaction Details-----------------"+"\n"+dt+"\nTxn No : XXXX7916"+"\nResponse Code : 000"+"\nAmount Deposited :"+m+"\nCurrent Balance :"+rs.getString("Amount")+"$");
				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(jf1,"Enter Amount and Click Deposit","Not Entered",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==withdraw)
		{
			try
			{
				ps1 = con.prepareStatement("select *from mtable,users where mtable.uid =? AND users.uid=?");
				ps1.setLong(1,k);
				ps1.setLong(2,k);
				long m =Long.parseLong(ltf.getText());
				rs = ps1.executeQuery();
				java.util.Date dt = new java.util.Date();
				while(rs.next())
				{	
				if(Long.parseLong(rs.getString("Amount"))!=0 && m<Long.parseLong(rs.getString("Amount")))
				{
				ps =con.prepareStatement("update mtable set Amount = Amount-? where uid =?");
				//m = Long.parseLong(ltf.getText());
				ps.setLong(1,m);
				ps.setLong(2,k);
				ps.executeUpdate();
				ltf.setText("");
				details.setText("Account Name :"+rs.getString("name")+"\n"+"Account No. :"+rs.getString("uid")+"\n"+"Mobile No. :"+rs.getString("mobile")+"\n"+"e-Mail :"+rs.getString("email"));
				details.append("\n\n\t"+"----------------Transaction Details-----------------"+"\n"+dt+"\nTxn No : XXXX7916"+"\nResponse Code : 000"+"\nAmount Withdrawn :"+m+"\nCurrent Balance :"+rs.getString("Amount")+"$");
				}
				else
				{
					JOptionPane.showMessageDialog(jf1,"Insufficient Balance, Please Contact Bank Manager..","No Amount Found",JOptionPane.ERROR_MESSAGE);
				}
				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(jf1,"Enter Amount and Click Withdraw","Not Entered",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==check)
		{
			try
			{
				ltf.setText("");
				ps =con.prepareStatement("select *from mtable,users where mtable.uid =? AND users.uid=?");
				ps.setLong(1,k);
				ps.setLong(2,k);
				rs = ps.executeQuery();
				java.util.Date dt = new java.util.Date();
				while(rs.next())
				{
					System.out.println("Your Current Balance is :"+rs.getString("Amount"));
					details.setText("Account Name :"+rs.getString("name")+"\n"+"Account No. :"+rs.getString("uid")+"\n"+"Mobile No. :"+rs.getString("mobile")+"\n"+"e-Mail :"+rs.getString("email"));
					details.append("\n\n\t"+"----------------Transaction Details-----------------"+"\n"+dt+"\nTxn No : XXXX7916"+"\nResponse Code : 000"+"\n"+"Current Balance :"+rs.getString("Amount")+"$");
					
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==logout)
		{
			jf.setVisible(true);
			jf1.setVisible(false);
			cl.show(jp,"Home");
			details.setText("");
			ltf.setText("");
		}
		/*----Forgot Frame----------*/
		else if(ae.getSource()==fb0)
		{
			cl1.show(fp,"Account");
			
		}
		else if(ae.getSource()==fb1)
		{
			try
			{
			st = con.createStatement();
			String p = "Select *from users";
			System.out.println("Button Pressed");
			ac = Long.parseLong(ftf.getText());
			System.out.println(ac);
			rsf = st.executeQuery(p);
			ps = con.prepareStatement("select *from users where uid =?");
			ps.setLong(1,ac);
			rs = ps.executeQuery();
			rsf =rs;
			count=0;
			while(rs.next())
			{
				count++;
			}
				if(count>0)
				{
				cl1.show(fp,"Email");
				}
				else
				{
					JOptionPane.showMessageDialog(jf2,"Account Number Not Found","No Entry Of Data",JOptionPane.ERROR_MESSAGE);
				} 			
				
			
		//	cl1.show(fp,"")
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(jf2,"Account Number Not Entered","No Entry Of Data",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		else if(ae.getSource()==fb2)
		{
			try
			{
			String em = ftf1.getText();
			if(em.length()==0)
			{
			JOptionPane.showMessageDialog(jf2,"Email Not Entered","No Entry Of Data",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				ps = con.prepareStatement("Select *from users where uid =? and email=?");
				ps.setLong(1,ac);
				ps.setString(2,em);
				rs = ps.executeQuery();
				count =0;
				while(rs.next())
				{
						count++;
				}
				if(count>0)
				{
					cl1.show(fp,"Mobile");
					System.out.println(em);
				}
					else
					{
						JOptionPane.showMessageDialog(jf2,"Email Not Found","Not Found",JOptionPane.ERROR_MESSAGE);
					}
			}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(jf2,"Email Not Entered","No Entry Of Data",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(ae.getSource()==fb3)
		{
			//System.out.println("Password is :"+ftf2.getText());
			try
			{
			long mb = Long.parseLong(ftf2.getText());
			ps = con.prepareStatement("Select *from users where uid =? and mobile=?");
			ps.setLong(1,ac);
			ps.setLong(2,mb);
			rs = ps.executeQuery();
			System.out.println(count);
			count =0;
			String pa;
			while(rs.next())
			{
				count++;
				pa=rs.getString("password");
				fta.setText("Your Password is :"+pa);
			}
			if(count>0)
			{
			System.out.println(mb);
			cl1.show(fp,"Password");
			}
			else
			{
			JOptionPane.showMessageDialog(jf2,"Mobile No. Not Found","Not Found",JOptionPane.ERROR_MESSAGE);	
			}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(jf2,"Mobile Number Not Entered","No Entry Of Data",JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		else if(ae.getSource()==fbk)
		{
			fta.setText("");
			ftf2.setText("");
			ftf1.setText("");
			ftf.setText("");
			jf2.setVisible(false);
			jf.setVisible(true);
			cl.show(jp,"Home");
			cl1.show(fp,"Note");
			
		}
		
	}
			
	

}
