package com.sundata.edu.framework.configurer;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * druid监控视图配置
 *
 * @ClassName: DruidStatViewServlet
 * @author whj
 * @date 2018年6月24日 上午10:54:27
 */
@WebServlet(urlPatterns = "/druid/*", initParams = { @WebInitParam(name = "allow", value = ""),
		@WebInitParam(name = "loginUsername", value = "admin"),
		@WebInitParam(name = "loginPassword", value = "5Hsundata123!@#"),
		@WebInitParam(name = "resetEnable", value = "true")
})
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 2359758657306626394L;

}
