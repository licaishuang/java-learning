package corejava.volume2.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamReduction {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "You", "Hi", "Him");
        Optional<String> startsWithH = words.parallelStream().filter(w -> w.startsWith("H")).findAny();
        List<String> listStartsWithH = words.parallelStream().filter(w -> w.startsWith("H")).collect(Collectors.toList());
        System.out.println(startsWithH);
        System.out.println(listStartsWithH);

        System.out.println(words.stream().parallel().anyMatch(w -> w.startsWith("Y")));
    }

}
