import java.io.File

object InputHandler {
	fun readNumbersFromCsv(fileName: String): List<Int> {
		return File(fileName).readLines().map { it.toInt() }
	}

	fun checkOperation(operation: String): Operation {
		return Operation.entries.find { it.symbol == operation }
			?: throw IllegalArgumentException("Unsupported operation: $operation")
	}
}
