package tddact

import java.util.Iterator;

import spock.lang.Specification
import spock.lang.Shared

class VendingMachineSpec extends Specification {

	VendingMachine v = new VendingMachine()


	def "�������������ƍ��v���z"() {
		expect:
			v.insert(amount)
			v.getAvailableTotalAmount() == totalAmount
		where:
			amount | totalAmount
			10     | 10
			50     | 50
			100    | 100
			1000   | 1000
	}
	
	def "�����񂨋��𓊓�"() {
		expect:
		v.insert(amount1)
		v.insert(amount2)
		v.insert(amount3)
		v.getAvailableTotalAmount() == totalAmount
		
		where:
		amount1 | amount2 | amount3 | totalAmount
		10      | 10      | 10      | 30 
		10      | 50      | 50      | 110
		
	}
	
	def "�����񂨋��𓊓������"() {
		expect:
			for(i in amount) {
				v.insert(i)
			}
			v.getAvailableTotalAmount() == totalAmount
		where:
			amount        | totalAmount
			[10]          | 10
			[10, 50]      | 60
			[10, 50, 100] | 160
	}
	
	def "�����߂�����Ɠ����������z���߂�"() {
		expect:
			for (i in amount) {
				v.insert(i)
			}
			v.getAvailableTotalAmount() == totalAmountBefore
			v.cancel() == canceledAmount
			v.getAvailableTotalAmount() == totalAmountAfter
		where: 
			amount        | totalAmountBefore | canceledAmount | totalAmountAfter
			[10]          | 10                | 10             | 0
			[10, 50]      | 60                | 60             | 0
			[10, 50, 100] | 160               | 160            | 0
	}
	
	def "�����Ȃ��������𓊓����Ă����v���z��0�̂܂܂ɂȂ�" () {
		expect:
			for (i in amount) {
				v.insert(i)
			}
			v.getAvailableTotalAmount() == totalAmountBefore
			v.cancel() == canceledAmount
			v.getAvailableTotalAmount() == totalAmountAfter
		where:
			amount        | totalAmountBefore | canceledAmount | totalAmountAfter
			[1]           | 0                 | 1              | 0
			[1, 5, 2000]  | 0                 | 2006           | 0
	}	
	
	def "�����Ȃ��������𓊓����Ă����v���z��0�̂܂܂ɂȂ���H" () {
		when:
			for (i in amount) {
				v.insert(i)
			}
		then:
			v.getAvailableTotalAmount() == totalAmountBefore
			v.cancel() == canceledAmount
		where:
			amount << [[1], [1, 5, 2000]]
			totalAmountBefore << [0, 0]
			canceledAmount << [1, 2006]
	}
	
	def "�W���[�X�̍݌ɏ��𓾂���"() {
		when: 
			Set<Juice> juiceStock =  v.getJuiceStock()

		then:
			juiceStock.contains(new Juice("�R�[��"    , 150, 5))
			juiceStock.contains(new Juice("���b�h�u��", 260, 0))
	}	

	@Shared juice1 = new Juice("�R�[��"    , 150, 5)
	@Shared Juice juice2 = new Juice("���b�h�u��", 260, 0)
	@Shared Juice juice3 = new Juice("���b�h�u��", 500, 10)
	
	def "�W���[�X�̍݌ɏ��𓾂����"() {
	
		expect:
			Set<Juice> juiceStock =  v.getJuiceStock()
			juiceStock.containsAll(juiceList as Set)
			dummy == 1
		where:
			juiceList          | dummy
			[juice1]           | 1
			[juice2]           | 1
			[juice1, juice2]   | 1
	}

}




