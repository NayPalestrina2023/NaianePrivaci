package com.naiane.privaci.security

import android.content.Context
import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class PasswordStore(context: Context) {

    private val prefs = context.getSharedPreferences("privaci_auth", Context.MODE_PRIVATE)

    fun isSet(): Boolean = prefs.contains("hash") && prefs.contains("salt")

    fun save(password: String) {
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
        val hash = derive(password, salt)
        prefs.edit()
            .putString("salt", Base64.encodeToString(salt, Base64.NO_WRAP))
            .putString("hash", Base64.encodeToString(hash, Base64.NO_WRAP))
            .apply()
    }

    fun verify(password: String): Boolean {
        val saltText = prefs.getString("salt", null) ?: return false
        val hashText = prefs.getString("hash", null) ?: return false
        val salt = Base64.decode(saltText, Base64.NO_WRAP)
        val saved = Base64.decode(hashText, Base64.NO_WRAP)
        return MessageDigest.isEqual(saved, derive(password, salt))
    }

    private fun derive(password: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, 120_000, 256)
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).encoded
    }
}
