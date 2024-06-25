package exo4.input

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import exo4.output.OutputType


class TaskArgs(parser: ArgParser) {
	val v by parser.flagging("enable verbose mode")

	val action by parser.positional("action to do") { Command[this] }.default(Command.List)

	val name by parser.positional("name of the task")

	val output by parser.storing("output value on console or file", "output") { OutputType[this] }
}