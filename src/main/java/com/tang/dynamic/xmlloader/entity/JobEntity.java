package com.tang.dynamic.xmlloader.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiejT
 * @ClassName: JobEntity
 * @description: TODO
 * @date 2022/2/21
 */
public class JobEntity {
    private String id;
    /**
     * job的class
     */
    private String clazz;
    /**
     * job的groupId
     */
    private String groupId;
    /**
     * job的name
     */
    private String name;
    /**
     * job的tirgger列表
     */
    private List<XMLTrigger> triggers = new ArrayList<>();

    public JobEntity() {

    }

    public JobEntity(String id, String clazz, String groupId, String name) {
        this.id = id;
        this.clazz = clazz;
        this.groupId = groupId;
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
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

    public List<XMLTrigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<XMLTrigger> triggers) {
        this.triggers = triggers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
