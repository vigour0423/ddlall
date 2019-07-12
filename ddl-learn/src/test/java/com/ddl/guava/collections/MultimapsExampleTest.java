package com.ddl.guava.collections;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class MultimapsExampleTest {

    @Test
    public void testBasic() {
        Multimap<String, String> multipleMap = LinkedListMultimap.create();
        HashMap<String, String> hashMap = Maps.newHashMap();

        hashMap.put("1", "1");
        hashMap.put("1", "2");
        assertThat(hashMap.size(), equalTo(1));


        multipleMap.put("1", "1");
        multipleMap.put("1", "2");
        assertThat(multipleMap.size(), equalTo(2));
        Collection<String> strings = multipleMap.get("1");
        System.out.println(strings);
    }
}