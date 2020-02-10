package Sequencer;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

public class SequencerManager {

	Sequence sequence = null;
	Track track = null;
	Synthesizer synthesizer = null;
	Sequencer sequencer = null;

	private static int TICK = 480;
	private int position = TICK * 4; // 1小節目は休符

	private String key = "major";

	private boolean useSecondaryDominant = true;
	private double pSecondaryDominant = 0.5;

	public SequencerManager() throws InvalidMidiDataException, MidiUnavailableException {
		sequence = new Sequence(Sequence.PPQ, TICK);
		track = sequence.createTrack();
		synthesizer = MidiSystem.getSynthesizer();
		synthesizer.open();
		sequencer = MidiSystem.getSequencer(false);
		sequencer.open();
		sequencer.setSequence(sequence);
		sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
	}

	public void addNoteToTrack(int instNum, int noteNum, int duration) throws InvalidMidiDataException {
		track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, instNum, noteNum, 127), position));
		track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, instNum, noteNum, 0), position + duration));
		position += duration;
	}

	public void addChordToTrack(int instNum, Chord chord, int duration) throws InvalidMidiDataException {
		for (int i = 0; i < chord.getNotesLength(); i++) {
			track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, instNum, chord.getNote(i), 127), position));
			track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, instNum, chord.getNote(i), 0),
					position + duration));
		}
		position += duration;
	}

	public void play() throws MidiUnavailableException, InvalidMidiDataException {
		sequencer.start();
	}

	public void selectChordProgression() {
		try {

			// コード進行の生成
			//GreenGreensStrategy strategy = new GreenGreensStrategy("C", "C", 8, key);
			SimpleIntroStrategy strategy = new SimpleIntroStrategy("C", "C", 8, key);
			List<InputChordData> chordSequence = strategy.makeSequence(480 * 4);

			// チェレスタ
			// 音色参考 https://mocha-java.com/program-change-sound-bank-1/
			track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 8, 0), TICK));
			for (int i = 0; i < chordSequence.size(); i++) {
				if(i == chordSequence.size()-1) {
					addChordToTrack(0, chordSequence.get(i).getChord(), chordSequence.get(i).getDuration());
				} else {
					addChordToTrack(0, chordSequence.get(i).getChord(), chordSequence.get(i).getDuration()/2);
					addChordToTrack(0, chordSequence.get(i).getChord(), chordSequence.get(i).getDuration()/2);
				}
			}

			// セカンダリードミナントの利用
			if (useSecondaryDominant) {
				makeSecondaryDominantSequence(chordSequence);
			}

			System.out.println();
			// デバッグ用
			for (int i = 0; i < chordSequence.size(); i++) {
				System.out.print(chordSequence.get(i).getChord().getChordName() + ", ");
			}

			// メロディの生成
			SimpleMelodyStrategy mStrategy = new SimpleMelodyStrategy(chordSequence.get(0).getChord(), TICK*4*8);
			List<Melody> melodySequence = mStrategy.makeSequence(TICK);
			// addする前に楽譜の最初に戻す
			resetPosition();

			// マリンバ
			// 音色参考 https://mocha-java.com/program-change-sound-bank-1/
			track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 12, 0), TICK));

			for(int i=0; i<melodySequence.size(); i++) {
				addNoteToTrack(1, melodySequence.get(i).getNote(), melodySequence.get(i).getDuration());
			}

			System.out.println();
			// デバッグ用
			for (int i = 0; i < melodySequence.size(); i++) {
				System.out.print(melodySequence.get(i).getNote() + ", ");
			}

		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

	private void makeSecondaryDominantSequence(List<InputChordData> sequence) {

		if (key.equals("major")) {
			// 置き換え
			for (int i = 0; i < sequence.size() - 1; i++) {
				String currentChordName = sequence.get(i).getChord().getChordName();
				String nextChordName = sequence.get(i + 1).getChord().getChordName();
				if (currentChordName.equals("Am") && nextChordName.equals("Dm")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("A7"), TICK));
					}
				} else if (currentChordName.equals("Bmb5") && nextChordName.equals("Em")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("B7"), TICK));
					}
				} else if (currentChordName.equals("C") && nextChordName.equals("F")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("C7"), TICK));
					}
				} else if (currentChordName.equals("Dm") && nextChordName.equals("G")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("D7"), TICK));
					}
				} else if (currentChordName.equals("Em") && nextChordName.equals("Am")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("E7"), TICK));
					}
				}
			}
		} else if (key.equals("minor")) {
			// 置き換え
			for (int i = 0; i < sequence.size() - 1; i++) {
				String currentChordName = sequence.get(i).getChord().getChordName();
				String nextChordName = sequence.get(i + 1).getChord().getChordName();
				if (currentChordName.equals("F") && nextChordName.equals("Bmb5")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("F7"), TICK));
					}
				} else if (currentChordName.equals("G") && nextChordName.equals("C")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("G7"), TICK));
					}
				} else if (currentChordName.equals("Am") && nextChordName.equals("Dm")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("A7"), TICK));
					}
				} else if (currentChordName.equals("Bmb5") && nextChordName.equals("E")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("B7"), TICK));
					}
				} else if (currentChordName.equals("C") && nextChordName.equals("F")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("C7"), TICK));
					}
				} else if (currentChordName.equals("Dm") && nextChordName.equals("G")) {
					if (Math.random() < pSecondaryDominant) {
						sequence.set(i, new InputChordData(new Chord("D7"), TICK));
					}
				}
			}
		}
	}

	public void resetPosition() {
		 position = TICK * 4;
	}

}
