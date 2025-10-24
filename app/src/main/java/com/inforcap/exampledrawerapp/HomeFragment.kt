package com.inforcap.exampledrawerapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.inforcap.exampledrawerapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private var textViewHomeTitle: TextView? = null
    private var editTextNombreHome: EditText? = null
    private var buttonSaludarHome: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding!!.getRoot()

        textViewHomeTitle = binding!!.textViewHomeTitle
        editTextNombreHome = binding!!.editTextNombreHome
        buttonSaludarHome = binding!!.buttonSaludarHome

        textViewHomeTitle!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                textViewHomeTitle!!.setText("Bienvenidos a mi App!!!")
            }
        })


        editTextNombreHome!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
                val textoIngresado = editTextNombreHome!!.getText().toString().trim { it <= ' ' }
                if (textoIngresado.length >= 5) {
                    buttonSaludarHome!!.setEnabled(true)
                } else {
                    buttonSaludarHome!!.setEnabled(false)
                }
            }

            override fun afterTextChanged(editable: Editable?) {
            }
        })

        buttonSaludarHome!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                Toast.makeText(
                    getContext(),
                    "Hola " + editTextNombreHome!!.getText().toString().trim { it <= ' ' },
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return view
    }
}