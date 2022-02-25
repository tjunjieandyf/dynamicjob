package com.tang.dynamic.xmlloader.entity;

/**
 * @author jiejT
 * @ClassName: XMLCronTrigger
 * @description: TODO
 * @date 2022/2/21
 */
public class XMLCronTrigger implements XMLTrigger {
    /**
     *trigger的groupID
     */
    private  String groupId;
    /**
     *trigger的name
     */
    private String name;
    /**
     *trigger的表达式
     */
    private String exp;

    public XMLCronTrigger(){

    }

    public XMLCronTrigger(String groupId,String name,String exp){
        this.groupId = groupId;
        this.name = name;
        this.exp = exp;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
