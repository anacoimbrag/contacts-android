package com.anacoimbra.android.contacts.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.unit.dp
import coil.request.GetRequestBuilder
import coil.transform.CircleCropTransformation
import com.anacoimbra.android.contacts.model.Contact
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ContactItem(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        verticalGravity = Alignment.CenterVertically,
        modifier = modifier.plus(Modifier.padding(12.dp))
    ) {
        CoilImage(
            request = GetRequestBuilder(ContextAmbient.current)
                .data(contact.image)
                .transformations(CircleCropTransformation())
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier.preferredSize(40.dp)
        )
        Text(text = contact.name, modifier = Modifier.padding(start = 12.dp))
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}