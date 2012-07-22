package tddact

import java.util.Iterator;

import spock.lang.Specification
import spock.lang.Shared

class VendingMachineSpec extends Specification {

	VendingMachine v = new VendingMachine()


	def "投入したお金と合計金額"() {
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
	
	def "複数回お金を投入"() {
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
	
	def "複数回お金を投入する改"() {
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
	
	def "払い戻しすると投入した金額が戻る"() {
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
	
	def "無効なお金だけを投入しても合計金額が0のままになる" () {
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
	
	def "無効なお金だけを投入しても合計金額が0のままになる改？" () {
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
	
	def "ジュースの在庫情報を得られる"() {
		when: 
			Set<Juice> juiceStock =  v.getJuiceStock()

		then:
			juiceStock.contains(new Juice("コーラ"    , 150, 5))
			juiceStock.contains(new Juice("レッドブル", 260, 0))
	}	

	@Shared juice1 = new Juice("コーラ"    , 150, 5)
	@Shared Juice juice2 = new Juice("レッドブル", 260, 0)
	@Shared Juice juice3 = new Juice("レッドブル", 500, 10)
	
	def "ジュースの在庫情報を得られる改"() {
	
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




