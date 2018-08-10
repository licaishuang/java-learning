package corejava.volume2.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorToMap {
    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println(idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(Collectors.toMap(
                Person::getId,
                Function.identity(),
                (existValue, newValue) -> {
                    throw new IllegalStateException();
                },
                TreeMap::new));
        System.out.println(idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.filter(l -> l.getDisplayLanguage().length() > 0).collect(Collectors.toMap(Locale::getDisplayLanguage, l -> l.getDisplayLanguage(l), (existingValue, newValue) -> existingValue));
        System.out.println(languageNames);
        System.out.println(languageNames.size());

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguages = locales.collect(
                Collectors.toMap(
                        Locale::getDisplayCountry,
                        l -> Collections.singleton(l.getDisplayLanguage()),
                        (a, b) -> {
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }));
        System.out.println(countryLanguages);
        System.out.println(countryLanguages.entrySet().stream().filter((e -> e.getValue().size() > 1)).collect(Collectors.toSet()));
    }

    public static Stream<Person> people() {
        return Stream.of(new Person(1, "peter"), new Person(2, "shawn"), new Person(3, "bain"));
    }

    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return getClass().getName() + "{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
