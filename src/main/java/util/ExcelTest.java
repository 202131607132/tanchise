package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.Classes;
import entity.Operator;
import entity.Student;
import impl.StudentImpl;
import impl.ClassesImpl;
import impl.OperatorImpl;
import impl.RoleImpl;

public class ExcelTest {

//    public static void main(String[] args) throws Exception {
//        String fileName = "C:\\Users\\ARHM\\Desktop\\11.xlsx";
//        readExcel(fileName);
//    }
	public static ArrayList<Student> readExcel(String fileNamee)
	{
		System.out.println(fileNamee);
		ArrayList<Student> stu = new ArrayList<>();
		StudentImpl studentImpl = new StudentImpl();
		RoleImpl roleImpl = new RoleImpl();
		OperatorImpl operatorImpl = new OperatorImpl();
		ClassesImpl classesImpl = new ClassesImpl();

        Workbook workbook = null;
        Row row = null;
        //获取Excel文档
        workbook = getWorkbook(fileNamee);
        //获取Excel文档的第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);
        //获取文档中已保存数据的行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        row = sheet.getRow(0);
        //获取当前行已保存数据的最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);//System.out.println("row--> "+row);
            if (null != row)
            {
                for (int j = 0; j < colnum;) {
                	Student s = new Student();
                    Cell num = row.getCell(j);
                    Cell name = row.getCell(j+1);
                    Cell sex = row.getCell(j+2);
                    Cell br = row.getCell(j+3);
                    Cell fileName = row.getCell(j+4);
                    Cell clas = row.getCell(j+5);
                    System.out.print("学号："+Integer.toString((int)num.getNumericCellValue()));
                    System.out.print("  姓名："+(String)name.getRichStringCellValue().getString());
                    System.out.print("  性别："+(String)sex.getRichStringCellValue().getString());
					System.out.print("  生日："+br.getDateCellValue());
                    System.out.print("  文件名："+(String)fileName.getRichStringCellValue().getString());
                    System.out.println("  班级："+(String)clas.getRichStringCellValue().getString());
                    
                    Student student = new Student();
        			Operator operator = new Operator();
        			//classesImpl.getclasid((String)clas.getRichStringCellValue().getString());
        			// 为学生添加操作员信息记录
        			operator.setName("stu"+Integer.toString((int)num.getNumericCellValue()));
        			operator.setPwd("stu"+Integer.toString((int)num.getNumericCellValue()));
        			operator.setRole(roleImpl.query("rol_id", "3").get(0));
        			operator = operatorImpl.add(operator);
        			
        			
        			// 将学生添加到学生表
        			student.setOperator(operator);
        			student.setNo(Integer.toString((int)num.getNumericCellValue()));
        			student.setName((String)name.getRichStringCellValue().getString());
        			student.setSex((String)sex.getRichStringCellValue().getString());
        			student.setBirth(br.getDateCellValue());
        			student.setClasses(classesImpl.getclasid((String)clas.getRichStringCellValue().getString()));
        			student.setPic((String)fileName.getRichStringCellValue().getString());
        			stu.add(student);
        			
        			
        			
        			//int ii = studentImpl.add(student);
        			//if(ii==1)
        				//System.out.print("cg");
//                	s.setId((int)id.getNumericCellValue());
//                	s.setName((String)name.getRichStringCellValue().getString());
//                	s.setSex((String)sex.getRichStringCellValue().getString());
//                	s.setGrade((String)grade.getRichStringCellValue().getString());
//                	s.setAge((int)age.getNumericCellValue());
//                	s.setScore((float)score.getNumericCellValue());
//                	s.setFileName((String)fileName.getRichStringCellValue().getString());
                	j=j+7;
//                	int a = m.insert(s.getId(),s.getName(),s.getSex(),s.getAge(),s.getGrade(),s.getScore(),s.getFileName());
//                	if(a==1)
//                		s.setZt("导入成功");
//                	else
//                		s.setZt("导入失败，id重复");
//                	stu.add(s);
                    
                	
                }
            }
        }
    
		return stu;
		
	
	}


    private static Workbook getWorkbook(String fileNamee) {//根据后缀获取Excel表格
        Workbook workbook = null;
        String suffix = fileNamee.substring(fileNamee.lastIndexOf(".") + 1);
        InputStream in = null;
        try {
            in = new FileInputStream(fileNamee);
            if ("xls".equals(suffix))
            {
                workbook = new HSSFWorkbook(in);
            }
            else if ("xlsx".equals(suffix))
            {
                workbook = new XSSFWorkbook(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}