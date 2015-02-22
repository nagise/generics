package visitor.card1;

import visitor.Visitor;

/**
 * CardVisitorは以下の4種のカードを取り扱う
 * <li> {@link NumberCard}
 * <li> {@link Jack}
 * <li> {@link Queen}
 * <li> {@link King}
 * 
 * @author nagise
 *
 * @param <V> CardVisitorの実装型自身
 */
public abstract class Card1Visitor<V extends Card1Visitor<? super V>> extends Visitor {

	/**
	 * Type-Variable-Injector (造語)
	 * 
	 * Visitorは扱えるNodeのバージョンによって抽象クラスを作成する。
	 * 
	 * 例えばCard ver1向けのCard1Visitorを拡張してCard ver2向けのCard2Visitorが作られる。
	 * <li>- Visitor
	 * <li>-- Card1Visitor : Card ver1用Visitor 抽象クラス
	 * <li>--- GetNumberCardVisitor : 実装クラス
	 * <li>--- NextCardVisitor : 実装クラス
	 * <li>--- Card2Visitor : Card ver2用Visitor 抽象クラス
	 * <li>---- NextCardVisitor2 : 実装クラス
	 * 
	 * Cardは自身がどのVisitorで取り扱い可能かを型変数で指定する。
	 * Card1 extends Card&lt;Card1Visitor&lt;?&gt;&gt;
	 * 
	 * Visitorは各visit()メソッドにて引数Pと戻り値Rの型変数を用いたいので
	 * クラスVisitorのスコープで型変数P,Rを宣言したい。
	 * 
	 * 一方、Node側ではどのVisitorで扱い可能かを宣言したいが、このときP,Rは指定したくない。
	 * P,Rはメソッドスコープで指定をしたいという矛盾が生じる。
	 * 
	 * これを解決するためにVisitorの型変数のうちP,Rだけを分離して扱いたい。
	 * それを実現するためにエンクロージング内部クラスを用いる。
	 * この型変数を導入するために作成するエンクロージング・内部クラスを指して
	 * Type-Variable-Injector とここでは名付けている。
	 * 
	 * @author nagise
	 *
	 * @param <P> Visitorのメソッドのパラメータ型
	 * @param <R> Visitorのメソッドの戻り値型
	 * @param <T> TVIの実装クラス自身の型
	 */
	public abstract class AbstractTVI<P, R, T extends AbstractTVI<P, R, T>> {
		/** NumberCardに対する処理 */
		public abstract R visit(NumberCard card, P param);
		/** Jackに対する処理 */
		public abstract R visit(Jack card, P param);
		/** Queenに対する処理 */
		public abstract R visit(Queen card, P param);
		/** Kingに対する処理 */
		public abstract R visit(King card, P param);
	}
}
