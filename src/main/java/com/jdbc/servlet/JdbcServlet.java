package com.jdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class JdbcServlet
 */
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JdbcServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html><body>");
		out.println("<h3>Employee Details:</h3>");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db2", "root", "root");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee");
			out.println("<table border='5px' width='250px'>");
			out.println("<tr> <th>Emp ID</th>");
			out.println("<th>Emp Name</th></tr>");
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				/*out.println("ID: " + id + "  ");
				out.println("Name: " + name + "<br>");*/
				out.println("<tr> <td>"+id+"</td><td>"+name+"</td></tr>");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println(e);
			}

		}
		out.println("</table>");
		out.println("</body></html>");
		System.out.println("data printed");
	}

}
