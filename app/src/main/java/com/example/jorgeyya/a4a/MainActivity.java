package com.example.jorgeyya.a4a;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Alumnos.interfazAlumno, Profesores.interfazProfesor, Configuracion.interfazConfiguracion, Consultas.interfazConsultas {

    private DbAdapter dbAdapter;

    Button prof,alum, bbdd, consultas, asignatura, verAsig;

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();


        prof = (Button) findViewById(R.id.add_profesor);

        alum = (Button) findViewById(R.id.add_alumno);

        bbdd = (Button) findViewById(R.id.conf_bbdd);

        consultas = (Button) findViewById(R.id.btn_consultas);

        asignatura = (Button) findViewById(R.id.btn_asig);

        verAsig = (Button) findViewById(R.id.ver_asig);




        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,Profesores.newInstance()).addToBackStack(null);
                ft.commit();


            }
        });

        alum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,Alumnos.newInstance()).addToBackStack(null);
                ft.commit();

            }
        });

        bbdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,Configuracion.newInstance()).addToBackStack(null);
                ft.commit();

            }
        });

        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,Consultas.newInstance()).addToBackStack(null);
                ft.commit();

            }
        });

        asignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,FragmentAsignaturas.newInstance(null,null)).addToBackStack(null);
                ft.commit();

            }
        });

        verAsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame_fragment,FragmentVerAsignaturas.newInstance(null,null)).addToBackStack(null);
                ft.commit();

            }
        });


    }

    @Override
    public void interfazAlumno(String nombre, String edad, String ciclo, String curso, String nota) {

        Log.d("prueba",nombre+" "+edad+" "+ciclo+" "+curso+" "+nota);
        dbAdapter.insertarAlumno(nombre,edad,ciclo,curso,nota);

    }



    @Override
    public void interfazProfesor(String nombre, String edad, String ciclo, String curso, String despacho) {

        Log.d("prueba",nombre+" "+edad+" "+ciclo+" "+curso+" "+despacho+"ee");
        dbAdapter.insertarProfesor(nombre,edad,ciclo,curso,despacho);

    }

    @Override
    public void interfazConfiguracion(String id, String nombreTabla) {

        Log.d("prueba","fffff");

        if(nombreTabla.equals("eliminateall")){

            dbAdapter.eliminarTodo();
        }else {


            dbAdapter.eliminarDatos(nombreTabla,id);


        }




    }

    @Override
    public ArrayList<String> interfazConsultas(int opcion1, int opcion2, String texto1, String texto2) {

        ArrayList<String> resultado = new ArrayList<String>();
        Log.d("asdfgh",opcion1+"   "+opcion2);
        resultado = dbAdapter.consultarDatos(opcion1,opcion2,texto1,texto2);

        return resultado;

    }


}
