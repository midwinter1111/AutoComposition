package Sequencer;

public class Melody {

	private int note;
	private String noteName;
	private int duration;

	public Melody(String noteName, int duration) {
		this.note = setMajorNoteByNoteName(noteName);
		this.noteName = noteName;
		this.duration = duration;
	}

	public int setMajorNoteByNoteName(String noteName) {
		int note;
		switch (noteName) {
		case "C":
			note = 60;
			break;
		case "D":
			note = 62;
			break;
		case "E":
			note = 64;
			break;
		case "F":
			note = 65;
			break;
		case "G":
			note = 67;
			break;
		case "A":
			note = 69;
			break;
		case "B":
			note = 71;
			break;
		case "HiC":
			note = 72;
			break;
		default:
			note = -1;
			break;
		}
		return note;
	}

	public int getNote() {
		return note;
	}

	public String getNoteName() {
		return noteName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
