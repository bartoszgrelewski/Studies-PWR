package me.bartek;

import me.bartek.algorithms.BST;
import me.bartek.algorithms.RBBst;
import me.bartek.algorithms.SplayTree;
import me.bartek.data.RBBstTreeItem;
import me.bartek.generators.RandomTabGenerator;

public class Run {
	public static void main(String[] args) {
		if (args.length == 0) {
			ex1();
			ex3();
			ex5();
		} else {
			switch (args[0]) {
				case "1" -> ex1();
				case "3" -> ex3();
				case "5" -> ex5();
				default -> System.out.println("Choose between 1 / 3 / 5!");
			}
		}
	}

	public static void ex1() {
		BST bst = new BST();

		bst.insertRandomElements(50);
		bst.deleteRandomElements(50);

		bst.setRoot(null);

		System.out.println("-------------------------------------");
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		int[] ascendingArray = tabGenerator.generateAscendingArray(50);
		bst.insertFromArray(ascendingArray);
		bst.deleteRandomElements(50);

		System.out.println(bst.countHeight());
	}

	public static void ex3() {
		RBBst rbBst = new RBBst();
		rbBst.insertRandomElements(50);
		rbBst.deleteRandomElements(50);

		rbBst.setRoot((RBBstTreeItem) null);

		System.out.println("-------------------------------------");
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		int[] ascendingArray = tabGenerator.generateAscendingArray(50);
		rbBst.insertFromArray(ascendingArray);
		rbBst.deleteRandomElements(50);

		System.out.println(rbBst.countHeight());
	}

	public static void ex5() {
		SplayTree splayTree = new SplayTree();
		splayTree.insertRandomElements(50);
		splayTree.deleteRandomElements(50);

		splayTree.setRoot(null);

		System.out.println("-------------------------------------");
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		int[] ascendingArray = tabGenerator.generateAscendingArray(50);
		splayTree.insertFromArray(ascendingArray);
		splayTree.deleteRandomElements(50);

		System.out.println(splayTree.countHeight());
	}
}
