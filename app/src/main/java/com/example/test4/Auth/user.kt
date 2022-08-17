package com.example.test4.Auth

import com.google.firebase.database.PropertyName
import kotlin.properties.Delegates

class user() {

    lateinit var town : String
    lateinit var type : String
    var sound by Delegates.notNull<Int>()
    var text by Delegates.notNull<Int>()

    constructor(town : String, type : String, sound : Int, text : Int) : this() {
        this.town = town
        this.type = type
        this.sound = sound
        this.text = text
    }
}
