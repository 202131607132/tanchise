package major.servlet;

import impl.MajorImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMajorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	MajorImpl majorImpl = new MajorImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 删除专业
		
		majorImpl.delete(majorImpl.query("maj_id", request.getParameter("maj_id")).get(0));
		response.sendRedirect("pages/search_major.jsp");
	}
}
