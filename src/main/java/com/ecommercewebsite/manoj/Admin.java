package com.ecommercewebsite.manoj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Admin {
	
	
	
	// Admin registration
	
	@PostMapping("/addAdmin")
	public String addAdmin(String userName,String userEmail,String userPassword,
			String userMobile,String userAddress,String userPhoto,String userType)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into user(userName,userEmail,userPassword,userMobile,userAddress,userPhoto,userType)"
				+ "values(?,?,?,?,?,?,?)");
		stmt.setString(1,userName);
		stmt.setString(2,userEmail);
		stmt.setString(3,userPassword);
		stmt.setString(4,userMobile);
		stmt.setString(5,userAddress);
		stmt.setString(6,userPhoto);
		stmt.setString(7,userType);
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
	
	
	
	
	
	//admin getting total no of categories of Products
	 
	 
	 @GetMapping("/api/countCategories")
		public Map<String,Long> countCategories() {
			System.out.println("checking");
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
						"root","#jaibalaji754#");
			
			PreparedStatement stmt=con.prepareStatement("select count(*)  from category ");
			
			ResultSet rs=stmt.executeQuery();
			
			Map<String,Long> map=new HashMap<String,Long>();
			if(rs.next())
			{
				
				map.put("Total Categories",rs.getLong(1));
				
				
				return map;
			
				
			}
			
			
			
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return null;
		}
	 
	
	 
	 
	//admin getting total no of  Products
	 
	 
		 @GetMapping("/api/countProducts")
			public Map<String,Long> countProducts() {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select count(*)  from product ");
				
				ResultSet rs=stmt.executeQuery();
				
				Map<String,Long> map=new HashMap<String,Long>();
				if(rs.next())
				{
					
					map.put("Total products",rs.getLong(1));
					
					
					return map;
				
					
				}
				
				
				
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
		 
		 
		//admin getting total no of  users
		 
		 
		 @GetMapping("/api/countUsers")
			public Map<String,Long> countUsers() {
				System.out.println("checking");
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
							"root","#jaibalaji754#");
				
				PreparedStatement stmt=con.prepareStatement("select count(*)  from user ");
				
				ResultSet rs=stmt.executeQuery();
				
				Map<String,Long> map=new HashMap<String,Long>();
				if(rs.next())
				{
					
					map.put("Total Users",rs.getLong(1));
					
					
					return map;
				
					
				}
				
				
				
				
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
		

}
