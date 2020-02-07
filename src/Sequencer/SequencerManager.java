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
	private int position = 480 * 4; // 1小節目は休符

	public SequencerManager() throws InvalidMidiDataException, MidiUnavailableException {
		sequence = new Sequence(Sequence.PPQ, 480);
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

			SimpleDiatonicStrategy strategy = new SimpleDiatonicStrategy("C", "C", 4, "major");
			List<InputChordData> sequence = strategy.makeSequence();

			for(int i=0; i<sequence.size(); i++) {
				addChordToTrack(0, sequence.get(i).getChord(), sequence.get(i).getDuration());
				System.out.print(sequence.get(i).getChord().getChordName() + ", ");
			}

			/*
			Chord chord1 = new Chord("C");
			Chord chord2 = new Chord("Dm");
			Chord chord3 = new Chord("Em");
			Chord chord4 = new Chord("F");
			Chord chord5 = new Chord("G");
			Chord chord6 = new Chord("Am");
			Chord chord7 = new Chord("Bmb5");
			Chord chord8 = new Chord("C7");
			Chord chord9 = new Chord("Dm7");
			Chord chord10 = new Chord("Em7");
			Chord chord11 = new Chord("F7");
			Chord chord12 = new Chord("G7");
			Chord chord13 = new Chord("Am7");
			Chord chord14 = new Chord("Bm7b5");
			addChordToTrack(0, chord1, 480);
			addChordToTrack(0, chord2, 480);
			addChordToTrack(0, chord3, 480);
			addChordToTrack(0, chord4, 480);
			addChordToTrack(0, chord5, 480);
			addChordToTrack(0, chord6, 480);
			addChordToTrack(0, chord7, 480);
			addChordToTrack(0, chord8, 480);
			addChordToTrack(0, chord9, 480);
			addChordToTrack(0, chord10, 480);
			addChordToTrack(0, chord11, 480);
			addChordToTrack(0, chord12, 480);
			addChordToTrack(0, chord13, 480);
			addChordToTrack(0, chord14, 480);*/
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}

	}

}
