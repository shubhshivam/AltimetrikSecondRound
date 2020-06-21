import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayItems
 */
@WebServlet("/DisplayItems")
public class DisplayItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	static String databaseName="productdetails";
	static String url="jdbc:mysql://localhost:3306/"+databaseName;
	static String username="root";
	static String password="Shivam@123!";
	Connection con;

	
    public DisplayItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	int added_cart_product=0;
	Cookie c= null;
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    PrintWriter pw=response.getWriter();
   	 
		
	    try 
	     {
	    	
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(url,username,password);
		
		String q="select * from products";
		
		PreparedStatement pst=con.prepareStatement(q);
		
		ResultSet rs=pst.executeQuery(); 
		
		try 
		{
		
			c= new Cookie(request.getParameter("ItemId"),"1");
	  
			response.addCookie(c);
		 
			pw.print("Product Added In the Cart Successfully..");
		}
		
		catch(Exception e) {
			pw.println(e.getMessage());
	      	
		}
		
		pw.print("<a href ='ViewCartItems'> View Cart</a>");
		
		
	            pw.print(
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

		 
		while(rs.next()) 
		{
			
		pw.print("<tr><td>"+rs.getInt(1)+"</td>"
		          +"<td>"+rs.getString(2)+"</td>"
		          +"<td>"+rs.getInt(3)+"</td>"
		          +"<td><a href='DisplayItems?ItemId=" +rs.getInt(1)+"'>Add To Cart</a></td>"
		          +"</tr><br><br>");
		}
		pw.print("</table></body></html>");
		}
		catch (Exception ex) {
		pw.print(ex);
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