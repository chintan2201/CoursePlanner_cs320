package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;

/**
 * Servlet implementation class Finish
 */
@WebServlet("/Finish")
public class Finish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Finish() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, List<String>> mapquarter = (Map<String, List<String>>) request
				.getSession().getAttribute("mapquarter");
		Map<String, List<Filemanager>> finalmap = (Map<String, List<Filemanager>>) request
				.getSession().getAttribute("finalmap");
		List<String> quarterstore = (List<String>) request.getSession()
				.getAttribute("quarterstore");

		// request.setAttribute("final", file);
		request.setAttribute("quarter", quarterstore);
		request.setAttribute("f", finalmap);
		request.setAttribute("mapquarter", mapquarter);
		request.getRequestDispatcher("/WEB-INF/Finish.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("done");
		if ("Done".equals(action)) {
			request.getSession().invalidate();
			response.sendRedirect("StartupServlet");
		} else if ("Save this course plan".equals(action)) {
			Map<String, List<Filemanager>> finalmap = (Map<String, List<Filemanager>>) request
					.getSession().getAttribute("finalmap");
			List<String> quarterstore = (List<String>) request.getSession()
					.getAttribute("quarterstore");

			for (int i = 0; i < quarterstore.size(); i++) {
				String quarter = quarterstore.get(i);
				if (finalmap.get(quarter) != null) {
					for (int j = 0; j < finalmap.get(quarter).size(); j++) {
						String ccode = finalmap.get(quarter).get(j).ccode;
						String ctitle = finalmap.get(quarter).get(j).ctitle;
						String cprerec = finalmap.get(quarter).get(j).cprerec;

						Connection c = null;
						try {
							DateFormat dateFormat = new SimpleDateFormat(
									"MM/dd/yyyy HH:mm a");
							Calendar cal = Calendar.getInstance();
							// System.out.println(dateFormat.format(cal.getTime()));

							String url = "jdbc:mysql://localhost/cs320stu55";
							String username = "cs320stu55";
							String password = "22X3AvLY";

							String sql = "insert into courseplans (course_code, course_title , course_prerec ,quarter ,time_stamp ,user_name_plan) values (?, ? ,? ,? ,? ,?)";

							c = DriverManager.getConnection(url, username,
									password);
							PreparedStatement pstmt = c.prepareStatement(sql);
							pstmt.setString(1, ccode);
							pstmt.setString(2, ctitle);
							pstmt.setString(3, cprerec);
							pstmt.setString(4, quarter);
							pstmt.setString(5, dateFormat.format(cal.getTime()));
							pstmt.setString(6, (String) request.getSession()
									.getAttribute("user"));
							pstmt.executeUpdate();
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

					}
				}
			}

			response.sendRedirect("StartupServlet");
		}
	}

}
