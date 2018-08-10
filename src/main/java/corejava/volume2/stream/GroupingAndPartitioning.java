package corejava.volume2.stream;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingAndPartitioning {


    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, List<Locale>> countryToLocals = locales.collect(Collectors.groupingBy(Locale::getCountry));
//        System.out.println(countryToLocals);
        List<Locale> swissLocales = countryToLocals.get("CH");
        System.out.println(swissLocales);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<Boolean, List<Locale>> englishAndOtherLocals = locales.collect(
                Collectors.partitioningBy(l -> l.getLanguage().equals("en"))
        );
        List<Locale> englishLocals = englishAndOtherLocals.get(true);
        List<Locale> otherLocals = englishAndOtherLocals.get(false);
        System.out.println(englishLocals);
        System.out.println(otherLocals);
    }

}
