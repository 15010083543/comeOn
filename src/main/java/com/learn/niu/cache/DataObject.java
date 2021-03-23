package com.learn.niu.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class DataObject {

  private final String data;
 
  private static int objectCounter = 0;
  // standard constructors/getters
   
  public static DataObject get(String data) {
    objectCounter++;
    return new DataObject(data);
  }

  public static void main(String[] args) {
    Cache<String, DataObject> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS)
            // 最大数据长度一万条
            .maximumSize(100)
            .build();

    String key = "A";
    DataObject dataObject = cache.getIfPresent(key);

    //assertNull(dataObject);
    //我们可以使用 put 方法手动填充缓存：
    dataObject = get("B");
    cache.put(key, dataObject);
    dataObject = cache.getIfPresent(key);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    dataObject = cache.getIfPresent(key);
    dataObject = get("C");
    cache.put("C", dataObject);
    dataObject = cache.getIfPresent("C");

    //cache.invalidate(key);
    dataObject = cache.getIfPresent(key);
    System.out.println("---");

    //assertNotNull(dataObject);
  }
}