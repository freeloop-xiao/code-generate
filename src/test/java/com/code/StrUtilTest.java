package com.code;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/3
 */
public class StrUtilTest {

    @Test
    public void parse(){
        String path = "/data/test/";
        String[] pathArr = path.split(FileUtil.FILE_SEPARATOR);
        System.out.println(pathArr);
    }
}
