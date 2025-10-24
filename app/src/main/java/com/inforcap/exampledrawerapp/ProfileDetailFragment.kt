package com.inforcap.exampledrawerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inforcap.exampledrawerapp.databinding.FragmentProfileDetailBinding

class ProfileDetailFragment : Fragment() {
    private lateinit var binding: FragmentProfileDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDetailBinding.inflate(inflater, container, false)

        // Recuperar datos enviados desde ProfileFragment
        val args = arguments
        val nombre = args?.getString("nombre")
        val apellido = args?.getString("apellido")
        val email = args?.getString("email")
        val telefono = args?.getString("telefono")
        val ciudad = args?.getString("ciudad")

        // Mostrar los datos en los TextView del layout
        binding.tvNombre.text = "Nombre: $nombre $apellido"
        binding.tvEmail.text = "Email: $email"
        binding.tvTelefono.text = "Teléfono: $telefono"
        binding.tvCiudad.text = "Ciudad: $ciudad"

        return binding.root
    }
}

