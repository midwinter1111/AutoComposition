package Sequencer;

public class InputChordData {

	private Chord chord = null;
	private int duration = 0;

	public InputChordData(Chord chord, int duration) {
		this.chord = chord;
		this.duration = duration;
	}

	public Chord getChord() {
		return this.chord;
	}

	public int getDuration() {
		return this.duration;
	}
}
