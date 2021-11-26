# thinking-in-spring-5.2.16


### 1. bean-scope 模块启动 web 服务流程
- 集成tomcat plugin
  File -> Settings -> Plugins -> download Smart Tomcat
- Run/Debug Configurations +Smart Tomcat Tomcat Server
Tomcat安装根目录 Deployment Directory：web项目的webapp目录
- 若提示“程序包不存在”
File -> settings -> Build,Excution,Deployment -> maven -> Runner -> Delegate IDE build/run actions to Maven 勾选上