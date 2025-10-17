package com.inforcap.exampledrawerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CarsFragment extends Fragment {

    private ListView listView;
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_cars, container, false);

        listView = fragmentView.findViewById(R.id.listViewLibrary);

        MyAdapter adapter = setUpAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String marcaSeleccionada = adapter.getItem(i).toString();
                Toast.makeText(adapterView.getContext(),"Elemento seleccionado: " +
                        marcaSeleccionada,Toast.LENGTH_SHORT).show();
                enviarDatosActitity(marcaSeleccionada);
            }
        });

        return fragmentView;
    }

    private MyAdapter setUpAdapter(){
        MyAdapter adapter = new MyAdapter();
        adapter.setValues(cargarLista());
        return adapter;
    }

    private void enviarDatosActitity(String texto){
        Intent intent = new Intent(getContext(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("marca",texto);
        bundle.putDouble("precio",Math.random()*10000000);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {

        private final List<String> values = new ArrayList<>();

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Object getItem(int i) {
            return values.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_list, container, false);
            }

            String textToShow = getItem(position).toString();
            ((TextView) convertView.findViewById(R.id.textViewItemList)).setText(textToShow);
            return convertView;
        }

        public void setValues(List<String> newValues){
            values.clear();
            values.addAll(newValues);
        }


    }

    private ArrayList<String> cargarLista(){
        ArrayList<String> valores = new ArrayList<>();
        valores.add("Ferrari");
        valores.add("Lada");
        valores.add("Fiat");
        valores.add("Mazda");
        valores.add("Lamborghini");
        valores.add("BMW");
        valores.add("Ford");
        valores.add("Chevrolet");
        valores.add("Suzuki");
        valores.add("Volvo");
        valores.add("Mercedes Benz");
        valores.add("Toyota");
        valores.add("Hyundai");
        valores.add("Kia");
        valores.add("Honda");
        valores.add("Opel");
        valores.add("MG");
        valores.add("Aston Martin");
        valores.add("Alpine");
        return valores;
    }
}