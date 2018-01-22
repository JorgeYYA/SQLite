package com.example.jorgeyya.a4a;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Configuracion extends Fragment {

    private interfazConfiguracion interfaz;
    EditText id;
    String idTexto, nombreTabla;
    RadioButton alumnos, profesores;

    Button borrarID, borrarTodo;


    public static Configuracion newInstance() {
        
        Bundle args = new Bundle();
        
        Configuracion fragment = new Configuracion();
        fragment.setArguments(args);
        return fragment;
    }


    public Configuracion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_configuracion, container, false);

        id = (EditText) v.findViewById(R.id.identificador);

        borrarID = (Button) v.findViewById(R.id.btn_borrar_id);

        borrarTodo = (Button) v.findViewById(R.id.btn_borrar_todo);

        alumnos = (RadioButton) v.findViewById(R.id.radio_alumnno);

        profesores = (RadioButton) v.findViewById(R.id.radio_profesor);



        borrarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTexto = id.getText().toString();

                nombreTabla = null;

                if(alumnos.isChecked()){

                    nombreTabla = "ALUMNOS";

                }else if(profesores.isChecked()){

                    nombreTabla = "PROFESORES";

                }


                interfaz.interfazConfiguracion(idTexto,nombreTabla);

            }
        });

        borrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTexto = id.getText().toString();



                interfaz.interfazConfiguracion(idTexto,"eliminateall");

            }
        });


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        interfaz = (interfazConfiguracion) activity;
        super.onAttach(activity);
    }

    public interface interfazConfiguracion{
        public void interfazConfiguracion(String id, String nombre);

    }

}
