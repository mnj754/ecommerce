package com.ecommercewebsite.manoj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cart {
	
	
	
	@PostMapping("/api/addToCart")
	public String addToCart(int userId,int pId,int pQuantity)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
					"root","#jaibalaji754#");
		
		PreparedStatement stmt=con.prepareStatement("select id,pQuantity from cart where userId=? and pId=?");
		stmt.setInt(1, userId);
		stmt.setInt(2, pId);
		
		ResultSet rs=stmt.executeQuery();
		int cartid=0;
		int productQuantity=0;
		if(rs.next())
		{
			//if the product is already in the cart
			cartid=rs.getInt("id");
			productQuantity=rs.getInt("pQuantity");
			
			PreparedStatement updatecart=con.prepareStatement("Update cart Set pQuantity=? Where id=?");
			updatecart.setInt(1, pQuantity);
			updatecart.setInt(2, cartid);
			int i=updatecart.executeUpdate();
			if(i>0)
			{
				return "cart updated successfully";
				
			}
			
		}
		else {
			// if the product is not in the cart inserting the new row with the product information and quantity
			
			PreparedStatement insertintocart=con.prepareStatement
					("insert into cart (userId,pId,pQuantity)values(?,?,?)");
			insertintocart.setInt(1, userId);
			insertintocart.setInt(2, pId);
			insertintocart.setInt(3, pQuantity);
			
			int i=insertintocart.executeUpdate();
			if(i>0)
				return "product added  to cart successfully";
			
				
			}
		
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "failed to add product to cart";
	}
	
	
	
	
	// calculate price after discount
	
		@GetMapping("/api/productFinalPrice/{productid}")
		public String productFinalPrice(@PathVariable("productid")int pId) {
			System.out.println("checking");
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
						"root","#jaibalaji754#");
			
			PreparedStatement stmt=con.prepareStatement("select pPrice,pDiscount from product where pId=?");
			stmt.setInt(1,pId);
			ResultSet rs=stmt.executeQuery();
			int finalPrice=0;
			int d=0;
			if(rs.next())
			{
				System.out.println(rs.getInt("pDiscount"));
				  d =(rs.getInt("pDiscount")*(rs.getInt("pPrice")))/100;
				  System.out.println(d);
				  finalPrice=rs.getInt("pPrice")-d;
			
				
			}
			return "finalPrice: "+finalPrice;
			
			
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return null;
		}

}
