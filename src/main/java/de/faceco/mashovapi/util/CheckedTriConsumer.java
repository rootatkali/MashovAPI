package de.faceco.mashovapi.util;

/**
 * A functional interface that accepts three parameters and returns no result,
 * with the possibility of throwing an exception.
 *
 * @param <T> The type of the first parameter
 * @param <K> The type of the second parameter
 * @param <U> The type of the third parameter
 * @param <E> The type of the thrown exception
 * @see CheckedBiConsumer
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface CheckedTriConsumer<T, K, U, E extends Exception> {
  void accept(T t, K k, U u) throws E;
}
