package visitor.card1;

/**
 * キングを表す
 * @author nagise
 */
public class King implements Card1 {
	/** {@inheritDoc} */
	@Override
	public
		<T extends Card1Visitor<V>.AbstractTVI<P, R, T>,
		 V extends Card1Visitor<? super V>,
		 P,
		 R>
	R accept(T visitorTVI, P param) {
		return visitorTVI.visit(this, param);
	}
	@Override
	public String toString() {
		return "King";
	}
}
