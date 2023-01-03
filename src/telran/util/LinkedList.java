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
		addTale(node);
		size++;
		return true;
	}

	private void addTale(Node<T> node) {
		if (head == null) {
			head = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public boolean remove(Object pattern) {
		Node<T> nodeToRemove = getNodeByObject(pattern);
		if (nodeToRemove != null) {
			removeInternal(nodeToRemove);
		}
		return nodeToRemove != null;
	}

	@Override
	public boolean removeIf(Predicate<? super T> predicate) {
		int oldSize = size;
		Node<T> current = tail;
		for (int i = size - 1; i >= 0; i--) {
			Node<T> prev = current.prev;
			if (predicate.test(current.obj)) {
				removeInternal(current);
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
		Node<T> node = new Node<>(element);
		if (index == size) {			
			addTale(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;
	}

	private void addMiddle(int index, Node<T> node) {
		Node<T> curNode = getNode(index);
		Node<T> prevNode = curNode.prev;
		
		node.prev = prevNode;
		node.next = curNode;
		prevNode.next = node;
		curNode.prev = node;
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

	private void addHead(Node<T> node) {
		head.prev = node;
		node.next = head;
		head = node;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> nodeToRemove = getNode(index);
		removeInternal(nodeToRemove);
		
		return nodeToRemove.obj;
	}
	
	private void removeInternal(Node<T> nodeToRemove) {
		if (size == 1) {
			removeSingle();
		} else if (nodeToRemove == tail) {
			removeTail();
		} else if (nodeToRemove == head) {
			removeHead();
		} else {
			removeMiddle(nodeToRemove);
		}
		size--;
	}

	private void removeMiddle(Node<T> nodeToRemove) {
		Node<T> prevNode = nodeToRemove.prev;
		Node<T> nextNode = nodeToRemove.next;
		nodeToRemove.prev = null;
		nodeToRemove.next = null;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}

	private void removeHead() {
		head = head.next;
		head.prev.next = null;
		head.prev = null;
	}

	private void removeTail() {	
		tail = tail.prev;
		tail.next.prev = null;
		tail.next = null;
	}

	private void removeSingle() {
		head = null;
		tail = null;
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
	
	private Node<T> getNodeByObject(Object pattern) {
		Node<T> current = head;
		while(current != null && !checkEquals(current.obj, pattern)) {
			current = current.next;
		}
		return current;
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
