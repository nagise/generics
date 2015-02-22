package visitor;

/**
 * Expression Problem に対するJavaでの対応方法の検討。
 * http://d.hatena.ne.jp/Nagise/20120426/1335451716
 * 
 * 本サンプルではトランプのカードに見立てた4つのクラス
 * <li> {@link visitor.card1.NumberCard}
 * <li> {@link visitor.card1.Jack}
 * <li> {@link visitor.card1.Queen}
 * <li> {@link visitor.card1.King}
 * 
 * に対して、処理の実装を記述する Card1Visitor と、
 * 後からカードに {@link visitor.card2.Joker} を追加して
 * 5つのクラスに対して処理の実装を記述する Card2Visitor を用意している。
 * 
 * ジェネリクスの技術的に特記するべきところは、エンクロージング内部クラスによって
 * 型変数の分離をしている点。詳細は
 * {@link visitor.card1.Card1Visitor.AbstractTVI}
 * を参照されたし。
 */
