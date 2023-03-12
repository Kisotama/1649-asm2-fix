package Stack;

import java.util.LinkedList;

public class Stack {
    public class Stacks<T> {
        private LinkedList<T> list = new LinkedList<T>();

        public void push(T item) {
            list.addFirst(item);
        }

        public T pop() {
            return list.removeFirst();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
        }
    }

}
