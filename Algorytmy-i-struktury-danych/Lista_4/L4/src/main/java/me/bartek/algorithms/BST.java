package me.bartek.algorithms;

import me.bartek.data.BstTreeItem;
import me.bartek.generators.RandomTabGenerator;

public class BST extends Tree {

	private BstTreeItem root;

	public BST() {
		this.root = null;
	}

	@Override
	public void insert(int k) {
		System.out.println("insert " + k);
		root = insertRecursive(root, k);
		printTree();
	}

	@Override
	public void delete(int k) {
		System.out.println("delete " + k);
		if (search(k) == null) {
			System.out.println("klucza " + k + " nie ma w drzewie, wiec nie mozemy go usunac ");
			return;
		}
		deleteRecursive(root, k);
		printTree();
	}

	public void insertRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		insertFromArray(tabGenerator.generate(numOfElements));
	}

	public void insertFromArray(int[] tab) {
		for (int i : tab)
			insert(i);
	}

	private BstTreeItem insertRecursive(BstTreeItem root, int k) {
		if (root != null) {
			if (compareGreater(root.getValue(), k)) {
				root.setLeftNode(insertRecursive(root.getLeftNode(getStatistics()), k));
			} else {
				root.setRightNode(insertRecursive(root.getRightNode(getStatistics()), k));
			}
		} else {
			root = new BstTreeItem(k);
		}
		return root;
	}

	public void deleteRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		deleteFromArray(tabGenerator.generate(numOfElements));
	}

	public void deleteFromArray(int[] tab) {
		for (int i : tab)
			delete(i);
	}

	private BstTreeItem deleteRecursive(BstTreeItem root, int k) {
		if (root == null)
			return null;

		if (compareGreater(root.getValue(), k)) {
			root.setLeftNode(deleteRecursive(root.getLeftNode(getStatistics()), k));
		} else if (compareLess(root.getValue(), k)) {
			root.setRightNode(deleteRecursive(root.getRightNode(getStatistics()), k));
		} else {
			//tree contains one or 0 children
			if (root.getLeftNode(getStatistics()) == null)
				return root.getRightNode(getStatistics());
			else if (root.getRightNode(getStatistics()) == null)
				return root.getLeftNode(getStatistics());

			root.setValue(findMinValue(root.getRightNode(getStatistics())));

			//delete successor
			root.setRightNode(deleteRecursive(root.getRightNode(getStatistics()), root.getValue()));
		}

		return root;
	}

	private int findMinValue(BstTreeItem root) {
		int minValue = root.getValue();
		while (root.getLeftNode(getStatistics()) != null) {
			minValue = root.getLeftNode(getStatistics()).getValue();
			root = root.getLeftNode(getStatistics());
		}
		return minValue;
	}

	public int countHeight() {
		return countHeightRecursive(root);
	}

	private int countHeightRecursive(BstTreeItem root) {
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

	public BstTreeItem search(int k) {
		return searchRecursive(root, k);
	}

	private BstTreeItem searchRecursive(BstTreeItem root, int k) {
		if (root == null)
			return null;

		if (root.getValue() == k)
			return root;
		else if (compareGreater(root.getValue(), k)) {
			return searchRecursive(root.getLeftNode(getStatistics()), k);
		} else {
			return searchRecursive(root.getRightNode(getStatistics()), k);
		}
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

	public BstTreeItem getRoot() {
		return root;
	}

	public void setRoot(BstTreeItem root) {
		this.root = root;
	}
}
