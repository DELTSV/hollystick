import exo4.input.Command
import exo4.input.Input
import exo4.output.ConsoleOutput
import exo4.task.Status
import exo4.task.Task
import exo4.task.TaskJsonDataSource
import exo4.task.TaskRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.datetime.Clock
import kotlin.test.assertFailsWith

class TaskRepositoryTest : StringSpec({

	val dataSource: TaskJsonDataSource = mockk()
	val taskRepository = TaskRepository(dataSource)
	val sampleTask = Task("Sample Task", Clock.System.now(), Status.Todo)

	"addTask should add a task to the data source" {
		every { dataSource.getTasks() } returns emptyList()
		every { dataSource.saveTasks(any()) } returns Unit

		taskRepository.addTask(sampleTask.name)

		verify { dataSource.saveTasks(listOf(sampleTask)) }
	}

	"removeTask should remove a task from the data source" {
		every { dataSource.getTasks() } returns listOf(sampleTask)
		every { dataSource.saveTasks(any()) } returns Unit

		taskRepository.removeTask("Sample Task")

		verify { dataSource.saveTasks(emptyList()) }
	}

	"finishTask should mark a task as done" {
		val finishedTask = sampleTask.copy(status = Status.Done)
		every { dataSource.getTasks() } returns listOf(sampleTask)
		every { dataSource.saveTasks(any()) } returns Unit

		taskRepository.finishTask("Sample Task")

		verify { dataSource.saveTasks(listOf(finishedTask)) }
	}

	"readAll should return all tasks from the data source" {
		every { dataSource.getTasks() } returns listOf(sampleTask)

		val tasks = taskRepository.readAll()

		tasks shouldBe listOf(sampleTask)
	}

	"readTaskByStatus should return tasks with the specified status" {
		val finishedTask = sampleTask.copy(status = Status.Done)
		every { dataSource.getTasks() } returns listOf(sampleTask, finishedTask)

		val tasks = taskRepository.readTaskByStatus(Status.Done)

		tasks shouldBe listOf(finishedTask)
	}
})

class InputTest : StringSpec({

	"should create Input object from args array" {
		val args = arrayOf("add", "task1")
		val input = Input(args)

		input.command shouldBe Command.Add
		input.arguments shouldBe "task1"
	}

	"should throw IllegalArgumentException for invalid command" {
		val args = arrayOf("invalid", "task1")

		assertFailsWith<IllegalArgumentException> {
			Input(args)
		}
	}
})

class ConsoleOutputTest : StringSpec({

	"displayTask should print the task details" {
		val output = ConsoleOutput()
		val task = Task("Sample Task", Clock.System.now(), Status.Todo)

		output.displayTask(task, 1)
	}

	"displayReportTask should print the report task details" {
		val output = ConsoleOutput()
		val task = Task("Sample Task", Clock.System.now(), Status.Done)

		output.displayReportTask(task)
	}
})