package com.tang.dynamic.xmlloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

/**
 *获取xml的document，并保存
 * @author jiejT
 * @date 2022/2/22
 * @return
 */
public class XMLDocumentHolder implements DocumentHolder{

    //建立一个HashMap用来存放字符串和文档
    private Map<String, Document> docs = new HashMap<String, Document>();

    @Override
    public Document getDocument(String filePath) {
        Document doc=this.docs.get(filePath);//用HashMap先根据路径获取文档

        if (doc==null) {
            this.docs.put(filePath, readDocument(filePath)); //如果为空，把路径和文档放进去
        }

        return  this.docs.get(filePath);
    }

    /**
     * 根据路径读Document
     * @param filePath
     * @return
     */
    private Document readDocument(String filePath) {

        Document doc =null;

        try {
            //借用dom4j的解析器
            SAXReader reader = new SAXReader(false);
            //校验文件的dtd
//            reader.setEntityResolver(new IocEntityResolver());
            //根据路径创建文件
            File xmlFile = ResourceUtils.getFile(filePath);

            if(xmlFile==null){
                throw new FileNotFoundException(filePath);
            }

            //用dom4j自带的reader读取去读返回一个Document
            doc = reader.read(xmlFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }


}