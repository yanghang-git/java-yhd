import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.impl.AddressCatalogDaoImpl;
import com.yhd.dao.impl.GoodsCatalogDaoImpl;
import com.yhd.pojo.Address;
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
import com.yhd.util.JDBCUtils;
import com.yhd.util.JsonUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author 杨航
 * @Date 2020/6/29 16:25
 * @Since 1.8
 */
public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println(new Timestamp(new Date().getTime()));
	}
}
