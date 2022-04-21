/**
 * klasa drzewa
 * @param <Type> typ
 */
public class Tree<Type /*! @cond x*/ extends Comparable<Type>/*! @endcond*/> {

    /**
     * deklaracja korzenia
     */
    public Node<Type> root;

    /**
     * konstruktor drzewa
     */
    public Tree() {
        root = null;
    }

    /**
     * metoda uzywajaca metody do szukania wartosci w drzewie
     * @param data dana
     * @return czy drzewo zawiera dana
     */
    public boolean search(Type data) {
        return check(data, root);
    }

    /**
     * metoda szukajaca danej w drzewie
     * @param data dana
     * @param node wezel
     * @return czy drzewo zawiera dana
     */
    private boolean check(Type data, Node<Type> node) {
        if (node == null) return false;
        if (data.compareTo(node.data) == 0) return true;
        if (data.compareTo(node.data) < 0) return check(data, node.leftSon);
        else return check(data, node.rightSon);
    }

    /**
     * metoda uzywajaca metody do dodawania
     * @param data dana
     */
    public void insert(Type data) {
        root = ins(data, root);
    }

    /**
     * metoda dodajaca dana do drzewa
     * @param data dana
     * @param node wezel
     * @return nowy wezel z dana
     */
    private Node<Type> ins(Type data, Node<Type> node) {
        if (node == null) return new Node<>(data);
        if (data.compareTo(node.data) < 0) node.leftSon = ins(data, node.leftSon);
        else if (data.compareTo(node.data) >= 0) node.rightSon = ins(data, node.rightSon);
        return node;
    }

    /**
     * metoda uzywajaca metody usuwajacej
     * @param data dana
     */
    public void delete(Type data) {
        if (check(data, root)) root = del(data, root);
        else System.out.println("nie ma takiej wartosci w drzewie");
    }

    /**
     * metoda usuwajaca dana z drzewa
     * @param data dana
     * @param node wezel
     * @return wezel z usunieta dana
     */
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

    /**
     * metoda znajdujaca nastepnik
     * @param node wezel
     * @return wezel bedacy nastepnikiem
     */
    private Node<Type> successor(Node<Type> node) {
        node = node.rightSon;
        while (node.leftSon != null) node = node.leftSon;
        return node;
    }

    /**
     * metoda uzywajaca metody do wypisywania drzewa
     * @return napis z wartosciami drzewa
     */
    public String inOrder() {
        return write(root);
    }

    /**
     * metoda zwracajaca napis z wartosciami drzewa
     * @param node wezel
     * @return napis z wartosciami drzewa
     */
    private String write(Node<Type> node) {
        if (node != null) return "(" + node.data + ":" + write(node.leftSon) + ":" + write(node.rightSon) + ")";
        return "()";
    }
}