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
        }
        context.startActivity(
            Intent.createChooser(emailIntent, context.getString(R.string.select_application_email))
        )
    }

    override fun callTo(phoneNumber: String) {
        val sendIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(
            Intent.createChooser(sendIntent, context.getString(R.string.select_application_call))
        )
    }
}
