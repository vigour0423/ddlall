package com.ddl.learn.java8.stream;


import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest6 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);

        Stream<Integer> stream2 = Stream.iterate(1, item -> item + 2).limit(6);

       // System.out.println(stream2.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).sum());

        //stream2.filter(item -> item > 200).mapToInt(item -> item * 2).skip(2).limit(2).max().ifPresent(System
        // .out::println);


  /*      IntSummaryStatistics summaryStatistics = stream2.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();

        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());*/

        /*System.out.println(stream2);
        System.out.println(stream2.filter(item -> item > 2));
        System.out.println(stream2.distinct());*/


        System.out.println(stream);
        Stream<Integer> stream3 = stream2.filter(item -> item > 2);
        System.out.println(stream3);
        Stream<Integer> stream4 = stream3.distinct();
        System.out.println(stream4);


    }
}
