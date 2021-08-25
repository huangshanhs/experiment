/**
 * 远程交互式实验管理系统
 */

package io.renren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ServletComponentScan
@ComponentScan(basePackages = {"com.hsp", "io.renren"})
@MapperScan(basePackages = {"com.hsp.experiment.each.dao", "io.renren.modules.app.dao", "io.renren.modules.job.dao", "io.renren.modules.oss.dao", "io.renren.modules.sys.dao"})
public class RenrenApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenrenApplication.class, args);
    }

}