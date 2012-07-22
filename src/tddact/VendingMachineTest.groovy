package tddact;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import spock.lang.Specification;

@RunWith(Enclosed.class)
public class VendingMachineTest extends Specification {

	@Test
	public void testNew() {
		VendingMachine v = new VendingMachine();
	}

	public static class お金の投入と合計金額 {


		private VendingMachine v;

		@Before
		public void before() {
			v = new VendingMachine();
		}
		
		@Test
		public void 十円玉() {
			v.insert(10);
			assert 11 == v.getAvailableTotalAmount()
		}

		@Test
		public void 五十円玉() {
			v.insert(50);
			assertEquals(50, v.getAvailableTotalAmount());
		}

		@Test
		public void 複数回投入できる() {
			v.insert(50);
			v.insert(50);
			v.insert(50);
			assertEquals(50 * 3, v.getAvailableTotalAmount());
		}

		@Test
		public void 払い戻しすると投入した金額が戻る() {
			v.insert(10);
			v.insert(50);
			v.insert(1000);
			assertEquals(1060, v.getAvailableTotalAmount());
			assertEquals(1060, v.cancel());
			assertEquals(0, v.getAvailableTotalAmount());
		}
		
		@Test
		public void 無効なお金だけを投入しても合計金額が0のままになる() {
			v.insert(1);
			v.insert(5);
			v.insert(2000);
			assertEquals(0, v.getAvailableTotalAmount());
			assertEquals(2006, v.cancel());
			assertEquals(0, v.getAvailableTotalAmount());
		}
	}

	public static class ジュースの管理 {
		
		private VendingMachine v;

		@Before
		public void before() {
			v = new VendingMachine();
		}
		
		@Test
		public void 格納されたジュース1種類の情報を確認できる () throws Exception {
			// Set<Juice> juiceStock = v.getJuiceStock();
		}
		
	}
	
}
