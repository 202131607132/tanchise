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
import entity.Operator;
import entity.Privilege;
import entity.Score;
import entity.Systemm;
import login.servlet.OnlineUsersListener;

public class LoginImpl implements ILogin {
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

	// 登录验证
	public String login(HttpServletRequest request, Operator operator) {
		session = request.getSession();
		checkResult = "success";
		log_operator = new Operator();
		Systemm s = new Systemm();

		try {
			conn = DB.getConn();
			pst = conn.prepareStatement("SELECT * FROM system");
			rs = pst.executeQuery();
			while (rs.next()) {
				s.setSysid(rs.getInt(1));
				s.setForbitc(rs.getInt(2));
				s.setForbist(rs.getInt(3));
				s.setInformtc(rs.getString(4).toString());
				s.setInformst(rs.getString(5).toString());
			}
			
			pst = conn.prepareStatement("SELECT * FROM operator WHERE ope_name = ?");
			pst.setString(1, operator.getName());
			rs = pst.executeQuery();

			if (!rs.next()) {
				checkResult = "账户不存在，请重新输入！";
				session.setAttribute("isLogin", "false");
			} else {
				if (!operator.getPwd().equals(rs.getString(3))) {
					checkResult = "您输入的密码不正确，请重新输入！";
					session.setAttribute("isLogin", "false");
				} else {
					if (s.getForbitc() == 1 && rs.getInt(4) == 2) {
						checkResult = "教师端维护中！";
					} else if (s.getForbist() == 1 && rs.getInt(4) == 3) {
						checkResult = "学生端维护中！";
					} else {

						
						System.out.println("在线用户数：" + OnlineUsersListener.getOnlineUsersCount());

						// 登录成功
						session.setAttribute("isLogin", "true");
						String ipAddress = request.getRemoteAddr();
						System.out.print("用户名：" + operator.getName() + "  IP地址：" + ipAddress + "  登录时间："
								+ new java.util.Date().toLocaleString());
						// 获得该用户的完整信息
						log_operator.setId(rs.getInt(1));
						log_operator.setName(rs.getString(2));
						log_operator.setPwd(rs.getString(3));
						log_operator.setRole(roleImpl.query("rol_id", rs.getString(4)).get(0));
						session.setAttribute("log_operator", log_operator);
						System.out.println("  rol:"+log_operator.getRole().getId());

						// 根据用户，获取对应的角色对应的权限
						list_privilege = privilegeImpl.query("rol_id", log_operator.getRole().getId() + "");
						List<Privilege> list = new ArrayList<Privilege>();
						list.add(list_privilege.get(0));

						for (int i = 1; i < list_privilege.size(); i++) {
							int y = 0;
							for (int x = 0; x < list.size(); x++) {

								if (!list.get(x).getMenu_name().equals(list_privilege.get(i).getMenu_name())) {
									y++;
								}
							}
							if (y == list.size()) {
								list.add(list_privilege.get(i));

							}

						}
						session.setAttribute("list", list);
						session.setAttribute("zxrs", OnlineUsersListener.getOnlineUsersCount());
						session.setAttribute("sy", s);
						session.setAttribute("rol", log_operator.getRole().getId());
						session.setAttribute("list_privilege", list_privilege);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pst, rs);
		}
		return checkResult;
	}
}