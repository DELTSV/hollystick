package exo3

data class CalculationDetail(
	val calculations: List<Pair<Int, Int>>,
	val result: Int,
	val operation: Operation
)
