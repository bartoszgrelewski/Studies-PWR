package me.bartek.data;

import me.bartek.statistics.AlgorithmStatistics;

import java.awt.*;

public class RBBstTreeItem {
	private Color color;
	private Integer value;
	private RBBstTreeItem parent;
	private RBBstTreeItem leftNode;
	private RBBstTreeItem rightNode;

	public RBBstTreeItem(Color color) {
		this.color = color;
	}

	public RBBstTreeItem(Integer value, Color color) {
		this.value = value;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public RBBstTreeItem getParent() {
		return parent;
	}

	public void setParent(RBBstTreeItem parent) {
		this.parent = parent;
	}

	public void setLeftNode(RBBstTreeItem leftNode) {
		this.leftNode = leftNode;
	}

	public void setRightNode(RBBstTreeItem rightNode) {
		this.rightNode = rightNode;
	}

	public RBBstTreeItem getLeftNode(AlgorithmStatistics statistics) {
		statistics.increasePointerRead();
		return leftNode;
	}

	public RBBstTreeItem getRightNode(AlgorithmStatistics statistics) {
		statistics.increaseKeyComparison();
		return rightNode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
