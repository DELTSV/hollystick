enum class Operation(val symbol: String, val label: String) : IOperation {
	ADD("+", "addition") {
		override fun apply(a: Int, b: Int) = a + b
	},
	MULTIPLY("*", "multiplication") {
		override fun apply(a: Int, b: Int) = a * b
	}
}