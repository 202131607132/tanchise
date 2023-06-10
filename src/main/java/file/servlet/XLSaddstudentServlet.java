package file.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import entity.Score;
import entity.Student;
import impl.StudentImpl;
import jxl.Cell;
import jxl.Workbook;
import jxl.format.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.DateFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.DateFormats;
import jxl.write.biff.RowsExceededException;
import jxl.write.DateTime;

/**
 * Servlet implementation class File
 */
@WebServlet("/XLSFile")
public class XLSaddstudentServlet extends HttpServlet {
	StudentImpl studentImpl = new StudentImpl();
	ArrayList<Student> stus = null;
	private Label label = null;
	private Number number = null;
	private Date birth;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> li = new ArrayList<>();
		File file = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (request.getParameter("ac").equals("aaaa")) {

				response.setContentType("application/vnd.ms-excel"); // 保证不乱码
				String fileName = "学生.xls";
				response.setHeader("Content-Disposition",
						"attachment;" + " filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
				OutputStream os = response.getOutputStream();
				WritableWorkbook book = Workbook.createWorkbook(os);
				int length = stus.size();
				int sheetSize = 10;
				int sheetNum = 1;
				if (length % sheetSize > 0) {
					sheetNum = length / sheetSize + 1;
				} else {
					sheetNum = length / sheetSize;
				}
				try {
					for (int pg = 0; pg < sheetNum; pg++) {
						WritableSheet ws = book.createSheet(("第-" + (pg + 1) + "-页"), pg);
						WritableFont title_big = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
						WritableFont title_little = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
						WritableFont cell = new WritableFont(WritableFont.ARIAL, 11);
						WritableCellFormat cell_format = new WritableCellFormat(cell);
						WritableCellFormat title_big_format = new WritableCellFormat(title_big);
						WritableCellFormat title_little_format = new WritableCellFormat(title_little);
						ws.setRowView(0, 600);
						title_big_format.setBorder(Border.ALL, BorderLineStyle.THIN);
						title_big_format.setAlignment(jxl.format.Alignment.CENTRE);
						title_big_format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
						title_little_format.setAlignment(jxl.format.Alignment.CENTRE);
						title_little_format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
						title_little_format.setBorder(Border.ALL, BorderLineStyle.THIN);
						cell_format.setAlignment(jxl.format.Alignment.CENTRE);
						cell_format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
						cell_format.setBorder(Border.ALL, BorderLineStyle.THIN);
						cell_format.setWrap(false);
						String ss[] = { "学生学号", "学生姓名", "性别", "生日", "照片名", "班级", "导入结果" };
						for (int i = 0; i < 7; i++) {
							label = new Label(i, 0, ss[i], title_little_format);
							ws.addCell(label);
							ws.setColumnView(i, 14);
						}
						String[] strArray = new String[li.size()];
						for (int i = pg * sheetSize; i < (pg + 1) * sheetSize; i++) {
							if (i < length) {
								Student bean = (Student) stus.get(i);
								for (int j = 0; j < 7; j = j + 7) {

									number = new Number(j, i + 1 - (pg * sheetSize), Integer.parseInt(bean.getNo()),
											cell_format);
									ws.addCell(number);
									label = new Label(j + 1, i + 1 - (pg * sheetSize), bean.getName(), cell_format);
									ws.addCell(label);
									label = new Label(j + 2, i + 1 - (pg * sheetSize), bean.getSex(), cell_format);
									ws.addCell(label);
									Date date = bean.getBirth2();
									// 创建DateTime对象，用于将日期写入Excel单元格
									DateTime dateTime = new DateTime(j + 3, i + 1 - (pg * sheetSize), date);
									ws.addCell(dateTime);
									label = new Label(j + 4, i + 1 - (pg * sheetSize), bean.getPic(), cell_format);
									ws.addCell(label);
									label = new Label(j + 5, i + 1 - (pg * sheetSize), bean.getClasses().getName(),
											cell_format);
									ws.addCell(label);
									label = new Label(j + 6, i + 1 - (pg * sheetSize), bean.getZt(), cell_format);
									ws.addCell(label);

								}
							} else {
								break;
							}
						}

					}
					book.write();
					book.close();
					os.close();
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}

			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				// 文件上传核心工具类
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
				upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
				upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

				if (ServletFileUpload.isMultipartContent(request)) {
					// 3. 把请求数据转换为list集合
					List<FileItem> list = upload.parseRequest(request);
					// 遍历
					for (FileItem item : list) {
						// 判断：普通文本数据
						if (item.isFormField()) {
							// 普通表单项操作
						}
						// 文件表单项
						else {
							// a. 获取文件名称
							String name = item.getName();
							// b. 得到上传目录
							String basePath = getServletContext().getRealPath("/");
							// c. 创建要上传的文件对象
							// String basePath = "f:\\";
							file = new File(basePath, name);
							// d. 上传
							item.write(file);

							item.delete(); // 删除组件运行时产生的临时文件
							stus = util.ExcelTest.readExcel(basePath + name);

							file.delete();
							int num = 0;

							for (Student s : stus) {
								String string = "导入失败！";
								System.out.println(s.getId() + "\t" + s.getOperator().getName() + "\t"
										+ s.getOperator().getPwd() + "\t" + s.getNo() + "\t" + s.getOperator().getId()
										+ "\t" + s.getName() + "\t" + s.getSex() + "\t" + s.getBirth() + "\t"
										+ s.getPic() + "\t" + s.getClasses().getId() + "\t" + s.getClasses().getName());

								if (1 == studentImpl.add2(s.getOperator().getId(), s.getNo(), s.getName(), s.getSex(),
										s.getBirth(), "../images/" + s.getPic(), s.getClasses().getId())) {
									string = "导入成功！";
									num++;
								}
								s.setZt(string);
								// model.insert(u.getId(),u.getName(),u.getPassword());
							}
							request.setAttribute("stuList", stus);
							request.setAttribute("num", num);
							request.getRequestDispatcher("pages/excel.jsp").forward(request, response);

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
