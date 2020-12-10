package top.elichn.learn;


import top.elichn.collection.consistenthash.better.AbstractConsistentHash;
import top.elichn.collection.consistenthash.better.TreeMapConsistentHash;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: TreeMapConsistentHashTest</p>
 * <p>Description: TreeMapConsistentHashTest</p>
 *
 * @author chenjian
 * @version 1.0
 * @date 2019/12/31
 */
public class TreeMapConsistentHashTest {

    @Test
    public void getFirstNodeValue() {
        AbstractConsistentHash map = new TreeMapConsistentHash();

        List<String> stringsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringsList.add("127.0.0." + i);
        }
        String process = map.process(stringsList, "zhangsan");
        System.out.println(process);
        Assert.assertEquals("127.0.0.2", process);
    }


    @Test
    public void getFirstNodeValue2() {
        AbstractConsistentHash map = new TreeMapConsistentHash();

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("127.0.0." + i);
        }
        String process = map.process(strings, "zhangsan2");
        System.out.println(process);

        Assert.assertEquals("127.0.0.3", process);
    }


    @Test
    public void getFirstNodeValue3() {
        AbstractConsistentHash map = new TreeMapConsistentHash();

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("127.0.0." + i);
        }
        String process = map.process(strings, "1551253899106");

        System.out.println(process);
        Assert.assertEquals("127.0.0.6", process);
    }
}
