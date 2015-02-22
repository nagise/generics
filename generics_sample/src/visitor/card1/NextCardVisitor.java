package visitor.card1;

/**
 * 次のカードを取得する機能を提供するCard1向けのVisitor
 * 
 * @author nagise
 */
public class NextCardVisitor extends Card1Visitor<NextCardVisitor> {
	public class TVI extends AbstractTVI<Void, Card1, TVI> {
		@Override
		public Card1 visit(NumberCard card, Void param) {
			if (card.number == 10) {
				return new Jack();
			}
			return new NumberCard(card.number+1);
		}

		@Override
		public Card1 visit(Jack card, Void param) {
			return new Queen();
		}

		@Override
		public Card1 visit(Queen card, Void param) {
			return new King();
		}

		@Override
		public Card1 visit(King card, Void param) {
			return new NumberCard(1);
		}}
}
