package exo4.task

interface ITaskCommand {
	fun finishTask(taskName: String)
	fun addTask(taskName: String)
	fun removeTask(taskName: String)
}