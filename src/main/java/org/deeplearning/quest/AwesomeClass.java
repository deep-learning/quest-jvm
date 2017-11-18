package org.deeplearning.quest;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AwesomeClass {

    public BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

    public static Integer compute(Function<Integer, Integer> function, Integer value) {
        return function.apply(value);
    }

    private static Integer invert(Integer value) {
        return -value;
    }

    public static Integer changeTheNumber(Function<Integer, Integer> func) {
        int num = 12;
        return compute(func, num);
    }

    public String greet(List<String> names) {
        return names.stream()
                    .map(name -> name + " ")
                    .reduce("Welcome ",
                            (acc, name) -> acc + name) + "!";
    }

    /*
     Use Optionals when “there is a clear need to represent ‘no result’
     or where null is likely to cause errors.”.
     */
    public Optional<Double> divide(Double first, Double second) {
        if (second == 0.0) {
            return Optional.empty();
        }

        return Optional.of(first / second);
    }

    public Optional<Integer> optional() {
        return Optional.of(12);
    }

    public static Integer invertTheNumber2() {
        Integer num = -1;
        Function<Integer, Integer> invertFunction = AwesomeClass::invert;
        return compute(a -> -a, num);
    }


    // stream

    public void stream() {
        Arrays.stream(new int[]{1, 2, 3})
              .forEachOrdered(System.out::println);

        List<Integer> list1 = Arrays.asList(1, 2, 3, null);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        Predicate<Integer> small = num -> num < 2;
        int sum = Stream.of(list1, list2)
                        .flatMap(List<Integer>::stream)
                        .filter(Objects::nonNull)
                        .filter(small.negate())
                        .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        toDoubleStream(new float[]{1.0f, 2.0f, 3.0f})
                .forEach(System.out::println);

        String joined = Stream.of("aa", "bb", "cc")
                              .collect(Collectors.joining(",", "[", "]"));
        System.out.println("joined = " + joined);

        System.out.println(
                Stream.of(1, 3, 3)
                      .sorted((a, b) -> a - b)
                      .mapToInt(x -> x)
                      .sorted()
                      .reduce(0, Integer::sum)
        );

        System.out.println(
                Stream.of(3, 2, 1)
                      .peek(System.out::println)
                      .mapToInt(x -> x)
                      .sum()
        );
    }

    public DoubleStream toDoubleStream(float[] array) {
        return IntStream.range(0, array.length)
                        .mapToDouble(i -> array[i]);
    }
}
