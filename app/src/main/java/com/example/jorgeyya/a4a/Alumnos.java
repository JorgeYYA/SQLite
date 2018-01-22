package com.example.jorgeyya.a4a;


        import android.app.Activity;
        import android.content.Context;
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
public class Alumnos extends Fragment {

    private interfazAlumno interfaz;
    EditText nombre, edad, ciclo, curso, nMedia;
    String nombreTexto, edadTexto, cicloTexto, cursoTexto, nMediaTexto;
    Button guardar;


    public Alumnos() {
        // Required empty public constructor
    }

    public static Alumnos newInstance() {

        Bundle args = new Bundle();

        Alumnos fragment = new Alumnos();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_alumnos, container, false);

        nombre = (EditText) v.findViewById(R.id.nom_alumno);
        edad = (EditText) v.findViewById(R.id.edad_alumno);
        ciclo = (EditText) v.findViewById(R.id.ciclo_alumno);
        curso = (EditText) v.findViewById(R.id.curso_alumno);
        nMedia = (EditText) v.findViewById(R.id.n_media_alumno);

        guardar = (Button) v.findViewById(R.id.btn_guardar_alumno);




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nombreTexto = nombre.getText().toString();
                edadTexto = edad.getText().toString();
                cicloTexto = ciclo.getText().toString();
                cursoTexto = curso.getText().toString();
                nMediaTexto = nMedia.getText().toString();


                interfaz.interfazAlumno(nombreTexto,edadTexto,cicloTexto,cursoTexto,nMediaTexto);

            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        interfaz = (interfazAlumno) activity;
        super.onAttach(activity);
    }

    public interface interfazAlumno{
        public void interfazAlumno(String nombre, String edad, String ciclo, String curso, String nota);

    }

}
