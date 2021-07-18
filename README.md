# guli
谷粒学院

### 一、简介

- 谷粒学院，是一个B2C模式的职业技能在线教育系统，分为前台用户系统和后台运营平台。

 - 前台用户系统包括课程、讲师、问答、文章几大大部分，使用了微服务技术架构，前后端分离开发。 

 - 项目前后端分离开发，后端采用SpringCloud微服务架构，持久层用的是MyBatis-Plus，微服务分库设计，使用Swagger生成接口文档 接入了阿里云视频点播、阿里云OSS。 系统分为前台用户系统和后台管理系统两部分。 前台用户系统包括：首页、课程、名师、问答、文章。 后台管理系统包括：讲师管理、课程分类管理、课程管理、统计分析、Banner管理、订单管理、权限管理等功能。
### 二、技术栈
 - 后端的主要技术架构是：SpringBoot + SpringCloud + MyBatis-Plus + HttpClient + MySQL + Maven+EasyExcel，
 其他涉及到的中间件包括Redis、阿里云OSS、阿里云视频点播 业务中使用了ECharts做图表展示，使用EasyExcel完成分类批量添加、注册分布式单点登录使用了JWT,使用nacos作为服务注册中心以及配置中心，远程调用框架
 由openFeign来实现，服务容错Hystrix,
 - 前端的架构是：Node.js + Vue.js +element-ui+NUXT+ECharts（统计图的处理） 

