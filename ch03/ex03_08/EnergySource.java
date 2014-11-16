package ex03_08;

public abstract class EnergySource {

	public abstract boolean empty();
	public abstract int getCharge();

	// 抽象クラスでクローンを生成できるかはサブクラスの実装依存とする
	public EnergySource clone() throws CloneNotSupportedException {
		return (EnergySource)super.clone();
	}
}
