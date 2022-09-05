import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class HashMapTesters {
    @Test
    public void testPut() {
        MyHashMap<Integer, String> curMap = new MyHashMap<Integer, String>();
        curMap.put(1,"numb1");
        curMap.put(34,"numb4");
        curMap.put(22,"numb66");
        curMap.printTable();
    }
}
