package org.deeplearning.quest;

import java.util.Map;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PStream {
    public void parallel() {
        double avg = Stream.of(1, 2, 3, 4)
                           .parallel()
                           .filter(x -> x > 2)
                           .mapToInt(x -> x * 2)
                           .average()
                           .getAsDouble();
        System.out.println("avg = " + avg);
    }

    public void concurrentReduction() {
        Map<Integer, Integer> map = Stream.of(1, 2, 3, 4, 5)
                                          .parallel()
                                          .distinct()
                                          .collect(Collectors.groupingByConcurrent(x -> x % 2 == 0));
    }
}
