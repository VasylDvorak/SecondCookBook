package com.example.cookbook.domain.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FormIngredientsInstructionTest {

    private lateinit var validator: FormIngredientsInstruction

    @Before
    fun setup() {
        validator = FormIngredientsInstruction()
    }


    @Test
    fun testForNotEqaulsTwoStringsVal() {
        assertNotEquals(validator.defaultStringIngredients, validator.defaultStringRecept)
    }

    @Test
    fun testForEqaulsTwoStringsVal() {
        assertEquals(validator.defaultStringIngredients, validator.get_DefaultStringIngredients())
    }


    @Test
    fun testForArrayEquals() {
        repeat(5) {
            assertArrayEquals(
                validator
                    .get_FirstArray((it + 1) * 2), validator.get_SecondArray(it + 1)
            )
        }
    }

    @Test
    fun testForCheckNull_ReturnNull() {
        assertNull(validator.checkNull(-5))
    }

    @Test
    fun testForCheckNull_ReturnNotNull() {
        assertNotNull(validator.checkNull(5))
    }

    @Test
    fun testForSameTitles() {
        assertSame(validator.titles, validator.get_Titles())
    }

    @Test
    fun testForFunctionOr_ReturnTrue() {
        assertTrue(validator.get_Or(false, true))
    }

    @Test
    fun testForFunctionOr_ReturnFalse() {
        assertFalse(validator.get_Or(false, false))
    }
}
