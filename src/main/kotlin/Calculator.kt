object Calculator {
	fun calculate(numbers: List<Int>, operation: Operation): Int {
		return numbers.reduce { acc, num -> operation.apply(acc, num) }
	}
}