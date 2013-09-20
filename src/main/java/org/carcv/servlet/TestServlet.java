package org.carcv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.carcv.beans.MediaObjectBean;
import org.carcv.model.MediaObject;
import org.carcv.model.MediaType;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/servlet/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @EJB
	 private MediaObjectBean bean;
	 
	   protected void processRequest(HttpServletRequest request,
	                                 HttpServletResponse response)
	                  throws ServletException, IOException {
	       PrintWriter out = response.getWriter();
	       
	       MediaObject m1 = new MediaObject("", MediaType.H264);
	       MediaObject m2 = new MediaObject("", MediaType.JPEG);
	       
	       bean.create(m1, m2);
	       out.println("Created and persisted " + m1.toString() + ", and " + m2.toString());
	 
	       MediaObject mo = bean.findById(0);
	       out.println("Query returned: " + mo.toString());
	       
	       bean.findById(1);
	       out.println("Query returned: " + mo.toString());
	       
	       bean.findById(2);
	       out.println("Query returned: " + mo.toString());
	   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
