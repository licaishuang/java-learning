package corejava.volume2.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("files", "CountLongWords.txt");
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(content.split("\\PL+"));
        show("words", words);

        Stream<String> songs = Stream.of("gently", "the", "down", "stream");
        show("song", songs);

        Stream<String> empty = Stream.empty();
        show("empty", empty);

        // infinite steam
        Stream<String> echo = Stream.generate(() -> "echo");
        show("echo", echo);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(content);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path)) {
            show("lines", lines);
        }
    }

    public static <T> void show(String title, Stream<T> stream) {
        final int size = 10;
        List<T> showElements = stream.limit(size + 1).collect(Collectors.toList());
        System.out.println(title + " :");
        showElements.forEach(e -> System.out.print(e + ","));
        System.out.println();
    }
}
