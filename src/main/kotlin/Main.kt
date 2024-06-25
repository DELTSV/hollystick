fun main(args: Array<String>) {
	if (args.size != 2) {
		println("Usage: calc <filename> <operation>")
		return
	}

	val fileName = args[0]
	val operation = args[1]

	val parsedOperation = InputHandler.checkOperation(operation)
	val numbers = InputHandler.readNumbersFromCsv(fileName)
	val result = Calculator.calculate(numbers, parsedOperation)

	val displayableOutput = OutputHandler.generateResults(numbers, parsedOperation, result)

	println(displayableOutput)
}



