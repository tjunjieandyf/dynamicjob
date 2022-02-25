package com.tang.dynamic.xmlloader;

import com.tang.dynamic.xmlloader.entity.JobEntity;
import org.dom4j.Element;

import java.util.List;

/**
 * @author jiejT
 * @ClassName: ElementReader
 * @description: TODO
 * @date 2022/2/21
 */
public interface ElementReader {
    /**
     *记录所有的ELements并根据类型分类
     * @author jiejT
     * @date 2022/2/21
     * @param	element
     * @return void
     */
    JobEntity readElements(Element element);
    /**
     *获取所有的job节点
     * @author jiejT
     * @date 2022/2/21
     * @param	element	 
     * @return java.util.List<org.dom4j.Element>
     */
    List<Element> getJobElements(Element element);
    /**
     *获取所有的触发器element
     * @author jiejT
     * @date 2022/2/21
     * @param	element
     * @return java.util.List<org.dom4j.Element>
     */
    List<Element> getTriggerElements(Element element);
    /**
     *根据元素的属性name获取属性值
     * @author jiejT
     * @date 2022/2/21
     * @param	element
     * @param	name
     * @return java.lang.String
     */
    String getAttribute(Element element, String name);
}
