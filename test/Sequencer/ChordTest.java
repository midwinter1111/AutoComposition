package Sequencer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class ChordTest {

	@Test
	void testSetNotesByChordName_C() {
		Chord sampleChord = new Chord("C");
		assertThat(sampleChord.getNote(0), is(60));
		assertThat(sampleChord.getNote(1), is(64));
		assertThat(sampleChord.getNote(2), is(67));
	}

	@Test
	void testSetNotesByChordName_Dm() {
		Chord sampleChord = new Chord("Dm");
		assertThat(sampleChord.getNote(0), is(62));
		assertThat(sampleChord.getNote(1), is(65));
		assertThat(sampleChord.getNote(2), is(69));
	}

	@Test
	void testSetNotesByChordName_Em() {
		Chord sampleChord = new Chord("Em");
		assertThat(sampleChord.getNote(0), is(64));
		assertThat(sampleChord.getNote(1), is(67));
		assertThat(sampleChord.getNote(2), is(71));
	}

	@Test
	void testSetNotesByChordName_F() {
		Chord sampleChord = new Chord("F");
		assertThat(sampleChord.getNote(0), is(65));
		assertThat(sampleChord.getNote(1), is(69));
		assertThat(sampleChord.getNote(2), is(72));
	}

	@Test
	void testSetNotesByChordName_G() {
		Chord sampleChord = new Chord("G");
		assertThat(sampleChord.getNote(0), is(67));
		assertThat(sampleChord.getNote(1), is(71));
		assertThat(sampleChord.getNote(2), is(74));
	}

	@Test
	void testSetNotesByChordName_Am() {
		Chord sampleChord = new Chord("Am");
		assertThat(sampleChord.getNote(0), is(69));
		assertThat(sampleChord.getNote(1), is(72));
		assertThat(sampleChord.getNote(2), is(76));
	}

	@Test
	void testSetNotesByChordName_Bmb5() {
		Chord sampleChord = new Chord("Bmb5");
		assertThat(sampleChord.getNote(0), is(71));
		assertThat(sampleChord.getNote(1), is(74));
		assertThat(sampleChord.getNote(2), is(77));
	}

	@Test
	void testFixNotesByOctave_Medium() {
		Chord sampleChord = new Chord("C");
		sampleChord.fixNotesByOctave(4);
		assertThat(sampleChord.getNote(0), is(60));
		assertThat(sampleChord.getNote(1), is(64));
		assertThat(sampleChord.getNote(2), is(67));
	}

	@Test
	void testFixNotesByOctave_Low() {
		Chord sampleChord = new Chord("C");
		sampleChord.fixNotesByOctave(3);
		assertThat(sampleChord.getNote(0), is(48));
		assertThat(sampleChord.getNote(1), is(52));
		assertThat(sampleChord.getNote(2), is(55));
	}

	@Test
	void testFixNotesByOctave_Hi() {
		Chord sampleChord = new Chord("C");
		sampleChord.fixNotesByOctave(5);
		assertThat(sampleChord.getNote(0), is(72));
		assertThat(sampleChord.getNote(1), is(76));
		assertThat(sampleChord.getNote(2), is(79));
	}

	@Test
	void testGetChordType_MajorChord() {
		Chord sampleChord = new Chord("C");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.major));
	}

	@Test
	void testGetChordType_MinorChord() {
		Chord sampleChord = new Chord("Dm");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.minor));
	}

	@Test
	void testGetChordType_OtherChord() {
		Chord sampleChord = new Chord("Bmb5");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.other));
	}

	@Test
	void testGetChordType_MajorSeventhChord() {
		Chord sampleChord = new Chord("C7");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.majorSeventh));
	}

	@Test
	void testGetChordType_MinorSeventhChord() {
		Chord sampleChord = new Chord("Dm7");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.minorSeventh));
	}

	@Test
	void testGetChordType_OtherSeventhChord() {
		Chord sampleChord = new Chord("Bm7b5");
		assertThat(sampleChord.getChordType(), is(ChordDetail.type.otherSeventh));
	}

}
