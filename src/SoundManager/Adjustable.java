package SoundManager;

public abstract class Adjustable {
	int value;
	public void increase() {
		value += 1;
	};
	public void decrease() {
		value -= 1;
	};
	public int getValue() {
		return value;
	};
	public Adjustable(int x) {
		value = x;
	}
}
