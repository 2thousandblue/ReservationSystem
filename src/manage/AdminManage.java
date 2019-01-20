package manage;

import exception.AdminException;
/**
 * @Description: 管理员接口
 * @author: 我的袜子都是洞
 * @date: 2019-01-19 14:00
 */
public interface AdminManage {
	/**
	 * 管理员登陆验证
	 * @return
	 */
	boolean loginAdmin (String loginname, String password) throws AdminException;
}
