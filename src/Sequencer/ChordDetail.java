package Sequencer;

public class ChordDetail {

	public static enum type {
		major, minor, other, majorSeventh, minorSeventh, otherSeventh
	};

	public static enum role {
		tonic, dominant, subDominant, tonicAndSubDominant
	};

	public static enum family {
		parent, child
	}

	public static enum function {
		charge, love, adventure
	}
}
