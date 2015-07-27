public final class ColorConstants {
	static final int Red = 0;
	static final int Green = 1;
	static final int Blue = 2;

	public ColorConstants(int value) {
		this.set(value);
	}

	public void set(int value) {
		if(value >= Red && value <= Blue)
			this.value = value;
	}

	public int get() {
		return value;
	}

	private int value = 0;
}
