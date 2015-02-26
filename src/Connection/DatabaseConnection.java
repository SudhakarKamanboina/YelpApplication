package Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.activation.DataSource;

import beans.Categories;
import beans.ElementsBean;
import beans.ReviewBean;
//import beans.Elements;
import beans.UserDetails;

public class DatabaseConnection {
	
	Connection con = null;
	static ResultSet rs;
    Statement stmt = null;
    DataSource ds = null;
	DatabaseConnection(){		
		try {			
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Smack123");
				stmt = con.createStatement();
				if(!con.isClosed())
					System.out.println("Successfully Connected!!!");
		//	ConnectionPoolManager ConnectionPoolManager = new ConnectionPoolManager();
			//ds = ConnectionPoolManager.getDS();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
/* First time users sign up*/
	
	public String signUp(String firstName, String lastName, String email, String pwd){
		String result = "";
		int rowcount;
		try {
			String query = "Insert into yelp_user (fname, lname, email, password) values ('" + firstName + "', '" + lastName + "','"+email+"','"+pwd+"')";
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Insert Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
/* Signed up users login*/	
	
	public String Login(String email, String password){
		String result = "";
		try {
			PreparedStatement stmt = con.prepareStatement("select * from yelp_user where email = ? ");    
			stmt.setString(1, email);    
			ResultSet rs = stmt.executeQuery();
			System.out.println("hello");
			if(rs.next())
			{
				result="true";
				System.out.println("Hello again");
				System.out.println(rs.getString(1));  
			}
			else{
				result="false";
				System.out.println("Does not exist in database");
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Does not exist in database");
			e.printStackTrace();
			
		}
		return result;
	}
/* Add Categories*/
	
	public String addCategory(String category){
		String result = "";
		int rowcount;
		try {
			String query = "Insert into category (categoryname) values ('" + category + "')";
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Inserting category Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	

/* Delete Categories*/
	
	public String deleteCategory(String category){
		String result = "";
		
		try {
			PreparedStatement stmt = con.prepareStatement("delete  from category where categoryname = ?");    
			stmt.setString(1, category);    
			stmt.executeUpdate();
			System.out.println("Deleted category");
/*			
			if(rs.next())
			{
				System.out.println("Deleted");
				//System.out.println(rs.getString(1));  
			}
			else{
				System.out.println("Does not exist in database");
			}*/
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	

/* Add Elements*/
	
	public String addElements(String elementname, String elementdescription, String categoryname){
		String result = "";
		int rowcount;
		try {
			String query = "Insert into elements (elementname, elementdescription, categoryname) values ('" + elementname + "', '" + elementdescription + "','"+categoryname+"')";
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Inserting category Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	

/* Delete Elements*/
	
	public String deleteElement(String elementname){
		String result = "";
		
		try {
			PreparedStatement stmt = con.prepareStatement("delete  from elements where elementname = ?");    
			stmt.setString(1, elementname);    
			stmt.executeUpdate();
			System.out.println("Deleted elements");
/*			if(rs.next())
			{
				System.out.println("Deleted");
				//System.out.println(rs.getString(1));  
			}
			else{
				System.out.println("Does not exist in database");
			}*/
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	
// Add review
public String addReview(int rating, String comments, String reviewedby, String elementname){
		String result = "";
		int rowcount;
		try {
			String query = "Insert into review (rating, comments, reviewedby, elementname) values ('" + rating + "', '" + comments + "','"+reviewedby+"','"+elementname+"')";
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Inserting review Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	



/* Select all users*/

public UserDetails[] selectAllUsers() throws SQLException{
		System.out.println("Select all users!!!");
		
		String sql = "Select count(user_id) as rowcount from yelp_user";

		PreparedStatement pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		rs.next();
		int rowcount = rs.getInt("rowcount");
		UserDetails[] userDetails = null;
		userDetails = new UserDetails[rowcount];

		
		PreparedStatement stmt = con.prepareStatement("select * from yelp_user");    
		ResultSet rs = stmt.executeQuery();
		int count=0;
		
		
		//Collection<UserDetails> userDetails=new ArrayList();
		
				while(rs.next()){
					UserDetails ud=new UserDetails();
					ud.setEmail(rs.getString("email"));
					ud.setFname(rs.getString("fname"));
					ud.setLname(rs.getString("lname"));
					ud.setLoginTime(rs.getDate("logintime"));
					System.out.println("fname@@"+ud.getFname());
					System.out.println("email@@"+ud.getEmail());
					System.out.println("lname"+ud.getLname());
					System.out.println("time@@"+ud.getLoginTime());
					//ud.setLoginTime(rs.getDate(6));
					userDetails[count]=ud;
					count++;
				}
		
		System.out.println("size of array"+ userDetails.length);
		rs.close();
		stmt.close();
		return userDetails;
		
}	

/* Select all Categories*/

public Categories[] getCategories()
{

	Categories[] categories = null;
	int count = 0;
	java.sql.PreparedStatement pStatement = null;
	ResultSet rs  = null;
	String sql = null;
	try
	{

		sql = "Select count(category_id) as rowcount from category";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		rs.next();
		int rowcount = rs.getInt("rowcount");
		rs.close();
		categories = new Categories[rowcount];
		sql = "Select * from category";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		while(rs.next())
		{
			Categories categoryVO = new Categories();
			categoryVO.setCategoryName(rs.getString("categoryname"));
			categories[count] = categoryVO;
			count++;

			//categories.add(categoryVO);
		}

		rs.close();
		pStatement.close();

	}

	catch(SQLException exception)
	{
		exception.printStackTrace();
	}
	finally
	{

		if(con!=null)
		{
			try {
			con.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


	return categories;

}
/* Fetch all elements*/
public ElementsBean[] getElementbyCategory(String category)
{

	ElementsBean[] elements = null;
	int count = 0;
	java.sql.PreparedStatement pStatement = null;
	ResultSet rs  = null;
	String sql = null;
	try
	{

		sql = "Select count(element_id) as rowcount from elements where categoryname='"+category+"'";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		rs.next();
		int rowcount = rs.getInt("rowcount");
		System.out.println(rowcount);
		rs.close();
		elements = new ElementsBean[rowcount];	
		sql = "Select * from elements where categoryname='"+category+"' ";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		
		
		while(rs.next())
		{
			ElementsBean elementVO = new ElementsBean();
			elementVO.setElementName(rs.getString("elementname"));
		
			elements[count] = elementVO;
			count++;

			//categories.add(categoryVO);
		}

		rs.close();
		pStatement.close();

	}

	catch(SQLException exception)
	{
		exception.printStackTrace();
	}
	finally
	{

		if(con!=null)
		{
			try {
			con.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


	return elements;

}

public ReviewBean[] getReviewsbyElements(String element)
{
	ReviewBean[] reviewBean=null;
	int count = 0;
	java.sql.PreparedStatement pStatement = null;
	ResultSet rs  = null;
	String sql = null;
	try
	{
		sql = "Select count(review_id) as rowcount from review where elementname='"+element+"'";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		rs.next();
		int rowcount = rs.getInt("rowcount");
		System.out.println(rowcount);
		rs.close();
		reviewBean = new ReviewBean[rowcount];	
		sql = "Select * from review where elementname='"+element+"' ";
		pStatement = con.prepareStatement(sql);
		rs = pStatement.executeQuery();
		while(rs.next())
		{
			ReviewBean reviewVO = new ReviewBean();
			reviewVO.setReviewedBy(rs.getString("reviewedby"));
			reviewVO.setComments(rs.getString("comments"));
			reviewVO.setRating(rs.getInt("rating"));
			
			reviewBean[count] = reviewVO;
			count++;

			
		}

		rs.close();
		pStatement.close();
		
	}
	catch(SQLException exception)
	{
		exception.printStackTrace();
	}
	finally
	{

		if(con!=null)
		{
			try {
			con.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	
	
	return reviewBean;
	
}

}
