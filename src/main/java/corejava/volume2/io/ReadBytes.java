package corejava.volume2.io;

import com.google.common.io.ByteStreams;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReadBytes {
    public static void main(String[] args) {
        String filePath1 = "F:\\test\\Better Faster Lighter Java.2004.chm"; // 772K
        String filePath2 = "F:\\test\\Concurrent Programming in Java.pdf"; // 2.42M
        String filePath3 = "F:\\test\\Effective.Enterprise.Java.pdf"; //4.02M
        String filePath4 = "F:\\test\\Core-Java-Vol-II-Advanced-Features.pdf"; // 76.8M

        List<String> filePaths = Arrays.asList(filePath1, filePath2, filePath3, filePath4);
        ReadBytes.test(filePaths);
        /**
         * 测试结果：
         * 采用readBytesByInputSteam，文件为772K时，比其它方法快；文件为2M、4M、76M时，都比其它方法慢
         * 总体速度：readBytesByFiles > readBytesByCommonIO > readBytesByGuava > readBytesByInputSteam
         *
         * File path: F:\test\Better Faster Lighter Java.2004.chm
         * readBytesByInputSteam: bytes length 790587, timeConsumed: 4
         * readBytesByFiles: bytes length 790587, timeConsumed: 13
         * readBytesByGuava: bytes length 790587, timeConsumed: 16
         * readBytesByCommonIO: bytes length 790587, timeConsumed: 14
         *
         * File path: F:\test\Concurrent Programming in Java.pdf
         * readBytesByInputSteam: bytes length 2538934, timeConsumed: 12
         * readBytesByFiles: bytes length 2538934, timeConsumed: 5
         * readBytesByGuava: bytes length 2538934, timeConsumed: 8
         * readBytesByCommonIO: bytes length 2538934, timeConsumed: 9
         *
         * File path: F:\test\Effective.Enterprise.Java.pdf
         * readBytesByInputSteam: bytes length 4216897, timeConsumed: 23
         * readBytesByFiles: bytes length 4216897, timeConsumed: 6
         * readBytesByGuava: bytes length 4216897, timeConsumed: 18
         * readBytesByCommonIO: bytes length 4216897, timeConsumed: 7
         *
         * File path: F:\test\Core-Java-Vol-II-Advanced-Features.pdf
         * readBytesByInputSteam: bytes length 80589736, timeConsumed: 353
         * readBytesByFiles: bytes length 80589736, timeConsumed: 123
         * readBytesByGuava: bytes length 80589736, timeConsumed: 258
         * readBytesByCommonIO: bytes length 80589736, timeConsumed: 198
         */
    }

    public static void test(List<String> filePathArray) {
        for (String filePath : filePathArray) {
            System.out.printf("File path: %s%n", filePath);
            ReadBytes.readBytesByInputSteam(filePath);
            ReadBytes.readBytesByFiles(filePath);
            ReadBytes.readBytesByGuava(filePath);
            ReadBytes.readBytesByCommonIO(filePath);
            System.out.printf("%n");
        }
    }

    public static byte[] readBytesByInputSteam(String filePath) {
        long start = System.currentTimeMillis();

        byte[] bytes = null;
        File file = new File(filePath);
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
             ByteArrayOutputStream bos = new ByteArrayOutputStream(1000)) {
            byte[] b = new byte[1024];
            int n;
            while ((n = dis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bytes = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long timeConsumed = end - start;
        long fileLength = bytes == null ? 0 : bytes.length;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.printf("%s: bytes length %d, timeConsumed: %d%n", methodName, fileLength, timeConsumed);

        return bytes;
    }

    public static byte[] readBytesByFiles(String filePath) {
        long start = System.currentTimeMillis();

        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long timeConsumed = end - start;
        long fileLength = bytes == null ? 0 : bytes.length;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.printf("%s: bytes length %d, timeConsumed: %d%n", methodName, fileLength, timeConsumed);

        return bytes;
    }

    public static byte[] readBytesByGuava(String filePath) {
        long start = System.currentTimeMillis();

        byte[] bytes = null;
        File file = new File(filePath);
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        ) {
            bytes = ByteStreams.toByteArray(dis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long timeConsumed = end - start;
        long fileLength = bytes == null ? 0 : bytes.length;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.printf("%s: bytes length %d, timeConsumed: %d%n", methodName, fileLength, timeConsumed);

        return bytes;
    }

    public static byte[] readBytesByCommonIO(String filePath) {
        long start = System.currentTimeMillis();

        byte[] bytes = null;
        File file = new File(filePath);
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        ) {
            bytes = IOUtils.toByteArray(dis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long timeConsumed = end - start;
        long fileLength = bytes == null ? 0 : bytes.length;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.printf("%s: bytes length %d, timeConsumed: %d%n", methodName, fileLength, timeConsumed);

        return bytes;
    }
}
