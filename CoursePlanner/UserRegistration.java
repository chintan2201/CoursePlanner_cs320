package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;


@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<String> val = new ArrayList<String>();
	Map<String, String> mapp = new HashMap<String, String>();

	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		 request.setAttribute("map", mapp);
		 request.getRequestDispatcher("/WEB-INF/UserRegistration.jsp").forward(request, response );
		
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Filemanager> f = new ArrayList<Filemanager>();
		String uname = request.getParameter("username");
		String pswd = request.getParameter("password");
		String rtpswd = request.getParameter("retypepassword");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu55";
			String username = "cs320stu55";
			String password = "22X3AvLY";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from userdetails");

			while (rs.next()) {
				f.add(new Filemanager(rs.getString("user_name"),rs.getString("password")));

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
		
		if (uname.isEmpty() || pswd.isEmpty() || rtpswd.isEmpty()) {
			if (uname.isEmpty()) {
				mapp.put("unamereq", "Username is required.");
			}
			if (pswd.isEmpty()) {
				mapp.put("pswdreq", "Password is required.");
			}
			if (rtpswd.isEmpty()) {
				mapp.put("rtpswdreq", "Retype Password is required.");
			}
		}
		if (uname.length() < 4) {
			mapp.put("unlength", "Username must be at least 4 characters");
		}
		for (int i = 0; i < f.size(); i++) {
			if (uname.equals(f.get(i).getUname())) {
				mapp.put("unexist", "Username already exist");
			}
		}

		if (pswd.length() < 4) {
			mapp.put("pswdlength", "Password must be at least 4 characters");
		}

		if (!pswd.equals(rtpswd)) {
			mapp.put("matchpswd", "Password and Retype password must match");

		}

		if (mapp.isEmpty()) {
			

			Connection con = null;
	        try
	        {
	        	String url = "jdbc:mysql://localhost/cs320stu55";
				String username = "cs320stu55";
				String password = "22X3AvLY";

	            String sql = "insert into userdetails (user_name, password , first_name, last_name) values (?, ? ,?, ?)";

	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setString( 1, uname );
	            pstmt.setString( 2, pswd );
	            pstmt.setString( 3, fname );
	            pstmt.setString( 4, lname );
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
	                if( con != null ) con.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		
			
			//Filemanager file = new Filemanager(uname, pswd, fname, lname);
			//Filemanager filecre = new Filemanager(uname, pswd);
			//f.add(file);
			//f.add(filecre);

			//request.getSession().setAttribute( "user",uname );
			response.sendRedirect("Login");
		} else {
			response.sendRedirect("UserRegistration");
		}
	}

}
