package org.misty.smooth.api.service.vo;

import java.util.*;

public final class SmoothServicePayloadHeaderContent implements List<String> {

    private final List<String> list;

    public SmoothServicePayloadHeaderContent() {
        this.list = new ArrayList<>();
    }

    public SmoothServicePayloadHeaderContent(Collection<String> initCollection) {
        this.list = new ArrayList<>(initCollection);
    }

    public SmoothServicePayloadHeaderContent(int initialCapacity) {
        this.list = new ArrayList<>(initialCapacity);
    }

    /**
     * for unit test
     */
    protected SmoothServicePayloadHeaderContent(List<String> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return this.list.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return this.list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public String get(int index) {
        return this.list.get(index);
    }

    @Override
    public String set(int index, String element) {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, String element) {
        this.list.add(index, element);
    }

    @Override
    public String remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }
}
