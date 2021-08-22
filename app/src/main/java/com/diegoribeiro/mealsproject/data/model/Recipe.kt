package com.diegoribeiro.mealsproject.data.model

data class Recipe(
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient2: String?,
    val strIngredient20: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String?,
    val strMeal: String,
    val strMealThumb: String?,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure2: String?,
    val strMeasure20: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
) {
    private fun getListIngredients(): ArrayList<Ingredient> {
        val ingredients = arrayListOf<Ingredient>()
        ingredients.add(Ingredient(strIngredient1, strMeasure1))
        ingredients.add(Ingredient(strIngredient2, strMeasure2))
        ingredients.add(Ingredient(strIngredient3, strMeasure3))
        ingredients.add(Ingredient(strIngredient4, strMeasure4))
        ingredients.add(Ingredient(strIngredient5, strMeasure5))
        ingredients.add(Ingredient(strIngredient6, strMeasure6))
        ingredients.add(Ingredient(strIngredient7, strMeasure7))
        ingredients.add(Ingredient(strIngredient8, strMeasure8))
        ingredients.add(Ingredient(strIngredient9, strMeasure9))
        ingredients.add(Ingredient(strIngredient10, strMeasure10))
        ingredients.add(Ingredient(strIngredient11, strMeasure11))
        ingredients.add(Ingredient(strIngredient12, strMeasure12))
        ingredients.add(Ingredient(strIngredient13, strMeasure13))
        ingredients.add(Ingredient(strIngredient14, strMeasure14))
        ingredients.add(Ingredient(strIngredient15, strMeasure15))
        ingredients.add(Ingredient(strIngredient16, strMeasure16))
        ingredients.add(Ingredient(strIngredient17, strMeasure17))
        ingredients.add(Ingredient(strIngredient18, strMeasure18))
        ingredients.add(Ingredient(strIngredient19, strMeasure19))
        ingredients.add(Ingredient(strIngredient20, strMeasure20))
        return ingredients
    }

    fun filterBlankIngredient(): ArrayList<Ingredient> {
        val filteredIngredientList = arrayListOf<Ingredient>()

        val inputIngredientList = getListIngredients()
        inputIngredientList.forEach { ingredient ->
            if (!ingredient.name.isNullOrBlank())
                filteredIngredientList.add(ingredient)
        }
        return filteredIngredientList
    }
}