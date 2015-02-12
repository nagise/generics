package swap;

import static org.junit.Assert.assertEquals;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * <p>Map&lt;K,V&gt; -&gt; Map&lt;K,V&gt; converter.</p>
 * 
 * <p>Map＜K,V＞ → Map＜V,K＞ の変換を行うサンプル。</p>
 * 
 * @author nagise
 */
public class MapSwapSample {
	/**
	 * <p>Map＜K,V＞ → Map＜V,K＞ の変換を行うユーティリティ。</p>
	 * 
	 * <p>この実装では戻り値のMapの実装はHashMapとなる。</p>
	 * 
	 * @param <K> 元の Map の key の型
	 * @param <V> 元の Map の value の型
	 * @param origin 変換元
	 * @return 変換後
	 */
	public static <K,V>
	Map<V,K> swap1(Map<K,V> origin) {
		return origin.entrySet().parallelStream()
			.map((entry)->new AbstractMap.SimpleEntry<V,K>(entry.getValue(), entry.getKey()))
			.collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue()));
	}
	
	/**
	 * <p>Map＜K,V＞ → Map＜V,K＞ の変換を行うユーティリティ。
	 * 変換後のMapの実装を指定することが出来る。</p>
	 * 
	 * <p>このメソッドでは型変数Pで引数のMapの具象型を、
	 * 型変数Rで戻り値のMapの具象型を指定するが、
	 * PとRが同じMapの実装であることを制約することができない。</p>
	 * 
	 * <p>そのため、型変数Pは役に立っておらず、これを取り除くと後述のswap3のシグネチャとなる。</p>
	 * 
	 * @param <K> 元の Map の key の型
	 * @param <V> 元の Map の value の型
	 * @param <R> 変換後の Map＜V,K＞ の具象型
	 * @param <P> 元のMap＜V,K＞ の具象型。ここでは役に立っていない
	 * @param origin 変換元
	 * @param supplier 変換後の Map＜V,K＞ の具象型インスタンスを提供するSupplier
	 * @return 変換後のMap. 型変数Sで指定されたMapの具象型となる
	 */
	public static <K,V,
		R extends Map<V,K>,
		P extends Map<K,V>>
	R swap2(P origin, Supplier<R> supplier) {
		return origin.entrySet().stream()
			.map((entry)->new AbstractMap.SimpleEntry<V,K>(entry.getValue(), entry.getKey()))
			.collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue(),
				(u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
				supplier));
	}

	/**
	 * <p>Map＜K,V＞ → Map＜V,K＞ の変換を行うユーティリティ。
	 * 変換後のMapの実装を指定することが出来る。</p>
	 * 
	 * @param <K> 元の Map の key の型
	 * @param <V> 元の Map の value の型
	 * @param <R> 変換後の Map＜V,K＞ の具象型
	 * @param origin 変換元
	 * @param supplier 変換後の Map＜V,K＞ の具象型インスタンスを提供するSupplier
	 * @return 変換後のMap. 型変数Sで指定されたMapの具象型となる
	 */
	public static <V,K,
		R extends Map<V,K>>
	R swap3(Map<K,V> origin, Supplier<R> supplier) {
		return origin.entrySet().stream()
			.map((entry)->new AbstractMap.SimpleEntry<V,K>(entry.getValue(), entry.getKey()))
			.collect(Collectors.toMap((e)->e.getKey(), (e)->e.getValue(),
				(u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
				supplier));
	}

	@Test
	public void swap1Test() {
		Map<String, Integer> origin = new LinkedHashMap<>();
		origin.put("あ", 1);
		origin.put("い", 2);
		origin.put("う", 3);
		origin.put("え", 4);
		origin.put("お", 5);
		Map<Integer, String> swap = MapSwapSample.swap1(origin);
		assertEquals("あ", swap.get(1));
		assertEquals("い", swap.get(2));
		assertEquals("う", swap.get(3));
		assertEquals("え", swap.get(4));
		assertEquals("お", swap.get(5));
	}

	@Test
	public void swap2Test() {
		Map<String, Integer> origin = new LinkedHashMap<>();
		origin.put("ろ", 2);
		origin.put("は", 3);
		origin.put("い", 1);
		origin.put("ほ", 5);
		origin.put("に", 4);
		System.out.println(origin);
		Map<Integer, String> swap = MapSwapSample.swap2(origin, ()->new TreeMap<>());
		List<String> list = new ArrayList<>(swap.values());
		assertEquals("い", list.get(0));
		assertEquals("ろ", list.get(1));
		assertEquals("は", list.get(2));
		assertEquals("に", list.get(3));
		assertEquals("ほ", list.get(4));
		System.out.println(swap);
	}

	@Test
	public void swap3Test() {
		Map<String, Integer> origin = new LinkedHashMap<>();
		origin.put("ろ", 2);
		origin.put("は", 3);
		origin.put("い", 1);
		origin.put("ほ", 5);
		origin.put("に", 4);
		System.out.println(origin);
		Map<Integer, String> swap = MapSwapSample.swap3(origin, ()->new TreeMap<>());
		List<String> list = new ArrayList<>(swap.values());
		assertEquals("い", list.get(0));
		assertEquals("ろ", list.get(1));
		assertEquals("は", list.get(2));
		assertEquals("に", list.get(3));
		assertEquals("ほ", list.get(4));
		System.out.println(swap);
	}
}
