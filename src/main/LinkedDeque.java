package main;

import java.util.AbstractCollection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Zobov on 27.03.14.
 */
public class LinkedDeque<E> extends AbstractCollection<E> implements Deque<E>{
    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private Node<E> head;


    public LinkedDeque() {
        size = 0;
        head = new Node<E>(null, null, null);
        head.prev = head.next = head;
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return head.next.element;
    }

    @Override
    public E getLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return head.prev.element;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return getFirst();
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return getLast();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //using this method we can add Node in any position in this Collection
    private void addNode(E e, Node<E> current) {
        Node<E> newNode = new Node<E>(e, current, current.prev);
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    //using this method we can remove Node in in this Collection
    private void removeNode(Node<E> current) {
        if (current == null) throw new NoSuchElementException();
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    //TODO need to test
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node<E> current = head.next; current.next != null; current = current.next)
            result.append(current.element.toString() + " ");
        return result.toString();
    }

    @Override
    public void addFirst(E e) {
        addNode(e, head.next);
    }


    @Override
    public void addLast(E e) {
        addNode(e, head);
    }

    @Override
    public E pollFirst() {
        E e = getFirst();
        removeNode(head.next);
        return e;
    }

    @Override
    public E pollLast() {
        E e = getLast();
        removeNode(head.prev);
        return e;
    }

    //Unsupported
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerFirst(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offerLast(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void push(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E pop() {
        throw new UnsupportedOperationException();
    }
}
