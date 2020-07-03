import com.yhd.dao.impl.AddressCatalogDaoImpl;
import com.yhd.dao.impl.GoodsCatalogDaoImpl;
import com.yhd.pojo.AddressCatalog;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.service.backend.AddressCatalogService;
import com.yhd.service.backend.GoodsCatalogService;
import com.yhd.service.backend.impl.AddressCatalogServiceImpl;
import com.yhd.service.backend.impl.GoodsCatalogServiceImpl;
import com.yhd.util.ConnectionFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author 杨航
 * @Date 2020/6/29 16:25
 * @Since 1.8
 */
public class Test {
	public static void main(String[] args) throws UnknownHostException {
		GoodsCatalogService service = new GoodsCatalogServiceImpl(ConnectionFactory.INSTANCE.create(),
				new GoodsCatalogDaoImpl());
		int id = 59;
		List<GoodsCatalog> list = service.getAllListByUpId(id);
		for (GoodsCatalog catalog : list) {
			System.out.println(catalog);
		}
	}
}
