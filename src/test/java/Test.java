import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.impl.AddressCatalogDaoImpl;
import com.yhd.dao.impl.GoodsCatalogDaoImpl;
import com.yhd.pojo.AddressCatalog;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.pojo.Indent;
import com.yhd.service.backend.AddressCatalogService;
import com.yhd.service.backend.GoodsCatalogService;
import com.yhd.service.backend.IndentService;
import com.yhd.service.backend.impl.AddressCatalogServiceImpl;
import com.yhd.service.backend.impl.GoodsCatalogServiceImpl;
import com.yhd.service.backend.impl.IndentServiceImpl;
import com.yhd.util.ConnectionFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
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
		IndentService service = new IndentServiceImpl(ConnectionFactory.INSTANCE.create(), DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent"));
		List<Indent> list = service.getListByStatusAndGoodsIdAndUserIdAndId(2, null, null, "2922e1650ff34196bb2faab7262571f9",1,1);
		list.forEach(System.out::println);
	}
}
