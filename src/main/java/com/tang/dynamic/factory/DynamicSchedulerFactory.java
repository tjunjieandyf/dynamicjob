package com.tang.dynamic.factory;


import com.tang.dynamic.factory.service.CatchJobService;
import com.tang.dynamic.xmlloader.entity.JobEntity;
import com.tang.dynamic.xmlloader.entity.XMLCronTrigger;
import com.tang.dynamic.xmlloader.entity.XMLTrigger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 调度工厂类
 */
@Service("dynamicSchedulerFactory")
@Component
public class DynamicSchedulerFactory {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 任务配置读取服务
     */
    @Autowired
    private CatchJobService catchJobService;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = getScheduler();
        // 为了避免org.quartz.ObjectAlreadyExistsException，在执行前将scheduler进行清理
        scheduler.clear();
        startJob(scheduler);
    }

    /**
     * 获取scheduler
     *
     * @return
     * @author pangxianhe
     * @date 2018年12月27日
     */
    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 项目启动 开启任务
     *
     * @param scheduler
     * @author pangxianhe
     * @date 2018年12月27日
     */
    private void startJob(Scheduler scheduler) {
        List<JobEntity> jobList = catchJobService.findAll();
        if (!CollectionUtils.isEmpty(jobList)) {
            for (JobEntity job : jobList) {
                try {
                    // 1-暂停的任务 0-正常运行任务
//                    if (1l == config.getStatus()) {
//                        continue;
//                    }
                    String classz = job.getClazz();
                    if (StringUtils.hasText(classz)) {
                        @SuppressWarnings("unchecked")
                        Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(classz);
                        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getName(), job.getGroupId())
                                .build();
                        List<XMLTrigger> triggerList = job.getTriggers();
                        if (!CollectionUtils.isEmpty(triggerList)) {
                            for (XMLTrigger trigger : triggerList) {
                                if (trigger instanceof XMLCronTrigger) {
                                    XMLCronTrigger cronTriggerEntity = (XMLCronTrigger) trigger;
                                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronTriggerEntity.getExp());
                                    CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(cronTriggerEntity.getName(), cronTriggerEntity.getGroupId())
                                            .withSchedule(scheduleBuilder).build();
                                    scheduler.scheduleJob(jobDetail, cronTrigger);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 任务暂停
     *
     * @param id
     * @throws Exception
     * @author pangxianhe
     * @date 2018年12月27日
     */
    public void pauseJob(String id) throws Exception {
        Scheduler scheduler = getScheduler();
        if (StringUtils.hasText(id)) {
            JobEntity job = catchJobService.getJobEntity(id);
            JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroupId());
            scheduler.deleteJob(jobKey);
        } else {
//            logger.error("调用任务暂停方法pauseJob，入参为空!");
        }
    }

    /**
     * 任务恢复
     *
     * @param id
     * @throws Exception
     * @author pangxianhe
     * @date 2018年12月27日
     */
    public void resumeJob(String id) throws Exception {
        Scheduler scheduler = getScheduler();
        JobEntity job = catchJobService.getJobEntity(id);
        JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroupId());

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getClazz());
            JobDetail newJobDetail = JobBuilder.newJob(clazz).withIdentity(job.getName(), job.getGroupId())
                    .build();

            List<XMLTrigger> triggerList = job.getTriggers();
            if (!CollectionUtils.isEmpty(triggerList)) {
                for (XMLTrigger trigger : triggerList) {
                    if (trigger instanceof XMLCronTrigger) {
                        XMLCronTrigger cronTriggerEntity = (XMLCronTrigger) trigger;
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronTriggerEntity.getExp());
                        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(cronTriggerEntity.getName(), cronTriggerEntity.getGroupId())
                                .withSchedule(scheduleBuilder).build();
                        scheduler.scheduleJob(newJobDetail, cronTrigger);
                    }
                }
            }
        } else {
            scheduler.resumeJob(jobKey);
        }
    }
}