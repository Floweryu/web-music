# web-music

北京化工大学应用软件实践——一个简单的web-music后台管理系统

参与者：[**Ice-Jeffrey**](https://github.com/Ice-Jeffrey)   [**carjeff** Car Jeff](https://github.com/carjeff)

### 文件功能

`music-server`——项目的后端

`music-manage`——项目后台管理的前端

### 项目简介

#### 前端：

采用`vue`全家桶，使用`nginx`部署到服务器上

进入前端目录，先`npm run install`安装依赖

执行`npm run dev`启动开发环境

执行`npm run prod`进行打包，打包目录是`admin`



#### 后端：

`springboot + mybatis + maven3.6.2 + mysql8.0^`

默认启动的是本地环境，也可指定启动环境文件，本地在导入数据库后，只需要将`img`文件夹放到`src`同级目录下即可启动运行。

****

采用以下命令可部署到服务器上，需要指定线上环境：

`nohup java -jar music-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > temp.txt 2>&1 &`

由于项目涉及到文件上传功能，下面说说解决方法：

改变`springboot`静态资源映射路径，不把图片等资源存放到`resources`下，而是存储到外挂目录——在`src`同级目录下的`img`中。这样做的好处是：打包可以不用将静态资源打包，静态资源在项目运行中是会动态增加的，这样做方便管理。但带来的问题是：后端部署到服务器上回造成资源路径找不到，这时需要配置`nginx`静态资源服务器：

![image-20210119201226597](https://i.loli.net/2021/01/19/wPCbFRoKW95ATvZ.png)

然后，在服务器中将资源文件放到`jar`包的同级目录即可访问资源。

#### 版权信息

本源码遵循MIT开源协议发布，并提供免费使用。