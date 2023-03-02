package com.code.generate.service;

/**
 * <p>
 * 配置信息业务类
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2023/3/2
 */
public interface ConfigService {


    /**
     * 数据库命令解析
     * 命令格式
     * db operation  param  value
     *      info
     *      set      host    localhost
     *
     * @param operation 操作 (info/set)
     * @param param     参数
     * @param value     参数值
     * @return result
     */
    Object dbCommandParse(String operation, String param, String value);


    /**
     * 模版配置命令解析器
     * template operation   param   value
     *          info
     *          set         author   "free loop"
     * @param operation 操作 info/set
     * @param param 参数名
     * @param value 参数值
     * @return result
     */
    Object templateCommandParse(String operation, String param, String value);


    /**
     * 修改数据库配置信息
     *
     * @param property 属性名
     * @param value    属性值
     * @return result
     */
    String setDbInfo(String property, String value);


    /**
     * 修改模版配置信息
     *
     * @param property 属性名
     * @param value    属性值
     * @return result
     */
    String setCodeTemplateInfo(String property,String value);



}
