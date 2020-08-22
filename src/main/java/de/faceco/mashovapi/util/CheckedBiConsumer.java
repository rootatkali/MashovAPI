package de.faceco.mashovapi.util;

/**
 * A functional interface that accepts two parameters and returns no result,
 * with the possibility of throwing an exception.
 *
 * @param <T> The type for the first parameter
 * @param <U> The type for the second parameter
 * @param <E> The type of exception to be raised
 * @see CheckedTriConsumer
 * @see java.util.function.BiConsumer
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface CheckedBiConsumer<T, U, E extends Exception> {
  void accept(T t, U u) throws E;
}
