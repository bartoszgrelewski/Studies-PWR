package me.bartek.statistics;

public class AlgorithmStatistics {
	private Integer keyComparison = 0;
	private Integer pointerRead = 0;
	private Integer height = 0;

	public AlgorithmStatistics() {
	}

	public AlgorithmStatistics(Integer keyComparison, Integer pointerRead) {
		this.keyComparison = keyComparison;
		this.pointerRead = pointerRead;
	}

	public Integer getKeyComparison() {
		return keyComparison;
	}

	public Integer getPointerRead() {
		return pointerRead;
	}

	public void setKeyComparison(Integer keyComparison) {
		this.keyComparison = keyComparison;
	}

	public void setPointerRead(Integer pointerRead) {
		this.pointerRead = pointerRead;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void increaseKeyComparison() {
		keyComparison++;
	}

	public void increasePointerRead() {
		pointerRead++;
	}

	public void reset() {
		keyComparison = 0;
		pointerRead = 0;
	}

	@Override
	public String toString() {
		return "AlgorithmStatistics{" +
				"keyComparison=" + keyComparison +
				", pointersRead=" + pointerRead +
				", height=" + height +
				'}';
	}
}
