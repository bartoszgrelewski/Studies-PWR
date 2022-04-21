public class Tree<Type extends Comparable<Type>> {

    public Node<Type> root;

    public Tree() {
        root = null;
    }

    public boolean search(Type data) {
        return check(data, root);
    }

    private boolean check(Type data, Node<Type> node) {
        if (node == null) return false;
        if (data.compareTo(node.data) == 0) return true;
        if (data.compareTo(node.data) < 0) return check(data, node.leftSon);
        else return check(data, node.rightSon);
    }

    public void insert(Type data) {
        root = ins(data, root);
    }

    private Node<Type> ins(Type data, Node<Type> node) {
        if (node == null) return new Node<>(data);
        if (data.compareTo(node.data) < 0) node.leftSon = ins(data, node.leftSon);
        else if (data.compareTo(node.data) >= 0) node.rightSon = ins(data, node.rightSon);
        return node;
    }

    public void delete(Type data) {
        if (check(data, root)) root = del(data, root);
        else System.out.println("nie ma takiej wartosci w drzewie");
    }

    private Node<Type> del(Type data, Node<Type> node) {
        if (data.compareTo(node.data) == 0) {
            if (node.leftSon == null && node.rightSon == null) return null;
            else if (node.leftSon != null && node.rightSon != null) {
                Node<Type> successor = successor(node);
                if (node.rightSon != successor) successor.rightSon = del(successor(node).data, node.rightSon);
                successor.leftSon = node.leftSon;
                return successor;
            } else if (node.leftSon != null) return node.leftSon;
            else return node.rightSon;
        } else if (data.compareTo(node.data) < 0) node.leftSon = del(data, node.leftSon);
        else if (data.compareTo(node.data) > 0) node.rightSon = del(data, node.rightSon);
        return node;
    }

    private Node<Type> successor(Node<Type> node) {
        node = node.rightSon;
        while (node.leftSon != null) node = node.leftSon;
        return node;
    }

    public String inOrder() {
        return write(root);
    }

    private String write(Node<Type> node) {
        if (node != null) return "(" + node.data + ":" + write(node.leftSon) + ":" + write(node.rightSon) + ")";
        return "()";
    }
}