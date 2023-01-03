package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if (index != -1) {
			remove(index);
		}
		return index != -1;
	}

	@Override
	public boolean removeIf(Predicate<? super T> predicate) {
		int oldSize = size;
		Node<T> current = tail;
		for (int i = size - 1; i >= 0; i--) {
			Node<T> prev = current.prev;
			if (predicate.test(current.obj)) {
				remove(i);
			}
			current = prev;
		}
		return size != oldSize;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		int index = 0;
		for (T t : this) {
			ar[index++] = t;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {			
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> curNode = getNode(index);
		Node<T> prevNode = curNode.prev;
		
		node.prev = prevNode;
		node.next = curNode;
		prevNode.next = node;
		curNode.prev = node;
		size++;
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> result = tail;
		for (int i = size - 1; i > index; i--) {
			result = result.prev;
		}
		return result;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		head.prev = node;
		node.next = head;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> nodeToRemove;
		if (size == 1) {
			nodeToRemove = removeOne();
		} else if (index == size - 1) {
			nodeToRemove = removeLast();
		} else if (index == 0) {
			nodeToRemove = removeFirst();
		} else {
			nodeToRemove = removeMiddle(index);
		}
		size--;
		
		return nodeToRemove.obj;
	}

	private Node<T> removeMiddle(int index) {
		Node<T> nodeToRemove = getNode(index);
		Node<T> prevNode = nodeToRemove.prev;
		Node<T> nextNode = nodeToRemove.next;
		nodeToRemove.prev = null;
		nodeToRemove.next = null;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		return nodeToRemove;
	}

	private Node<T> removeFirst() {
		Node<T> nodeToRemove = head;
		head = head.next;
		head.prev = null;
		nodeToRemove.next = null;
		return nodeToRemove;
	}

	private Node<T> removeLast() {
		Node<T> nodeToRemove = tail;
		tail = tail.prev;
		tail.next = null;
		nodeToRemove.prev = null;
		return nodeToRemove;
	}

	private Node<T> removeOne() {
		Node<T> nodeToRemove = head;
		head = null;
		tail = null;
		return nodeToRemove;
	}

	@Override
	public int indexOf(Object pattern) {
		Node<T> current = head;
		int index = 0;
		while(index < size && !checkEquals(current.obj, pattern)) {
			current = current.next;
			index++;
		}
		return index == size ? -1 : index;
	}

	private boolean checkEquals(T currentObj, Object pattern) {
		return currentObj == null ? pattern == null : currentObj.equals(pattern);
	}

	@Override
	public int lastIndexOf(Object pattern) {
		Node<T> current = tail;
		int index = size - 1;
		while(index >= 0 && !checkEquals(current.obj, pattern)) {
			current = current.prev;
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public T set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		T res = node.obj;
		node.obj = element;
		return res;
	}
	
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		
		Node (T obj) {
			this.obj = obj;
		}
	}
	
	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			return res;
		}
		
	}

}
