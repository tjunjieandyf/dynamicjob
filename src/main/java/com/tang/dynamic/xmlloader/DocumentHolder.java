package com.tang.dynamic.xmlloader;

import org.dom4j.Document;

/**
 * @author jiejT
 * @ClassName: DocumentHolder
 * @description: TODO
 * @date 2022/2/21
 */
public interface DocumentHolder {
    Document getDocument(String filePath);
}
