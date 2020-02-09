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
			SimpleDiatonicStrategy strategy = new SimpleDiatonicStrategy("C", "C", 8, key);
			List<InputChordData> sequence = strategy.makeSequence(960);

			for (int i = 0; i < sequence.size(); i++) {
				addChordToTrack(0, sequence.get(i).getChord(), sequence.get(i).getDuration());
			}

			// セカンダリードミナントの利用
			if (useSecondaryDominant) {
				makeSecondaryDominantSequence(sequence);
			}

			System.out.println();
			// デバッグ用
			for (int i = 0; i < sequence.size(); i++) {
				System.out.print(sequence.get(i).getChord().getChordName() + ", ");
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

}
