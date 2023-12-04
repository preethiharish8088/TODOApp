package service;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mydao;
import dto.customer;
import helper.AES;

public class Myservice {

	public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));

		int age = LocalDate.now().getYear() - dob.getYear();

		if (age < 18) {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Sorry! You are not eligible for Creating Account</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		} else {
			mydao dao = new mydao();
			customer customer2 = dao.findByEmail(email);

			if (customer2 == null) {
				customer customer = new customer();
				customer.setName(name);
				customer.setAge(age);
				customer.setDob(dob);
				customer.setEmail(email);
				customer.setGender(gender);
				customer.setMobile(mobile);
				customer.setPassword(AES.encrypt(password, "123"));

				dao.savecustomer(customer);

				resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success </h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Account Already Exists with the Email : "
						+ email + " </h1>");
				req.getRequestDispatcher("signup.html").include(req, resp);
			}
		}

	}

public void login(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
{
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	mydao dao=new mydao();
	customer customer=dao.findByEmail(email);
	if(customer==null) 
	{
		resp.getWriter().print("<h1 align='center' style='color:red'>Invalid email</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	else {
		if(password.equals(AES.decrypt(customer.getPassword(), "123")))
		{
			resp.getWriter().print("<h1 align='center' style='color:green'>Login success</h1>");
			req.getRequestDispatcher("signup.jsp").include(req, resp);
			
		}	else {
			resp.getWriter().print("<h1 align='center' style='color:green'>Invalid password</h1>");
			req.getRequestDispatcher("login.jsp").include(req, resp);
		}
		
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

