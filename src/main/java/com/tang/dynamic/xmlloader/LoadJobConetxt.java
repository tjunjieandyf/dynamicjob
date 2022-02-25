package com.tang.dynamic.xmlloader;

import com.tang.dynamic.xmlloader.entity.JobEntity;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author jiejT
 * @ClassName: LoadJobPostProcessor
 * @description: 加载xml中配置的job
 * @date 2022/2/21
 */
@PropertySource("classpath:application.yml")
public class LoadJobConetxt implements ApplicationContextAware {
    @Value("${jobConfigPath}")
    private String filePath;

    private ApplicationContext applicationContext;
    /**
     *文件读取holder
     */
    private DocumentHolder xmlDocumentHolder = new XMLDocumentHolder();
    private ElementLoader elementLoader =new ElementLoaderImpl();
    private ElementReader xmlElementReader = new XMLElementReaderImpl();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        loadJob();
    }

    private void loadJob() {
        if (!StringUtils.hasText(filePath)) {
            filePath = "classpath:jobconfig.xml";
        }
        //解析xml
        elementLoader.addElements(xmlDocumentHolder.getDocument(filePath));
        Collection<Element> elements = elementLoader.getElements();
        if (!CollectionUtils.isEmpty(elements)) {
            for (Element ele : elements) {
                //读取job节点，并解析为JobEntity实例
                JobEntity job = xmlElementReader.readElements(ele);
                //注册到容器中
                if (this.applicationContext != null) {
                    BeanDefinitionRegistry beanDefReg = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
                    BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.rootBeanDefinition(JobEntity.class);
                    //设置属性
                    beanDefBuilder.addPropertyValue("name", job.getName());
                    beanDefBuilder.addPropertyValue("groupId", job.getGroupId());
                    beanDefBuilder.addPropertyValue("clazz", job.getClazz());
                    beanDefBuilder.addPropertyValue("triggers", job.getTriggers());

                    BeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
                    String beanName = job.getId();
                    if (!beanDefReg.containsBeanDefinition(beanName)) {
                        beanDefReg.registerBeanDefinition(beanName, beanDef);
                    }
                }
            }
        }
    }
}
