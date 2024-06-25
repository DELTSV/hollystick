package exo4.output

import exo4.task.Status
import exo4.task.Task
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.File

class FileOutput(fileName: String): IOutput {

	private val file = File(fileName)

	override fun displayTask(task: Task, index: Int) {
		val status = if(task.status == Status.Done) 'X' else ' '
		file.appendText("[${"%02d".format(index)}][$status] ${task.name} ${task.creationDate}\n")
	}

	override fun displayReportTask(task: Task) {
		file.appendText("- ${task.name} (${task.creationDate.toLocalDateTime(TimeZone.currentSystemDefault()).date})")
	}
}