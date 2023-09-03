import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    // Credenciales válidas (simuladas)
    private val validUsername = "usuario"
    private val validPassword = "contraseña"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar vistas
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Configurar clic del botón de inicio de sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validar las credenciales
            if (isValidCredentials(username, password)) {
                // Credenciales válidas, navegar a la pantalla de galería
                navigateToGallery()
            } else {
                // Credenciales incorrectas, mostrar un mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para validar las credenciales
    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == validUsername && password == validPassword
    }

    // Función para navegar a la pantalla de galería
    private fun navigateToGallery() {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
        finish() // Cerrar la actividad de inicio de sesión para que no se pueda volver atrás
    }
}