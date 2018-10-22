package com.ddl.learn.guava.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class TypeTokenTest {
    public static void main(String[] args) {
        //泛型类型擦除
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
    }

    @Test
    public void typeTokenTest() {
        //注意上面第一行代码使用了一个空的匿名类。
        // 第二行使用了resolveType方法解析出泛型类型，第三行代码打印出泛型类型.
        TypeToken<ArrayList<String>> typeToken = new TypeToken<>() {};
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType());
        System.out.println(genericTypeToken.getRawType());
    }
}
