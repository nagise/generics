package visitor.card1;

/**
 * 1-10までの数字のカードを表す
 * @author nagise
 */
public class NumberCard implements Card1 {
	/** カードの数字 */
	public int number;
	/**
	 * @param number カードの数字
	 */
	public NumberCard(int number) {
		if (number < 1 || 10 < number) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

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
		return "#"+number;
	}
}
