package corejava.volume1.lambda;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("sort in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("sort by length:");
        Arrays.sort(planets, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> {
            System.out.println("The time is:" + new Date());
            Toolkit.getDefaultToolkit().beep();
        });
        t.start();

        JOptionPane.showMessageDialog(null, "Quit Program?");
        System.exit(0);
    }
}