package org.deeplearning.quest;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class BlogPost {
    public BlogPost() {
    }

    String title;
    String author;
    BlogPostType type;
    int likes;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BlogPostType getType() {
        return type;
    }

    public int getLikes() {
        return likes;
    }
}

enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
}

class Tuple {
    public Tuple(BlogPostType type, String author) {
        this.type = type;
        this.author = author;
    }

    public BlogPostType getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    BlogPostType type;
    String author;
}

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
        Map<Integer, List<Integer>> collect = Stream.of(1, 2, 3, 4, 5)
                                                    .parallel()
                                                    .distinct()
                                                    .collect(groupingBy(x -> x, Collectors.toList()));

    }

    private List<BlogPost> blogPosts;

    public void groupBy1() {
        Map<BlogPostType, List<BlogPost>> g1 = blogPosts.stream()
                                                        .collect(groupingBy(BlogPost::getType));

        Map<Tuple, List<BlogPost>> g2 = blogPosts.stream()
                                                 .collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));

        // To group the List of BlogPosts first by author and then by type:
        Map<String, Map<BlogPostType, List<BlogPost>>> map = blogPosts.stream()
                                                                      .collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));

        // By using the downstream collector we can apply aggregation functions in the results of the classification function.
        Map<BlogPostType, Double> avgLikesPerType = blogPosts.stream()
                                                             .collect(groupingBy(BlogPost::getType, Collectors.averagingInt(BlogPost::getLikes)));

        Map<BlogPostType, Integer> likesPerType = blogPosts.stream()
                                                           .collect(groupingBy(BlogPost::getType, Collectors.summingInt(BlogPost::getLikes)));

        Map<BlogPostType, Optional<BlogPost>> maxLikesPerType = blogPosts.stream()
                                                                         .collect(groupingBy(BlogPost::getType,
                                                                                             Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));

        Map<BlogPostType, IntSummaryStatistics> collect = blogPosts.stream()
                                                                   .collect(groupingBy(BlogPost::getType,
                                                                                       summarizingInt(BlogPost::getLikes)));

        Map<BlogPostType, String> collect1 = blogPosts.stream()
                                                      .collect(groupingBy(BlogPost::getType,
                                                                          mapping(BlogPost::getTitle, joining(", ", "Post titles[", "]"))));
        EnumMap<BlogPostType, List<BlogPost>> postsPerType = blogPosts.stream()
                                                                      .collect(groupingBy(BlogPost::getType,
                                                                                          () -> new EnumMap<>(BlogPostType.class),
                                                                                          toList()));

        ConcurrentMap<BlogPostType, List<BlogPost>> collect2 = blogPosts.parallelStream()
                                                                        .collect(groupingByConcurrent(BlogPost::getType));
    }
}
