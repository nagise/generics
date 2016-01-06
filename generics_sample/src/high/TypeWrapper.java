package high;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 型変数のラッパー型
 * @param <T> 型変数
 */
public class TypeWrapper<T> {
	/**
	 * 高階型変数というかtype constructorのようなことを表現したかったんだ
	 */
	public static void main(String[] args) {
		TypeWrapper<String>.ArrayListWrapper aw = TypeWrapper.<String>arrayListWrapper();
		TypeWrapper<String>.LinkedListWrapper lw = TypeWrapper.<String>linkedListWrapper();
		
		ArrayList<String> source = new ArrayList<>();
		source.add("1");
		source.add("2");
		source.add("3");

		LinkedList<String> linkedList = aw.map(lw, source);
		System.out.println(linkedList.getClass());
		System.out.println(linkedList);
	}
	
	/** シングルトン */
	private static TypeWrapper<?> singleton = new TypeWrapper<>();
	private static TypeWrapper<?>.ArrayListWrapper singletonArrayListWrapper = singleton.new ArrayListWrapper();
	private static TypeWrapper<?>.LinkedListWrapper singletonLinkedListWrapper = singleton.new LinkedListWrapper();

	/** @return ArrayListWrapperのインスタンスを返す */
	@SuppressWarnings("unchecked")
	public static <X> TypeWrapper<X>.ArrayListWrapper arrayListWrapper() {
		return (TypeWrapper<X>.ArrayListWrapper)singletonArrayListWrapper;
	}
	/** @return LinkedListWrapperのインスタンスを返す */
	@SuppressWarnings("unchecked")
	public static <X> TypeWrapper<X>.LinkedListWrapper linkedListWrapper() {
		return (TypeWrapper<X>.LinkedListWrapper)singletonLinkedListWrapper;
	}
	
	/**
	 * 型変数Tをとるコレクション型を表す抽象ラッパー型
	 * @param <C> コレクションの型
	 */
	protected abstract class CollectionWrapper<C extends Collection<T>> {
		/** @return 空のコレクションを返す */
		public abstract C empty();

		/**
		 * このコレクションのラッパーが表すコレクションから、対象の型の実装にエンティティを複写する
		 * @param col 対象の型を表すラッパー
		 * @param source 複写元となるコレクション
		 * @return 複写したコレクション
		 */
		public <CT extends Collection<T>> CT map(CollectionWrapper<CT> col, C source) {
			CT target = col.empty();
			for (T e : source) {
				target.add(e);
			}
			return target;
		}
	}
	/** ArrayListのラッパー */
	private class ArrayListWrapper extends CollectionWrapper<ArrayList<T>> {
		@Override
		public ArrayList<T> empty() {
			return new ArrayList<T>();
		}
	}
	/** LinkedListのラッパー */
	private class LinkedListWrapper extends CollectionWrapper<java.util.LinkedList<T>> {
		@Override
		public LinkedList<T> empty() {
			return new LinkedList<T>();
		}
	}
}
