package com.code.generate.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 自定扩展VelocityTemplateEngine
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/4
 */
public class CustomVelocityTemplateEngine extends VelocityTemplateEngine {

    private static final Set<String> SOURCE_FILE_NAME_SET = new HashSet<>();

    static {
        SOURCE_FILE_NAME_SET.add("settings.gradle");
        SOURCE_FILE_NAME_SET.add("build.gradle");
    }


    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {

        String entityName = tableInfo.getEntityName();

        String parentPath = getPathInfo(OutputFile.parent);

        customFiles.forEach(file -> {

            String filePath = StringUtils.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;

            if (StringUtils.isNotBlank(file.getPackageName())) {
                filePath = filePath + File.separator + file.getPackageName();
            }

            String fileName = filePath + File.separator + entityName + file.getFileName();

            if (SOURCE_FILE_NAME_SET.contains(file.getFileName())) {
                fileName = filePath + File.separator + file.getFileName();
            }

            outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        });
    }
}
