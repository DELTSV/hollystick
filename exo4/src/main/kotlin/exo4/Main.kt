package exo4

import com.xenomachina.argparser.ArgParser
import exo4.input.Command
import exo4.input.Input
import exo4.input.TaskArgs
import exo4.output.IOutput
import exo4.task.*

fun main(args: Array<String>) {
	val arguments = ArgParser(args).parseInto(::TaskArgs)
	val output = IOutput.Factory[arguments.output]
	val dataSource = TaskJsonDataSource("./data.json")
	val taskRepository = TaskRepository(dataSource)
	val taskController = TaskController(taskRepository)
	val res = taskController.computeInput(Input(arguments.action ?: Command.List, arguments.name))
	when(arguments.action) {
		null -> output.displayTasks(res)
		Command.List -> output.displayTasks(res)
		Command.Report -> output.displayReport(res)
		else -> output.displayText("Opération effectué")
	}
}