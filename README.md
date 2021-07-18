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
### 三、业务模块
 &nbsp;&nbsp; ![图片](https://user-images.githubusercontent.com/64575645/126060216-117961cb-a51d-4ffc-8669-b077dae260b0.png)
### 四、技术架构
 &nbsp;&nbsp; ![图片](https://user-images.githubusercontent.com/64575645/126060263-ee2eb7c0-12ab-444e-af38-a4a6f209265e.png)

### 五、项目模块功能简介
 - guli-parent，由十个微服务组成
   - api-gateway:统一路由处理，通过整合spring cloud gateway组件来实现统一的接口调用
   - service-edu:核心的业务模块，需要通过远程调用其他模块来实现自身的业务。
   - service-oss:阿里云OSS,通过与阿里云OSS整合实现一个文件系统存储系统中文件上传。
   - service-vod:阿里云Vod，视频点播系统，通过本地系统调用阿里云vod的SDK来实现视频的上传与在线播放
   - service-cms:后台运营平台通过此模块的功能，可以实现控制前台用户系统的首页内容。
   - service-ucenter:前台用户系统的用户中心模块。功能包括登录，注册等，登录已实现通过微信扫码自动登录，（qq登录还未测试）。
   - service-trade:订单微服务，功能包括订单的增删查，整合了微信端的支付功能（但是还未测试，使用模拟接口进行调试）。
   - service-statistics:统计模块，前端整合Echarts来实现简单的一个折线图统计。
   - service-acl:权限管理模块，整合Spring secruity实现用户管理，角色管理，以及菜单管理。用户根据角色来获取对应的菜单。
 - guli-admin:
   - 前端运营管理模块，基础框架由 [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)搭建
 - guli-site；
   - 前台用户系统，使用 [nuxt.js](https://www.nuxtjs.cn/)快速搭建
