package demo.system.mybatis.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;

/**
 * 代码生成器（视图模式）
 *
 * @author 水张哲
 * @date 2021/10/18
 */
public class CodeViewGenerator {

    static final String URL = "jdbc:mysql://127.0.0.1:3306/cssc_demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    static final String USERNAME = "root";
    static final String PASSWORD = "123456";
    static final String SYSTEM_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder
                        // 设置作者
                        .author("水张哲")
                        // 开启 swagger 模式
                        .enableSwagger()
                        // 覆盖已生成文件
                        .fileOverride()
                        // 指定输出目录
                        .outputDir(SYSTEM_DIR + "/src/main/java")
                        // 注释日期格式
                        .commentDate("yyyy/MM/dd")
                        // 生成后不打开路径文件夹
                        .disableOpenDir()
                )
                // 包配置
                .packageConfig(builder -> builder
                        // 设置父包路径
                        .parent("demo.common")
                        .controller("controller")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("repository.mapper")
                        .entity("repository.po")
                        // 设置 mapperXml 生成路径
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, SYSTEM_DIR + "/src/main/resources/mapper/")))
                // 模板配置
                .templateConfig(builder -> builder
                        .service("/templates/service.java.vm")
                        .serviceImpl("/templates/serviceImpl.java.vm")
                        .mapper("/templates/mapper.java.vm")
                        .entity("/templates/entity.java.vm")
                        // 如果是视图请开启此注释
                        .disable(TemplateType.CONTROLLER)
                        .build())
                // 策略配置
                .strategyConfig(builder -> builder
                        // 设置需要生成的表名
                        .addInclude("v_role_re_resource")
                        // 设置过滤表前缀
                        .addTablePrefix("v_")
                        // service 配置
                        .serviceBuilder()
                        .formatServiceFileName("%sViewService")
                        .formatServiceImplFileName("%sViewServiceImpl")
                        // mapper 配置
                        .mapperBuilder()
                        .formatMapperFileName("%sViewMapper")
                        .formatXmlFileName("%sViewMapper")
                        // entity 配置
                        .entityBuilder()
                        .enableLombok()
                        .formatFileName("%sView"))
                // 执行
                .execute();
    }
}
