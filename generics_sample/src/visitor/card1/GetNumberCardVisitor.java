package visitor.card1;

/**
 * カードの数字を取得する機能を提供するCard1向けのVisitor
 * 
 * @author nagise
 */
public class GetNumberCardVisitor extends Card1Visitor<GetNumberCardVisitor> {

	public class TVI extends AbstractTVI<Void, Integer, TVI> {
		@Override
		public Integer visit(NumberCard card, Void param) {
			return card.number;
		}

		@Override
		public Integer visit(Jack card, Void param) {
			return 11;
		}

		@Override
		public Integer visit(Queen card, Void param) {
			return 12;
		}

		@Override
		public Integer visit(King card, Void param) {
			return 13;
		}}
}
