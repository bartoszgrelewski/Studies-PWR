package me.bartek.data;

import me.bartek.statistics.AlgorithmStatistics;

public class BstTreeItem {

	private Integer value;
	private BstTreeItem leftNode;
	private BstTreeItem rightNode;

	public BstTreeItem() {
	}

	public BstTreeItem(Integer value) {
		this.value = value;
		leftNode = null;
		rightNode = null;
	}

	public BstTreeItem(Integer value, BstTreeItem leftNode, BstTreeItem rightNode) {
		this.value = value;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public BstTreeItem getLeftNode(AlgorithmStatistics statistics) {
		statistics.increasePointerRead();
		return leftNode;
	}

	public void setLeftNode(BstTreeItem leftNode) {
		this.leftNode = leftNode;
	}

	public BstTreeItem getRightNode(AlgorithmStatistics statistics) {
		statistics.increasePointerRead();
		return rightNode;
	}

	public void setRightNode(BstTreeItem rightNode) {
		this.rightNode = rightNode;
	}
}
