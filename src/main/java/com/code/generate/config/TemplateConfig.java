package com.code.generate.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 代码生成配置信息
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/1
 */
@Data
@Component
public class TemplateConfig {


    /**
     * 源代码结尾路径
     */
    public static final String JAVA_DIR_END = "src/main/java";

    /**
     * xml结尾路径
     */
    public static final String XML_DIR_END = "src/main/resources/mapper";


    /**
     * 生成模版作者
     */
    @Value("${template.author}")
    private String author;

    /**
     * 工程路径
     */
    @Value("${template.project-path}")
    private String path;

    /**
     * 代码生成包名
     */
    @Value("${template.package-name}")
    private String packageName;

    /**
     * 生成代码表列表
     */
    @Value("#{'${template.tables}'.split(',')}")
    private List<String> tables;

    @Override
    public String toString() {
        return "模版配置信息：>>>>>>>>>>>>>>>>>>>>>>\n" +
                "author=" + author + "\n" +
                "path=" + path + "\n" +
                "packageName=" + packageName + "\n" +
                "tables=" + tables + "\n" +
                "================================";
    }
}
