package Sequencer;

import java.util.ArrayList;
import java.util.List;

public class SimpleMelodyStrategy {

	private Chord startChord;
	private int chordLength;
	private int vital;
	private List<InputChordData> sequence;

	private int bar = 0; //小節

	public SimpleMelodyStrategy(Chord startChord, int length, List<InputChordData> sequence) {
		this.startChord = startChord;
		this.chordLength = length;
		vital = 50;
		this.sequence = sequence;
	}

	public List<Melody> makeSequence(int tick) {
		List<Melody> sequence = new ArrayList<>();
		//sequence.add(new Melody("C", tick));
		String currentNoteName = "C";

		int cnt = 0;
		for (int i = 0; i < 4 * 7; i++) {
			if (cnt == 0) {
				currentNoteName = this.sequence.get(bar).getChord().getChordName();
				sequence.add(new Melody(currentNoteName, tick));
				bar++;
			} else {
				String nextNoteName = determineNextNote(currentNoteName, this.sequence.get(bar).getKey());
				sequence.add(new Melody(nextNoteName, tick));
				currentNoteName = nextNoteName;
			}
			if (cnt == 3) {
				cnt = 0;
			} else {
				cnt++;
			}
		}

		// リズム調整
		changeMelody(sequence);

		// 繰り返し
		//for (int i = 0; i < 4 * 7 - 2; i++) {
		//	sequence.add(new Melody(sequence.get(i).getNoteName(), sequence.get(i).getDuration()));
		//}

		String endNoteName = this.sequence.get(bar).getChord().getChordName();
		sequence.add(new Melody(endNoteName, 480 * 4));

		return sequence;
	}

	public String determineNextNote(String note, String key) {
		String next = "";
		double r;

		switch (note) {
		case "LowA":
			r = Math.random();
			if (r < 0.2) {
				next = "LowA";
			} else if (r < 0.4) {
				next = "C";
			} else {
				next = "LowB";
			}
			break;
		case "LowB":
			r = Math.random();
			if (r < 0.2) {
				next = "LowB";
			} else if (r < 0.4) {
				next = "D";
			} else if (r < 0.7) {
				next = "LowA";
			} else {
				next = "C";
			}
			break;
		case "C":
		case "C7": //
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
		case "Dm": //
		case "Dm7": //
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
		case "Em": //
		case "E7": //
		case "Em7": //
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
		case "F7": //
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
		case "G7": //
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
		case "Am": //
		case "Am7": //
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
		case "Bmb5": //
		case "Bm7b5": //
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

		// 転調判定
		if (key.equals("minor")) {
			switch (next) {
			case "C":
				next = "LowA";
				break;
			case "D":
				next = "LowB";
				break;
			case "E":
				next = "C";
				break;
			case "F":
				next = "D";
				break;
			case "G":
				next = "E";
				break;
			case "A":
				next = "F";
				break;
			case "B":
				next = "G";
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
		}

		return next;
	}

	public void changeMelody(List<Melody> sequence) {
		for (int i = 0; i < sequence.size() - 1; i++) {
			double r = Math.random();
			double p1 = 0.3 + (vital - 50) * 0.01;
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
