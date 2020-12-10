package top.elichn.collection.consistenthash.better;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>Title: TreeMapConsistentHash</p>
 * <p>Description: TreeMap实现  确实要优于排序Map</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/31
 */
public class TreeMapConsistentHash extends AbstractConsistentHash {

    private TreeMap<Long, String> treeMap = new TreeMap<>();

    /**
     * 虚拟节点数量
     */
    private static final int VIRTUAL_NODE_SIZE = 2;

    @Override
    public void add(long key, String value) {
        for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            Long hash = super.hash("vir" + key + i);
            treeMap.put(hash, value);
        }
        treeMap.put(key, value);
    }

    @Override
    public String getFirstNodeValue(String value) {
        long hash = super.hash(value);
        System.out.println("value=" + value + " hash = " + hash);
        SortedMap<Long, String> last = treeMap.tailMap(hash);
        if (!last.isEmpty()) {
            return last.get(last.firstKey());
        }
        return treeMap.firstEntry().getValue();
    }

    public static void main(String[] args) {
        AbstractConsistentHash map = new TreeMapConsistentHash();

        List<String> stringsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringsList.add("127.0.0." + i);
        }
        String process = map.process(stringsList, "zhangsan");
        System.out.println(process);
        System.out.println();


        TreeMap<Long, String> treeMap = new TreeMap<>();
        treeMap.put(8L, "127.0.0.8");
        treeMap.put(10L, "127.0.0.10");
        treeMap.put(100L, "127.0.0.100");
        treeMap.put(1000L, "127.0.0.1000");

        SortedMap<Long, String> last = treeMap.tailMap(2L);
        if (!last.isEmpty()) {
            System.out.println(last.get(last.firstKey()));
        } else {
            System.out.println(treeMap.firstEntry().getValue());
        }
    }
}