package SoundManager;

public class Octave extends Adjustable {
	public Octave(int x) {
		super(x);
	}
	@Override
	public void increase() {
		if (value < 8)
			value++;
	}
	@Override
	public void decrease() {
		if (value > 1)
			value--;
	}
	@Override
	public void setValue(int value) {
		this.value=value;
	}
}
