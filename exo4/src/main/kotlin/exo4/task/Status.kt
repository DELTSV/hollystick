package exo4.task

enum class Status(val value: String) {
	Todo("todo"),
	Done("done");

	companion object {
		operator fun get(value: String?): Status? = entries.firstOrNull { it.value == value }
	}
}