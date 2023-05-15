package com.code.generate.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.simple.SimpleDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * <p>
 * 数据库配置信息
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/1
 */
@Data
@Component
public class DbConfig {

    public static final String DB_URL = "jdbc:mysql://{}:{}/{}?useUnicode=true&useSSL=false&characterEncoding=utf8&&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    /**
     * 数据库主机
     */
    @Value("${db.host}")
    private String host;

    /**
     * 数据库端口
     */
    @Value("${db.port}")
    private String port;

    /**
     * 数据库名
     */
    @Value("${db.name}")
    private String db;

    /**
     * 连接数据库用户
     */
    @Value("${db.user}")
    private String user;


    /**
     * 连接数据库密码
     */
    @Value("${db.password}")
    private String password;

    @Override
    public String toString() {
        return "           数据库配置信息 \n" +
                " ==============================\n" +
                "  host     = " + host + "\n" +
                "  port     = " + port + "\n" +
                "  db       = " + db + "\n" +
                "  username = " + user + "\n" +
                "  password = " + password + "\n" +
                " ==============================\n";
    }

    public String check() {
        try {
            String url = StrUtil.format(DbConfig.DB_URL, getHost(), getPort(), getDb());
            DataSource ds = new SimpleDataSource(url, getUser(), getPassword());
            ds.getConnection();
        } catch (Exception e) {
            return StrUtil.format("数据库配置信息错误! - {}", e.getMessage()) + "\n" +
                    "url: " + StrUtil.format(DbConfig.DB_URL, getHost(), getPort(), getDb()) + "\n" +
                    "user: " + getUser() + "\n" +
                    "password: " + getPassword() + "\n";
        }
        return "OK!";
    }
}
