#micro-serice

##简介
该项目为使用spring-boot搭建的微服务的基础服务架构，用户快速搭建可用的单体服务。

##功能模块
无具体业务模块，由自行填充

##辅助工具
####swagger
swagger为在线接口文档，并使用了开源UI库swagger-bootstrap-ui提高用户操作体验；同时使用了帐密登录管理。

####请求拦截器IpInterceptor
IpInterceptor的作用是拦截指定接口请求，并输出请求的参数日志；便于调试；

####统一错误处理器SystemExceptionHandler
SystemExceptionHandler可截取系统所有请求错误，并统一返回错误信息；统一的错误返回前端，可避免因未知错误带来移动端闪退情况。

##版本信息
JDK 1.8
spring-boot 2.1.3
swagger 2.9.2


