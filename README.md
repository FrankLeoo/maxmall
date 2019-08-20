# MaxMall分销系统
## 前言
MaxMall电商分销系统致力于打造一套适合微信分销的电商平台，该系统模拟三级分销系统，采用三级分销多级分润模式。该系统涉及 分销商、供应商、平台管理员等多种分销角色，技术采用spring-cloud 2.0微服务化框架、eureka注册中心、ribbon负载均衡、hystrix限流降级、spring security权限控制、redis缓存 elasticsearch索引等主流微服务化治理方案。

## 项目介绍
MaxMall电商分销系统分为maxmall java后端管理系统、maxmall-portal vue前端管理系统、maxmall-webapp 微信小程序系统。后台管理系统包含商品管理、品牌管理、商品分类管理、订单管理、分销管理、分销配置、营销管理、权限管理、统计报表等模块。

## 项目演示
演示地址：[http://106.52.183.251](http://106.52.183.251/)
GIT地址 ：https://github.com/FrankLeoo

![1.gif](https://github.com/FrankLeoo/maxmall/blob/master/document/系统微服务/1.gif)

![2.gif](https://github.com/FrankLeoo/maxmall/blob/master/document/系统微服务/2.gif)

![3.gif](https://github.com/FrankLeoo/maxmall/blob/master/document/系统微服务/3.gif)

![4.gif](https://github.com/FrankLeoo/maxmall/blob/master/document/系统微服务/4.gif)

## 项目结构
maxmall<br>
|----maxmall-common（基础common项目)<br>
|--------maxmall-common-base（通用静态类、异常、枚举等）<br>
|--------maxmall-common-config（项目通用配置config）<br>
|--------maxmall-common-core（项目核心基础包：redis、mybatis、拦截器和注解等）<br>
|--------maxmall-common-security（security核心包：Authorize配置文件、jwt配置、验证码拦截）<br>
|--------maxmall-common-util（项目工具类：分页、时间工具、tree工具等多种工具）<br>
|--------maxmall-common-zk（分布式ID生成器zk实现）<br>
|----maxmall-dalgen（mybatis dalge生成器）<br>
|----maxmall-discovery（spring config配置中心）<br>
|----maxmall-eureka（eureka分布式注册发现中心）<br>
|----maxmall-provider（后端项目）<br>
|--------maxmall-provider-marketing（会员营销中心）<br>
|--------maxmall-provider-merchant（分销商商户中心）<br>
|--------maxmall-provider-order（订单支付中心）<br>
|--------maxmall-provider-product（商品中心）<br>
|----maxmall-provider-api（项目接口）<br>
|--------maxmall-provider-product-api（feign接口配置）<br>

## 项目文档
营销中心：[https://shimo.im/docs/2473QyXLPlEF13wP/]
商品中心：[https://shimo.im/docs/65bqnrvlMvDIvqyn/]
订单中心：[https://shimo.im/docs/dL9kBMz8NDdTYqK5/]
商户中心：[https://shimo.im/docs/0m8AZVlDe7bU5Aba/]

## 技术选型
### 前端技术
| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Vue | 前端框架 | [https://vuejs.org/](https://vuejs.org/) |
| Vue-router | 路由框架 | [https://router.vuejs.org/](https://router.vuejs.org/) |
| Vuex | 全局状态管理框架 | [https://vuex.vuejs.org/](https://vuex.vuejs.org/) |
| Element | 前端UI框架 | [https://element.eleme.io/](https://element.eleme.io/) |
| Axios | 前端HTTP框架 | [https://github.com/axios/axios](https://github.com/axios/axios) |
| v-charts | 基于Echarts的图表框架 | [https://v-charts.js.org/](https://v-charts.js.org/) |
| Js-cookie | cookie管理工具 | [https://github.com/js-cookie/js-cookie](https://github.com/js-cookie/js-cookie) |
| nprogress | 进度条控件 | [https://github.com/rstacruz/nprogress](https://github.com/rstacruz/nprogress) |

### 后端技术
| 技术 | 说明 | 官网 |
| --- | --- | --- |
| Spring Boot | 容器+MVC框架 | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security | 认证和授权框架 | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| JWT | JWT登录支持 | [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt) |
| MyBatis | ORM框架 | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatisGenerator | 数据层代码生成 | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html) |
| PageHelper | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| Swagger-UI | 文档生产工具 | [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
| Hibernator-Validator | 验证框架 | [http://hibernate.org/validator/](http://hibernate.org/validator/) |
| Elasticsearch | 搜索引擎 | [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch) |
| RabbitMq | 消息队列 | [https://www.rabbitmq.com/](https://www.rabbitmq.com/) |
| Redis | 分布式缓存 | [https://redis.io/](https://redis.io/) |
| Docker | 应用容器引擎 | [https://www.docker.com/](https://www.docker.com/) |
| Druid | 数据库连接池 | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS | 对象存储 | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |
| Lombok | 简化对象封装工具 | [https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |

## 架构设计
![image.png](https://upload-images.jianshu.io/upload_images/10436362-ecffeb867b0859da.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 搭建步骤
1. 在本地系统安装redis、zookeeper、elasticsearch等程序组件
2. 搭建mysql数据库，导入数据库备份sql文件 并 修改maxmall后端项目application.yml数据库配置
3. 依次启动maxmall-eureka、maxmall-discovery、maxmall-merchant、maxmall-marketing、maxmall-order、maxmall-product项目
4. 本地搭建node环境并安装使用npm
5. 待所有后端项目正常启动后，打开maxmall-portal 前端vue项目，并在根目录下运行npm run dev命令启动前端项目





