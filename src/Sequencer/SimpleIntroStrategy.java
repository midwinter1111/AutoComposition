package Sequencer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleIntroStrategy {

	private String startChordName;
	private String endChordName;
	private int minLength;
	private String key;
	Random r;

	public SimpleIntroStrategy(String startChordName, String endChordName, int minLength, String key) {
		this.startChordName = startChordName;
		this.endChordName = endChordName;
		this.minLength = minLength;
		this.key = key;
		r = new Random();
	}

	public List<InputChordData> makeSequence(int tick) {
		List<InputChordData> sequence = new ArrayList<>();

		Chord start = new Chord(startChordName);
		sequence.add(new InputChordData(start, tick));

		String currentChordName = start.getChordName();

		for (int i = 1; i < 8; i++) {
			Chord currentChord = new Chord(currentChordName);
			String[] nextList;
			if (key.equals("major")) {
				nextList = NextChordListForDiatonic.getNextChordListInMajorKey(currentChord.getChordName());
				String nextChordName = determineNextChord(nextList);
				// 転調判定
				if (currentChordName.equals("Bmb5") && nextChordName.equals("E7")) {
					key = "minor";
				}
				sequence.add(new InputChordData(new Chord(nextChordName), tick));
				currentChordName = nextChordName;
			} else if (key.equals("minor")) {
				nextList = NextChordListForDiatonic.getNextChordListInMinorKey(currentChord.getChordName());
				String nextChordName = determineNextChord(nextList);
				// 転調判定
				if (currentChordName.equals("G") && nextChordName.equals("C")) {
					key = "major";
				}
				sequence.add(new InputChordData(new Chord(nextChordName), tick));
				currentChordName = nextChordName;
			} else {
				// 異常
				return null;
			}
		}

		// 繰り返し
		//for (int i = 0; i < 8; i++) {
		//	sequence.add(new InputChordData(sequence.get(i).getChord(), tick));
		//}

		return sequence;
	}

	public String determineNextChord(String[] nextList) {
		int index = r.nextInt(nextList.length);
		return nextList[index];
	}

}
