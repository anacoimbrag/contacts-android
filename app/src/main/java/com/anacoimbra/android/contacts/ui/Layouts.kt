package com.anacoimbra.android.contacts.ui

import androidx.compose.Composable
import androidx.compose.launchInComposition
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import coil.request.GetRequestBuilder
import coil.transform.CircleCropTransformation
import com.anacoimbra.android.contacts.R
import com.anacoimbra.android.contacts.helper.getContacts
import com.anacoimbra.android.contacts.model.Contact
import com.anacoimbra.android.contacts.model.ContactList
import com.anacoimbra.android.contacts.ui.theme.ContactsTheme
import com.anacoimbra.android.contacts.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ContactList(onClick: (contact: Contact) -> Unit) {
    val list = state { ContactList(contacts = emptyList()) }
    launchInComposition {
        list.value = getContacts()
    }
    Scaffold(
        topAppBar = {
            TopAppBar(
                title = {
                    Text(text = "Contacts")
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        bodyContent = {
            VerticalScroller {
                list.value.contacts.map {
                    ContactItem(it, modifier = Modifier.clickable(onClick = {
                        onClick(it)
                    }))
                }
            }
        }
    )
}

@Composable
fun ContactDetail(contact: Contact, onBackPressed: () -> Unit) {
    Scaffold(
        topAppBar = {
            TopAppBar(
                title = {
                    Text(text = contact.name)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(asset = vectorResource(id = R.drawable.ic_back))
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        },
        bodyContent = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalGravity = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {
                    CoilImage(
                        request = GetRequestBuilder(ContextAmbient.current)
                            .data(contact.image)
                            .transformations(CircleCropTransformation())
                            .build(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.preferredSize(100.dp)
                    )
                    Text(
                        text = contact.name,
                        style = typography.h4.plus(TextStyle(textAlign = TextAlign.Center))
                    )
                    Text(text = contact.email, color = Color.Gray)
                    Text(
                        text = contact.phone,
                        style = typography.body1.plus(TextStyle(color = MaterialTheme.colors.secondary)),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun ListPreview() {
    ContactsTheme {
        ContactList() {}
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    ContactsTheme {
        ContactDetail(
            Contact(
                id = "123",
                name = "Jane doe",
                email = "jane@email.com",
                phone = "(11) 9999-9999",
                image = "https://i.pravatar.cc/150"
            )
        ) {

        }
    }
}