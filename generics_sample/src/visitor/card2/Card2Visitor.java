package visitor.card2;

import visitor.card1.Card1Visitor;
import visitor.card1.Jack;
import visitor.card1.King;
import visitor.card1.NumberCard;
import visitor.card1.Queen;

/**
 * Card2Visitorは以下の5種のカードを取り扱う
 * 
 * <li> {@link NumberCard}
 * <li> {@link Jack}
 * <li> {@link Queen}
 * <li> {@link King}
 * <li> {@link Joker}
 * 
 * @author nagise
 *
 * @param <V> CardVisitorの実装型
 */
public abstract class Card2Visitor<V extends Card2Visitor<? super V>> extends Card1Visitor<V> {

	/**
	 * Type-Variable-Injector.
	 * 
	 * 詳細は {@link Card1Visitor.AbstractTVI} を参照。
	 * 
	 * @author nagise
	 *
	 * @param <P> Visitorのメソッドのパラメータ型
	 * @param <R> Visitorのメソッドの戻り値型
	 * @param <T> TVIの実装クラス自身の型
	 */
	public abstract class AbstractTVI<P, R, T extends AbstractTVI<P, R, T>>
		extends Card1Visitor<V>.AbstractTVI<P, R, T>{

		/** NumberCardに対する処理 */
		public abstract R visit(NumberCard card, P param);
		/** Jackに対する処理 */
		public abstract R visit(Jack card, P param);
		/** Queenに対する処理 */
		public abstract R visit(Queen card, P param);
		/** Kingに対する処理 */
		public abstract R visit(King card, P param);
		/** Jokerに対する処理 */
		public abstract R visit(Joker card, P param);
	}
}
