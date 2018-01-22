package com.example.jorgeyya.a4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;


public class DbAdapter {


    private static final String DATABASE_NAME = "dbclase.db";
    private static final String DATABASE_TABLE_ALUMNOS = "ALUMNOS";
    private static final String DATABASE_TABLE_PROFESORES = "PROFESORES";
    private static final String DATABASE_TABLE_ASIGNATURAS = "ASIGNATURAS";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";
    private static final String DESPACHO = "despacho";

    private static final String NOMBRE_ASIGNATURA = "nombre_asig";
    private static final String HORAS_ASIGNATURA = "horas_asig";


    private static final String DATABASE_CREATE_1 = "CREATE TABLE "+DATABASE_TABLE_ALUMNOS+" (_id integer primary key autoincrement, "+NOMBRE+" text, "+EDAD+" text, "+CICLO+" text, "+CURSO+" text, "+NOTA+" text);";
    private static final String DATABASE_CREATE_2 = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (_id integer primary key autoincrement, "+NOMBRE+" text, "+EDAD+" text, "+CICLO+" text, "+CURSO+" text, "+DESPACHO+" text);";

    private static final String DATABASE_CREATE_3 = "CREATE TABLE "+DATABASE_TABLE_ASIGNATURAS+" (_id integer primary key autoincrement, "+NOMBRE_ASIGNATURA+" text, "+HORAS_ASIGNATURA+" text);";


    private static final String DATABASE_DROP_1 = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ALUMNOS+";";
    private static final String DATABASE_DROP_2 = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";
    private static final String DATABASE_DROP_3 = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ASIGNATURAS+";";


    private final Context context;

    private MyDbHelper dbHelper;

    private SQLiteDatabase db;

    public DbAdapter(Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarAlumno(String nombre, String edad, String ciclo, String curso, String nota){

        ContentValues newValues = new ContentValues();

        newValues.put(NOMBRE,nombre);
        newValues.put(EDAD,edad);
        newValues.put(CICLO,ciclo);
        newValues.put(CURSO,curso);
        newValues.put(NOTA,nota);

        db.insert(DATABASE_TABLE_ALUMNOS,null,newValues);


    }

    public void insertarAsignatura(String nombre, String horas){

        ContentValues newValues = new ContentValues();

        newValues.put(NOMBRE_ASIGNATURA,nombre);
        newValues.put(HORAS_ASIGNATURA,horas);


        db.insert(DATABASE_TABLE_ASIGNATURAS,null,newValues);


    }

    public Cursor recogerDatos(String select){



        //Cursor c = db.rawQuery(" SELECT * FROM "+tabla+" WHERE nombre=? ", args);
        Cursor c = db.rawQuery(select, null);



        return c;
    }

    public void eliminarDatos(String tabla, String id){




        //Cursor c = db.rawQuery(" SELECT * FROM "+tabla+" WHERE nombre=? ", args);
        //db.delete(tabla,"where _id = "+id,null);
        db.delete(tabla, "_id" + "=" + id, null);



    }

    public void eliminarTodo(){

        //File database = new File("data//data//com.example.jorgeyya.a4a/database/"+DATABASE_NAME);

        db.delete(DATABASE_TABLE_ALUMNOS, null, null);
        db.delete(DATABASE_TABLE_PROFESORES, null, null);

    }

    public ArrayList<String> consultarDatos(int opcion1, int opcion2, String busqueda1, String busqueda2){
        String v1="", v2="", v3="";

        ArrayList<String> resultado = new ArrayList<String>();

        switch(opcion1){

            case 0: v1 =DATABASE_TABLE_ALUMNOS;break;
            case 1: v1 =DATABASE_TABLE_PROFESORES;break;
            case 2: v1 =DATABASE_TABLE_ALUMNOS;break;

        }

        switch(opcion2){

            case 0: v2 = " SELECT * FROM "+v1+" where "+CICLO+" = '"+busqueda1+"'";break;
            case 1: v2 = " SELECT * FROM "+v1+" where "+CURSO+" = '"+busqueda1+"'";break;
            case 2: v2 = " SELECT * FROM "+v1+" where ("+CICLO+" = '"+busqueda1+"') AND ("+CURSO+" = '"+busqueda2+"')";break;
            case 3: v2 = " SELECT * FROM "+v1;break;

        }

        if(opcion1==2){

            switch(opcion2){

                case 0: v3 = " SELECT * FROM "+DATABASE_TABLE_PROFESORES+" where "+CICLO+" = '"+busqueda1+"'";break;
                case 1: v3 = " SELECT * FROM "+DATABASE_TABLE_PROFESORES+" where "+CURSO+" = '"+busqueda1+"'";break;
                case 2: v3 = " SELECT * FROM "+DATABASE_TABLE_PROFESORES+" where ("+CICLO+" = '"+busqueda1+"') AND ("+CURSO+" = '"+busqueda2+"')";break;
                case 3: v3 = " SELECT * FROM "+DATABASE_TABLE_PROFESORES;break;

            }

        }



        //Log.d("prueba",id);

        Cursor c = recogerDatos(v2);
        Log.d("pruebasu",v2);

        resultado.add(v1);
        resultado = recorreCursor(c, resultado);

        if(opcion1==2){

            resultado.add("PROFESORES");
            c = recogerDatos(v3);
            resultado = recorreCursor(c, resultado);

        }
        return resultado;

    }

    public ArrayList<String> mostrarAsignaturas(){

        String consulta;
        ArrayList<String> resultado = new ArrayList<String>();

        consulta = " SELECT * FROM "+DATABASE_TABLE_ASIGNATURAS;
        //Log.d("prueba",id);

        Cursor c = recogerDatos(consulta);


        resultado.add("ASIGNATURAS");
        resultado = recorreCursor(c, resultado);
        return resultado;

    }

    public ArrayList<String> recorreCursor(Cursor c,  ArrayList<String> resultado){

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                int numero = c.getColumnCount();

                //AQUI hay un outOfBounds exception
                resultado.add("-----------------");
                for (int i=0;i<numero;i++) {

                    resultado.add(c.getColumnName(i)+": "+c.getString(i));
                    Log.d("pruebasusu", "jooorl " + c.getColumnName(i)+": "+c.getString(i));
                }



            } while (c.moveToNext());
        }
        return resultado;

    }



    public void insertarProfesor(String nombre, String edad, String ciclo, String curso, String despacho){

        ContentValues newValues = new ContentValues();

        newValues.put(NOMBRE,nombre);
        newValues.put(EDAD,edad);
        newValues.put(CICLO,ciclo);
        newValues.put(CURSO,curso);
        newValues.put(DESPACHO,despacho);

        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);

    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_1);
            db.execSQL(DATABASE_CREATE_2);
            db.execSQL(DATABASE_CREATE_3);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_1);
            db.execSQL(DATABASE_DROP_2);
            db.execSQL(DATABASE_DROP_3);
            onCreate(db);
        }

    }

}