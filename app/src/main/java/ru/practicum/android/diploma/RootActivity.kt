package ru.practicum.android.diploma

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val menuDivider = findViewById<View>(R.id.menuTopDivider)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.jobFragment,
                R.id.filtersFragment,
                R.id.industryFragment,
                R.id.countryFragment,
                R.id.regionFragment,
                R.id.placeToWorkFragment -> {
                    bottomNavigationView.isVisible = false
                    menuDivider.isVisible = false
                }

                else -> {
                    bottomNavigationView.isVisible = true
                    menuDivider.isVisible = true
                }
            }
        }
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
