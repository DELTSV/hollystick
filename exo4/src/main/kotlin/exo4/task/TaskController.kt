package exo4.task

import exo4.input.Input
import exo4.input.Command

class TaskController (private val taskRepository: ITaskRepository) {
	fun computeInput(input: Input): List<Task> {
		return when (input.command) {
			Command.Remove -> { taskRepository.removeTask(input.arguments); emptyList() }
			Command.List -> taskRepository.readAll()
			Command.Add -> { taskRepository.addTask(input.arguments); emptyList() }
			Command.Finish -> { taskRepository.finishTask(input.arguments); emptyList() }
			Command.Report -> taskRepository.readAll()
		}
	}
}