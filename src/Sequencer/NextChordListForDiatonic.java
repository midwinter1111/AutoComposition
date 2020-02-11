package Sequencer;

public class NextChordListForDiatonic {

	// 長調の場合

	private static String[] nextListMajorKeyForC = {
			"Dm", "Em", "F", "G7", "Am", "Bmb5"
	};
	private static double[] pMajorKeyForC = {
			1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6
	};

	private static String[] nextListMajorKeyForDm = {
			"Em", "F", "G7", "Am", "Bmb5"
	};
	private static double[] pMajorKeyForDm = {
			1 / 5, 1 / 5, 1 / 5, 1 / 5, 1 / 5
	};

	private static String[] nextListMajorKeyForEm = {
			"Dm", "F", "Am"
	};
	private static double[] pMajorKeyForEm = {
			1 / 3, 1 / 3, 1 / 3
	};

	private static String[] nextListMajorKeyForF = {
			"C", "Dm", "Em", "G7", "Bmb5"
	};
	private static double[] pMajorKeyForF = { // Amは避ける
			1 / 5, 1 / 5, 1 / 5, 1 / 5, 1 / 5
	};

	private static String[] nextListMajorKeyForG7 = {
			"C", "Em", "Am"
	};
	private static double[] pMajorKeyForG7 = {
			1 / 3, 1 / 3, 1 / 3
	};

	private static String[] nextListMajorKeyForAm = {
			"Dm", "Em", "F", "G7", "Bmb5"
	};
	private static double[] pMajorKeyForAm = {
			1 / 5, 1 / 5, 1 / 5, 1 / 5, 1 / 5
	};

	private static String[] nextListMajorKeyForBmb5 = {
			"E7" // 転調する
	};
	private static double[] pMajorKeyForBmb5 = {
			1
	};

	// 短調

	private static String[] nextListMinorKeyForAm = {
			"Bmb5", "C", "Dm", "E7", "F", "G"
	};
	private static double[] pMinorKeyForAm = {
			1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6
	};

	private static String[] nextListMinorKeyForBmb5 = {
			"C", "E7"
	};
	private static double[] pMinorKeyForBmb5 = {
			1 / 2, 1 / 2
	};

	private static String[] nextListMinorKeyForC = {
			"Bmb5", "Dm", "F"
	};
	private static double[] pMinorKeyForC = {
			1 / 3, 1 / 3, 1 / 3
	};

	private static String[] nextListMinorKeyForDm = { // Cは避ける
			"Am", "Bmb5", "E7", "F", "G"
	};
	private static double[] pMinorKeyForDm = {
			1 / 5, 1 / 5, 1 / 5, 1 / 5, 1 / 5
	};

	private static String[] nextListMinorKeyForE7 = {
			"Am", "C", "F"
	};
	private static double[] pMinorKeyForE7 = {
			1 / 3, 1 / 3, 1 / 3
	};

	private static String[] nextListMinorKeyForF = {
			"Am", "Bmb5", "C", "Dm", "E7", "G"
	};
	private static double[] pMinorKeyForF = {
			1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6, 1 / 6
	};

	private static String[] nextListMinorKeyForG = {
			"Am", "E7", "C" // Cの場合は転調する
	};
	private static double[] pMinorKeyForG = {
			1 / 3, 1 / 3, 1 / 3
	};

	public NextChordListForDiatonic() {

	}

	public static String[] getNextChordListInMajorKey(String chordName) {

		switch (chordName) {
		case "C":
			return nextListMajorKeyForC;
		case "Dm":
			return nextListMajorKeyForDm;
		case "Em":
			return nextListMajorKeyForEm;
		case "F":
			return nextListMajorKeyForF;
		case "G7":
			return nextListMajorKeyForG7;
		case "Am":
			return nextListMajorKeyForAm;
		case "Bmb5":
			return nextListMajorKeyForBmb5;
		default:
			return null;
		}
	}

	public static double[] getProbabilityInMajorKey(String chordName) {

		switch (chordName) {
		case "C":
			return pMajorKeyForC;
		case "Dm":
			return pMajorKeyForDm;
		case "Em":
			return pMajorKeyForEm;
		case "F":
			return pMajorKeyForF;
		case "G7":
			return pMajorKeyForG7;
		case "Am":
			return pMajorKeyForAm;
		case "Bmb5":
			return pMajorKeyForBmb5;
		default:
			return null;
		}
	}

	public static String[] getNextChordListInMinorKey(String chordName) {

		switch (chordName) {
		case "C":
			return nextListMinorKeyForC;
		case "Dm":
			return nextListMinorKeyForDm;
		case "E7":
			return nextListMinorKeyForE7;
		case "F":
			return nextListMinorKeyForF;
		case "G":
			return nextListMinorKeyForG;
		case "Am":
			return nextListMinorKeyForAm;
		case "Bmb5":
			return nextListMinorKeyForBmb5;
		default:
			return null;
		}
	}

	public static double[] getProbabilityInMinorKey(String chordName) {

		switch (chordName) {
		case "C":
			return pMinorKeyForC;
		case "Dm":
			return pMinorKeyForDm;
		case "E7":
			return pMinorKeyForE7;
		case "F":
			return pMinorKeyForF;
		case "G":
			return pMinorKeyForG;
		case "Am":
			return pMinorKeyForAm;
		case "Bmb5":
			return pMinorKeyForBmb5;
		default:
			return null;
		}
	}

}
