package org.misty.smooth.api.service.vo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class SmoothServicePayloadHeaderContent implements Set<String> {

    private final Set<String> set;

    public SmoothServicePayloadHeaderContent() {
        this(false);
    }

    public SmoothServicePayloadHeaderContent(boolean sequential) {
        this.set = sequential ? new TreeSet<>() : new HashSet<>();
    }

    /**
     * for unit test
     */
    protected SmoothServicePayloadHeaderContent(Set<String> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return this.set.toString();
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.set.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return this.set.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.set.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return this.set.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return this.set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return this.set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.set.removeAll(c);
    }

    @Override
    public void clear() {
        this.set.clear();
    }

    @Override
    public Spliterator<String> spliterator() {
        return this.set.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return this.set.removeIf(filter);
    }

    @Override
    public Stream<String> stream() {
        return this.set.stream();
    }

    @Override
    public Stream<String> parallelStream() {
        return this.set.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        this.set.forEach(action);
    }
}
