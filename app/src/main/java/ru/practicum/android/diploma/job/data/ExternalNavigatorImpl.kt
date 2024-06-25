package ru.practicum.android.diploma.job.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.job.domain.ExternalNavigator

class ExternalNavigatorImpl(private val context: Context) : ExternalNavigator {
    override fun emailTo(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(
            Intent.createChooser(emailIntent, context.getString(R.string.select_application_email)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

    override fun callTo(phoneNumber: String) {
        val sendIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        val chooserIntent =
            Intent.createChooser(sendIntent, context.getString(R.string.select_application_call)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        context.startActivity(
            chooserIntent
        )
    }

    override fun shareLink(alternateUrl: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, alternateUrl)
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(sendIntent)
    }
}
