package exo4

import com.xenomachina.argparser.ArgParser
import exo4.input.Input
import exo4.input.TaskArgs
import exo4.output.ConsoleOutput
import exo4.task.Status
import exo4.task.Task
import kotlinx.datetime.Clock

fun main(args: Array<String>) {
	val arguments = ArgParser(args).parseInto(::TaskArgs)
	val input = Input(args)
	println(input)
	val output = ConsoleOutput()
	output.displayReportTask(Task("test", Clock.System.now(), Status.Todo))
}