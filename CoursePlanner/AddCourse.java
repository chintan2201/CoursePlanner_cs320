package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;


@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		List<Filemanager> entries = new ArrayList<Filemanager>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu55";
			String username = "cs320stu55";
			String password = "22X3AvLY";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from courses");

			while (rs.next()) {
				entries.add(new Filemanager(rs.getString("coursecode"), rs
						.getString("coursetitle"), rs.getString("courseprereq")));

			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		/*List<Filemanager> entries = (List<Filemanager>) getServletContext()
				.getAttribute("entries");*/
		request.setAttribute("entry", entries);
		request.getRequestDispatcher("/WEB-INF/AddCourse.jsp").forward(request,
				response);

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get the user input
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}
		String coursecode = request.getParameter("ccode");
		String coursetitle = request.getParameter("ctitle");
		String[] Prerec = request.getParameterValues("Prerec");
		String str = null;
		if (Prerec != null) {
			for (String s : Prerec) {
				if (str == null)
					str = " ";
				str += s + " ";
			}
		}
		if(str != null)
		{
			str=str.trim();
		}
		
			Connection c = null;
	        try
	        {
	        	String url = "jdbc:mysql://localhost/cs320stu55";
				String username = "cs320stu55";
				String password = "22X3AvLY";

	            String sql = "insert into courses (coursecode, coursetitle , courseprereq) values (?, ? ,?)";

	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setString( 1, coursecode );
	            pstmt.setString( 2, coursetitle );
	            pstmt.setString( 3, str );
	            pstmt.executeUpdate();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		
		// create a new Course Entry
		//Filemanager entry = new Filemanager(coursecode, coursetitle, str);

		//List<Filemanager> entries = (List<Filemanager>) getServletContext()
			//	.getAttribute("entries");
		//entries.add(entry);

		response.sendRedirect("StartupServlet");
	}

}
