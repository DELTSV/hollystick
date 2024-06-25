package exo3

import java.io.File

/**
 * Pour exécuter le programme, vous pouvez utiliser https://play.kotlinlang.org/ Kotlin 1.9.24 JVM
 */

fun main(args: Array<String>) {
	if(args.size < 2) {
		println("Il manque des arguments")
		help()
		return
	}

	if(args.any { it == "--help" || it == "-h" }) {
		help()
		return
	}

	val fileName = args[0]
	val operation = try {
		Operation[args[1]]
	} catch (e: IllegalArgumentException) {
		println("Erreur: $e")
		return
	}

	val reader = if(fileName == "-") {
		System.`in`.bufferedReader()
	} else {
		File(fileName).bufferedReader()
	}

	calculate(reader, operation).onSuccess { detail ->
		printDetail(detail)
	}.onFailure {
		println(it.message)
		help()
	}

	return
}

fun help() {
	println("Utilisation: calc <file-name> <operation>")
	println("	<file-name> : Chemin relatif ou absolu vers un fichier. Lit depuis la console si \"-\" est spécifié (Ctrl+D pour fermer l'input)")
	println("	<operation> : \"+\" \"-\" \"*\" \"/\"")
}

fun printDetail(calculationDetail: CalculationDetail) {
	println("        ${calculationDetail.calculations.first().first}")
	calculationDetail.calculations.drop(1).forEach {
		println("        ${it.first} (=${it.second})")
	}
	println("total = ${calculationDetail.result} (${calculationDetail.operation.name.lowercase()})")
}