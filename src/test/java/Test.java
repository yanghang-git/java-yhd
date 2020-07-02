import com.yhd.dao.impl.AddressCatalogDaoImpl;
import com.yhd.pojo.AddressCatalog;
import com.yhd.service.backend.AddressCatalogService;
import com.yhd.service.backend.impl.AddressCatalogServiceImpl;
import com.yhd.util.ConnectionFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 杨航
 * @Date 2020/6/29 16:25
 * @Since 1.8
 */
public class Test {
	public static void main(String[] args) throws UnknownHostException {
		AddressCatalogService service = new AddressCatalogServiceImpl(ConnectionFactory.INSTANCE.create(),
				new AddressCatalogDaoImpl());
		List<AddressCatalog> listByUpId = service.getListByUpId(1);

		System.out.println(listByUpId);
	}
}
