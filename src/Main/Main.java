package Main;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import Sequencer.SequencerManager;

public class Main {

	public static void main(String[] args) {
		try {
			SequencerManager manager = new SequencerManager();
			manager.selectChordProgression();
			manager.play();
			manager.outMIDI();
		} catch (InvalidMidiDataException | MidiUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

}
