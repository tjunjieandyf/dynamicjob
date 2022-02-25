package com.tang.dynamic.listener;

import com.tang.dynamic.factory.DynamicJobFactory;
import com.tang.dynamic.factory.DynamicSchedulerFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务运行工厂类
 */
@Configuration
public class StartSchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    public DynamicSchedulerFactory dynamicSchedulerFactory;
    @Autowired
    private DynamicJobFactory myJobFactory;
    // springboot 启动监听
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            dynamicSchedulerFactory.scheduleJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //注入SchedulerFactoryBean
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }

}