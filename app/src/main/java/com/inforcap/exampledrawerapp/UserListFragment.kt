package com.inforcap.exampledrawerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.inforcap.exampledrawerapp.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)

        // Crear lista de nombres desde el repositorio
        val nombres = UserRepository.listaUsuarios.map { it.nombre }

        // Crear adaptador para mostrar los nombres
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, nombres)
        binding.listViewUsers.adapter = adapter

        // Evento al seleccionar un nombre
        binding.listViewUsers.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val user = UserRepository.listaUsuarios[position]

                val bundle = Bundle().apply {
                    putString("nombre", user.nombre)
                    putString("apellido", user.apellido)
                    putString("email", user.email)
                    putString("telefono", user.telefono)
                    putString("ciudad", user.ciudad)
                }

                val fragmentDestino = ProfileDetailFragment()
                fragmentDestino.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragmentDestino)
                    .addToBackStack(null)
                    .commit()
            }

        return binding.root
    }
}
