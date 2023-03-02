package com.code.generate.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.code.generate.config.DbConfig;
import com.code.generate.config.TemplateConfig;
import com.code.generate.service.CodeGenerateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 代码生成实现类
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/2
 */
@Slf4j
@Service
@AllArgsConstructor
public class CodeGenerateServiceImpl implements CodeGenerateService {

    private final DbConfig dbConfig;

    private final TemplateConfig templateConfig;

    @Override
    public FastAutoGenerator createGenerator() {
        String url = StrUtil.format(DbConfig.DB_URL, dbConfig.getHost(), dbConfig.getPort(), dbConfig.getDb());
        return FastAutoGenerator.create(url, dbConfig.getUser(), dbConfig.getPassword());
    }

    @Override
    public String build() {
        System.out.println(dbConfig);
        System.out.println(templateConfig);

        FastAutoGenerator generator = createGenerator();

        // 代码生成路径
        String codePath = templateConfig.getPath() + TemplateConfig.JAVA_DIR_END;

        // xml生成目录
        String xmlPath = templateConfig.getPath() + TemplateConfig.XML_DIR_END;

        // 获取全包路径
        String packageName = templateConfig.getPackageName();

        if (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.lastIndexOf("."));
        }

        // 代码生成路径
        String packagePath = packageName.substring(0, packageName.lastIndexOf("."));

        // 模块名称
        String moduleName = packageName.substring(packageName.lastIndexOf(".") + 1);


        generator.globalConfig(builder -> {
            // 设置作者
            builder.author(templateConfig.getAuthor())
                    // 开启swagger注释
                    .enableSwagger()
                    // 生成之后不打开文件夹
                    .disableOpenDir()
                    // 指定类文件输出目录
                    .outputDir(codePath);
        }).packageConfig(builder -> {
            // 设置父包名
            builder.parent(packagePath)
                    // 设置父包模块名
                    .moduleName(moduleName).entity("domain.entity")
                    // 设置mapperXml生成路径
                    .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath));
        }).strategyConfig(builder -> {
            // 设置需要生成的表名
            builder.addInclude(templateConfig.getTables())
                    // 设置过滤表前缀
                    .addTablePrefix("t_", "c_")
                    // 开启Lombok
                    .entityBuilder().enableLombok();
        }).injectionConfig(builder -> {
            // 添加自定义文件输出
            List<CustomFile> customFiles = new ArrayList<>();
            customFiles.add(new CustomFile.Builder().fileName("AddDTO.java").templatePath("/templates/add-dto.java.vm").packageName("domain/dto").build());
            customFiles.add(new CustomFile.Builder().fileName("EditDTO.java").templatePath("/templates/edit-dto.java.vm").packageName("domain/dto").build());
            customFiles.add(new CustomFile.Builder().fileName("VO.java").templatePath("/templates/vo.java.vm").packageName("domain/vo").build());
            customFiles.add(new CustomFile.Builder().fileName("Converter.java").templatePath("/templates/converter.java.vm").packageName("domain/converter").build());
            builder.customFile(customFiles);
        }).templateEngine(new VelocityTemplateEngine()).execute();
        return "生成代码成功！";
    }

}
