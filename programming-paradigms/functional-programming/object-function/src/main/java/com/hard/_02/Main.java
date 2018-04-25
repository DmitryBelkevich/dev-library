package com.hard._02;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Strategy2 strategy = new Strategy2();

        Collection<Integer> collection = new ArrayList<>();

        collection.add(1);
        collection.add(2);
        collection.add(3);

        String str = strategy.joinNumbers(collection);
        System.out.println(str);
    }
}

/**
 * imperative style
 */

class Strategy1 {
    public String joinNumbers(Collection<?> collection) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean first = true;

        for (Object element : collection) {
            if (first)
                first = false;
            else
                stringBuilder.append(", ");

            stringBuilder.append(element);
        }

        return stringBuilder.toString();
    }
}

/**
 * functional style
 */

interface Function<F, T> {
    T apply(F from);
}

class Strategy2 {
    private Function<Integer, String> intToString = new Function<Integer, String>() {
        @Override
        public String apply(Integer t) {
            return t.toString();
        }
    };

    private <F, T> Collection<T> map(Collection<F> from, Function<? super F, ? extends T> transformer) {
        Collection<T> collection = new ArrayList<>();

        for (F element : from)
            collection.add(transformer.apply(element));

        return collection;
    }

    private <T> String join(Collection<T> collection, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean first = true;

        for (T element : collection) {
            if (first)
                first = false;
            else
                stringBuilder.append(separator);

            stringBuilder.append(element);
        }

        return stringBuilder.toString();
    }

    public String joinNumbers(Collection<? extends Integer> numbers) {
        Collection<String> strings = map(numbers, intToString);

        String result = join(strings, ", ");

        return result;
    }
}
