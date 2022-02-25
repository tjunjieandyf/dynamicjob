package com.tang.dynamic.xmlloader;

import com.tang.dynamic.xmlloader.entity.JobEntity;
import com.tang.dynamic.xmlloader.entity.XMLCronTrigger;
import com.tang.dynamic.xmlloader.entity.XMLTrigger;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiejT
 * @ClassName: XMLElementReaderImpl
 * @description: TODO
 * @date 2022/2/21
 */
public class XMLElementReaderImpl implements ElementReader {
    private List<JobEntity> jobElements = new ArrayList<>();

    private final String  JOB_ELEMENT_NAME = "job";

    private final String  TRIGGERS_NAME = "triggers";

    private final String  CRON_TRIGGER_NAME = "crontrigger";

//    private final String  TRIGGERS_NAME = "triggers";
    @Override
    public JobEntity readElements(Element element) {
        JobEntity job = null;
        if(element!=null){
            //job节点
            if(JOB_ELEMENT_NAME.equals(element.getName())){
                String groupId = element.attributeValue("groupId");
                String jobName = element.attributeValue("name");
                String clazz = element.attributeValue("class");
                String id = element.attributeValue("id");
                job = new JobEntity(id,clazz,groupId,jobName);

                Element triggers = element.element(TRIGGERS_NAME);
                List<Element> cronList = null;
                if(triggers!=null){
                    cronList = triggers.elements(CRON_TRIGGER_NAME);
                    //TODO 后续可能有其他trigger,加载这里
                }
                List<XMLTrigger> triggerList = new ArrayList<>();

                if(!CollectionUtils.isEmpty(cronList)){
                    for(Element ele:cronList) {
                        String triggerName = ele.attributeValue("name");
                        String exp = ele.getText().trim();
                        XMLCronTrigger cronTrigger = new XMLCronTrigger(groupId,triggerName,exp);
                        triggerList.add(cronTrigger);
                    }
                }

                job.setTriggers(triggerList);
            }
        }

        return job;
    }

    @Override
    public List<Element> getJobElements(Element element) {
        return null;
    }

    @Override
    public List<Element> getTriggerElements(Element element) {
        return null;
    }

    @Override
    public String getAttribute(Element element, String name) {
        return null;
    }

    private void loadJob(Element element){
        if(element!=null){
            //job节点
            if(JOB_ELEMENT_NAME.equals(element.getName())){
                String id = element.attributeValue("id");
                String groupId = element.attributeValue("groupId");
                String jobName = element.attributeValue("name");
                String clazz = element.attributeValue("class");
                JobEntity job = new JobEntity(id,clazz,groupId,jobName);

                Element triggers = element.element(TRIGGERS_NAME);
                List<Element> cronList = null;
                if(triggers!=null){
                    cronList = triggers.elements(CRON_TRIGGER_NAME);
                    //TODO 后续可能有其他trigger,加载这里
                }
                List<XMLTrigger> triggerList = new ArrayList<>();

                 if(CollectionUtils.isEmpty(cronList)){
                     for(Element ele:cronList) {
                         String triggerName = ele.attributeValue("name");
                         String exp = ele.getText();
                         XMLCronTrigger cronTrigger = new XMLCronTrigger(groupId,triggerName,exp);
                         triggerList.add(cronTrigger);
                     }
                 }

                 job.setTriggers(triggerList);
            }
        }
    }
}
