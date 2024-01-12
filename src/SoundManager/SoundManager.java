package SoundManager;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class SoundManager {
	private Synthesizer synthesizer;
	private MidiChannel channel;
	private static final int KEYS_PER_OCTAVE = 12;
	public Adjustable octave = new Octave(5),
			volume = new Volume(50);
	
	public SoundManager() {
		startSynthesizer();
	}
	
	private void startSynthesizer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
	
	public void noteOn(int k) {
		channel.controlChange(7, (int)(volume.getValue()*1.27));
		channel.noteOn(KEYS_PER_OCTAVE * octave.getValue() + k, 180);
	}
	
	public void noteOff(int k) {
		channel.noteOff(KEYS_PER_OCTAVE * octave.getValue() + k);
	}
}
