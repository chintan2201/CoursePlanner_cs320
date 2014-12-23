package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
 * Servlet implementation class CoursePlanner
 */
@WebServlet("/CoursePlanner")
public class CoursePlanner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoursePlanner() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

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
		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
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

		String[] code = request.getParameterValues("SelectSub");
		// List<String> sel = new ArrayList<String>();
		// List<Filemanager> notselected = new ArrayList<Filemanager>();
		List<String> selected = new ArrayList<String>();
		List<Filemanager> nextquartersub = new ArrayList<Filemanager>();
		Map<String, List<Filemanager>> finalmap = new HashMap<String, List<Filemanager>>();
		List<String> quarterstore = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		Quarter q1 = new Quarter();

		// calculate next quarter
		Calendar cal = Calendar.getInstance();
		int weekno = cal.get(Calendar.WEEK_OF_YEAR);

		map = q1.getQuarter(weekno, 0);
		quarterstore.add(map.get("nquarter"));

		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				selected.add(code[i]);

			}
		}
		if (selected.size() != 0) {
			nextquartersub = q1.NextQuarterPlan(entries, selected);
		}
		else
		{
			nextquartersub = entries;
		}

		// finalmap.put(map.get("nquarter"), nextsub);
		request.getSession().setAttribute("selected", selected);
		request.getSession().setAttribute("quarterstore", quarterstore);
		request.getSession()
				.setAttribute("CurrentYear", cal.get(Calendar.YEAR));
		request.getSession().setAttribute("mapquarter", map);
		request.getSession().setAttribute("finalmap", finalmap);
		request.getSession().setAttribute("nextquartersub", nextquartersub);
		request.getSession().setAttribute("entries", entries);
		response.sendRedirect("NextQuarterPlan");

	}

}