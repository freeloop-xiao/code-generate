package com.code;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CodeGenerateApplicationTests {


    /**
     * 代码生成公共父路径
     */
    private static final String PROJECT_PATH = "/Users/xiaokun/code/code-generate/src/main/";


    @Test
    void fastGenerate() {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/tx_account?useUnicode=true&useSSL=false&characterEncoding=utf8&&serverTimezone=UTC",
                        "root", "freeloop")
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("free loop")
                            // 开启swagger注释
                            .enableSwagger()
                            // 生成之后不打开文件夹
                            .disableOpenDir()
                            // 指定类文件输出目录
                            .outputDir(PROJECT_PATH + "java");
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.free.loop")
                            // 设置父包模块名
                            .moduleName("example")
                            .entity("domain.entity")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + "resources/mapper"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("t_account")
                            // 设置过滤表前缀
                            .addTablePrefix("t_", "c_")
                            // 开启Lombok
                            .entityBuilder().enableLombok();
                })
                .injectionConfig(builder -> {
                    // 添加自定义文件输出
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().fileName("AddDTO.java").templatePath("/templates/add-dto.java.vm").packageName("domain/dto").build());
                    customFiles.add(new CustomFile.Builder().fileName("EditDTO.java").templatePath("/templates/edit-dto.java.vm").packageName("domain/dto").build());
                    customFiles.add(new CustomFile.Builder().fileName("VO.java").templatePath("/templates/vo.java.vm").packageName("domain/vo").build());
                    customFiles.add(new CustomFile.Builder().fileName("Converter.java").templatePath("/templates/converter.java.vm").packageName("domain/converter").build());
                    builder.customFile(customFiles);
                })
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

}
