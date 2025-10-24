package com.inforcap.exampledrawerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.inforcap.exampledrawerapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnEnviar.setOnClickListener {

            // 1️⃣ Crear un objeto User con los datos ingresados
            val user = User(
                nombre = binding.etNombre.text.toString(),
                apellido = binding.etApellido.text.toString(),
                email = binding.etEmail.text.toString(),
                telefono = binding.etTelefono.text.toString(),
                ciudad = binding.etCiudad.text.toString()
            )

            // 2️⃣ Guardar el usuario en la lista compartida
            UserRepository.listaUsuarios.add(user)

            // 🔹 2.1 Guardar también en almacenamiento permanente
            UserRepository.saveUsers(requireContext())

            // 3️⃣ Mostrar confirmación
            Toast.makeText(requireContext(), "Usuario guardado correctamente", Toast.LENGTH_SHORT).show()

            // 4️⃣ Limpiar campos del formulario
            binding.etNombre.text.clear()
            binding.etApellido.text.clear()
            binding.etEmail.text.clear()
            binding.etTelefono.text.clear()
            binding.etCiudad.text.clear()

            // 5️⃣ Ir al fragmento con la lista de usuarios
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, UserListFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}
