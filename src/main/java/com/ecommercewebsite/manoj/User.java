package com.ecommercewebsite.manoj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.data.repository.query.ReturnedType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class User {
	
	
	@PostMapping("/signup")
	public String signup(String userName,String userEmail,String userPassword,
			String userMobile,String userAddress,String userPhoto)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into user(userName,userEmail,userPassword,userMobile,userAddress,userPhoto)"
				+ "values(?,?,?,?,?,?)");
		stmt.setString(1,userName);
		stmt.setString(2,userEmail);
		stmt.setString(3,userPassword);
		stmt.setString(4,userMobile);
		stmt.setString(5,userAddress);
		stmt.setString(6,userPhoto);
		int i=stmt.executeUpdate();
		if(i>0)
		{
			return "successfully registered";
		}
		else {
			return "please try again";
		}
			
		}
	

        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	
	return "";
}
	
	
	
	@PostMapping("/api/login")
	public String login(String email,String password)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root",
				"#jaibalaji754#");
		Statement stmt=con.createStatement();
		String query="select userPassword,userType from user where userEmail='"+email+"'";
		ResultSet rs=stmt.executeQuery(query);
		if(rs.next())
		{
			if(rs.getString("userPassword").equals(password)&& rs.getString("userType").equals("normal")) {
				return "login user successfully";
			}
			else if(rs.getString("userPassword").equals(password)&& rs.getString("userType").equals("admin"))
			{
				return "admin login successfully";
				
			}
				else {
				return "please check your  password";
			}
		}else {
			return "pls signup first";
		}
	}
			
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	return "";
	}
	
	

	@GetMapping("/api/forgotPassword")
	public String forgotPassword(String email)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
		Statement stmt=con.createStatement();
		String query="select * from user where userEmail='"+email+"'";
		ResultSet rs=stmt.executeQuery(query);
		if(rs.next())
		{
			Random random=new Random();
			int otp1 = random.nextInt(9999);
			System.out.println("OTP: "+otp1);
			String query2 ="update user set otp=? where userEmail=('"+email+"')";
			
			PreparedStatement st=con.prepareStatement(query2);
			st.setInt(1, otp1);
			
			int i=st.executeUpdate();
			
			System.out.println("no of statement:"+i );
			
			Email("manojshivh754@gmail.com","wffhqebcmtjyoxqb",
					email,"otp to reset password is",Integer.toString(otp1));   
			System.out.println( "successfully SendEmail mail");
			return "otp send  successfully";

		
			
		}
	
		
			else
				return "invalid email id";
	}
			
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	
	
	return "";
	}
	
	
	//verify_otp
	
	
	@PostMapping("/api/verify_otp")
	public String verify_otp(String email,int otp)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
			Statement stmt=con.createStatement();
			String query="select otp from user where userEmail='"+email+"'";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				if(rs.getInt("otp")==otp) {
					return " you can successfully reset your password";
				}
				
			else {
				return "invalid otp try again";
			}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "";
		
	}
	
	
	 public static void Email(String from,String password,String to,String sub,String msg){  
         //Get properties object    
         Properties props = new Properties();    
         props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class",    
                   "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");    
         //get Session   
         Session session = Session.getDefaultInstance(props,    
          new javax.mail.Authenticator() {    
          protected PasswordAuthentication getPasswordAuthentication() {    
          return new PasswordAuthentication(from,password);  
          }    
         });    
         //compose message    
         try {    
          MimeMessage message = new MimeMessage(session);    
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
          message.setSubject(sub);    
          message.setText(msg);    
          //send message  
          Transport.send(message);    
          System.out.println("message sent successfully");    
           
         } catch (MessagingException e) {throw new RuntimeException(e);}    
            
	 }}  
	 
	 
	                   