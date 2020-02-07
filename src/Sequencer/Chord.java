package Sequencer;

public class Chord {

	private String chordName = "";
	private int[] notes;

	public enum type {
		major, minor, other, majorSeventh, minorSeventh, otherSeventh
	};

	public Chord(String chordName) {
		this.chordName = chordName;
		notes = new int[3]; // 暫定的に3和音に設定しておく
		setNotesByChordName(chordName);
	}

	public void setNotesByChordName(String chordName) {
		switch (chordName) {
		case "C": // ド ミ ソ
			notes[0] = 60;
			notes[1] = 64;
			notes[2] = 67;
			break;
		case "Dm": // レ ファ ラ
			notes[0] = 62;
			notes[1] = 65;
			notes[2] = 69;
			break;
		case "Em": // ミ ソ シ
			notes[0] = 64;
			notes[1] = 67;
			notes[2] = 71;
			break;
		case "F": // ファ ラ ド
			notes[0] = 65;
			notes[1] = 69;
			notes[2] = 72;
			break;
		case "G": // ソ シ レ
			notes[0] = 67;
			notes[1] = 71;
			notes[2] = 74;
			break;
		case "Am": // ラ ド ミ
			notes[0] = 69;
			notes[1] = 72;
			notes[2] = 76;
			break;
		case "Bmb5": // シ レ ファ
			notes[0] = 71;
			notes[1] = 74;
			notes[2] = 77;
			break;
		// セブンスコード -> 4和音に設定する
		case "C7": // ド ミ ソ シ
			notes = new int[4];
			notes[0] = 60;
			notes[1] = 64;
			notes[2] = 67;
			notes[3] = 71;
			break;
		case "Dm7": // レ ファ ラ ド
			notes = new int[4];
			notes[0] = 62;
			notes[1] = 65;
			notes[2] = 69;
			notes[3] = 72;
			break;
		case "E7": // ミ ソ シ レ
			notes = new int[4];
			notes[0] = 64;
			notes[1] = 68;
			notes[2] = 71;
			notes[3] = 74;
			break;
		case "Em7": // ミ ソ シ レ
			notes = new int[4];
			notes[0] = 64;
			notes[1] = 67;
			notes[2] = 71;
			notes[3] = 74;
			break;
		case "F7": // ファ ラ ド ミ
			notes = new int[4];
			notes[0] = 65;
			notes[1] = 69;
			notes[2] = 72;
			notes[3] = 76;
			break;
		case "G7": // ソ シ レ ファ
			notes = new int[4];
			notes[0] = 67;
			notes[1] = 71;
			notes[2] = 74;
			notes[3] = 77;
			break;
		case "Am7": // ラ ド ミ ソ
			notes = new int[4];
			notes[0] = 69;
			notes[1] = 72;
			notes[2] = 76;
			notes[3] = 79;
			break;
		case "Bm7b5": // シ レ ファ ラ
			notes = new int[4];
			notes[0] = 71;
			notes[1] = 74;
			notes[2] = 77;
			notes[3] = 81;
			break;
		default:
			break;
		}
	}

	public void fixNotesByOctave(int octave) {
		int fixValue = (octave - 4) * 12;
		for (int i = 0; i < notes.length; i++) {
			notes[i] += fixValue;
		}
	}

	public String getChordName() {
		return chordName;
	}

	public int getNote(int index) {
		return notes[index];
	}

	public int getNotesLength() {
		return notes.length;
	}

	public type getChordType() {
		if (notes.length == 3) {
			int bottom = notes[1] - notes[0];
			int head = notes[2] - notes[1];
			if (bottom > head) {
				return type.major;
			} else if (bottom < head) {
				return type.minor;
			} else {
				return type.other;
			}
		} else { // for seventh chord
			int bottom = notes[1] - notes[0];
			int head = notes[2] - notes[1];
			if (bottom > head) {
				return type.majorSeventh;
			} else if (bottom < head) {
				return type.minorSeventh;
			} else {
				return type.otherSeventh;
			}
		}
	}

}
