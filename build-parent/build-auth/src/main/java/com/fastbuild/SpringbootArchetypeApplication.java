package com.fastbuild;

import com.fastbuild.common.CheckOsInfo;
import com.fastbuild.common.EPlatform;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.sql.SQLException;

@SpringBootApplication
public class SpringbootArchetypeApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringbootArchetypeApplication.class);

	public static void main(String[] args) {
		// 启动h2 数据库
		startH2Server();
		SpringApplication springApplication = new SpringApplication(SpringbootArchetypeApplication.class);
		//设置banner的模式 OFF:隐藏 CONSOLE:控制台
		springApplication.setBannerMode(Banner.Mode.CONSOLE);
		//启动springboot应用程序
		springApplication.run(args);

	}

	/**
	 *  启动h2 数据库服务
	 *  服务式 （Server） jdbc:h2:tcp://localhost/~/test
	 *  嵌入式（Embedded） jdbc:h2:~/test
	 *  内存式（Memory） jdbc:h2:tcp://localhost/mem:test
	 *
	 *  设置存储文件地址后若数据库链接后跟~/xxx形式的链接 会导致冲突
	 *
	 *  http://www.importnew.com/17924.html
	 */
	private static void startH2Server() {
		try {
			String h2DBBaseDir = null;
//			String h2Port = "9843";
//			jdbc:h2:tcp://localhost:9092/auth;DB_CLOSE_DELAY=-1;WRITE_DELAY=0
			EPlatform platform = CheckOsInfo.getOSname();
			if(platform.equals(EPlatform.Windows)){
				h2DBBaseDir = "D:\\home\\h2_base\\";
			}else if (platform.equals(EPlatform.Mac_OS)||platform.equals(EPlatform.Mac_OS_X)||platform.equals(EPlatform.Linux)){
				h2DBBaseDir = "/usr/local/h2_base/";
			}else{
				throw new RuntimeException("当前系统不匹配，此仅支持 windows，linux ，mac");
			}
			File file = new File(h2DBBaseDir);
			if (!file.exists()) file.mkdirs();
			Server h2Server = Server.createTcpServer("-tcpAllowOthers","-baseDir",h2DBBaseDir).start();
//			Server h2Server = Server.createTcpServer("-tcpAllowOthers","-tcpPort",h2Port,"-baseDir",h2DBBaseDir).start();
			if (h2Server.isRunning(true)) {
				logger.debug("H2 数据库 存储文件目录为 h2DBBaseDir:{}",h2DBBaseDir);
				logger.debug("H2 server was started and is running.");
			} else {
				throw new RuntimeException("Could not start H2 server.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to start H2 server: ", e);
		}
	}
}
