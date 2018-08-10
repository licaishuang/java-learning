package corejava.volume2.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static void main(String[] args) {
        Iterator<Integer> it = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();

        // toArray return Object[]
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println(numbers);
        Integer[] integerNumbers = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println(integerNumbers);


        String sentence = "We are going to write an example of collecting results of steam";
        List<String> words = Arrays.asList(sentence.split(" "));
        words.stream().forEach(System.out::println);

        Set<String> set = words.stream().collect(Collectors.toSet());
        TreeSet<String> treeSet = words.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);
        System.out.println(treeSet);

        String result = words.stream().collect(Collectors.joining());
        String result2 = words.stream().collect(Collectors.joining(","));
        System.out.println(result);
        System.out.println(result2);

        IntSummaryStatistics summary = words.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println(summary.getAverage());
        System.out.println(summary.getCount());
        System.out.println(summary.getMax());
    }
}
