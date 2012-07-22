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

	public static class �����̓����ƍ��v���z {


		private VendingMachine v;

		@Before
		public void before() {
			v = new VendingMachine();
		}
		
		@Test
		public void �\�~��() {
			v.insert(10);
			assert 11 == v.getAvailableTotalAmount()
		}

		@Test
		public void �܏\�~��() {
			v.insert(50);
			assertEquals(50, v.getAvailableTotalAmount());
		}

		@Test
		public void �����񓊓��ł���() {
			v.insert(50);
			v.insert(50);
			v.insert(50);
			assertEquals(50 * 3, v.getAvailableTotalAmount());
		}

		@Test
		public void �����߂�����Ɠ����������z���߂�() {
			v.insert(10);
			v.insert(50);
			v.insert(1000);
			assertEquals(1060, v.getAvailableTotalAmount());
			assertEquals(1060, v.cancel());
			assertEquals(0, v.getAvailableTotalAmount());
		}
		
		@Test
		public void �����Ȃ��������𓊓����Ă����v���z��0�̂܂܂ɂȂ�() {
			v.insert(1);
			v.insert(5);
			v.insert(2000);
			assertEquals(0, v.getAvailableTotalAmount());
			assertEquals(2006, v.cancel());
			assertEquals(0, v.getAvailableTotalAmount());
		}
	}

	public static class �W���[�X�̊Ǘ� {
		
		private VendingMachine v;

		@Before
		public void before() {
			v = new VendingMachine();
		}
		
		@Test
		public void �i�[���ꂽ�W���[�X1��ނ̏����m�F�ł��� () throws Exception {
			// Set<Juice> juiceStock = v.getJuiceStock();
		}
		
	}
	
}
