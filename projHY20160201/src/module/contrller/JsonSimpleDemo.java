package module.contrller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.sql.*;

@WebServlet("/JsonSimpleDemo")
public class JsonSimpleDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public JsonSimpleDemo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/proj";
		String query = "select store_name from store where store_name like ?";
		String keyword = request.getParameter("keyword");
		keyword ="%" + keyword + "%";
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(url, "root", "1234");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, keyword);
			rs = stmt.executeQuery();
			JSONArray list = new JSONArray();
			 while (rs.next())
			 {
				 list.add(rs.getString(1));
			 }
			 out.print(list);
			
		}
		catch(SQLException e){
			out.println("Error:" + e.getMessage());
		}
		finally{
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
