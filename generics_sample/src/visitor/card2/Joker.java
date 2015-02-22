package visitor.card2;

/**
 * ジョーカーを表すクラス
 * @author nagise
 */
public class Joker implements Card2 {
	/** {@inheritDoc} */
	@Override
	public
	<T extends Card2Visitor<V>.AbstractTVI<P, R, T>,
	 V extends Card2Visitor<? super V>,
	 P,
	 R>
	R accept(T visitorTVI, P param) {
		return visitorTVI.visit(this, param);
	}
	@Override
	public String toString() {
		return "Joker";
	}
}
