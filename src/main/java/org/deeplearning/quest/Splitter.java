package org.deeplearning.quest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Splitter<T> {
    private List<T> passed;
    private List<T> notPassed;

    private Splitter(List<T> passed, List<T> notPassed) {
        this.passed = passed;
        this.notPassed = notPassed;
    }

    public Splitter<T> workWithPassed(Consumer<Stream<T>> func) {
        func.accept(passed.stream());
        return this;
    }

    public Splitter<T> workWithNotPassed(Consumer<Stream<T>> func) {
        func.accept(notPassed.stream());
        return this;
    }

    public static <T> Splitter<T> splitBy(Collection<T> coll, Predicate<T> pred) {
        List<T> passed = new LinkedList<>();
        List<T> notPassed = new LinkedList<>();
        coll.stream()
            .forEach(x -> {
                if (pred.test(x)) {
                    passed.add(x);
                } else {
                    notPassed.add(x);
                }
            });
        return new Splitter<>(passed, notPassed);
    }
}
