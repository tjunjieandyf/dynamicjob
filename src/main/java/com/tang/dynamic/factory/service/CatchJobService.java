package com.tang.dynamic.factory.service;

import com.tang.dynamic.xmlloader.entity.JobEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiejT
 * @ClassName: CatchJobService
 * @description: 获取容器中的job服务类
 * @date 2022/2/23
 */
@Service
//@DependsOn("loadjobconetxt")
public class CatchJobService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     *获取容器中的所有JobEntity
     * @author jiejT
     * @date 2022/2/23
     * @param
     * @return java.util.List<com.tang.dynamic.xmlloader.entity.JobEntity>
     */
    public List<JobEntity> findAll(){
        List<JobEntity> jobList = null;
        if(applicationContext!=null){
            String[] jobNames = applicationContext.getBeanNamesForType(JobEntity.class);
            if(jobNames!=null&&jobNames.length>0){
                jobList  = new ArrayList<>();
                for(String beanName:jobNames){
                    JobEntity job = applicationContext.getBean(beanName,JobEntity.class);
                    jobList.add(job);
                }
            }
        }
        return jobList;
    }

    /**
     *获取特定JobEntity
     * @author jiejT
     * @date 2022/2/23
     * @param	id
     * @return com.tang.dynamic.xmlloader.entity.JobEntity
     */
    public JobEntity getJobEntity(String id){
        JobEntity job = null;
        if(applicationContext!=null){
            job = applicationContext.getBean(id,JobEntity.class);
        }

        return job;
    }
}
