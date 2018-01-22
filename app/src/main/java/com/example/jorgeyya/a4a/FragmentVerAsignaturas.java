package com.example.jorgeyya.a4a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentVerAsignaturas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentVerAsignaturas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVerAsignaturas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView nombre, horas;

    ArrayAdapter<String> adaptador;

    private DbAdapter dbAdapter;

    ArrayList<String> resultado;

    ListView lista;

    private OnFragmentInteractionListener mListener;

    public FragmentVerAsignaturas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVerAsignaturas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVerAsignaturas newInstance(String param1, String param2) {
        FragmentVerAsignaturas fragment = new FragmentVerAsignaturas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_asignaturas, container, false);

        resultado = new ArrayList<String>();

        dbAdapter = new DbAdapter(getActivity());
        dbAdapter.open();

        nombre = (TextView) v.findViewById(R.id.nom_asig);
        horas = (TextView) v.findViewById(R.id.horas_asig);

        lista = (ListView)v.findViewById(R.id.lista_resultado);




        resultado = dbAdapter.mostrarAsignaturas();

        adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,resultado);

        lista.setAdapter(adaptador);

        registerForContextMenu(lista);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
