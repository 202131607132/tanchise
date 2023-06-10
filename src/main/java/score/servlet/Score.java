package score.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Classes;
import entity.Student;
import entity.Subject;
import impl.SubjectImpl;
import impl.ScoreImpl;

/**
 * Servlet implementation class Score
 */
@WebServlet("/Score")
public class Score extends HttpServlet {
	List<Subject> list_subject;
	

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		if(id!=null)
		{
		ScoreImpl scoreImpl = new ScoreImpl();
		List<entity.Score> list_score;
		list_score = scoreImpl.sreach(Integer.parseInt(id));
		String pingjun;
		String text = null;
		Double daily=0.0;
		Double exam=0.0;
		Double count=0.0;
		String daily_d;
		String exam_d;
		String count_d;
		int daily_59=0,daily_69=0,daily_79=0,daily_89=0,daily_90=0;
		int exam_59=0,exam_69=0,exam_79=0,exam_89=0,exam_90=0;
		int count_59 = 0,count_69=0,count_79=0,count_89=0,count_90=0;
		for (Subject s:list_subject) {
			if(s.getId()==Integer.parseInt(id))
				text=s.getName();
		}
		for (entity.Score s:list_score) {
            daily+=s.getDaily();
            exam+=s.getExam();
            count+=s.getCount();
            
            if(s.getDaily()<60)
            	daily_59++;
            else if(s.getDaily()<70)
            	daily_69++;
            else if(s.getDaily()<80)
            	daily_79++;
            else if(s.getDaily()<90)
            	daily_89++;
            else 
            	daily_90++;
            
            if(s.getExam()<60)
            	exam_59++;
            else if(s.getExam()<70)
            	exam_69++;
            else if(s.getExam()<80)
            	exam_79++;
            else if(s.getExam()<90)
            	exam_89++;
            else
            	exam_90++;
            
            if(s.getCount()<60)
            	count_59++;
            else if(s.getCount()<70)
            	count_69++;
            else if(s.getCount()<80)
            	count_79++;
            else if(s.getCount()<90)
            	count_89++;
            else
            	count_90++;
        }
		daily=daily/list_score.size();
		exam=exam/list_score.size();
		count=count/list_score.size();
		pingjun="平时分平均分："+daily+"<br>考试分平均分："+exam+"<br>总分平均分："+count;
		daily_d=daily_59+","+daily_69+","+daily_79+","+daily_89+","+daily_90;
		exam_d=exam_59+","+exam_69+","+exam_79+","+exam_89+","+exam_90;
		count_d=count_59+","+count_69+","+count_79+","+count_89+","+count_90;
		session.setAttribute("id", id);
		session.setAttribute("text", text);
		session.setAttribute("daily_d", daily_d);
		session.setAttribute("exam_d", exam_d);
		session.setAttribute("count_d", count_d);
		session.setAttribute("pingjun", pingjun);
		response.sendRedirect("pages/score_tj.jsp");
		System.out.println(daily_d);
		System.out.println(exam_d);
		System.out.println(count_d);
		}else {
		SubjectImpl subjectImpl = new SubjectImpl();
		
		
		// 准备添加学生，查询可用班级
		list_subject = subjectImpl.all();
		System.out.println(list_subject.size());
		session.setAttribute("list_subject", list_subject);
		response.sendRedirect("pages/score_tj.jsp");}
		
	}

}
