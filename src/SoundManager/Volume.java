package SoundManager;

public class Volume extends Adjustable {
	public Volume(int x) {
		super(x);
	}
	@Override
	public void increase() {
		if (value < 100)
			value += 10;
	}
	@Override
	public void decrease() {
		if (value > 0)
			value -= 10;
	}
}
