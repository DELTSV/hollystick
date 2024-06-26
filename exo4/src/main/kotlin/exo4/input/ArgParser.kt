package exo4.input

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import exo4.output.ConsoleOutputType
import exo4.output.OutputType


class TaskArgs(parser: ArgParser) {
	val action by parser.storing("action to do") { Command[this] }.default(Command.List)

	val name by parser.storing("name of the task").default("")

	val output by parser.storing("-o", "--output", help = "output value on console or file", transform = { OutputType[this] }).default(ConsoleOutputType)
}