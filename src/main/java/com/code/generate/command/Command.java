package com.code.generate.command;


import com.code.generate.config.DbConfig;
import com.code.generate.service.CodeGenerateService;
import com.code.generate.service.ConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static org.springframework.shell.standard.ShellOption.NULL;

/**
 * <p>
 * 代码生成命令控制器
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/1
 */
@Slf4j
@ShellComponent
@AllArgsConstructor
public class Command {

    private final ConfigService configService;

    private final DbConfig dbConfig;

    private final CodeGenerateService codeGenerateService;


    /**
     * DB配置命令
     *
     * @param operation 操作 info/set
     * @param param     属性
     * @param value     属性值
     * @return result
     */
    @ShellMethod("db")
    public Object db(@ShellOption(defaultValue = NULL) String operation,
                     @ShellOption(defaultValue = NULL) String param,
                     @ShellOption(defaultValue = NULL) String value) {
        return configService.dbCommandParse(operation, param, value);
    }


    /**
     * 模版配置命令
     *
     * @param operation 操作 info/set
     * @param param     属性名
     * @param value     属性值
     * @return result
     */
    @ShellMethod("template")
    public Object template(@ShellOption(defaultValue = NULL) String operation,
                           @ShellOption(defaultValue = NULL) String param,
                           @ShellOption(defaultValue = NULL) String value) {
        return configService.templateCommandParse(operation, param, value);
    }


    /**
     * 代码生成命令
     *
     * @return result
     */
    @ShellMethod("generate")
    public Object generate() {
        return codeGenerateService.build();
    }

    /**
     * 数据库信息检测
     *
     * @return result
     */
    @ShellMethod("check")
    public Object check() {
        return dbConfig.check();
    }
}
