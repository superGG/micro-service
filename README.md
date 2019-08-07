# micro-serice

## 简介
该项目为使用spring-boot搭建的微服务的基础服务架构，用户快速搭建可用的单体服务。

## 功能模块
无具体业务模块，由自行填充

## 辅助工具
#### swagger
swagger为在线接口文档，并使用了开源UI库swagger-bootstrap-ui提高用户操作体验；同时使用了帐密登录管理。

#### 请求拦截器IpInterceptor
IpInterceptor的作用是拦截指定接口请求，并输出请求的参数日志；便于调试；

#### 逆向工程工具 generator
generator可逆向根据数据库表结构生成对应的Java Bean 、Dao和Mapper.xml

#### 错误信息枚举 ExceptionEnum
错误信息枚举类，可统一定义错误编码与错误信息

#### 自定义错误类 ExceptionEntity
自定义错误类，结合错误枚举类ExceptionEnum一起使用。亦可根据自身需求，抛出错误编码与错误信息

#### 统一错误处理器SystemExceptionHandler
SystemExceptionHandler可截取系统所有请求错误，并统一返回错误信息；统一的错误返回前端，可避免因未知错误带来移动端闪退情况。

## 注意事项
下载该框架后，注意修改指定包路径信息，确保运行无误：
1、generatorConfig.xml文件中，相关对象保存路径、Dao文件路径、以及数据表对应关系
2、Application主运行文件中的Mapper扫描注解@MapperScan
3、Swagger2Config，swagger的配置文件中，配置模块controller的对应路径
4、application-*.yml配置文件中，配置dao层显示输出sql语句
5、默认存在的Mapper.xml文件中，所有指定路径的需一次性替换

## 版本信息
JDK 1.8
spring-boot 2.1.3
swagger 2.9.2
mybatis 1.3.4
pageHelper 1.2.10

