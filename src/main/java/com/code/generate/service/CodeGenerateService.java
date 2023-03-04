package com.code.generate.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

/**
 * <p>
 * 代码生成接口
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/1
 */
public interface CodeGenerateService {


    /**
     * 创建Generator
     *
     * @return generator
     */
    FastAutoGenerator createGenerator();



    /**
     * 构建Generator
     */
    String build();

}
