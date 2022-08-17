package com.example.test4.Auth

import com.google.firebase.database.PropertyName
import kotlin.properties.Delegates

class user() {

    lateinit var town : String
    lateinit var type : String
    lateinit var sound : String
    lateinit var text : String
    lateinit var nickname : String

    constructor(town : String, type : String, sound : String, text : String, nickname : String) : this() {
        this.town = town
        this.type = type
        this.sound = sound
        this.text = text
        this.nickname = nickname
    }
}
