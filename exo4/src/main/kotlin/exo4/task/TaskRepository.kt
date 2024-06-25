package exo4.task

class TaskRepository(private val dataSource: ITaskDataSource) : ITaskCommand, IReadTask {
	override fun readTaskByStatus(status: Status) : List<Task> = dataSource.getTasks().filter { it.status == status }
	override fun readAll() : List<Task> = dataSource.getTasks()

	override fun finishTask(taskName: String) {
		dataSource.getTasks().map { if (it.name == taskName) it.copy(status = Status.Done) else it }.apply {
			dataSource.saveTasks(this)
		}
	}
	override fun addTask(taskName: String) {
		(dataSource.getTasks() + Task(name = taskName)).apply { dataSource.saveTasks(this) }
	}
	override fun removeTask(taskName: String) {
		dataSource.getTasks().filter { it.name != taskName }.apply { dataSource.saveTasks(this) }
	}
}