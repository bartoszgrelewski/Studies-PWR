public class Node <Type extends Comparable<Type>> {

    Node(Type data) {
        this.data = data;
        leftSon = null;
        rightSon = null;
    }

    public Type data;
    public Node<Type> leftSon;
    public Node<Type> rightSon;
}
