package visitor;

/**
 * Visitorによって扱われるノードを表す。
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
 * Cardの実装型はこれらのVisitorの継承階層のうち、どのVisitorで扱われるかを型変数で指定する。
 * 
 * @author nagise
 *
 * @param <V> このノードを取り扱えるVisitor.
 */
public interface Node<V extends Visitor> {

}
