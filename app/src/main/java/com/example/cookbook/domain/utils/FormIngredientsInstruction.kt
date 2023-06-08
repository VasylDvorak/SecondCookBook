package com.example.cookbook.domain.utils

import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import com.example.cookbook.domain.entity.entity_recipe.Meal

class FormIngredientsInstruction {

    val defaultStringIngredients ="Ingredients"
    val defaultStringRecept ="Recept"
    val commonTitles ="Ingredients"+" "+"Recept"

    fun get_DefaultStringIngredients()=defaultStringIngredients

    fun get_SecondArray(multiply: Int):Array<Int>{
        val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
        for(i in numbers.indices){
            numbers[i]*=multiply*2
        }
        return numbers
    }
    fun get_FirstArray(multiply: Int): Array<Int> {
        val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
        for(i in numbers.indices){
            numbers[i]*=multiply
        }
        return numbers
    }

    fun checkNull(argument: Int): Int? {
        if(argument>0){
            return 1
        }else{
            return null
        }
    }

    val titles = Pair(defaultStringIngredients, defaultStringRecept)

    fun get_Titles()=titles

    fun get_Or(argumentOne: Boolean, argumentTwo: Boolean)= argumentOne || argumentTwo

    fun ingredientsList(currentRecipe: Meal): SpannableStringBuilder {
        var stringsIngredientsList: MutableList<Pair<String, String>> = mutableListOf()
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient1 ?: "", currentRecipe.strMeasure1 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient2 ?: "", currentRecipe.strMeasure2 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient3 ?: "", currentRecipe.strMeasure3 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient4 ?: "", currentRecipe.strMeasure4 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient5 ?: "", currentRecipe.strMeasure5 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient6 ?: "", currentRecipe.strMeasure6 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient7 ?: "", currentRecipe.strMeasure7 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient8 ?: "", currentRecipe.strMeasure8 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient9 ?: "", currentRecipe.strMeasure9 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient10 ?: "", currentRecipe.strMeasure10 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient11 ?: "", currentRecipe.strMeasure11 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient12 ?: "", currentRecipe.strMeasure12 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient13 ?: "", currentRecipe.strMeasure13 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient14 ?: "", currentRecipe.strMeasure14 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient15 ?: "", currentRecipe.strMeasure15 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient16 ?: "", currentRecipe.strMeasure16 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient17 ?: "", currentRecipe.strMeasure17 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient18 ?: "", currentRecipe.strMeasure18 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient19 ?: "", currentRecipe.strMeasure19 ?: ""))
        stringsIngredientsList.add(Pair(currentRecipe.strIngredient20 ?: "", currentRecipe.strMeasure20 ?: ""))


        var outIngredients = SpannableStringBuilder("INGREDIENTS:\n")
        outIngredients.setSpan(StyleSpan(BOLD), 0, outIngredients.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        var totalString=""
        for(oneElement in stringsIngredientsList){
            if(oneElement.first != ""){
                totalString ="${totalString+oneElement.first}: ${oneElement.second}\n"
            } }

        outIngredients.append(totalString)

        return outIngredients
    }
    fun formInstructionText(instruction: String): SpannableStringBuilder {
        var outInstruction = SpannableStringBuilder("INSTRUCTION:\n")
        outInstruction.setSpan(
            StyleSpan(Typeface.BOLD), 0, outInstruction.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        outInstruction.append(instruction)
        return outInstruction
    }
}