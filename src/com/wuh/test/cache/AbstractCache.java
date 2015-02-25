package com.wuh.test.cache;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public abstract class  AbstractCache {
    
	private static Log log = LogFactory.getLog(AbstractCache.class);
    
    protected Cache ehcache;
    
    /**
     * 根据key取缓存
     * @param cacheKey
     * @return
     */
    public Object getEhCacheObject(String cacheKey) {
        Element el = this.ehcache.get(cacheKey);
        if (el == null) {
            log.debug(this.ehcache.getName() + "缓存中： " + cacheKey + "为空");
            el = new Element(cacheKey, "");
            return el.getObjectValue();
        }
        log.debug(cacheKey + "使用缓存： " + this.ehcache.getName());
        return el.getObjectValue();
    }
    
    /**
     * 缓存数据
     * @param cacheKey
     * @param obj
     * @return
     */
    public boolean setEhCacheObject(String cacheKey, Object obj) {
        Element element = new Element(cacheKey, obj);
        this.ehcache.put(element);
        log.debug(cacheKey + "加入到缓存： " + this.ehcache.getName());
        return true;
    }

    /**
     * 取所有key
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getEhCacheKeys(){
        return (List<String>)this.ehcache.getKeys();
    }
    
    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean isKeyInCache(Object key){
        return  this.ehcache.isKeyInCache(key);
    }
    
    /**
     * 删除集合和中的key对应的缓存
     * @param cacheKeys
     */
    public void remove(List<String> cacheKeys){
        this.ehcache.removeAll(cacheKeys);
    }
    
    /**
     * 取缓存数
     * @return
     */
    public int getSize(){
        return this.ehcache.getSize();
    }
    
    /**
     * 删除所有缓存
     */
    public void removeAll(){
    	this.ehcache.removeAll();
    }
    
    /**
     * 删除指定key对应的缓存
     * @param cacheKey
     */
    public void remove(String cacheKey){
        this.ehcache.remove(cacheKey);
    }
    
    /**
     * 打印相关信息
     * 
     */
    public void printCacheInfo(){
       
        System.out.println(" this.cache.getStatus()"+ this.ehcache.getStatus());
        System.out.println("this.cache.getSize()"+this.ehcache.getSize());
        System.out.println("this.cache.getDiskStoreSize():"+this.ehcache.getDiskStoreSize());
        System.out.println("this.cache.getMemoryStoreSize()"+this.ehcache.getMemoryStoreSize());
        
    }
}
