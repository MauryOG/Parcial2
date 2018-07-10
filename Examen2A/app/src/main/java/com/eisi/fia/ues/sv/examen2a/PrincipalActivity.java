package com.eisi.fia.ues.sv.examen2a;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PrincipalActivity extends ListActivity
{
    String[] menu={"Eliminar Equipo","Actualizar Partido","Consultar Partidos","Llenado Inicial"};
    String[] actividades={"EliminarEquipoActivity","ActualizarPartidosClausuraActivity","ConsultarPartidosClausuraActvity"};
    controlBD BDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
        BDHelper = new controlBD(this);
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        if(position!=3)
        {
            String nombreValue = actividades[position];
            try
            {
                Class<?> clase = Class.forName("com.eisi.fia.ues.sv.examen2a"+nombreValue);
                Intent intent = new Intent(this,clase);
                this.startActivity(intent);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            BDHelper = new controlBD(this);
            BDHelper.abrir();
            String tost = BDHelper.llenadoInicial();
            BDHelper.cerrar();
            Toast.makeText(this,tost,Toast.LENGTH_SHORT).show();
        }
    }
}
