package exo3

import org.junit.jupiter.api.Test
import java.io.BufferedReader

class Test {

	private val invalidInput = """
		1
		2
		3
		error
		5
	""".trimIndent()

	private val validInput = """
		1
		2
		3
		4
		5
	""".trimIndent()

	private val validInputWithZero = """
		0
		1
		2
		3
		4
		5
	""".trimIndent()

	private val validList = listOf(1, 2, 3, 4, 5)

	@Test
	fun `should not retrieve list of int`() {
		val result = readInput(BufferedReader(invalidInput.byteInputStream().reader()))
		assert(result.isFailure)
	}

	@Test
	fun `should retrieve list of int`() {
		val result = readInput(BufferedReader(validInput.byteInputStream().reader()))
		assert(result.isSuccess)
		assert(validList == result.getOrThrow())
	}

	@Test
	fun `should not calculate with invalid input`() {
		val reader = BufferedReader(invalidInput.byteInputStream().reader())
		val result = calculate(reader, Operation.Addition)
		assert(result.isFailure)
	}

	@Test
	fun `should calculate with valid input`() {
		val reader = BufferedReader(validInput.byteInputStream().reader())
		val result = calculate(reader, Operation.Addition)
		assert(result.isSuccess)
		assert(result.getOrThrow().result == 15)
	}

	@Test
	fun `should not divide by zero`() {
		val reader = BufferedReader(validInputWithZero.byteInputStream().reader())
		val result = calculate(reader, Operation.Division)
		assert(result.isFailure)
		assert(result.exceptionOrNull() is IllegalArgumentException)
		assert(result.exceptionOrNull()?.message == "On ne peut pas diviser par 0")
	}
}