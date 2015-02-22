package visitor;

import visitor.card1.Card1;
import visitor.card1.GetNumberCardVisitor;
import visitor.card1.King;
import visitor.card1.NextCardVisitor;
import visitor.card1.NumberCard;
import visitor.card1.Queen;
import visitor.card2.Card2;
import visitor.card2.Joker;
import visitor.card2.NextCardVisitor2;

public class Main {
	public static void main(String[] args) {
		card1sample();
		card2sample();
	}
	
	public static void card1sample() {
		GetNumberCardVisitor v1 = new GetNumberCardVisitor();
		Card1 card;
		Integer ret;
		card = new NumberCard(1);
		ret = card.accept(v1.new TVI(), null);
		System.out.println("get number : "+ret);

		card = new Queen();
		ret = card.accept(v1.new TVI(), null);
		System.out.println("get number : "+ret);

		// FIXME 現状ではCard2 extends Card1 としているので
		// Jokerに対してCard1用のGetNumberCardVisitorが呼び出せる
//		card = new Joker();
//		ret = card.accept(v.new TVI(), null);
//		System.out.println(ret);

		NextCardVisitor v2 = new NextCardVisitor();
		card = new King();
		card = card.accept(v2.new TVI(), null);
		System.out.println("next card : "+card);
	}

	public static void card2sample() {
		NextCardVisitor2 ncv2 = new NextCardVisitor2();
		Card1 card;

		card = new King();
		card = card.accept(ncv2.new TVI(), null);
		System.out.println("next v2 : "+card);

		Card2 card2 = new Joker();
		card = card2.accept(ncv2.new TVI(), null);
		System.out.println("next v2 : "+card);
	}
}
