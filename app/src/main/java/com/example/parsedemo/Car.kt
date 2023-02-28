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

    fun setMake(description: String) {
        put(KEY_MAKE, description)
    }

    fun getModel(): String? {
        return getString(KEY_MODEL)
    }

    fun setModel(parsefile: String) {
        put(KEY_MODEL, parsefile)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setImage(parsefile: ParseFile) {
        put(KEY_IMAGE, parsefile)
    }

    fun getHorsepower(): String? {
        return getString(KEY_HORSEPOWER)
    }

    fun setHorsepower(user: String) {
        put(KEY_USER, user)
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
        const val KEY_HORSEPOWER = "user"
        const val KEY_USER = "user"
    }
}