import com.yhd.dao.AdminDao;
import com.yhd.dao.BaseDao;
import com.yhd.dao.impl.AdminDaoImpl;
import com.yhd.pojo.Admin;
import com.yhd.util.ConnectionFactory;

import java.sql.Connection;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:20
 * @Since 1.8
 */
public class Test {
	public static void main(String[] args) {
		Connection conn = ConnectionFactory.INSTANCE.create();
		AdminDao dao = new AdminDaoImpl();
		Admin admin = dao.getAdminByIdAndPassword(conn, "Yoyo", "yoyo123");
		System.out.println(admin);
	}

}
