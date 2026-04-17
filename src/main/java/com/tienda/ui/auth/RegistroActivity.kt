package com.tienda.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.tienda.R
import com.tienda.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class RegistroActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etRepetirContrasena: EditText
    private lateinit var checkTerminos: CheckBox
    private lateinit var btnRegistrarse: Button
    private lateinit var tvYaTienesCuenta: TextView

    @Serializable

    data class UsuarioData(
        val id: String,
        val nombres: String,
        val apellidos: String,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        val rootView = findViewById<ViewGroup>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = maxOf(systemBars.bottom, imeInsets.bottom)
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, bottomPadding)
            insets
        }

        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etCorreo = findViewById(R.id.etCorreo)
        etContrasena = findViewById(R.id.etContrasena)
        etRepetirContrasena = findViewById(R.id.etRepetirContrasena)
        checkTerminos = findViewById(R.id.checkTerminos)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        tvYaTienesCuenta = findViewById(R.id.tvYaTienesCuenta)



        btnRegistrarse.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val repetirContrasena = etRepetirContrasena.text.toString().trim()
            val terminosAceptados = checkTerminos.isChecked.toString().trim()


            // Validaciones

            if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || repetirContrasena.isEmpty() || terminosAceptados.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (contrasena.length < 8) {
                Toast.makeText(
                    this,
                    "La contraseña debe tener al menos 8 caracteres",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (contrasena != repetirContrasena) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!checkTerminos.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            //Registro y Supabase

            lifecycleScope.launch {
                try {
                    //paso 1 : registrar el usuario e supabase
                    val resultado = SupabaseClient.client.auth.signUpWith(Email) {
                        email = correo
                        password = contrasena

                    }

                    //paso 2: Guardar los datos adicionales

                    val userId = SupabaseClient.client.auth.currentUserOrNull()?.id ?: ""
                    SupabaseClient.client.postgrest["usuarios"].insert(
                        UsuarioData(
                            id = userId,
                            nombres = nombres,
                            apellidos = apellidos
                        )
                    )
                    //paso 3: redirigir al usuario
                    runOnUiThread {
                        Toast.makeText(
                            this@RegistroActivity,
                            "Registro exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@RegistroActivity, LoginActivity::class.java))
                        finish()
                    }
                } catch (e: Exception) {

                    runOnUiThread {
                        Toast.makeText(
                            this@RegistroActivity,
                            "Error en el registro",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
        tvYaTienesCuenta.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }
}