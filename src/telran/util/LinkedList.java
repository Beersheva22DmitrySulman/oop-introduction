package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
	private Node<T> head;
	private Node<T> tail;

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		addTail(node);
		size++;
		return true;
	}

	private void addTail(Node<T> node) {
		if (head == null) {
			head = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}
	
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalStateException();
		}
		getNode(index1).next = getNode(index2);
	}
	
	public boolean hasLoop() {
		boolean res = false;
		Node<T> current = head;

		while (current != null && !res) {
			if (current.isVisited) {
				res = true;
			}
			current.isVisited = true;
			current = current.next;
		}
		return res;
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
			addTail(node);
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
		removeNode(nodeToRemove);

		return nodeToRemove.obj;
	}

	private void removeNode(Node<T> nodeToRemove) {
		if (nodeToRemove == head) {
			removeHead();
		} else if (nodeToRemove == tail) {
			removeTail();
		} else {
			removeMiddle(nodeToRemove);
		}
		size--;
	}

	private void removeMiddle(Node<T> nodeToRemove) {
		Node<T> prevNode = nodeToRemove.prev;
		Node<T> nextNode = nodeToRemove.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}

	private void removeHead() {
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}
	}

	private void removeTail() {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;
	}

	@Override
	public int indexOf(Object pattern) {
		Node<T> current = head;
		int index = 0;
		while (current != null && !isEqual(current.obj, pattern)) {
			current = current.next;
			index++;
		}
		return current == null ? -1 : index;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		Node<T> current = tail;
		int index = size - 1;
		while (current != null && !isEqual(current.obj, pattern)) {
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
		boolean isVisited;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

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
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}
	}
}
