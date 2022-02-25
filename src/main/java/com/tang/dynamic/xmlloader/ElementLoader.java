package com.tang.dynamic.xmlloader;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;

/**
 * @author jiejT
 * @ClassName: ElementLoader
 * @description: TODO
 * @date 2022/2/21
 */
public interface ElementLoader {
    /**
     *添加元素
     * @author jiejT
     * @date 2022/2/21
     * @param	doc
     * @return void
     */
    void addElements(Document doc);
    /**
     *获取元素
     * @author jiejT
     * @date 2022/2/21
     * @param	id
     * @return org.dom4j.Element
     */
    Element getElement(String id);
    /**
     *获取所有元素
     * @author jiejT
     * @date 2022/2/21
     * @param
     * @return java.util.Collection<org.dom4j.Element>
     */
    Collection<Element> getElements();
}
