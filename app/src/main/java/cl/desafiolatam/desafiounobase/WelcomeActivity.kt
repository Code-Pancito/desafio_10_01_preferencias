package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    lateinit var nameUser: TextView
    lateinit var advance: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        nameUser = findViewById(R.id.welcome_name)
        advance = findViewById(R.id.advance_button)

        sharedPreferences = getSharedPreferences("cl.desafiolatam.desafiounobase", Context.MODE_PRIVATE)
        setUpViewsAndListener()
    }

    private fun setUpViewsAndListener() {
        val userName = if(this::sharedPreferences.isInitialized) {
            sharedPreferences.getString("activeUser", "")
        } else ""

        nameUser.text = userName
        advance.setOnClickListener {

            // TODO que sepa cual usuario vio la pantalla de bienvenida
            sharedPreferences.edit().putBoolean("hasSeenWelcome", true).apply()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
