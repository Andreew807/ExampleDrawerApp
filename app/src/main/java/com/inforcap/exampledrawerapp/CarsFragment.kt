package com.inforcap.exampledrawerapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CarsFragment : Fragment() {
    private var listView: ListView? = null
    private var fragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_cars, container, false)

        listView = fragmentView!!.findViewById<ListView>(R.id.listViewLibrary)

        val adapter = setUpAdapter()
        listView!!.setAdapter(adapter)
        listView!!.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                val marcaSeleccionada = adapter.getItem(i).toString()
                Toast.makeText(
                    adapterView.getContext(), "Elemento seleccionado: " +
                            marcaSeleccionada, Toast.LENGTH_SHORT
                ).show()
                enviarDatosActitity(marcaSeleccionada)
            }
        })

        return fragmentView!!
    }

    private fun setUpAdapter(): MyAdapter {
        val adapter = MyAdapter()
        adapter.setValues(cargarLista())
        return adapter
    }

    private fun enviarDatosActitity(texto: String?) {
        val intent = Intent(getContext(), DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString("marca", texto)
        bundle.putDouble("precio", Math.random() * 10000000)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private inner class MyAdapter : BaseAdapter() {
        private val values: MutableList<String?> = ArrayList<String?>()

        override fun getCount(): Int {
            return values.size
        }

        override fun getItem(i: Int): Any? {
            return values.get(i)
        }

        override fun getItemId(i: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = layoutInflater.inflate(R.layout.item_list, parent, false)
            }

            val textToShow = getItem(position).toString()
            val textView = view!!.findViewById<TextView>(R.id.textViewItemList)
            textView.text = textToShow

            return view
        }


        fun setValues(newValues: MutableList<String?>) {
            values.clear()
            values.addAll(newValues)
        }
    }

    private fun cargarLista(): ArrayList<String?> {
        val valores = ArrayList<String?>()
        valores.add("Ferrari")
        valores.add("Lada")
        valores.add("Fiat")
        valores.add("Mazda")
        valores.add("Lamborghini")
        valores.add("BMW")
        valores.add("Ford")
        valores.add("Chevrolet")
        valores.add("Suzuki")
        valores.add("Volvo")
        valores.add("Mercedes Benz")
        valores.add("Toyota")
        valores.add("Hyundai")
        valores.add("Kia")
        valores.add("Honda")
        valores.add("Opel")
        valores.add("MG")
        valores.add("Aston Martin")
        valores.add("Alpine")
        return valores
    }
}