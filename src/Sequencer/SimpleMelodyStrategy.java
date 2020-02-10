package Sequencer;

import java.util.ArrayList;
import java.util.List;

public class SimpleMelodyStrategy {

	private Chord startChord;
	private int chordLength;
	private int vital;

	private int bar = 0;

	public SimpleMelodyStrategy(Chord startChord, int length) {
		this.startChord = startChord;
		this.chordLength = length;
		vital = 50;
	}

	public List<Melody> makeSequence(int tick) {
		List<Melody> sequence = new ArrayList<>();
		sequence.add(new Melody("C", tick));
		String currentNoteName = "C";

		for (int i = 0; i < chordLength-480*4; i += tick) {
			String nextNoteName = determineNextNote(currentNoteName);
			sequence.add(new Melody(nextNoteName, tick));
			currentNoteName = nextNoteName;
		}

		// リズム調整
		changeMelody(sequence);

		// 繰り返し
		//for (int i = 0; i < 4 * 7 - 2; i++) {
		//	sequence.add(new Melody(sequence.get(i).getNoteName(), sequence.get(i).getDuration()));
		//}

		//String endNoteName = determineNextNote(currentNoteName);
		//sequence.add(new Melody(endNoteName, 480 * 4));

		return sequence;
	}

	public String determineNextNote(String note) {
		String next = "";
		double r;

		switch (note) {
		case "C":
			r = Math.random();
			if (r < 0.2) {
				next = "C";
			} else if (r < 0.4) {
				next = "E";
			} else {
				next = "D";
			}
			break;
		case "D":
			r = Math.random();
			if (r < 0.2) {
				next = "D";
			} else if (r < 0.4) {
				next = "F";
			} else if (r < 0.7) {
				next = "C";
			} else {
				next = "E";
			}
			break;
		case "E":
			r = Math.random();
			if (r < 0.2) {
				next = "E";
			} else if (r < 0.3) {
				next = "C";
			} else if (r < 0.4) {
				next = "G";
			} else if (r < 0.7) {
				next = "D";
			} else {
				next = "F";
			}
			break;
		case "F":
			r = Math.random();
			if (r < 0.2) {
				next = "F";
			} else if (r < 0.3) {
				next = "D";
			} else if (r < 0.4) {
				next = "A";
			} else if (r < 0.7) {
				next = "E";
			} else {
				next = "G";
			}
			break;
		case "G":
			r = Math.random();
			if (r < 0.2) {
				next = "G";
			} else if (r < 0.3) {
				next = "E";
			} else if (r < 0.4) {
				next = "B";
			} else if (r < 0.7) {
				next = "F";
			} else {
				next = "A";
			}
			break;
		case "A":
			r = Math.random();
			if (r < 0.2) {
				next = "A";
			} else if (r < 0.3) {
				next = "F";
			} else if (r < 0.4) {
				next = "HiC";
			} else if (r < 0.7) {
				next = "G";
			} else {
				next = "B";
			}
			break;
		case "B":
			r = Math.random();
			if (r < 0.2) {
				next = "B";
			} else if (r < 0.4) {
				next = "G";
			} else if (r < 0.7) {
				next = "A";
			} else {
				next = "HiC";
			}
			break;
		case "HiC":
			r = Math.random();
			if (r < 0.2) {
				next = "HiC";
			} else if (r < 0.4) {
				next = "A";
			} else {
				next = "B";
			}
			break;
		default:
			break;
		}

		return next;
	}

	public void changeMelody(List<Melody> sequence) {
		for (int i = 0; i < sequence.size() - 1; i++) {
			double r = Math.random();
			double p1 = 0.2 + (vital - 50) * 0.01;
			double p2 = 0.4 + (50 - vital) * 0.01;
			if (r < p1) {
				sequence.set(i, new Melody(sequence.get(i).getNoteName(), sequence.get(i).getDuration() + 240));
				sequence.set(i + 1,
						new Melody(sequence.get(i + 1).getNoteName(), sequence.get(i + 1).getDuration() - 240));
				vital += 5;
			} else if (r < p2) {
				sequence.set(i, new Melody(sequence.get(i).getNoteName(), sequence.get(i).getDuration() - 240));
				sequence.set(i + 1,
						new Melody(sequence.get(i + 1).getNoteName(), sequence.get(i + 1).getDuration() + 240));
				vital -= 5;
			}
		}
	}

}
