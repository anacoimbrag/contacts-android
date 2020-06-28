package com.anacoimbra.android.contacts.navigation

import com.anacoimbra.android.contacts.model.Contact

sealed class Route {
    object ContactList : Route()
    data class ContactDetail(val contact: Contact) : Route()
}