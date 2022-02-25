package com.tang.dynamic.xmlloader;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiejT
 * @ClassName: ElementLoaderImpl
 * @description: 获取根节点下的所有节点
 * @date 2022/2/21
 */
public class ElementLoaderImpl implements ElementLoader{
    /**
     *元素map
     */
    private Map<String, Element> elements=new HashMap<String, Element>();

    @Override
    public void addElements(Document doc) {
        @SuppressWarnings("unchecked")
        List<Element> eles = doc.getRootElement().elements();

        for (Element e : eles) {
            String id=e.attributeValue("id");
            elements.put(id, e);
        }
    }

    @Override
    public Element getElement(String id) {
        return elements.get(id);
    }

    @Override
    public Collection<Element> getElements() {
        return this.elements.values();
    }
}
