<p align="center">
	<a href="#"><img src="https://img.codingtalk.cn/B44paPc1585145412609"></a>
</p>
<p align="center">[power by 码上talk©]</p>
<p align="center">
	<a target="_blank" href="https://gitee.com/running-cat/tacomall-springcloud/blob/master/LICENSE">
		<img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="LICENSE"/>
	</a>
    <a target="_blank" href="https://spring.io/projects/spring-boot">
		<img src="https://img.shields.io/badge/springboot-2.1.7.RELEASE-green" alt="springboot-2.1.7.RELEASE"/>
	</a>
     <a target="_blank" href="https://spring.io/projects/spring-cloud">
		<img src="https://img.shields.io/badge/spring cloud-Greenwich.SR3-blue" alt="springcloud-Greenwich.SR3"/>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
	</a>
	<a target="_blank" href="https://swagger.io">
		<img src="https://img.shields.io/badge/swagger-2.0-orange.svg"/>
	</a>
</p>
<p align="center">
面向中台架构设计，践行“小前台，大中台”思想，基于spring cloud开发的微服务商城后台。
弹性的水平架构设计，优雅的代码，合理的注释，丰富的接口文档，适合想入门微服务，却又没有适合的项目练习的同学们。
同时该项目适用于企业面对超大型业务需求，免于项目技术选型的迷茫，搭建项目繁琐，服务集群部署（提供docker集群自动化CI/CD解决方案）将主要的精力用于业务开发上。
</p>

<p align="center">
spring cloud微服务基于spirngboot开发，还没有springboot开发基础？公司项目不太需要微服务？不用纠结 =====>
<a href="https://gitee.com/running-cat/tacomall-springboot" target="_blank">另有springboot版本</a>
</p>

<p align="center">
 tacomall并不是一个单纯的后台项目，你可能还需要跨平台小程序=====>
<a href="https://gitee.com/running-cat/tacomall-uniapp" target="_blank">tacomall-uniapp</a>
</p>

<p align="center">
除此之外，你希望我们有一个完整的商城开源项目=====>
<a href="https://gitee.com/running-cat/tacomall-enterprise" target="_blank">商家管理后台</a>
<a href="https://gitee.com/running-cat/tacomall-admin" target="_blank">平台管理后台</a>
</p>

[TOC]

```
                                                                
                                                                
                                                                
 __  ___  ___      ___      ___      _   __      ___     // //  
  / /   //   ) ) //   ) ) //   ) ) // ) )  ) ) //   ) ) // //   
 / /   //   / / //       //   / / // / /  / / //   / / // //    
/ /   ((___( ( ((____   ((___/ / // / /  / / ((___( ( // //     

如果你发现项目不错，不要忘记给项目点个赞👍，你的支持是我们前进的动力 :)
```

## 社区

<table border="0">
    <tr>
        <td>关注“码上talk”微信公众号</td>
        <td>tacomall QQ交流群</td>
        <td>我的微信</td>
    </tr>
    <tr>
        <td><img title="码上talk" src="https://img.codingtalk.cn/ncr3m3k1585147351285" height="200" width="200"/></td>
        <td><img title="tacomall QQ交流群" src="https://img.codingtalk.cn/haPkJxc1585147447571" height="200" width="220"/></td>
        <td><img title="码上talk|RC" src="https://img.codingtalk.cn/meJzskn1584540440273" height="200" width="220"/></td>
    </tr>
</table>

<p>如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢</p>

<p>如果您对此项目感兴趣，请点右上角 "Star" 支持一下谢谢</p>

<p>如果需要帮助请留言或者加微信，晚上20：00后统一回复解决</p>

## 项目结构

通过项目结构，你将清楚明白你即将入手的是一个怎么样的项目，你可能需要什么，如何快速的把它变成自己的产品。
### 核心业务

- [x] [服务注册](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-eureka) 服务发现、注册
- [x] [服务配置](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-config) 服务配置
- [x] [服务监控](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-admin) 服务监控及服务调整(包含：日志等级调整、基础服务状态与服务使用状态)
- [x] [服务网关](https://gitee.com/running-cat/tacomall-springcloud/tree/master/monitor) 接口鉴权和路由分配
- [x] [服务调度](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-job) 任务集中调度服务
- [x] [业务中台](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-service/app) 平台业务中台化（包括用户，商品等颗粒化业务）
- [ ] [数据中台](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-service/tacomall-service-data) 平台数据中台化（商品标签，画像）
- [x] [小程序前台](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-enterface/tacomall-enterface-portal) 小程序接口
- [ ] [商家前台](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-enterface/tacomall-enterface-enterprise) 商家接口
- [ ] [平台前台](https://gitee.com/running-cat/tacomall-springcloud/tree/master/tacomall-enterface/tacomall-enterface-admin) 平台接口

### 架构原理

中台架构是一种业务思想，它是为了面对急剧变化的业务需求和相对滞后的软件项目交付的矛盾而产生的业务组织和代码开发方式的转变，而微服务又是一个很好的支持中台架构实现的技术手段。

#### 中台架构

中台架构原理图（注意：并非项目实际结构，仅代表原理）

<p>
    <img src="https://img.codingtalk.cn/z7rTfRj1585230074899" alt="中台架构原理图"/>
</p>

#### 微服务

微服务原理图（注意：并非项目实际结构，仅代表原理）

<p>
    <img src="https://img.codingtalk.cn/dCkDQz61585230264007" alt="微服务原理图"/>
</p>

### 代码描述

~~~
tacomall-sprincloud                             项目
├─tacomall-admin                                服务监控
├─common                               公共依赖
├─tacomall-config                               服务监控
├─tacomall-eureka                               服务发现、注册
├─api                                  前台（接口）
│  ├─api-portal                        小程序前台
│  ├─api-merchant                      商家前台
│  ├─api-admin                         平台前台
├─tacomall-job                                  任务调度
│  ├─tacomall-job-admin                         任务配置
│  ├─tacomall-job-executor                      任务执行
├─tacomall-service                              中台
│  ├─app                       业务中台
│  │  ├─app-file               文件服务
│  │  ├─app-member             会员服务
│  │  ├─app-op                 第三方开放平台
│  │  ├─app-goods              商品服务
│  ├─tacomall-service-data                      数据中台
│  │  ├─tacomall-service-data-goods             商品画像
├─tacomall-zull                                 服务网关
├─pom.xml                                       依赖构建配置
~~~

## 上手指南

我们希望开源的项目能够让每一个人都能够一看就懂，轻松上手，但这并不意味者我们不需要做任何东西，相反，在正式运行项目前，你必须完成以下步骤。

### 环境要求

为了避免运行开发中遇到一些意想不到的问题，我们推荐你安装如下环境。

1. Git
2. JDK1.8+
3. Maven3.5+
4. Mysql5.7+
5. Idea2019.3.3
6. Postman
7. mysql
8. pdman
### 安装步骤

通过以下步骤，你将很快看见项目运行起来了！

1. 克隆项目

```
git clone https://gitee.com/running-cat/tacomall-springcloud.git
```
2. 配置idea

为了更好的运行项目，我们需要将idea的jdk环境路径配置为我们上面早已安装好的jdk1.8+,同时配置idea默认maven路径为我们上述安装好的maven并配置好国内源。

3. 导入idea

打开idea导入克隆下来的项目

4. maven依赖安装

右键根目录的pom.xml，maven->reimport

5. 导入数据

打开我们的数据库设计工具（pdman）[下载地址](https://gitee.com/robergroup/pdman?_from=gitee_search)。

```
导入项目（_doc/pdman/tacomall.pdman.json）->配置数据库->运行导入mysql
```

初始化了数据库后，为了有数据展示，我们提供了一个简易的[京东爬虫程序](https://gitee.com/running-cat/tacomall-springboot/tree/master/_doc/jd)用于初始化数据库数据

6. 修改数据库配置

在每个服务项目中（src/main/resources/application-dev.yml）修改相应的数据库配置。

7. 运行服务

项目中有许多微服务，眼花缭乱的你不用惊慌，其实每个服务的启动方式和springboot是一样的,只不过是我们需要注意启动顺序。

> 为了快速看见效果，在保证启动tacomall-eureka,monitor下，只需启动tacomall-service/app-member,tacomall-service/app-op和api/api-portal

## 测试

看到这里，我们认为你已经正确配置启动项目了，接下来你将通过postman看到实际效果。

```
通过tacomall-uniapp登录页调用接口更加哟 :-)

http://localhost:4000/portal/member/wxMaLogin
```

## 部署

我们提供了docker容器化部署方案，详情请查看[部署](https://gitee.com/running-cat/tacomall-springcloud/blob/master/LICENSE)，但并不意味着你不得不选择docker部署，你仍然可以自由选择你喜欢的部署方式进行部署。

## 使用的框架

项目中使用到以下框架（不限于）

| 框架             | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| spring cloud              | 整体提供微服务解决方案                      |
| springboot        | 提供web服务功能 |
| mybatis-plus    | 提供简化的数据库操作接口      |
| dynamic-datasource-spring-boot-starter    | 多数据源解决方案      |
| lock4j-spring-boot-starter    | 分布式锁解决方案      |
| jobs-spring-boot-starter    | 分布式任务调度解决方案      |
| springfox-swagger2    | 接口文档      |
| weixin-java-miniapp   | 小程序sdk      |

## 版本控制

每个版本的发布我们将在[RELEASE.md](https://gitee.com/running-cat/tacomall-springcloud/blob/master/RELEASE.md)记录跟踪。

## 版权声明

项目在[Apache License 2.0](https://gitee.com/running-cat/tacomall-springcloud/blob/master/LICENSE)下自由使用。