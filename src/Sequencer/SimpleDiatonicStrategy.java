package Sequencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleDiatonicStrategy {

	private String startChordName;
	private String endChordName;
	private int minLength;
	private String key;
	Random r;

	public SimpleDiatonicStrategy(String startChordName, String endChordName, int minLength, String key) {
		this.startChordName = startChordName;
		this.endChordName = endChordName;
		this.minLength = minLength;
		this.key = key;
		r = new Random();
	}

	public List<InputChordData> makeSequence(int tick) {
		List<InputChordData> sequence = new ArrayList<>();

		Chord start = new Chord(startChordName);
		sequence.add(new InputChordData(start, tick, key));

		String currentChordName = start.getChordName();

		do {
			Chord currentChord = new Chord(currentChordName);
			String[] nextList;
			if (key.equals("major")) {
				nextList = NextChordListForDiatonic.getNextChordListInMajorKey(currentChord.getChordName());
				String nextChordName = determineNextChord(nextList);
				// 転調判定
				if(currentChordName.equals("Bmb5") && nextChordName.equals("E7")) {
					key = "minor";
				}
				currentChordName = nextChordName;
			} else if (key.equals("minor")) {
				nextList = NextChordListForDiatonic.getNextChordListInMinorKey(currentChord.getChordName());
				String nextChordName = determineNextChord(nextList);
				// 転調判定
				if(currentChordName.equals("G") && nextChordName.equals("C")) {
					key = "major";
				}
				currentChordName = nextChordName;
			} else {
				// 異常
				return null;
			}

			Chord nextChord = new Chord(currentChordName);
			sequence.add(new InputChordData(nextChord, tick, key));
		} while ((sequence.size() < minLength) || (!currentChordName.equals(endChordName)));

		return sequence;
	}

	public String determineNextChord(String[] nextList) {
		int index = r.nextInt(nextList.length);
		return nextList[index];
	}

}
