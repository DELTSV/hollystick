package exo4.output

import exo4.task.Task

interface IOutput {
	fun displayTask(task: Task, index: Int)

	fun displayTasks(tasks: List<Task>) {
		tasks.forEachIndexed { i, t -> displayTask(t, i) }
	}

	fun displayReport(tasks: List<Task>) {
		tasks.forEach { displayReportTask(it) }
	}

	fun displayReportTask(task: Task)

	fun displayText(string: String)

	companion object Factory {
		operator fun get(type: OutputType): IOutput = when (type) {
			is ConsoleOutputType -> { ConsoleOutput() }
			is FileOutputType -> { FileOutput(type.fileName) }
		}
	}
}