package top.elichn.collection.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Title: HashMapTraversal</p>
 * <p>Description: HashMapTraversal</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/3/4
 */
public class HashMapTraversal {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("c", "cc");
        map.put("d", "dd");

        // Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        Iterator iterator = map.entrySet().iterator();

        // 1. java8 lambda
        map.entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        });

        System.out.println();

        // 2. entrySet遍历
        while (iterator.hasNext()) {
            // Map.Entry entry = iterator.next();
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println();

        // 3. keySet遍历 效率低下，不使用
        Iterator iterator4keyset = map.keySet().iterator();
        while (iterator4keyset.hasNext()) {
            Object key = iterator4keyset.next();
            System.out.println(key + " -> " + map.get(key));
        }
    }
}
