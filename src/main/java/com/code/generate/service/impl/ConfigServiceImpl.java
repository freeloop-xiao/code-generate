package com.code.generate.service.impl;

import com.code.generate.config.DbConfig;
import com.code.generate.config.TemplateConfig;
import com.code.generate.service.ConfigService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.shell.standard.ShellOption.NULL;

/**
 * <p>
 * 配置修改业务方法
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/2
 */
@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    /**
     * 查询数据配置信息命令
     */
    private static final String INFO_COMMAND = "info";

    /**
     * 设置数据库配置字段信息命令
     */
    private static final String SET_COMMAND = "set";

    /**
     * 帮助信息
     */
    private static final String HELP_COMMAND = "help";

    private final DbConfig dbConfig;

    private final TemplateConfig templateConfig;


    @Override
    public Object dbCommandParse(String operation, String param, String value) {
        if (StringUtils.isEmpty(operation)
                || Objects.equals(operation, NULL)
                || Objects.equals(operation, INFO_COMMAND)) {
            return dbConfig;
        } else if (Objects.equals(operation, SET_COMMAND)) {
            return setDbInfo(param, value);
        } else if (Objects.equals(operation, HELP_COMMAND)) {
            return dbHelp();
        }
        return "命令或者参数错误";
    }


    @Override
    public Object templateCommandParse(String operation, String param, String value) {
        if (StringUtils.isEmpty(operation)
                || Objects.equals(operation, NULL)
                || Objects.equals(operation, INFO_COMMAND)) {
            return templateConfig;
        } else if (Objects.equals(operation, SET_COMMAND)) {
            return setCodeTemplateInfo(param, value);
        } else if (Objects.equals(operation, HELP_COMMAND)) {
            return templateHelp();
        }
        return "命令或者参数错误";
    }

    @Override
    public String setDbInfo(String property, String value) {
        switch (property) {
            case "host" -> dbConfig.setHost(value);
            case "port" -> dbConfig.setPort(value);
            case "db" -> dbConfig.setDb(value);
            case "user" -> dbConfig.setUser(value);
            case "password" -> dbConfig.setPassword(value);
        }
        return "数据库配置 [ " + property + " ] 修改成功 - [" + value + "]";
    }

    @Override
    public String setCodeTemplateInfo(String property, String value) {
        switch (property) {
            case "author" -> templateConfig.setAuthor(value);
            case "path" -> templateConfig.setPath(value);
            case "package" -> templateConfig.setPackageName(value);
            case "tables" -> {
                List<String> tables = Arrays.stream(value.split(","))
                        .filter(x -> !StringUtils.isEmpty(x))
                        .toList();
                templateConfig.setTables(tables);
            }
        }
        return "模版配置 [ " + property + " ] 修改成功 - [" + value + "]";
    }


    private String dbHelp() {
        return """
                db命令：
                   该命令是用来查看数据库配置信息和修改数据配置信息\s
                -----------------------------------------------
                查看数据库信息：
                  db 或者 db info\s
                -----------------------------------------------
                配置数据库信息：
                  db  set  属性  属性值\s
                  属性：
                         host ：数据库主机地址\s
                         port ：数据库端口\s
                         db ：数据库名称\s
                         user ：数据库用户\s
                         password ：数据库密码\s
                -----------------------------------------------
                """;

    }


    private String templateHelp() {

        return """
                template命令：
                   该命令是用来查看模版配置信息和修改模版配置信息\s
                -----------------------------------------------
                查看模版信息：
                  template 或者 template info\s
                -----------------------------------------------
                配置模版信息：
                  template  set  属性  属性值\s
                  属性：
                         author ：模版作者署名\s
                         path ：项目根目录\s
                         package ：代码生成包名(模块名称包含在内)\s
                         tables ：表名列表(多个表用逗号隔开,)\s
                -----------------------------------------------
                """;
    }
}
