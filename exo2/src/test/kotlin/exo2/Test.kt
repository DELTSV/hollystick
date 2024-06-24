package exo2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Test {
	@Test
	fun `should not create Commande with less than 7 args`() {
		val args = listOf("arg1", "arg2", "arg3", "arg4", "arg5", "arg6")
		assertEquals(null, Commande.fromArgs(args.toTypedArray()))
	}

	@Test
	fun `should not create Commande with invalid size`() {
		val args = listOf("assiette", "arg2", "arg3", "invalid size", "arg5", "arg6")
		assertThrows<IllegalArgumentException> { Commande.fromArgs(args.toTypedArray()) }
	}

	@Test
	fun `should create Commande`() {
		val args = listOf("assiette", "arg2", "arg3", "petit", "normal", "arg6")
		assert(Commande.fromArgs(args.toTypedArray()) != null)
	}

}