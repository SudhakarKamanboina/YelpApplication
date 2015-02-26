package Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import beans.Categories;
import beans.ElementsBean;
import beans.ReviewBean;
//import beans.Elements;
import beans.UserDetails;

import java.awt.AWTException;
import java.sql.*;
@WebService
public class Service {	
	DatabaseConnection db=new DatabaseConnection();
/*Sign up*/	
	public String signUp(String firstName, String lastName, String email, String pwd)
	{
		System.out.println("Inside Signup");
		String result;
		
		/*//**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*//*
*/		
		result = db.signUp(firstName,lastName,email,pwd);
		
		return result;//this value is returned to the calling servlet
	}	
/*Login*/
	public String login(String email, String password)
	{
		System.out.println("Inside login");
		String result;
		
		/**//**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*//*
*/		
		result = db.Login(email, password);
	//	System.out.println("Result="+ result);
		return result;//this value is returned to the calling servlet
		
	}
/*Add Categories	*/
public String addCategories(String category)
	{
		System.out.println("Inside add categories");
		String result;
		
		/**//**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*//*
*/		
		result = db.addCategory(category);
		
		return result;//this value is returned to the calling servlet
	}
/*Delete Categories*/
public String deleteCategories(String category)
{
	System.out.println("Inside delete category");
	String result;
	
	/**//**This space is left for validation and manipulation of 
	input values entered by the user as a part of the server side validation*//*
*/	
	result = db.deleteCategory(category);
	
	return result;//this value is returned to the calling servlet
}
/*Add Elements*/
public String addElements(String elementname, String elementdescription, String categoryname)
{
	System.out.println("Inside Adding elements");
	String result;
	
	/**//**This space is left for validation and manipulation of 
	input values entered by the user as a part of the server side validation*//*
*/	
	result = db.addElements(elementname,elementdescription,categoryname);
	
	return result;//this value is returned to the calling servlet
}
/*Delete Elements*/
public String deleteElements(String elementname)
{
	System.out.println("Inside deleting elements");
	String result;
	
/*	*//**This space is left for validation and manipulation of 
	input values entered by the user as a part of the server side validation*//*
*/	
	result = db.deleteElement(elementname);
	
	return result;//this value is returned to the calling servlet
}

/*Add review	*/
public String addReview(int rating, String comments, String reviewedby, String elementname )
{
	System.out.println("Inside review");
	String result;
/*	*//**This space is left for validation and manipulation of 
	input values entered by the user as a part of the server side validation*//*
*/	
	result=db.addReview(rating,comments,reviewedby,elementname);
	return result;
	
}

/*Display All users*/	
public UserDetails[] selectAllUsers() 
	{
		System.out.println("Display All Users");
		
		UserDetails[] userDetails=null;
		try {
			userDetails=db.selectAllUsers();
			//System.out.println("@@@"+userDetails);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDetails;//this value is returned to the calling servlet
	}

/*Get all categories*/

public Categories[] displayAllcategories()
{
	System.out.println("Display all categories");
	Categories[] categories=null;
	try{
		categories=db.getCategories();
	//	System.out.println(categories.length +":length");

		for(int i=0; i<categories.length; i++)
		{

		System.out.println(categories[i].getCategoryName());

		}
	} catch(Exception exception){

		exception.printStackTrace();

}
	return categories;
}
/*Get all Elements*/
public ElementsBean[] fetchElements(String category)
{
	System.out.println("Get all elements");
	ElementsBean[] elements=null;
	try{
		elements=db.getElementbyCategory(category);
	//	System.out.println(categories.length +":length");

		for(int i=0; i<elements.length; i++)
		{

		System.out.println(elements[i].getElementName());

		}
	} catch(Exception exception){

		exception.printStackTrace();

}
	return elements;
}

/*Get all Reviews*/
public ReviewBean[] getReviews(String element)
{
	System.out.println("Get all reviews");
	ReviewBean[] reviewBean=null;
	try
	{
		reviewBean=db.getReviewsbyElements(element);
	
		for (int i=0;i<reviewBean.length;i++)
		{
			System.out.println(reviewBean[i].getReviewedBy());
			System.out.println(reviewBean[i].getRating());
			System.out.println(reviewBean[i].getComments());			
		}
	}
	catch(Exception exception){

		exception.printStackTrace();

	}
	
	return reviewBean;
	
}
}