package exo4.task

interface IReadTask {
	fun readTaskByStatus(status: Status) : List<Task>
	fun readAll() : List<Task>
}