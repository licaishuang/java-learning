package corejava.volume2.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        System.out.println(Paths.get("files", "CountLongWords.txt").toAbsolutePath().toString());
        String content = new String(Files.readAllBytes(Paths.get("files", "CountLongWords.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split("\\PL+"));

        long count = 0;
        final int LONG_WORD_LEN = 10;
        for (String word : words) {
            if (word.length() > LONG_WORD_LEN)
                count++;
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > LONG_WORD_LEN).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > LONG_WORD_LEN).count();
        System.out.println(count);
    }

}
