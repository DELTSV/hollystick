package exo4.output

import exo4.task.Status
import exo4.task.Task
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ConsoleOutput: IOutput {
	override fun displayTask(task: Task, index: Int) {
		val status = if(task.status == Status.Done) 'X' else ' '
		println("[${"%02d".format(index)}][$status] ${task.name} ${task.creationDate}")
	}

	override fun displayReportTask(task: Task) {
		println("- ${task.name} (${task.creationDate.toLocalDateTime(TimeZone.currentSystemDefault()).date})")
	}

	override fun displayText(string: String) {
		println(string)
	}
}