package me.bartek.algorithms;

import me.bartek.data.BstTreeItem;
import me.bartek.data.RBBstTreeItem;
import me.bartek.generators.RandomTabGenerator;

public class SplayTree extends BST {
	public BstTreeItem root;

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
			deleteNode1(i);
	}

	public BstTreeItem newNode1(int key) {
		BstTreeItem bstTreeItem = new BstTreeItem();
		bstTreeItem.setValue(key);
		return bstTreeItem;
	}

	public BstTreeItem rightRotate(BstTreeItem x) {
		BstTreeItem y = x.getLeftNode(getStatistics());
		x.setLeftNode(y.getRightNode(getStatistics()));
		y.setRightNode(x);
		return y;
	}

	public BstTreeItem leftRotate(BstTreeItem x) {
		BstTreeItem y = x.getRightNode(getStatistics());
		x.setRightNode(y.getLeftNode(getStatistics()));
		y.setLeftNode(x);
		return y;
	}

	public BstTreeItem splay(BstTreeItem root, int key) {
		if (root == null || compareEqual(root.getValue(), key))
			return root;

		if (compareGreater(root.getValue(), key)) {
			if (root.getLeftNode(getStatistics()) == null) return root;

			if (compareGreater(root.getLeftNode(getStatistics()).getValue(), key)) {

				root.getLeftNode(getStatistics()).setLeftNode(splay(root.getLeftNode(getStatistics()).getLeftNode(getStatistics()), key));

				root = rightRotate(root);
			} else if (compareLess(root.getLeftNode(getStatistics()).getValue(), key)) {
				root.getLeftNode(getStatistics()).setRightNode(splay(root.getLeftNode(getStatistics()).getRightNode(getStatistics()), key));

				if (root.getLeftNode(getStatistics()).getRightNode(getStatistics()) != null)
					root.setLeftNode(leftRotate(root.getLeftNode(getStatistics())));
			}

			return (root.getLeftNode(getStatistics()) == null) ?
					root : rightRotate(root);
		} else {
			if (root.getRightNode(getStatistics()) == null) return root;

			if (compareGreater(root.getRightNode(getStatistics()).getValue(), key)) {
				root.getRightNode(getStatistics()).setLeftNode(splay(root.getRightNode(getStatistics()).getLeftNode(getStatistics()), key));

				if (root.getRightNode(getStatistics()).getLeftNode(getStatistics()) != null)
					root.setRightNode(rightRotate(root.getRightNode(getStatistics())));
			} else if (compareLess(root.getRightNode(getStatistics()).getValue(), key)) {
				root.getRightNode(getStatistics()).setRightNode(splay(root.getRightNode(getStatistics()).getRightNode(getStatistics()), key));
				root = leftRotate(root);
			}

			return (root.getRightNode(getStatistics()) == null) ?
					root : leftRotate(root);
		}
	}

	public void insert(int k) {
		System.out.println("insert " + k);
		root = insertRecursive(k);
		printTree();
	}

	public BstTreeItem insertRecursive(int k) {
		if (root == null)
			return newNode1(k);

		root = splay(root, k);

		if (compareEqual(root.getValue(), k)) return root;
		BstTreeItem newNode1 = newNode1(k);

		if (compareGreater(root.getValue(), k)) {
			newNode1.setRightNode(root);
			newNode1.setLeftNode(root.getLeftNode(getStatistics()));
			root.setLeftNode(null);
		} else {
			newNode1.setLeftNode(root);
			newNode1.setRightNode(root.getRightNode(getStatistics()));
			root.setRightNode(null);
		}

		return newNode1;
	}

	public BstTreeItem search(BstTreeItem root, int key) {
		return splay(root, key);
	}

	public void deleteNode1(int key) {
		if (search(root, key) != null) {
			System.out.println("Delete: " + key);
		} else {
			System.out.println("Klucza " + key + " nie ma w drzewie!");
			return;
		}

		BstTreeItem temp;
		if (root == null) {
			return;
		}

		root = splay(root, key);

		if (!compareEqual(key, root.getValue())) {
			return;
		}

		if (root.getLeftNode(getStatistics()) == null) {
			root = root.getRightNode(getStatistics());
		} else {
			temp = root;

			root = splay(root.getLeftNode(getStatistics()), key);

			root.setRightNode(temp.getRightNode(getStatistics()));
		}
		printTree();
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

	@Override
	public void setRoot(BstTreeItem root) {
		this.root = root;
	}

	public void printTree() {
		System.out.println(traversePreOrder(root));
	}

	private String traversePreOrder(BstTreeItem root) {

		if (root == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(root.getValue());

		String pointerRight = "└──";
		String pointerLeft = (root.getRightNode(getStatistics()) != null) ? "├──" : "└──";

		traverseNodes(sb, "", pointerLeft, root.getLeftNode(getStatistics()), root.getRightNode(getStatistics()) != null);
		traverseNodes(sb, "", pointerRight, root.getRightNode(getStatistics()), false);

		return sb.toString();
	}

	private void traverseNodes(StringBuilder sb, String padding, String pointer, BstTreeItem node,
							   boolean hasRightSibling) {
		if (node != null) {
			sb.append("\n");
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.getValue());

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
}
