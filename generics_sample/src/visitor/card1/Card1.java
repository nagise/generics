package visitor.card1;

import visitor.Node;

/**
 * バージョン1のカードを表現する。
 * 
 * @author nagise
 */
public interface Card1 extends Node<Card1Visitor<?>> {
	/**
	 * Card1Visitorのためのacceptメソッド
	 * 
	 * @param visitorTVI Card1Visitorの実装クラスのType-Variable-Injector
	 * @param param Visitorによって実装される機能の引数
	 * @return Visitorによって実装される機能の戻り値
	 * @param <T> Card1Visitorの実装クラスのType-Variable-Injector の型
	 * @param <V> Card1Visitorの実装クラス
	 * @param <P> Visitorによって実装される機能の引数の型
	 * @param <R> Visitorによって実装される機能の戻り値の型
	 */
	<T extends Card1Visitor<V>.AbstractTVI<P, R, T>,
	 V extends Card1Visitor<? super V>,
	 P,
	 R>
	R accept(T visitorTVI, P param);
}
