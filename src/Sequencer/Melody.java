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
		case "LowA":
			note = 57;
			break;
		case "LowB":
			note = 59;
			break;
		case "C":
		case "C7": //
			note = 60;
			break;
		case "D":
		case "Dm": //
		case "Dm7": //
			note = 62;
			break;
		case "E":
		case "Em": //
		case "E7": //
		case "Em7": //
			note = 64;
			break;
		case "F":
		case "F7": //
			note = 65;
			break;
		case "G":
		case "G7": //
			note = 67;
			break;
		case "A":
		case "Am": //
		case "Am7": //
			note = 69;
			break;
		case "B":
		case "Bmb5": //
		case "Bm7b5": //
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
