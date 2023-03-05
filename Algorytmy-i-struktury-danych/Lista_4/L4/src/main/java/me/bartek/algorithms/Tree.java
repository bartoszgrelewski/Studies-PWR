package me.bartek.algorithms;

import me.bartek.statistics.AlgorithmStatistics;

public abstract class Tree {
	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	public abstract void insert(int k);

	public abstract void delete(int k);

	public AlgorithmStatistics getStatistics() {
		return statistics;
	}

	public void resetStatistics() {
		statistics.reset();
	}

	public boolean compareLessEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i <= j;
	}

	public boolean compareGreaterEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i >= j;
	}

	public boolean compareLess(int i, int j) {
		statistics.increaseKeyComparison();
		return i < j;
	}

	public boolean compareGreater(int i, int j) {
		statistics.increaseKeyComparison();
		return i > j;
	}

	public boolean compareEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i == j;
	}
}
