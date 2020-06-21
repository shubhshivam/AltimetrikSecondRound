import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCartItems
 */
@WebServlet("/ViewCartItems")
public class ViewCartItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCartItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	response.setContentType("text/html");
	
	PrintWriter pw=response.getWriter();
	
	Connection con= ConnectDB.connect();
	
	String q;
	
	PreparedStatement pst;
	
	ResultSet rs;
	
	try
	{
		
		Cookie c= new Cookie(request.getParameter("ItemId"),"1");
	
		c.setMaxAge(0);
		
		response.addCookie(c);
		
		pw.print("Product Removed From Cart Successfully..");
		
	}
	catch (Exception ex) {
		
	}
	pw.print("<a href ='DisplayItems'> Go Back</a>");
	
	try 
	{
	
		Cookie ck[]= request.getCookies();
	    pw.print ( 
	    		"<style>"
	            +"td,th{padding:14px 30px}"
	            +"body{font-family:arial;}"
	            +"table{border:1px solid black;padding:20px;margin-top:50px;}"
	            +"a{text-decoration:none;border:1px solid black;padding:10px 10px;}"
	            +"a:hover{color:red;}"
	            
	            +"</style>"
	            
	            +"<table>"
	            +"<tr>"
	            +"<th>Id</th>"
	            +"<th>Product Name</th>"
	            +"<th>Price</th>"
	            );
	for(int i=0;i<ck.length;i++) 
	{
	
		q= "select * from products where pid="+ck[i].getName() + "";
	pst=con.prepareStatement(q);
	rs=pst.executeQuery();
	while(rs.next()) 
	{
	
		pw.print("<tr><td>"+ rs.getInt(1)+"</td>"
	              +"<td>"+   rs.getString(2)+"</td>"
	              +"<td>"+   rs.getInt(3)+"</td>"
	              +"<td><a href='ViewCartItems?ItemId="+rs.getInt(1)+"'>Remove From Cart</a></td>"
	              +"</tr><br><br>");
	}
	}
	pw.print("</table></body></html>");
	}
	catch(Exception ex){
	}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}