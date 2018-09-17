package corejava.volume2.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadCVS {
    public static void main(String[] args) throws IOException {
        Path csvPath = Paths.get("F:\\test\\5r_new_2_20180630_9.csv");
        List<String> lines = Files.readAllLines(csvPath);
        int countOf3d = 0;
        for (String line : lines) {
            if (line.contains(".3d.")) {
                countOf3d++;
                if(!lines.contains(line.replace(".3d", ""))){
                    System.out.println(line);
                }
            }
        }
        System.out.println(countOf3d);
    }
}
