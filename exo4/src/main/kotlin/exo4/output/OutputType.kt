package exo4.output

sealed interface OutputType {
	companion object {
		operator fun get(name: String, fileName: String = "file.out") = when (name) {
			"console" -> ConsoleOutputType
			"file" -> FileOutputType(fileName)
			else -> throw IllegalArgumentException("Invalid output type")
		}
	}
}

data object ConsoleOutputType : OutputType
class FileOutputType(val fileName: String): OutputType