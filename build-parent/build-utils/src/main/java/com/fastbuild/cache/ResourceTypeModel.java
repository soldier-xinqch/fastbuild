package com.fastbuild.cache;

import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

/**
 * 缓存资源池构件模型
 *
 * @auther xinch
 * @create 2018/1/26 18:19
 */
public class ResourceTypeModel {

    private Long size;
    private MemoryUnit memoryUnit;

    private EntryUnit entryUnit =  EntryUnit.ENTRIES;

    public ResourceTypeModel(Long size, MemoryUnit memoryUnit) {
        this.size = size;
        this.memoryUnit = memoryUnit;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public MemoryUnit getMemoryUnit() {
        return memoryUnit;
    }

    public void setMemoryUnit(MemoryUnit memoryUnit) {
        this.memoryUnit = memoryUnit;
    }

    public EntryUnit getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(EntryUnit entryUnit) {
        this.entryUnit = entryUnit;
    }
}
