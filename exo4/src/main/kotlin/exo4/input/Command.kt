package exo4.input

enum class Command(val value: String) {
	Add("add"),
	Remove("remove"),
	List("list"),
	Finish("finish"),
	Report("report");

	companion object {
		operator fun get(value: String): Command? = entries.firstOrNull { it.value == value }
	}
}