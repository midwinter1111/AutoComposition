package Sequencer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ・汗みどろで恋人とは会わない（冒険→恋愛のコード進行は使わない）
 * ・親分が疲れたら子分が助っ人に（子分は親分の代わりに使うことができる）
 * ・子分の面子はつぶさない（単独なら親分でも子分でも良いが、連続する際は子分の後に親分が続いてはいけない）
 * ・子分同士の順列は4度上行が基本（Em→Amの順で使うとポジティブな進行）
 * ・本当は4人が正式（○7の方が、三和音より都会的）
 * ・6番目は道化役者（CとFに関しては6度の音を足すとおちゃらけキャラになる）
 * ・焦らして焦らして終わらせない（G7の後にCを置かず、トニック子分を使うと曲にもう一波乱生まれる）
 * ・どのコードも専属の冒険コードを従える（どのコードもトニック（充電コード）と見立てると
 *   それに進みたくなるドミナント（冒険コード）がある→セカンダリードミナント）
 * ・泣かせるならセカンダリードミナント（セカンダリードミナントは「お涙頂戴」のお決まりのセリフ）
 *
 * @author winter
 *
 */
public class FunctionableStrategy {

	private String startChordName;
	private String endChordName;
	private int minLength;
	private String key;

	public FunctionableStrategy(String startChordName, String endChordName, int minLength, String key) {
		this.startChordName = startChordName;
		this.endChordName = endChordName;
		this.minLength = minLength;
		this.key = key;
	}

	public List<InputChordData> makeSequence() {
		List<InputChordData> sequence = new ArrayList<>();

		return sequence;
	}

}
