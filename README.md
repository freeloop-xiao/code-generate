## 🐯 需求
|     | 功能      | 描述       |
|-----|---------|----------|
| ⭐️  | 生成项目工程  | 生成项目模版工程 |


## 🐯 平台简介

**FreeLoop**，以开发者为中心，打造通用用户管理快速开发平台，全部开源，个人与企业可 100% 免费使用。

| 项目名             | 说明                                     | 传送门                                                          |
|-----------------|----------------------------------------|--------------------------------------------------------------|
| `code-generate` | Spring Shell && Mybatis-plus-generator | [Github](https://github.com/freeloop-xiao/code-generate.git) |

## 🐼 内置功能

系统内置代码配置命令和模版，可以用于快速生成业务系统模版代码：


* 检测数据库配置
* 生成代码功能
* 如果不存在工程build.gradle和settings.gradle文件会自动生成


🙂 所有功能，都通过 **测试** 保证高质量。

### 系统功能

|     | 功能       | 描述             |
|-----|----------|----------------|
|     | 数据库配置检查  | 附属功能           |
| ⭐️  | 根据模版生成代码 | 根据模版生成业务增删改查代码 |

### 启动方式

 java -jar code-generate-0.0.1-SNAPSHOT.jar

说明：
    可以将使用配置文件的方式生成模版代码,application.yaml放到jar包同级的config目录下；

### 命令说明

|       | 命令       | 描述        |
|-------|----------|-----------|
| ⭐ ️️  | db       | 设置数据库配置   |
| ⭐️    | template | 设置模版配置信息  |

#### 命令：db
    选项：

    1.查看数据库配置信息命令： db 或者 db info

    2. host: 数据库主机 - 例如：db set host 127.0.0.1

    3. port: 数据库端口 - 例如：db set port 3306

    4. db: 数据库名 - 例如: db set db user

    5. user: 数据库用户名 - 例如: db set user root

    6. password: 数据库密码 - 例如 db set password xzY@123.!
 
    7. check 检测数据库连接信息 - 例如 db check

#### 命令：template
    选项：

    1.查看模版配置信息命令： template 或者 template info

    2. author: 生成代码署名 - 例如：template set author "free loop"

    3. path: 项目根目录 - 例如：template set path /code/code-generate

    4. package: 生成代码包名 - 例如: tempalte set package com.free.loop.demo

    5. tables: 生成代码表列表 - 例如: template set tables t_user,t_role

    6. group:  工程group(如果需要生成build.gradle文件则需要配置否则可以不配置)- 例如: template set group com.code


#### 命令：generate
    1.生成代码 - 例如 generate



### 后端

| 框架                                                     | 说明              | 版本         | 学习指南                                                           |
|--------------------------------------------------------|-----------------|------------|----------------------------------------------------------------|
| [Spring Boot](https://spring.io/projects/spring-boot)  | 应用开发框架          | 2.7.8      | [文档](https://github.com/YunaiV/SpringBoot-Labs)                |
| [Spring Shell](https://spring.io/projects/spring-boot) | 应用开发框架          | 2.7.8      | [文档](https://github.com/YunaiV/SpringBoot-Labs)                |
| [MySQL](https://www.mysql.com/cn/)                     | 数据库服务器          | 5.7 / 8.0+ |                                                                |
| [MyBatis Plus](https://mp.baomidou.com/)               | MyBatis 增强工具包   | 3.5.3.1    | [文档](http://www.iocoder.cn/Spring-Boot/MyBatis/?yudao)         |
