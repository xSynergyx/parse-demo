package com.example.parsedemo

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

/**
 * Make : String
 * Model : File
 * User: User
 */
@ParseClassName("Car")
class Car : ParseObject() {

    fun getMake(): String? {
        return getString(KEY_MAKE)
    }

    fun setMake(make: String) {
        put(KEY_MAKE, make)
    }

    fun getModel(): String? {
        return getString(KEY_MODEL)
    }

    fun setModel(model: String) {
        put(KEY_MODEL, model)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setImage(parseFile: ParseFile) {
        put(KEY_IMAGE, parseFile)
    }

    fun getHorsepower(): String? {
        return getString(KEY_HORSEPOWER)
    }

    fun setHorsepower(horsepower: String) {
        put(KEY_HORSEPOWER, horsepower)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    companion object {
        const val KEY_IMAGE = "image"
        const val KEY_MAKE = "make"
        const val KEY_MODEL = "model"
        const val KEY_HORSEPOWER = "horsepower"
        const val KEY_USER = "user"
    }
}