package exo2

fun main(args: Array<String>) {
	// pour tester on peut utiliser https://play.kotlinlang.org/
	// Program arguments: assiette couscous coca moyen baba normal yes
	if(args.size < 7){
		println("Il manque des arguments")
		println("main <type> <name> <beverage> <size> <dessert> <dsize> <coffee>")
		return
	}
	Commande.fromArgs(args)?.let { commande ->
		val price = calculPrixCommande(commande)
		println("Prix à payer : " + price + "€")
		return
	}
	println("Mauvais arguments")
}
