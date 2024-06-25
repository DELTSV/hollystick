package exo4.task

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Task(
	val name: String,
	val creationDate: Instant = Clock.System.now(),
	val status: Status = Status.Done
)