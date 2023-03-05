package me.bartek.algorithms;

import me.bartek.data.RBBstTreeItem;
import me.bartek.generators.RandomTabGenerator;

import java.awt.*;

public class RBBst extends BST {

	private RBBstTreeItem root = null;

	@Override
	public void insertRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		insertFromArray(tabGenerator.generate(numOfElements));
	}

	@Override
	public void insertFromArray(int[] tab) {
		for (int i : tab)
			insert(i);
	}

	@Override
	public void deleteRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		deleteFromArray(tabGenerator.generate(numOfElements));
	}

	@Override
	public void deleteFromArray(int[] tab) {
		for (int i : tab)
			delete(i);
	}

	@Override
	public void delete(int k) {
		System.out.println("delete " + k);
		if (searchNode(k) == null) {
			System.out.println("klucza " + k + " nie ma w drzewie, wiec nie mozemy go usunac ");
			return;
		}
		deleteNode(k);
		printTree();
	}

	public RBBstTreeItem searchNode(int key) {
		RBBstTreeItem node = root;
		while (node != null) {
			if (key == node.getValue()) {
				return node;
			} else if (key < node.getValue()) {
				node = node.getLeftNode(getStatistics());
			} else {
				node = node.getRightNode(getStatistics());
			}
		}

		return null;
	}

	// -- Insertion ----------------------------------------------------------------------------------
	@Override
	public void insert(int k) {
		System.out.println("insert " + k);
		RBBstTreeItem node = root;
		RBBstTreeItem parent = null;

		while (node != null) {
			parent = node;
			if (k < node.getValue()) {
				node = node.getLeftNode(getStatistics());
			} else if (k > node.getValue()) {
				node = node.getRightNode(getStatistics());
			} else {
				throw new IllegalArgumentException("BST already contains a node with key " + k);
			}
		}

		// Insert new node
		RBBstTreeItem newNode = new RBBstTreeItem(k, Color.red);
		if (parent == null) {
			root = newNode;
		} else if (k < parent.getValue()) {
			parent.setLeftNode(newNode);
		} else {
			parent.setRightNode(newNode);
		}
		newNode.setParent(parent);

		fixRedBlackPropertiesAfterInsert(newNode);
		printTree();

	}

	@SuppressWarnings("squid:S125") // Ignore SonarCloud complains about commented code line 70.
	private void fixRedBlackPropertiesAfterInsert(RBBstTreeItem node) {
		RBBstTreeItem parent = node.getParent();

		// Case 1: Parent is null, we've reached the root, the end of the recursion
		if (parent == null) {
			// Uncomment the following line if you want to enforce black roots (rule 2):
			// node.color = BLACK;
			return;
		}

		// Parent is black --> nothing to do
		if (parent.getColor() == Color.black) {
			return;
		}

		// From here on, parent is red
		RBBstTreeItem grandparent = parent.getParent();

		// Case 2:
		// Not having a grandparent means that parent is the root. If we enforce black roots
		// (rule 2), grandparent will never be null, and the following if-then block can be
		// removed.
		if (grandparent == null) {
			// As this method is only called on red nodes (either on newly inserted ones - or -
			// recursively on red grandparents), all we have to do is to recolor the root black.
			parent.setColor(Color.black);
			return;
		}

		// Get the uncle (may be null/nil, in which case its color is BLACK)
		RBBstTreeItem uncle = getUncle(parent);

		// Case 3: Uncle is red -> recolor parent, grandparent and uncle
		if (uncle != null && uncle.getColor() == Color.red) {
			parent.setColor(Color.black);
			grandparent.setColor(Color.red);
			uncle.setColor(Color.black);

			// Call recursively for grandparent, which is now red.
			// It might be root or have a red parent, in which case we need to fix more...
			fixRedBlackPropertiesAfterInsert(grandparent);
		}

		// Parent is left child of grandparent
		else if (parent == grandparent.getLeftNode(getStatistics())) {
			// Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
			if (node == parent.getRightNode(getStatistics())) {
				rotateLeft(parent);

				// Let "parent" point to the new root node of the rotated sub-tree.
				// It will be recolored in the next step, which we're going to fall-through to.
				parent = node;
			}

			// Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
			rotateRight(grandparent);

			// Recolor original parent and grandparent
			parent.setColor(Color.black);
			grandparent.setColor(Color.red);
		}

		// Parent is right child of grandparent
		else {
			// Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
			if (node == parent.getLeftNode(getStatistics())) {
				rotateRight(parent);

				// Let "parent" point to the new root node of the rotated sub-tree.
				// It will be recolored in the next step, which we're going to fall-through to.
				parent = node;
			}

			// Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
			rotateLeft(grandparent);

			// Recolor original parent and grandparent
			parent.setColor(Color.black);
			grandparent.setColor(Color.red);
		}
	}

	private RBBstTreeItem getUncle(RBBstTreeItem parent) {
		RBBstTreeItem grandparent = parent.getParent();
		if (grandparent.getLeftNode(getStatistics()) == parent) {
			return grandparent.getRightNode(getStatistics());
		} else if (grandparent.getRightNode(getStatistics()) == parent) {
			return grandparent.getLeftNode(getStatistics());
		} else {
			throw new IllegalStateException("Parent is not a child of its grandparent");
		}
	}

	public void deleteNode(int key) {
		RBBstTreeItem node = root;

		while (node != null && node.getValue() != key) {
			// Traverse the tree to the left or right depending on the key
			if (key < node.getValue()) {
				node = node.getLeftNode(getStatistics());
			} else {
				node = node.getRightNode(getStatistics());
			}
		}

		if (node == null) {
			return;
		}

		RBBstTreeItem movedUpNode;
		Color deletedNodeColor;

		// RBBstTreeItem has zero or one child
		if (node.getLeftNode(getStatistics()) == null || node.getRightNode(getStatistics()) == null) {
			movedUpNode = deleteNodeWithZeroOrOneChild(node);
			deletedNodeColor = node.getColor();
		}

		// RBBstTreeItem has two children
		else {
			// Find minimum node of right subtree ("inorder successor" of current node)
			RBBstTreeItem inOrderSuccessor = findMinimum(node.getRightNode(getStatistics()));

			// Copy inorder successor's getValue() to current node (keep its color!)
			node.setValue(inOrderSuccessor.getValue());

			// Delete inorder successor just as we would delete a node with 0 or 1 child
			movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
			deletedNodeColor = inOrderSuccessor.getColor();
		}

		if (deletedNodeColor == Color.black) {
			fixRedBlackPropertiesAfterDelete(movedUpNode);

			// Remove the temporary NIL node
			if (movedUpNode.getClass() == NilNode.class) {
				replaceParentsChild(movedUpNode.getParent(), movedUpNode, null);
			}
		}
	}

	private RBBstTreeItem deleteNodeWithZeroOrOneChild(RBBstTreeItem node) {
		// RBBstTreeItem has ONLY a left child --> replace by its left child
		if (node.getLeftNode(getStatistics()) != null) {
			replaceParentsChild(node.getParent(), node, node.getLeftNode(getStatistics()));
			return node.getLeftNode(getStatistics()); // moved-up node
		}

		// RBBstTreeItem has ONLY a right child --> replace by its right child
		else if (node.getRightNode(getStatistics()) != null) {
			replaceParentsChild(node.getParent(), node, node.getRightNode(getStatistics()));
			return node.getRightNode(getStatistics()); // moved-up node
		}

		// RBBstTreeItem has no children -->
		// * node is red --> just remove it
		// * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
		else {
			RBBstTreeItem newChild = node.getColor() == Color.black ? new NilNode() : null;
			replaceParentsChild(node.getParent(), node, newChild);
			return newChild;
		}
	}

	private RBBstTreeItem findMinimum(RBBstTreeItem node) {
		while (node.getLeftNode(getStatistics()) != null) {
			node = node.getLeftNode(getStatistics());
		}
		return node;
	}

	@SuppressWarnings("squid:S125") // Ignore SonarCloud complains about commented code line 256.
	private void fixRedBlackPropertiesAfterDelete(RBBstTreeItem node) {
		// Case 1: Examined node is root, end of recursion
		if (node == root) {
			// Uncomment the following line if you want to enforce black roots (rule 2):
			// node.color = BLACK;
			return;
		}

		RBBstTreeItem sibling = getSibling(node);

		// Case 2: Red sibling
		if (sibling.getColor() == Color.RED) {
			handleRedSibling(node, sibling);
			sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
		}

		// Cases 3+4: Black sibling with two black children
		if (isBlack(sibling.getLeftNode(getStatistics())) && isBlack(sibling.getRightNode(getStatistics()))) {
			sibling.setColor(Color.red);

			// Case 3: Black sibling with two black children + red parent
			if (node.getParent().getColor() == Color.red) {
				node.getParent().setColor(Color.black);
			}

			// Case 4: Black sibling with two black children + black parent
			else {
				fixRedBlackPropertiesAfterDelete(node.getParent());
			}
		}

		// Case 5+6: Black sibling with at least one red child
		else {
			handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
		}
	}

	private void handleRedSibling(RBBstTreeItem node, RBBstTreeItem sibling) {
		// Recolor...
		sibling.setColor(Color.black);
		node.getParent().setColor(Color.red);

		// ... and rotate
		if (node == node.getParent().getLeftNode(getStatistics())) {
			rotateLeft(node.getParent());
		} else {
			rotateRight(node.getParent());
		}
	}

	private void handleBlackSiblingWithAtLeastOneRedChild(RBBstTreeItem node, RBBstTreeItem sibling) {
		boolean nodeIsLeftChild = node == node.getParent().getLeftNode(getStatistics());

		// Case 5: Black sibling with at least one red child + "outer nephew" is black
		// --> Recolor sibling and its child, and rotate around sibling
		if (nodeIsLeftChild && isBlack(sibling.getRightNode(getStatistics()))) {
			sibling.getLeftNode(getStatistics()).setColor(Color.BLACK);
			sibling.setColor(Color.red);
			rotateRight(sibling);
			sibling = node.getParent().getRightNode(getStatistics());
		} else if (!nodeIsLeftChild && isBlack(sibling.getLeftNode(getStatistics()))) {
			sibling.getRightNode(getStatistics()).setColor(Color.black);
			sibling.setColor(Color.red);
			rotateLeft(sibling);
			sibling = node.getParent().getLeftNode(getStatistics());
		}

		// Fall-through to case 6...

		// Case 6: Black sibling with at least one red child + "outer nephew" is red
		// --> Recolor sibling + parent + sibling's child, and rotate around parent
		sibling.setColor(node.getParent().getColor());
		node.getParent().setColor(Color.BLACK);
		if (nodeIsLeftChild) {
			sibling.getRightNode(getStatistics()).setColor(Color.black);
			rotateLeft(node.getParent());
		} else {
			sibling.getLeftNode(getStatistics()).setColor(Color.black);
			rotateRight(node.getParent());
		}
	}

	private RBBstTreeItem getSibling(RBBstTreeItem node) {
		RBBstTreeItem parent = node.getParent();
		if (node == parent.getLeftNode(getStatistics())) {
			return parent.getRightNode(getStatistics());
		} else if (node == parent.getRightNode(getStatistics())) {
			return parent.getLeftNode(getStatistics());
		} else {
			throw new IllegalStateException("Parent is not a child of its grandparent");
		}
	}

	private boolean isBlack(RBBstTreeItem node) {
		return node == null || node.getColor() == Color.black;
	}

	private static class NilNode extends RBBstTreeItem {
		private NilNode() {
			super(Color.black);
		}
	}

	private void rotateRight(RBBstTreeItem node) {
		RBBstTreeItem parent = node.getParent();
		RBBstTreeItem leftChild = node.getLeftNode(getStatistics());

		node.setLeftNode(leftChild.getRightNode(getStatistics()));
		if (leftChild.getRightNode(getStatistics()) != null) {
			leftChild.getRightNode(getStatistics()).setParent(node);
		}

		leftChild.setRightNode(node);
		node.setParent(leftChild);

		replaceParentsChild(parent, node, leftChild);
	}

	private void rotateLeft(RBBstTreeItem node) {
		RBBstTreeItem parent = node.getParent();
		RBBstTreeItem rightChild = node.getRightNode(getStatistics());

		node.setRightNode(rightChild.getLeftNode(getStatistics()));
		if (rightChild.getLeftNode(getStatistics()) != null) {
			rightChild.getLeftNode(getStatistics()).setParent(node);
		}

		rightChild.setLeftNode(node);
		node.setParent(rightChild);

		replaceParentsChild(parent, node, rightChild);
	}

	private void replaceParentsChild(RBBstTreeItem parent, RBBstTreeItem oldChild, RBBstTreeItem newChild) {
		if (parent == null) {
			root = newChild;
		} else if (parent.getLeftNode(getStatistics()) == oldChild) {
			parent.setLeftNode(newChild);
		} else if (parent.getRightNode(getStatistics()) == oldChild) {
			parent.setRightNode(newChild);
		} else {
			throw new IllegalStateException("RBBstTreeItem is not a child of its parent");
		}

		if (newChild != null) {
			newChild.setParent(parent);
		}
	}

	@Override
	public void printTree() {
		System.out.println(traversePreOrder(root));
	}

	private String traversePreOrder(RBBstTreeItem root) {

		if (root == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(root.getValue()).append("[B]");

		String pointerRight = "└──";
		String pointerLeft = (root.getRightNode(getStatistics()) != null) ? "├──" : "└──";

		traverseNodes(sb, "", pointerLeft, root.getLeftNode(getStatistics()), root.getRightNode(getStatistics()) != null);
		traverseNodes(sb, "", pointerRight, root.getRightNode(getStatistics()), false);

		return sb.toString();
	}

	private void traverseNodes(StringBuilder sb, String padding, String pointer, RBBstTreeItem node,
							   boolean hasRightSibling) {
		if (node != null) {
			sb.append("\n");
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.getValue()).append(node.getColor() == Color.red ? "[R]" : "[B]");

			StringBuilder paddingBuilder = new StringBuilder(padding);
			if (hasRightSibling) {
				paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			String paddingForBoth = paddingBuilder.toString();
			String pointerRight = "└──";
			String pointerLeft = (node.getRightNode(getStatistics()) != null) ? "├──" : "└──";

			traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeftNode(getStatistics()), node.getRightNode(getStatistics()) != null);
			traverseNodes(sb, paddingForBoth, pointerRight, node.getRightNode(getStatistics()), false);
		}
	}

	public void setRoot(RBBstTreeItem root) {
		this.root = root;
	}

	public int countHeight() {
		return countHeightRecursive(root);
	}

	private int countHeightRecursive(RBBstTreeItem root) {
		if (root == null)
			return -1;
		else {
			// compute the depth of each subtree
			int lDepth = countHeightRecursive(root.getLeftNode(getStatistics()));
			int rDepth = countHeightRecursive(root.getRightNode(getStatistics()));

			// use the larger one
			if (compareGreater(lDepth, rDepth))
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}
}
