package com.example.jorgeyya.a4a;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profesores extends Fragment {

    private interfazProfesor interfaz;
    EditText nombre, edad, ciclo, curso, despacho;
    String nombreTexto, edadTexto, cicloTexto, cursoTexto, despachoTexto;
    Button guardar;

    public static Profesores newInstance() {
        
        Bundle args = new Bundle();
        
        Profesores fragment = new Profesores();
        fragment.setArguments(args);
        return fragment;
    }

    public Profesores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profesores, container, false);

        nombre = (EditText) v.findViewById(R.id.nom_profesor);
        edad = (EditText) v.findViewById(R.id.edad_profesor);
        ciclo = (EditText) v.findViewById(R.id.ciclo_profesor);
        curso = (EditText) v.findViewById(R.id.curso_profesor);
        despacho = (EditText) v.findViewById(R.id.despacho_profesor);

        guardar = (Button) v.findViewById(R.id.btn_guardar_profesor);




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombreTexto = nombre.getText().toString();
                edadTexto = edad.getText().toString();
                cicloTexto = ciclo.getText().toString();
                cursoTexto = curso.getText().toString();
                despachoTexto = despacho.getText().toString();


                interfaz.interfazProfesor(nombreTexto,edadTexto,cicloTexto,cursoTexto,despachoTexto);


            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        interfaz = (interfazProfesor) activity;
        super.onAttach(activity);
    }

    public interface interfazProfesor{
        public void interfazProfesor(String nombre, String edad, String ciclo, String curso, String despacho);

    }

}
