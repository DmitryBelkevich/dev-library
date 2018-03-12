package hard;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
    }
}

class Tree<T> {
    private Node<T> root;

    static class Node<T> {
        private T data;
        private Node<T>[] nodes;
    }
}
