package exo4.task

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class TaskJsonDataSource(private val filePath: String) : ITaskDataSource {
	override fun getTasks(): List<Task> = try {
		val file = File(filePath)
		Json.decodeFromStream<List<Task>>(file.inputStream())
	} catch (_: Exception) {
		emptyList()
	}

	override fun saveTasks(tasks: List<Task>) {
		val file = File(filePath)
		file.parentFile.mkdirs()
		file.writeText(Json.encodeToString(tasks))
	}
}