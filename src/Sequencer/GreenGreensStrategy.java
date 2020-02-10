package Sequencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GreenGreensStrategy {

	private String startChordName;
	private String endChordName;
	private int minLength;
	private String key;
	Random r;

	public GreenGreensStrategy(String startChordName, String endChordName, int minLength, String key) {
		this.startChordName = startChordName;
		this.endChordName = endChordName;
		this.minLength = minLength;
		this.key = key;
		r = new Random();
	}

	public List<InputChordData> makeSequence(int tick) {
		List<InputChordData> sequence = new ArrayList<>();

		Chord start = new Chord(startChordName);
		addStandardRhythm(start, sequence);
		addStandardRhythm(start, sequence);
		Chord chord2 = moveToNextChord(start);
		addShortRhythm(chord2, sequence);
		Chord chord3 = moveToNextChord(chord2);
		addShortRhythm(chord3, sequence);
		Chord chord4 = moveToNextChord(chord3);
		addStandardRhythm(chord4, sequence);

		addStandardRhythm(start, sequence);
		addStandardRhythm(start, sequence);
		addShortRhythm(chord2, sequence);
		addShortRhythm(chord3, sequence);
		chord4 = moveToNextChord(chord3);
		addShortRhythm(chord4, sequence);
		Chord chord5 = moveToNextChord(chord4);
		addShortRhythm(chord5, sequence);

		addFinalRhythm(start, sequence);

		return sequence;
	}

	public void addStandardRhythm(Chord currentChord, List<InputChordData> sequence) {
		// u ttttttt
		String nextChordName = "";
		Chord nextChord = null;
		switch (currentChord.getChordName()) {
		case "C":
			nextChord = new Chord("G7");
			break;
		case "Dm":
			nextChord = new Chord("Am");
			break;
		case "Em":
			nextChord = new Chord("Bmb5");
			break;
		case "F":
			nextChord = new Chord("C");
			nextChord.fixNotesByOctave(5);
			break;
		case "G7":
			nextChord = new Chord("Dm");
			nextChord.fixNotesByOctave(5);
			break;
		case "Am":
			nextChord = new Chord("Em");
			nextChord.fixNotesByOctave(5);
			break;
		case "Bmb5":
			nextChord = new Chord("F");
			nextChord.fixNotesByOctave(5);
			break;
		default:
			break;
		}

		sequence.add(new InputChordData(currentChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
	}

	public void addShortRhythm(Chord currentChord, List<InputChordData> sequence) {
		// u ttt
		String nextChordName = "";
		Chord nextChord = null;
		switch (currentChord.getChordName()) {
		case "C":
			nextChord = new Chord("G");
			break;
		case "Dm":
			nextChord = new Chord("Am");
			break;
		case "Em":
			nextChord = new Chord("Bmb5");
			break;
		case "E7":
			nextChord = new Chord("Bmb5");
			break;
		case "F":
			nextChord = new Chord("C");
			nextChord.fixNotesByOctave(5);
			break;
		case "G":
			nextChord = new Chord("Dm");
			nextChord.fixNotesByOctave(5);
			break;
		case "G7":
			nextChord = new Chord("Dm");
			nextChord.fixNotesByOctave(5);
			break;
		case "Am":
			nextChord = new Chord("Em");
			nextChord.fixNotesByOctave(5);
			break;
		case "Bmb5":
			nextChord = new Chord("F");
			nextChord.fixNotesByOctave(5);
			break;
		default:
			break;
		}

		sequence.add(new InputChordData(currentChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
		sequence.add(new InputChordData(nextChord, 240, key));
	}

	public void addFinalRhythm(Chord currentChord, List<InputChordData> sequence) {
		// u ttt
		String nextChordName = "";
		Chord nextChord = null;
		switch (currentChord.getChordName()) {
		case "C":
			nextChord = new Chord("G");
			break;
		case "Dm":
			nextChord = new Chord("Am");
			break;
		case "Em":
			nextChord = new Chord("Bmb5");
			break;
		case "E7":
			nextChord = new Chord("Bmb5");
			break;
		case "F":
			nextChord = new Chord("C");
			nextChord.fixNotesByOctave(5);
			break;
		case "G":
			nextChord = new Chord("Dm");
			nextChord.fixNotesByOctave(5);
			break;
		case "G7":
			nextChord = new Chord("Dm");
			nextChord.fixNotesByOctave(5);
			break;
		case "Am":
			nextChord = new Chord("Em");
			nextChord.fixNotesByOctave(5);
			break;
		case "Bmb5":
			nextChord = new Chord("F");
			nextChord.fixNotesByOctave(5);
			break;
		default:
			break;
		}

		sequence.add(new InputChordData(currentChord, 960, key));
		sequence.add(new InputChordData(nextChord, 960, key));
	}

	public Chord moveToNextChord(Chord currentChord) {
		String[] nextList;
		String currentChordName = currentChord.getChordName();

		if (key.equals("major")) {
			nextList = NextChordListForDiatonic.getNextChordListInMajorKey(currentChord.getChordName());
			String nextChordName = determineNextChord(nextList);
			// 転調判定
			if (currentChordName.equals("Bmb5") && nextChordName.equals("E7")) {
				key = "minor";
			}
			currentChordName = nextChordName;
		} else if (key.equals("minor")) {
			nextList = NextChordListForDiatonic.getNextChordListInMinorKey(currentChord.getChordName());
			String nextChordName = determineNextChord(nextList);
			// 転調判定
			if (currentChordName.equals("G") && nextChordName.equals("C")) {
				key = "major";
			}
			currentChordName = nextChordName;
		} else {
			// 異常
			System.out.println("");
			return null;
		}

		Chord nextChord = new Chord(currentChordName);

		return nextChord;
	}

	public String determineNextChord(String[] nextList) {
		int index = r.nextInt(nextList.length);
		return nextList[index];
	}

}
