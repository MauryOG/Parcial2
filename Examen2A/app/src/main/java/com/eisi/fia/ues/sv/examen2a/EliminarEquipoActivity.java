package com.eisi.fia.ues.sv.examen2a;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEquipoActivity extends Activity
{
    EditText editcodeq;
    controlBD controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_equipo);
        controlhelper = new controlBD(this);
        editcodeq = (EditText) findViewById(R.id.editcodeq);
    }

    public void eliminarEquipo(View v)
    {
        String regEliminados;
        Equipo equipo = new Equipo();
        equipo.setCodeq(editcodeq.getText().toString());
        controlhelper.abrir();
        regEliminados = controlhelper.eliminarEquipo(equipo);
        controlhelper.cerrar();
        Toast.makeText(this,regEliminados,Toast.LENGTH_SHORT).show();
    }
}
