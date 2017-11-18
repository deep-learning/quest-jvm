package org.deeplearning.quest;

import org.omg.PortableInterceptor.ACTIVE;

import java.util.Arrays;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        AwesomeClass awesomeClass = new AwesomeClass();
        System.out.println(awesomeClass.greet(Arrays.asList("zhenglai", "junlai", "minglai")));
        int optVal = awesomeClass.optional()
                                 .map(i -> i + 10)
                                 .filter(i -> i > 10)
                                 .flatMap(i -> Optional.of(1000 + i))
                                 .orElse(-10000);
        System.out.println(optVal);
        awesomeClass.stream();
    }
}
