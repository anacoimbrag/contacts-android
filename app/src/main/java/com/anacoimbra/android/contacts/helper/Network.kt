package com.anacoimbra.android.contacts.helper

import android.util.Log
import com.anacoimbra.android.contacts.model.ContactList
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

private const val JSON_URL =
    "https://gist.githubusercontent.com/anacoimbrag/50cb96b6c57100c1881be36d98bc4d2a/raw/1c2176ffaafc54baeb82f62d81e7a3fa4615244a/contactData.json"

suspend fun getContacts(): ContactList = withContext(Dispatchers.IO) {
    val json = URL(JSON_URL).readText()
    Log.d("getContacts", "Read: $json")
    Gson().fromJson(json, ContactList::class.java)
}