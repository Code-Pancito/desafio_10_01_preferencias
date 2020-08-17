package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var nameInput: TextInputEditText
    lateinit var advance: Button
    lateinit var container: ConstraintLayout
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameInput = findViewById(R.id.name_input)
        advance = findViewById(R.id.login_button)
        container = findViewById(R.id.container)
        setUpListeners()
        if(!this::sharedPreferences.isInitialized)
            initSharePreferences()
    }

    private fun setUpListeners() {
        advance.setOnClickListener {
            if (nameInput.text!!.isNotEmpty()) {

                sharedPreferences.edit().putString("activeUser", nameInput.text.toString()).apply()

                val intent = if (hasSeenWelcome()) {
                    Intent(this, HomeActivity::class.java)
                } else {
                    Intent(this, WelcomeActivity::class.java)
                }
                startActivity(intent)
            } else {
                Snackbar.make(container, "El nombre no puede estar vacío", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hasSeenWelcome(): Boolean {
        var returnValue = false

        if(this::sharedPreferences.isInitialized)
            returnValue = sharedPreferences.getBoolean("hasSeenWelcome", false)

        return returnValue
    }

    //TODO que guarde el hasSeenWelcome de cada usuario

    public fun initSharePreferences() {
        sharedPreferences = getSharedPreferences("cl.desafiolatam.desafiounobase", Context.MODE_PRIVATE)
    }
}
