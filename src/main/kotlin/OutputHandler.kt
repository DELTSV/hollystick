object OutputHandler {
	fun generateResults(numbers: List<Int>, operation: Operation, result: Int): String {
		val stringBuilder = StringBuilder()

		numbers.forEachIndexed { index, num ->
			if (index == 0) {
				stringBuilder.append("\t${num}\n")
			} else {
				stringBuilder.append("\t${operation.symbol}$num (=${numbers.subList(0, index + 1).sum()})\n")
			}
		}

		stringBuilder.append("\t-------\n")
		stringBuilder.append("total = $result (${operation.label})")

		return stringBuilder.toString()
	}
}