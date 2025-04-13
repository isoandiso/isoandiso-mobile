package com.pedrosiccha.core.util

fun String.isEmailValid(): Boolean =
    this.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))

fun String.isPasswordSecure(): Boolean =
    this.length >= 6 // Puedes extender con validación de números, mayúsculas, etc.