package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,
				response);
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Filemanager> credential = new ArrayList<Filemanager>();
				
		String uname = request.getParameter("username");
		String pswd = request.getParameter("password");

		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu55";
			String username = "cs320stu55";
			String password = "22X3AvLY";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from userdetails");

			while (rs.next()) {
				credential.add(new Filemanager(rs.getString("user_name"), rs
						.getString("password")));

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
		
		for (int i = 0; i < credential.size(); i++) {
			if (uname.equals(credential.get(i).getUname())
					&& pswd.equals(credential.get(i).getPassword())) {
				request.getSession().setAttribute("user",
						credential.get(i).getUname());
				response.sendRedirect("StartupServlet");
				return;
			} else {
				if (i == (credential.size() - 1)) {
					response.sendRedirect("Login");
				}
			}

		}
	}

}
