package exo3

import java.io.BufferedReader

fun calculate(reader: BufferedReader, operation: Operation): Result<CalculationDetail> {
	readInput(reader).onSuccess { numbers ->
		if(operation == Operation.Division && numbers.any { it == 0 }) {
			return Result.failure(IllegalArgumentException("On ne peut pas diviser par 0"))
		}
		return Result.success(when(operation) {
			Operation.Addition -> add(numbers)
			Operation.Soustraction -> sub(numbers)
			Operation.Multiplication -> mul(numbers)
			Operation.Division -> div(numbers)
		})
	}.onFailure {
		return Result.failure(it)
	}
	return Result.failure(Exception("Erreur inconnue"))
}

private fun add(numbers: List<Int>): CalculationDetail{
	var result = 0
	val calculations = numbers.map {
		result += it
		it to result
	}
	return CalculationDetail(calculations, result, Operation.Addition)
}

private fun sub(numbers: List<Int>): CalculationDetail {
	var result = 0
	val calculations = numbers.map {
		result -= it
		it to result
	}
	return CalculationDetail(calculations, result, Operation.Soustraction)
}

private fun mul(numbers: List<Int>): CalculationDetail {
	var result = 1
	val calculations = numbers.map {
		result *= it
		it to result
	}
	return CalculationDetail(calculations, result, Operation.Multiplication)
}

private fun div(numbers: List<Int>): CalculationDetail {
	var result = numbers.first()
	val calculations = numbers.drop(1).map {
		result /= it
		it to result
	}
	return CalculationDetail(listOf(numbers.first() to numbers.first()) + calculations, result, Operation.Division)
}

fun readInput(reader: BufferedReader): Result<List<Int>> {
	val numbersOrNull = reader.lines().map { line ->
		line.toIntOrNull()
	}.toList()
	val numbers = numbersOrNull.filterNotNull()

	if(numbers.size != numbersOrNull.size) {
		return Result.failure(IllegalArgumentException("Toutes les entrées ne sont pas des nombres"))
	}

	if(numbers.isEmpty()) {
		return Result.failure(IllegalArgumentException("Aucune données"))
	}

	return Result.success(numbers)
}