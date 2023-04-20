package com.ecommercewebsite.manoj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.ReturnedType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Product {
	
	
	
	@GetMapping("/api/addProduct")
	public String addProduct(String pName,String pDesc,String pPhoto,int pPrice,int pDiscount, int pQuantity, int categoryId )
	{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
		PreparedStatement stmt=con.prepareStatement("insert into product(pName,pDesc,pPhoto,pPrice,pDiscount,pQuantity,categoryId)values(?,?,?,?,?,?,?)");
		stmt.setString(1, pName);
		stmt.setString(2,pDesc);
		stmt.setString(3,pPhoto);
		stmt.setInt(4,pPrice);
		stmt.setInt(5,pDiscount);
		stmt.setInt(6,pQuantity);
		stmt.setInt(7,categoryId);
		
		
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
	
	
	
	
	// search products api
	
	
		@GetMapping("/api/products/search")
		public Map searchProducts(String keyword,int categoryId) {
			System.out.println("checking");
			List<String> list=new ArrayList();
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
						"root","#jaibalaji754#");
			
			PreparedStatement stmt=con.prepareStatement("select * from product where pName like ? and categoryId=?");
			stmt.setString(1, "%"+keyword+"%");
			stmt.setInt(2, categoryId);
			
			ResultSet rs=stmt.executeQuery();
			ArrayList list2=new ArrayList<>();
			
			while(rs.next())
			{
				Map map=new HashMap();
				map.put("pId",rs.getInt("pId"));
				map.put("pName",rs.getString("pName"));
				map.put("pDesc",rs.getString("pDesc"));
				map.put("pPhoto",rs.getString("pPhoto"));
				map.put("pPrice",rs.getString("pPrice"));
				map.put("pDiscount",rs.getString("pDiscount"));
				map.put("pQuantity",rs.getString("pQuantity"));
				map.put("categoryId",rs.getString("categoryId"));
				
	             list2.add(map);
			
				
			}
			Map newmap=new HashMap();
			newmap.put("products",list);
			
			return newmap;
			
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return null;
		}
		
		
		
		
		//get all products
		@GetMapping("/api/getAllProducts")
		public Map getAllProducts()
		{
			
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","#jaibalaji754#");
			PreparedStatement stmt=con.prepareStatement("select * from product");
			
			
			
			ResultSet rs=stmt.executeQuery();
			ArrayList list=new ArrayList();
			
			while(rs.next())
			{
				Map map=new HashMap();
				map.put("pId",rs.getString("pId"));
				map.put("pName",rs.getString("pName"));
				map.put("pDesc",rs.getString("pDesc"));
				map.put("pPhoto",rs.getString("pPhoto"));
				map.put("pPrice",rs.getString("pPrice"));
				map.put("pDiscount",rs.getString("pDiscount"));
				map.put("pQuantity",rs.getString("pQuantity"));
				map.put("categoryId",rs.getString("categoryId"));
				
				list.add(map);
			}
			Map map1=new HashMap();
			
			map1.put("products", list);
			return map1;
			
			
			
			
			
			
			}
			
		
		
	        catch (Exception e) {
				// TODO: handle exception
	        	e.printStackTrace();
			}
		
		
		return null;

	}
		
		
		
		
		
		// get products by category id
		
		
		@GetMapping("/api/products/{categoryid}")
		public Map products(@PathVariable("categoryid")String categoryId) {
			System.out.println("checking");
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
						"root","#jaibalaji754#");
			
			PreparedStatement stmt=con.prepareStatement("select * from product where categoryId=?");
			stmt.setInt(1, Integer.parseInt(categoryId));
			ResultSet rs=stmt.executeQuery();
			ArrayList list=new ArrayList();
			while(rs.next())
			{
				Map map=new HashMap();
				map.put("pId",rs.getInt("pId"));
				map.put("pName",rs.getString("pName"));
				map.put("pDesc",rs.getString("pDesc"));
				map.put("pPhoto",rs.getString("pPhoto"));
				map.put("pPrice",rs.getString("pPrice"));
				map.put("pDiscount",rs.getString("pDiscount"));
				map.put("pQuantity",rs.getString("pQuantity"));
				map.put("categoryId",rs.getString("categoryId"));
				
				list.add(map);
			
				
			}
			Map newmap=new HashMap();
			newmap.put("products",list);
			
			return newmap;
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return null;
		}
		
		
		
		// get category by category id 
		
		
		
		@GetMapping("/getCategory/{categpryid}")
		public String getCategory(@PathVariable("categoryid")String categoryId) {
			System.out.println("checking");
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce",
						"root","#jaibalaji754#");
			
			PreparedStatement stmt=con.prepareStatement("select categoryTitle from category where categoryId=?");
			stmt.setInt(1, Integer.parseInt(categoryId));
			ResultSet rs=stmt.executeQuery();
			ArrayList list=new ArrayList();
			String s=null;
			if(rs.next())
			{
				 s =rs.getString("categoryTitle");
			
				
			}
			return s;
			
			
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return null;
		}
		
		
	
	
}
	
	
	
	