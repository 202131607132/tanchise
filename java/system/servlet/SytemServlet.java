package system.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Systemm;
import impl.LoginImpl;
import impl.SystemImpl;

/**
 * Servlet implementation class SytemServlet
 */
@WebServlet("/SytemServlet")
public class SytemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemImpl systemImpl = new SystemImpl();
	Systemm system = new Systemm();
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String forbitc = request.getParameter("forbitc");
		String forbist = request.getParameter("forbist");
		String informtc = request.getParameter("informtc");
		String informst = request.getParameter("informst");
		System.out.println(forbitc+forbist+informtc+informst);
		system.setForbitc(Integer.parseInt(request.getParameter("forbitc")));
		system.setForbist(Integer.parseInt(request.getParameter("forbist")));
		system.setInformtc(request.getParameter("informtc"));
		system.setInformst(request.getParameter("informst"));
		systemImpl.system(request, system);
		String systeminfo = systemImpl.system(request, system);
		request.getSession().setAttribute("errorr", systeminfo);
		request.getSession().setAttribute("sy", system);
		response.sendRedirect("pages/system.jsp");
	}

}
