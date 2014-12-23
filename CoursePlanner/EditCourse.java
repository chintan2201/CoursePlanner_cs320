package cp5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cp5.model.Filemanager;



@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public EditCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if( request.getSession().getAttribute( "user" ) == null )
		{
			response.sendRedirect( "Login" );
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
		
		int index = Integer.parseInt(request.getParameter("id"));
		Filemanager entry = entries.get(index);
		
		//get separated prerec
		String s = entry.getCprerec();
		List<String> str = new ArrayList<String>();
		if(s == "" || s == null)
		{
			s=" ";
			str = Arrays.asList(s.split(" "));
		}
		else
		{
			str = Arrays.asList(s.split(" "));
			
		}
		//Map<String,List<String>> check = new HashMap<String,List<String>>();
		List<String> yes = new ArrayList<String>();
		List<String> no = new ArrayList<String>();
		//List<String> check=new ArrayList<String>();
	
		for(int i=0; i<entries.size();++i)
		{
			Filemanager ent = entries.get(i);
			String code = ent.getCcode();
			//List<String> check=new ArrayList<String>();
			
			
			if(!entry.getCcode().equals(code))
			{
				

					if (!str.isEmpty() && str.contains(code))
					{
						yes.add(code);
						
					}	

					else
					{
						no.add(code);
					}
			}
		}
		
		//Set the attribute
				request.setAttribute("entry", entries);
				request.setAttribute("ent", entry);
				request.setAttribute("str", str);
				request.setAttribute("yes", yes);
				request.setAttribute("no", no);
				request.setAttribute("index", index);
				request.getRequestDispatcher("/WEB-INF/EditCourse.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the user input

		if( request.getSession().getAttribute( "user" ) == null )
		{
			response.sendRedirect( "Login" );
			return;
		}
		String coursecode = request.getParameter("ccode");
		String coursetitle = request.getParameter("ctitle");
		String[] Prerec = request.getParameterValues("Prerec");
		int index = Integer.parseInt(request.getParameter("id"));
		
		String str = null;
		if(Prerec != null)
		{

			for(String s : Prerec)	
			{
				if(str == null)
					str = " ";
				str += s+ " ";
			}

		}
		if(str != null)
		{
			str=str.trim();
		}
		//List<Filemanager> entries = new ArrayList<Filemanager>();
		
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://localhost/cs320stu55";
			String username = "cs320stu55";
			String password = "22X3AvLY";

            String sql = "update courses set coursecode = ? , coursetitle = ? , courseprereq = ? where id = ?" ;

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, coursecode );
            pstmt.setString( 2, coursetitle );
            pstmt.setString( 3, str );
            pstmt.setInt(4, index+1);
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

		
	//	entries.get(index).setCcode(coursecode);
		//entries.get(index).setCtitle(coursetitle);
	//	entries.get(index).setCprerec(str);

		response.sendRedirect("StartupServlet");
	}

}
