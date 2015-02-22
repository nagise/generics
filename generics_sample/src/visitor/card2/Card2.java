package visitor.card2;

import visitor.card1.Card1;
import visitor.card1.Card1Visitor;

/**
 * バージョン2のカードを表現する。
 * バージョン1のカードに加え、Jokerが追加される。
 * 
 * @author nagise
 */
public interface Card2 extends Card1 {
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
	<T extends Card2Visitor<V>.AbstractTVI<P, R, T>,
	 V extends Card2Visitor<? super V>,
	 P,
	 R>
	R accept(T visitorTVI, P param);
	
	/**
	 * FIXME Card2はCard1を継承しているためこのメソッドが存在してしまう。
	 * Card2はCard1に含まれないのでis-a関係にないことから
	 * 継承関係としないことが望ましい。
	 * 
	 * しかし、Card1-Card2を継承関係にしなかった場合、
	 * NextCardVisitor2のようにCardを返すVisitorの実装で困る。
	 * 数の多いCard2側を親として、Card1が子となるように継承すれば
	 * Card1, Card2の集合をうまく表せるのだが後付で親を足すわけにもいかず…。
	 */
	@Override
	default <T extends Card1Visitor<V>.AbstractTVI<P, R, T>,
	 V extends Card1Visitor<? super V>, P, R>
		R accept(T visitorTVI, P param) {
		throw new IllegalArgumentException();
	}
}
