package com.example.jorgeyya.a4a;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Consultas extends Fragment {

    int valor1, valor2;

    Button buscar;

    ArrayList<String> resultado;



    EditText op1,op2;

    String opcion1, opcion2;

    ListView lista;

    ArrayAdapter<String> adaptador;

    private interfazConsultas interfaz;

    public static Consultas newInstance() {

        Bundle args = new Bundle();

        Consultas fragment = new Consultas();
        fragment.setArguments(args);
        return fragment;
    }

    public Consultas() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        resultado = new ArrayList<String>();

        View v = inflater.inflate(R.layout.fragment_consultas, container, false);

        buscar = (Button) v.findViewById(R.id.btn_buscar);



        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_uno);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.menu_uno, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner_dos);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.menu_dos, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        op1 = (EditText) v.findViewById(R.id.opcion1);
        op2 = (EditText) v.findViewById(R.id.opcion2);




        lista = (ListView)v.findViewById(R.id.lista_resultado);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Log.d("asdfgh",""+position);
                valor1 = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               // Log.d("asdfgh",""+position);
                valor2 = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                opcion1 = op1.getText().toString();

                opcion2 = op2.getText().toString();

                resultado = interfaz.interfazConsultas(valor1,valor2, opcion1, opcion2);

                adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,resultado);

                lista.setAdapter(adaptador);

                registerForContextMenu(lista);

            }
        });

        return v;
    }

    public void onAttach(Activity activity) {
        interfaz = (interfazConsultas) activity;
        super.onAttach(activity);
    }

    public interface interfazConsultas{
        public ArrayList<String> interfazConsultas(int opcion1, int opcion2, String op1, String op2);

    }

}
