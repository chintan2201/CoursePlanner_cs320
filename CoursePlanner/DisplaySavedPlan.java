package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import cp5.model.Quarter;

/**
 * Servlet implementation class DisplaySavedPlan
 */
@WebServlet("/DisplaySavedPlan")
public class DisplaySavedPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplaySavedPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
			return;
		}
		String user = request.getParameter("u");
		String timestamp = request.getParameter("t");
		List<String> quarter = new ArrayList<String>();
		List<Quarter> ent = new ArrayList<Quarter>();
		Map<String,List<Quarter>> map = new HashMap<String, List<Quarter>>();
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs320stu55";
			String username = "cs320stu55";
			String password = "22X3AvLY";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from courseplans where user_name_plan='"+user+"' and time_stamp='"+timestamp+"'");
			
			while (rs.next()) {
				if(!quarter.contains(rs.getString("quarter")))
				{
					quarter.add(rs.getString("quarter"));
				}
				ent.add(new Quarter(rs.getString("quarter"),rs.getString("course_code"),rs.getString("course_title"),rs.getString("course_prerec")));
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
		request.setAttribute("quart", quarter);
		request.setAttribute("map", ent);
		request.getRequestDispatcher("/WEB-INF/DisplaySavedPlan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
