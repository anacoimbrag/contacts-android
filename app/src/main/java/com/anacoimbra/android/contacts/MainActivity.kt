package com.anacoimbra.android.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.core.setContent
import androidx.ui.tooling.preview.Preview
import com.anacoimbra.android.contacts.navigation.Route
import com.anacoimbra.android.contacts.ui.ContactDetail
import com.anacoimbra.android.contacts.ui.ContactList
import com.anacoimbra.android.contacts.ui.theme.ContactsTheme
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(AmbientBackPressHandler provides backPressHandler) {
                ContactsTheme {
                    ContactApp()
                }
            }

        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle())
            super.onBackPressed()
    }
}

@Composable
fun ContactApp(default: Route = Route.ContactList) {
    Router(defaultRouting = default) { backStack ->
        when (val route = backStack.last()) {
            is Route.ContactList -> ContactList(
                onClick = {
                    backStack.push(Route.ContactDetail(it))
                })
            is Route.ContactDetail -> ContactDetail(route.contact) { backStack.pop() }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    val backPressHandler = BackPressHandler()
    Providers(AmbientBackPressHandler provides backPressHandler) {
        ContactsTheme {
            ContactApp(default = Route.ContactList)
        }
    }
}