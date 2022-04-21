/**
 * klasa wezla
 * @param <Type> typ
 */
public class Node <Type /*! @cond x*/ extends Comparable<Type>/*! @endcond*/> {

    /**
     * konstruktor wezla
     * @param data dana
     */
    Node(Type data) {
        this.data = data;
        leftSon = null;
        rightSon = null;
    }

    /**
     * deklaracja danej oraz potomkow wezla
     */
    public Type data;
    public Node<Type> leftSon;
    public Node<Type> rightSon;
}
