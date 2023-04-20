package com.ecommercewebsite.manoj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Category {
	
	@GetMapping("/api/addCategory")
	public String addCategory(String categoryTitle,String categoryDescription)
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into category(categoryTitle,categoryDescription)values(?,?)");
		stmt.setString(1, categoryTitle);
		stmt.setString(2,categoryDescription);
		
		int i=stmt.executeUpdate();
		if(i>0)
		{
			return "successfully added";
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

}
