package cp5.servlet;

import java.io.IOException;
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
 * Servlet implementation class NextQuarterPlan
 */
@WebServlet("/NextQuarterPlan")
public class NextQuarterPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NextQuarterPlan() {
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

		HashMap<String, String> map = (HashMap<String, String>) request
				.getSession().getAttribute("mapquarter");
		List<String> selected = (List<String>) request.getSession()
				.getAttribute("selected");
		List<String> quarterstore = (List<String>) request.getSession()
				.getAttribute("quarterstore");
		List<Filemanager> nextquartersub = (List<Filemanager>) request
				.getSession().getAttribute("nextquartersub");

		request.setAttribute("entry", nextquartersub);
		request.setAttribute("nextquarter", map.get("nquarter"));

		request.getRequestDispatcher("/WEB-INF/NextQuarterPlan.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HashMap<String, String> map = (HashMap<String, String>) request
				.getSession().getAttribute("mapquarter");
		List<String> selected = (List<String>) request.getSession()
				.getAttribute("selected");
		List<String> quarterstore = (List<String>) request.getSession()
				.getAttribute("quarterstore");
		List<Filemanager> nextquartersub = new ArrayList<Filemanager>();
		List<Filemanager> entries = (List<Filemanager>) request.getSession()
				.getAttribute("entries");
		Map<String, List<Filemanager>> finalmap;
		if (request.getSession().getAttribute("finalmap") == null) {
			finalmap = new HashMap<String, List<Filemanager>>();
		} else {
			finalmap = (Map<String, List<Filemanager>>) request.getSession()
					.getAttribute("finalmap");
		}
		List<Filemanager> nextsub = new ArrayList<Filemanager>();

		// for get week of the year
		int year = (int) request.getSession().getAttribute("CurrentYear");
		Quarter q1 = new Quarter();
		Calendar cal = Calendar.getInstance();
		int weekno = 0;
		// int ye = 0;
		String nextquarter = map.get("nquarter");
		if (nextquarter.equals("Winter" + " " + year))
			weekno = 1;
		if (nextquarter.equals("Spring" + " " + year))
			weekno = 13;
		if (nextquarter.equals("Summer" + " " + year))
			weekno = 25;
		if (nextquarter.equals("Fall" + " " + year))
			weekno = 38;
		if (nextquarter.equals("Winter" + " " + (year + 1))) {
			weekno = 1;
			year++;
			map = q1.getQuarter(weekno, year);
			request.getSession().setAttribute("CurrentYear", year);
		} else {
			map = q1.getQuarter(weekno, year);
		}
		request.getSession().setAttribute("CurrentYear", year);
		quarterstore.add(map.get("nquarter"));

		String[] code = request.getParameterValues("SelectSub");

		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				selected.add(code[i]);
				for (int x = 0; x < entries.size(); x++) {
					if (entries.get(x).ccode.equals(code[i])) {
						nextsub.add(entries.get(x));
					}
				}
			}
		}
		// for next button click
		if ("Next".equals(action)) {

			if(nextsub.size() != 0)
			{
				nextquartersub = q1.NextQuarterPlan(entries, selected);
			}
			else
			{
				nextquartersub = (List<Filemanager>)request.getSession().getAttribute("nextquartersub");
			}
			finalmap.put(map.get("cquarter"), nextsub);
			request.getSession().setAttribute("selected", selected);
			request.getSession().setAttribute("quarterstore", quarterstore);

			request.getSession().setAttribute("mapquarter", map);
			request.getSession().setAttribute("finalmap", finalmap);
			request.getSession().setAttribute("nextquartersub", nextquartersub);
			// request.getSession().setAttribute("entries", entries);
			if (nextquartersub.size() == 0) {
				response.sendRedirect("Finish");
			} else {
				response.sendRedirect("NextQuarterPlan");
			}
		}

		// for Finish button click
		else if ("Finish".equals(action)) {
			
			finalmap.put(map.get("cquarter"), nextsub);
			request.getSession().setAttribute("selected", selected);
			request.getSession().setAttribute("quarterstore", quarterstore);

			request.getSession().setAttribute("mapquarter", map);
			request.getSession().setAttribute("finalmap", finalmap);
			request.getSession().setAttribute("nextquartersub", nextquartersub);
			response.sendRedirect("Finish");
		}
	}

}