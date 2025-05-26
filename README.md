https://github.com/wangcongmao/WHDMSS-backend

开发环境要求
Spring Boot 3 版本 JDK 17

Apache Maven 3.6+ 以上版本 下载 (opens new window)（请删除 settings.xml 里的 mirror 配置）

MySql5.7.11+

1、获取源代码：

git clone git@github.com:wangcongmao/WHDMSS-backend.git

cd jeesite

 注意：不要放到中文或带空格的目录下。

2、IDEA：菜单中点击 File -> Open，然后选择刚下载的 jeesite 文件夹，点击 Open as Project 按钮。

4、此时 IDEA 或 Eclipse 会根据 JeeSite 工程配置，自动下载 Maven 依赖并编译项目。初次加载可能会较慢（根据自身网络情况而定）若工程上有小叉号，请打开 Problems 窗口，查看具体错误内容，直到无错误为止。



2、 打开 /web(-api)/src/main/resources/config/application.yml（v4.0：jeesite.yml）文件

数据库：根目录下的whjeesite.sql是使用mysqldump导出的整个数据库


配置您的产品名称、公司名称、产品版本和 JDBC 连接，例如：

数据库连接
jdbc:

数据库连接

jdbc:

Mysql 数据库配置

type: mysql

driver: com.mysql.cj.jdbc.Driver

url: jdbc:mysql://127.0.0.1:3306/jeesite_v5?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true

username: root

password: 123456

testSql: SELECT 1


#配置服务端口

2、打开 /web(-api)/src/main/resources/config/application.yml 文件，

配置您的服务端口 port、部署路径 context-path 例如:

server:
port: 8980
servlet:
context-path: /js
tomcat:
uri-encoding: UTF-8

#启动 Web 服务

#Vue 分离版
前端技术栈：TS + Vue3 + Vite。

1、IDEA：右上角运行调试配置下拉框，选择 ApiApplication 运行配置，点击 Debug 图标，启动服务。

3、分离前端需要单独部署 Vue 项目，部署方法详见：https://jeesite.com/docs/vue-install-deploy/

#部署完成后访问
1、浏览器访问：http://127.0.0.1:8980/js (opens new window)分离版：http://127.0.0.1:3100(opens new window)

2、默认最高管理员账号：system    密码：admin

3、恭喜您已经部署完成，开启您的开发之旅吧