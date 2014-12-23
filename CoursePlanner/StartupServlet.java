package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;


@WebServlet(urlPatterns = "/StartupServlet", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String filename;

	public StartupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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
		request.setAttribute("entry", entries);
		request.getRequestDispatcher("/WEB-INF/StartupServlet.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
