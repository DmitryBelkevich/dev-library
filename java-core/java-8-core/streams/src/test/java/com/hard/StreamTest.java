package com.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {
    public static class CreateStream {
        @Test
        public void arrays_asList() {
            List<String> list = Arrays.asList("a", "b", "c");

            list.forEach(System.out::println);
        }

        @Test
        public void arrays_stream_Object() {
            Arrays.stream(new String[]{"a", "b", "c"})
                    .forEach(System.out::println);
        }

        @Test
        public void arrays_stream_int() {
            Arrays.stream(new int[]{1, 2, 3})
                    .forEach(System.out::println);
        }

        @Test
        public void stream_of() {
            Stream.of("a", "b", "c")
                    .forEach(System.out::println);
        }

        @Test
        public void IntStream_range() {
            IntStream.range(5, 10)
                    .forEach(System.out::println);
        }

        @Test
        public void LongStream_range() {
            LongStream.range(5, 10)
                    .forEach(System.out::println);
        }

        @Test
        public void DoubleStream_builder() {
            DoubleStream.builder()
                    .add(1.0)
                    .add(2.0)
                    .add(3.0)
                    .build()
                    .forEach(System.out::println);
        }
    }

    public static class Operations {
        public static class Intermediate {
            /**
             * filter
             * операция: промежуточная
             * принимает предикат, который фильтрует все элементы потока
             */
            @Test
            public void filter_test() {
                Arrays.asList("a1", "b1", "c1", "a2", "b2", "c2")
                        .stream()
                        .filter(str -> str.startsWith("a"))
                        .forEach(System.out::println);  // a1, a2
            }

            /**
             * sorted
             * операция: промежуточная
             * возвращает отсортированное представление потока.
             * Элементы сортируются в обычном порядке, если не был предоставлен свой компаратор
             */
            @Test
            public void sorted_test() {
                Arrays.asList("b", "c", "a")
                        .stream()
                        .sorted()
                        .forEach(System.out::println);  // a, b, c
            }

            /**
             * map
             * операция: промежуточная
             * преобразовывает каждый элемент в другой объект при помощи переданной функции.
             * так же можно использовать map для преобразования каждого объекта в объект другого типа.
             * тип результирующего потока зависит от типа функции, который передан при вызове map
             */
            @Test
            public void map_test() {
                Arrays.asList("a", "b", "c")
                        .stream()
                        .map(String::toUpperCase)
                        .forEach(System.out::println);  // A, B, C
            }
        }

        public static class Terminal {
            /**
             * anyMatch
             * проверяет, удовлетворяет ли поток заданному предикату
             */
            @Test
            public void anyMatch_test() {
                List<String> list = Arrays.asList("a", "b", "c");

                boolean anyMatch = list.stream()
                        .anyMatch((s) -> s.startsWith("a"));

                Assert.assertTrue(anyMatch);
            }

            /**
             * allMatch
             * проверяет, удовлетворяет ли поток заданному предикату
             */
            @Test
            public void allMatch_test() {
                List<String> list = Arrays.asList("a1", "a2", "a3");

                boolean allMatch = list.stream()
                        .allMatch((s) -> s.startsWith("a"));

                Assert.assertTrue(allMatch);
            }

            /**
             * noneMatch
             * проверяет, удовлетворяет ли поток заданному предикату
             */
            @Test
            public void noneMatch_test() {
                List<String> list = Arrays.asList("a", "b", "c");

                boolean noneMatch = list.stream()
                        .noneMatch((s) -> s.startsWith("z"));

                Assert.assertTrue(noneMatch);
            }

            /**
             * count
             * возвращает количество элементов в потоке
             */
            @Test
            public void count_test() {
                List<String> list = Arrays.asList("a", "b", "c");

                long count = list.stream()
                        .count();

                Assert.assertEquals(3, count);
            }

            /**
             * findFirst
             */
            @Test
            public void findFirst_test() {
                List<String> list = Arrays.asList("a", "b", "c");

                Optional<String> optional = list.stream()
                        .findFirst();

                optional.ifPresent(System.out::println);
            }

            /**
             * reduce
             * производит свертку элементов потока по заданной функции.
             * Результатом является опциональное значение
             */
            @Test
            public void reduce_test() {
                List<String> list = Arrays.asList("a", "b", "c");

                Optional<String> optional = list.stream()
                        .reduce((s1, s2) -> s1 + ", " + s2);

                optional.ifPresent(System.out::println); // a, b, c
            }
        }
    }

    @Test
    public void test1() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    public static class Cast {
        @Test
        public void mapToInt() {
            Stream.of("1", "2", "3")
                    .mapToInt(Integer::parseInt)
                    .forEach(System.out::println);
        }

        @Test
        public void mapToLong() {
            Stream.of("1", "2", "3")
                    .mapToLong(Long::parseLong)
                    .forEach(System.out::println);
        }

        @Test
        public void mapToDouble() {
            Stream.of("1", "2", "3")
                    .mapToDouble(Double::parseDouble)
                    .forEach(System.out::println);
        }

        @Test
        public void mapToObj() {
            Arrays.stream(new int[]{1, 2, 3})
                    .mapToObj(i -> i)
                    .forEach(System.out::println);
        }

        @Test
        public void mapToObj_mapToObj() {
            Stream.of(1.0, 2.0, 3.0)
                    .mapToInt(Double::intValue)
                    .mapToObj(i -> i)
                    .forEach(System.out::println);
        }
    }
}
