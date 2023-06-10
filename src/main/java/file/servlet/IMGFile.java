package file.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Student;
import impl.StudentImpl;
import java.util.Map;
import java.util.HashMap;


/**
 * Servlet implementation class IMGFile
 */
@WebServlet("/IMGFile")
public class IMGFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentImpl studentImpl = new StudentImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IMGFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 处理文件上传
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List<FileItem> items;
	    Map<String, String> imgMap = new HashMap<>();
		try {
			items = upload.parseRequest(request);
			
	
	    String avatarFilePath = null;
	    for (FileItem item : items) {

	            // 处理文件上传
	            String fileName = item.getName();
	            
	            int i = studentImpl.s_img(fileName);
	            System.out.println(i +"  图片名："+fileName);
	            if(i==1)
	            {
	            	String uploadPath = getServletContext().getRealPath("/") + "images/";
	                File uploadDir = new File(uploadPath);
	                if (!uploadDir.exists()) {
	                    uploadDir.mkdir();
	                }
	                File uploadedFile = new File(uploadPath + fileName);
	                item.write(uploadedFile);
	                avatarFilePath = "images/" + fileName;
	                imgMap.put(fileName, "匹配成功！");
	                
	            }else if(i>1)
	            	imgMap.put(fileName, "多次匹配！");
	            else {
	            	imgMap.put(fileName, "不匹配！");
				}
	            
	            
	            
	        
	    }
	    request.setAttribute("imgMap", imgMap);
		request.getRequestDispatcher("pages/img.jsp").forward(request, response);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
