package exo3

enum class Operation(val value: String) {
	Addition("+"),
	Soustraction("-"),
	Multiplication("*"),
	Division("/");

	companion object {
		operator fun get(value: String): Operation {
			return entries.find { it.value == value } ?: throw IllegalArgumentException("Operation inconnue: $value")
		}
	}
}