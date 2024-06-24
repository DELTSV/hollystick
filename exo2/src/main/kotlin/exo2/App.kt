package exo2

enum class DSize(val value: String) {
	Normal("normal"),
	Other("other");

	companion object {
		fun getFromString(value: String): DSize = when(value) {
			Normal.value -> Normal
			else -> Other
		}
	}
}

enum class Size(val value: String, val amount: Int) {
	Petit("petit", 2),
	Moyen("moyen", 3),
	Grand("grand", 3);

	companion object {
		fun getFromString(value: String): Size = when(value) {
			Petit.value -> Petit
			Moyen.value -> Moyen
			Grand.value -> Grand
			else -> throw IllegalArgumentException("Taille invalide. \"petit\", \"moyen\" ou \"grand\"")
		}
	}
}

enum class Type(val value: String, val amount: Int) {
	Assiette("assiette", 15),
	Other("other", 10);

	companion object {
		fun getFromString(value: String): Type = when (value) {
			"assiette" -> Assiette
			else -> Other
		}
	}
}



data class Commande(
	val type: Type,
	val name: String,
	val beverage: String,
	val size: Size,
	val dessert: String,
	val dsize: DSize,
	val coffee: Boolean
) {
	companion object {
		fun fromArgs(args: Array<String>): Commande? {
			if(args.size < 7) {
				return null
			}
			try {
				return Commande(
					Type.getFromString(args[0]),
					args[1],
					args[2],
					Size.getFromString(args[3]),
					args[4],
					DSize.getFromString(args[5]),
					args[6] == "yes"
				)
			} catch (e: Exception) {
				throw IllegalArgumentException(e)
			}
		}
	}
}

fun assiette(size: Size, dsize: DSize): Int = Type.Assiette.amount + when (size) {
	Size.Petit -> {
		// dans ce cas, on applique la formule standard
		Size.Petit.amount + if (dsize == DSize.Normal) {
			// pas de formule
			// on ajoute le prix du dessert normal
			2
		} else {
			// sinon on rajoute le prix du dessert special
			4
		}
	}

	Size.Moyen -> {
		// dans ce cas, on applique la formule standard
		Size.Moyen.amount + if (dsize == DSize.Normal) {
			// j'affiche la formule appliquée
			print("Prix Formule Standard appliquée ")
			// le prix de la formule est donc 18
			18
		} else {
			// sinon on rajoute le prix du dessert special
			4
		}
	}

	Size.Grand -> {
		// dans ce cas, on applique la formule standard
		Size.Grand.amount + if (dsize == DSize.Normal) {
			// pas de formule
			// on ajoute le prix du dessert normal
			2
		} else {
			// dans ce cas on a la fomule max
			print("Prix Formule Max appliquée ")
			21
		}
	}
}

fun other(size: Size, dsize: DSize): Int = Type.Other.amount + when (size) {
	Size.Petit -> {
		// dans ce cas, on applique la formule standard
		Size.Petit.amount + if (dsize == DSize.Normal) {
			// pas de formule
			// on ajoute le prix du dessert normal
			2
		} else {
			// sinon on rajoute le prix du dessert special
			4
		}
	}

	Size.Moyen -> {
		// dans ce cas, on applique la formule standard
		Size.Moyen.amount + if (dsize == DSize.Normal) {
			// j'affiche la formule appliquée
			print("Prix Formule Standard appliquée ")
			// le prix de la formule est donc 13
			13
		} else {
			// sinon on rajoute le prix du dessert special
			4
		}
	}

	Size.Grand -> {
		// dans ce cas, on applique la formule standard
		Size.Grand.amount + if (dsize == DSize.Normal) {
			// pas de formule
			// on ajoute le prix du dessert normal
			2
		} else {
			// dans ce cas on a la fomule max
			print("Prix Formule Max appliquée ")
			16
		}
	}
}

// calcule le prix payé par l'utilisateur dans le restaurant, en fonction de type de
// repas qu'il prend (assiette ou sandwich), de la taille de la boisson (petit, moyen, grand), du dessert et
// de son type (normal ou special) et si il prend un café ou pas (yes ou no).
// les prix sont fixes pour chaque type de chose mais des réductions peuvent s'appliquer
// si cela rentre dans une formule!
fun calculPrixCommande(commande: Commande): Int {
	if (commande.name.isEmpty()) {
		return -1
	}

	return when (commande.type) {
		Type.Assiette -> assiette(commande.size, commande.dsize)
		else -> other(commande.size, commande.dsize)
	} + if (commande.type == Type.Assiette && commande.size == Size.Moyen && commande.dsize == DSize.Normal && commande.coffee) {
		print(" avec café offert!")
		0
	} else if (!commande.coffee) {
		// Assume coffee costs 1 unit, adding to the total only if coffee is not included
		1
	} else {
		0
	}
}