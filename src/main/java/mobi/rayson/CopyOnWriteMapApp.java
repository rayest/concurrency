package mobi.rayson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 5:42 PM
 *  Description: 写时复制。适用于读多于写的情况
 *  参考 CopyOnWriteArrayList 实现 CopyOnWriteMap
 *  简单实现黑名单添加与黑名单判断
 **/
public class CopyOnWriteMapApp {

  private CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<>();

  public boolean isBlackList(String id) {
    return null != blackListMap.get(id);
  }

  public void addBlackList(String id) {
    blackListMap.put(id, Boolean.TRUE);
  }

  public void addBlackList(Map<String, Boolean> ids) {
    blackListMap.putAll(ids);
  }
}

class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
  private volatile Map<K, V> internalMap;

  public CopyOnWriteMap() {
    internalMap = new HashMap<>();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean containsKey(Object key) {
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  @Override
  public V get(Object key) {
    return internalMap.get(key);
  }

  @Override
  public V put(K key, V value) {
    synchronized (this) {
      Map<K, V> newMap = new HashMap<>(internalMap);
      V val = newMap.put(key, value);
      internalMap = newMap;
      return val;
    }
  }

  @Override
  public V remove(Object key) {
    Map<K, V> newMap = new HashMap<>(internalMap);
    V val = newMap.get(key);
    newMap.remove(key);
    internalMap = newMap;
    return val;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    synchronized (this) {
      Map<K, V> newMap = new HashMap<>(internalMap);
      newMap.putAll(m);
      internalMap = newMap;
    }
  }

  @Override
  public void clear() {

  }

  @Override
  public Set<K> keySet() {
    return null;
  }

  @Override
  public Collection<V> values() {
    return null;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return null;
  }
}