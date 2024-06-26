package exo4.task

interface ITaskDataSource {
	fun getTasks(): List<Task>
	fun saveTasks(tasks: List<Task>)
}