package visitor.card2;

import visitor.card1.Card1;
import visitor.card1.Jack;
import visitor.card1.King;
import visitor.card1.NumberCard;
import visitor.card1.Queen;

/**
 * 次のカードを取得する機能を提供するCard2向けのVisitor
 * 
 * @author nagise
 */
public class NextCardVisitor2 extends Card2Visitor<NextCardVisitor2> {
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
			return new Joker();
		}

		@Override
		public Card1 visit(Joker card, Void param) {
			return new NumberCard(1);
		}
	}
}
