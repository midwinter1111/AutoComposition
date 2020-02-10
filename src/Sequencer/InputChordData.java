package Sequencer;

public class InputChordData {

	private Chord chord = null;
	private int duration = 0;
	private String key;

	public InputChordData(Chord chord, int duration, String key) {
		this.chord = chord;
		this.duration = duration;
		this.key = key;
	}

	public Chord getChord() {
		return this.chord;
	}

	public int getDuration() {
		return this.duration;
	}

	public String getKey() {
		return this.key;
	}
}
