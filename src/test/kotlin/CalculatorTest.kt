import org.junit.jupiter.api.Test
import java.io.File

class CalculatorTest {

	@Test
	fun testAdditionOperation() {
		val numbers = listOf(1, 2, 3, 4, 5)
		val operation = Operation.ADD
		val result = Calculator.calculate(numbers, operation)
		assert(result == 15) { "Expected 15, but got $result" }
	}

	@Test
	fun testMultiplicationOperation() {
		val numbers = listOf(1, 2, 3, 4, 5)
		val operation = Operation.MULTIPLY
		val result = Calculator.calculate(numbers, operation)
		assert(result == 120) { "Expected 120, but got $result" }
	}

	@Test
	fun testEmptyList() {
		val numbers = listOf<Int>()
		val operation = Operation.ADD
		try {
			Calculator.calculate(numbers, operation)
			assert(false) { "Expected an exception to be thrown" }
		} catch (e: Exception) {
			assert(e is UnsupportedOperationException) { "Expected UnsupportedOperationException, but got ${e::class.simpleName}" }
		}
	}

	@Test
	fun testInvalidOperation() {
		val invalidOperation = "%"
		try {
			InputHandler.checkOperation(invalidOperation)
			assert(false) { "Expected an exception to be thrown" }
		} catch (e: IllegalArgumentException) {
			assert(e.message == "Unsupported operation: $invalidOperation") { "Expected IllegalArgumentException with message 'Unsupported operation: $invalidOperation', but got ${e.message}" }
		}
	}

	@Test
	fun testReadNumbersFromCsv() {
		val fileName = "test_numbers.csv"
		File(fileName).writeText("1\n2\n3\n4\n5")
		val numbers = InputHandler.readNumbersFromCsv(fileName)
		assert(numbers == listOf(1, 2, 3, 4, 5)) { "Expected [1, 2, 3, 4, 5], but got $numbers" }
		File(fileName).delete()
	}

	@Test
	fun testGenerateResults() {
		val numbers = listOf(1, 2, 3, 4, 5)
		val operation = Operation.ADD
		val result = 15
		val expectedOutput = """	1
	+2 (=3)
	+3 (=6)
	+4 (=10)
	+5 (=15)
	-------
total = 15 (addition)"""

		val output = OutputHandler.generateResults(numbers, operation, result)
		assert(output == expectedOutput) { "Expected output:\n$expectedOutput\nBut got:\n$output" }
	}
}