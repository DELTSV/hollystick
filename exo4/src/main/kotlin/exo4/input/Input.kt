package exo4.input

data class Input(
	val command: Command,
	val arguments: String
) {
	constructor(args: Array<String>) : this(
		getCommandFromArg(args[0]),
		args[1]
	)

	private companion object Factory {
		fun getCommandFromArg(arg: String) = Command[arg]
			?: throw IllegalArgumentException("You must provide a command has a first argument string instead of $arg")
	}
}
