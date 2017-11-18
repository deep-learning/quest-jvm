package org.deeplearning.quest;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.control.Option;

import java.util.stream.Collectors;


public class VavrTest0 {

  public static void test0() {
//        List<Integer> l0 = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    Queue<Integer> queue = Queue.of(1, 2, 3)
        .enqueue(12)
        .enqueue(13);

    Option<Tuple2<Integer, Queue<Integer>>> dequeued = queue.dequeueOption();
    Option<Integer> element = dequeued.map(Tuple2::_1);
    Option<Queue<Integer>> remaining = dequeued.map(Tuple2::_2);
    System.out.println(Queue.empty().dequeueOption());
    java.util.stream.Stream.of(1, 2, 3)
        .map(Object::toString)
        .collect(Collectors.toList());

    // l0.add(12);
  }

  String join(String... words) {
    return List.of(words)
        .intersperse(", ")
        .fold("", String::concat);
  }

  public static void test1() {
    Tuple2<Integer, String> entry = Tuple.of(1, "a");
    Integer key = entry._1;
    String value = entry._2;
    entry.map(Object::toString, s -> s);
    entry.map(
        (i, s) -> Tuple.of(i / 3, s + "aaa")
    );
    entry.apply((i, s) -> s + i);
    List.of(1, 2, 3).groupBy(i -> i % 2);
    List.of("a", "b", "c").zipWithIndex();
  }

  public static void test2() {
   /*
   You can lift a partial function into a total function that returns an Option result. The term partial function comes from mathematics. A partial function from X to Y is a function f: X′ → Y, for some subset X′ of X. It generalizes the concept of a function f: X → Y by not forcing f to map every element of X to an element of Y. That means a partial function works properly only for some input values. If the function is called with a disallowed input value, it will typically throw an exception.
    */
  }

  public static void main(String[] args) {
    VavrTest0 v = new VavrTest0();
    String join = v.join("aaa", "bbb", "ccc");
    System.out.println("join = " + join);
  }
}
