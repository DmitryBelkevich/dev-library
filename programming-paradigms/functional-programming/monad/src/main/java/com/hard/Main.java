package com.hard;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Maybe<Integer> x = new Maybe<>(2);
        Monad<Integer> y = x
                .bind(v -> new Maybe<>(v + 2))
                .bind(v -> new Maybe<>(v * 2));

        int result = ((Maybe<Integer>) y).getVal();
        System.out.println(result);
    }
}

interface Monad<T> {
    <U> Monad<U> bind(Function<T, Monad<U>> function);
}

class Maybe<T> implements Monad<T> {
    private final T val;

    public Maybe(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    @Override
    public <U> Monad<U> bind(Function<T, Monad<U>> function) {
        if (val == null) {
            Monad<U> monad = new Maybe<>(null);
            return monad;
        }

        Monad<U> monad = function.apply(val);
        return monad;
    }
}
