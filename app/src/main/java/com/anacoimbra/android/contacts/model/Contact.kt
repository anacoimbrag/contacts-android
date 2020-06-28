package com.anacoimbra.android.contacts.model

data class Contact(
    var id: String,
    var name: String,
    var email: String,
    var phone: String,
    var image: String
)

data class ContactList(var contacts: List<Contact>)