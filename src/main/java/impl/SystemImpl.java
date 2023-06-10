package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.DB;
import dao.ILogin;
import dao.ISystem;
import entity.Operator;
import entity.Privilege;
import entity.Systemm;
import login.servlet.OnlineUsersListener;

public class SystemImpl implements ISystem {
	private PrivilegeImpl1 privilegeImpl = new PrivilegeImpl1();
	private RoleImpl roleImpl = new RoleImpl();
	private List<Privilege> list_privilege;
	private PreparedStatement pst;
	private Operator log_operator;
	private HttpSession session;
	private String checkResult;
	private Connection conn;
	private ResultSet rs;
	private String accountName;
	OnlineUsersListener o = new OnlineUsersListener();
	@Override
	public String system(HttpServletRequest request, Systemm system) {
		session = request.getSession();
		checkResult = "success";
		int i = 0;
		try {
			conn = DB.getConn();
			pst = conn.prepareStatement("update system set forbitc=? , forbist = ? , informtc = ? , informst = ?  where sysid= 1");
			pst.setInt(1,system.getForbitc());
			pst.setInt(2,system.getForbist());
			pst.setString(3,system.getInformtc());
			pst.setString(4,system.getInformst());
			i = pst.executeUpdate();
			if(i!=0)
				checkResult = "修改成功！";
			else {
				checkResult = "修改失败！";
			}
			
			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pst, rs);
		}
		
		
		return checkResult;
	}

	
}