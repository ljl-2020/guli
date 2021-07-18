/**
 * FileName: CodeGenerator
 * Author: ljl
 * Date: 2021/3/19 22:56
 * Description: 代码生成器
 * History:
 */


package com.ljl.guli.service.edu;

import com.alibaba.nacos.client.utils.StringUtils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CodeGenerator {


        @Test
        public void generatorCode(){
            // 1、创建代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 2、全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath+ "/src/main/java");
            gc.setAuthor("luojl");
            gc.setOpen(false); //生成后是否打开资源管理器
            gc.setFileOverride(false); //重新生成时文件是否覆盖
            gc.setServiceName("%sService");	//去掉Service接口的首字母I
            gc.setIdType(IdType.ID_WORKER_STR); //主键策略
            gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
            gc.setSwagger2(true);//开启Swagger2模式

            mpg.setGlobalConfig(gc);

            // 3、数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/edu_service?serverTimezone=GMT%2B8");
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("123456");
            dsc.setDbType(DbType.MYSQL);
            mpg.setDataSource(dsc);

            // 4、包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.ljl.guli.service"); //包名
            pc.setModuleName("edu"); //模块名
            pc.setController("controller");
            pc.setEntity("entity");
            pc.setService("service");
            pc.setMapper("mapper");
            mpg.setPackageInfo(pc);

            // 5、策略配置
            StrategyConfig strategy = new StrategyConfig();
//            strategy.setInclude("edu_course","edu_course_description","edu_chapter","edu_video");
            strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
            strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

            strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
            strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

            //自动填充
            TableFill tableFill = new TableFill("gmt_create", FieldFill.INSERT);
            TableFill tableFill2 = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
            ArrayList<TableFill> list = new ArrayList<>();
            list.add(tableFill);
            list.add(tableFill2);
            strategy.setTableFillList(list);

            strategy.setLogicDeleteFieldName("is_deleted");
            strategy.setEntityBooleanColumnRemoveIsPrefix(true);
            strategy.setRestControllerStyle(true); //restful api风格控制器
            strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

            strategy.setSuperEntityClass("com.ljl.guli.service.base.model.BaseEntity");
            strategy.setSuperEntityColumns("id","gmt_create","gmt_modified");

            mpg.setStrategy(strategy);


            // 6、执行
            mpg.execute();

        }


}
