package com.eisi.fia.ues.sv.examen2a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class controlBD
{
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public controlBD(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
                super(context,"examen2A.s3db",null,1);
        }
        public void onCreate (SQLiteDatabase db)
        {
            try
            {
                //Creacion de tablas de base de datos
                db.execSQL("CREATE TABLE EQUIPO(CODEQ VARCHAR(3) NOT NULL, NOMEQ VARCHAR(30) NOT NULL, GANADOS INTEGER NOT NULL, PERDIDOS INTEGER NOT NULL, EMPATADOS INTEGER NOT NULL, PRIMARY KEY (CODEQ));");
                db.execSQL("CREATE TABLE PARTIDOSCLAUSURA(NUMFECHA VARCHAR(2) NOT NULL, CODEQ VARCHAR(3) NOT NULL, GOLESAFAVOR INTEGER NOT NULL, GOLESENCONTRA INTEGER NOT NULL, CODRIVAL VARCHAR(3) NOT NULL, PRIMARY KEY (NUMFECHA));");
            }
            catch (SQLException e)
            {
              e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            //Logica Actualizacion
            db.execSQL("DROP TABLE IF EXISTS EQUIPO");
            db.execSQL("DROP TABLE IF EXISTS PARTIDOSCLAUSURA");
        }
    }

    public void abrir () throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar ()
    {
        DBHelper.close();
    }


    public boolean verificarIntegridad(Object dato, int relacion)
    {
        switch (relacion)
        {
            //verifica que no exista el equipo
            case 1:
            {
                Equipo equipo = (Equipo) dato;
                String[] id = {equipo.getCodeq()};
                abrir();
                Cursor c1 = db.query("equipo", null, "codeq = ?", id, null, null, null);
                if (c1.moveToFirst())
                {
                    return true;
                }
                return false;
            }
            //verifica que exista el equipo
            case 2:
            {
                Equipo equipo = (Equipo)dato;
                String[] id = {equipo.getCodeq()};
                abrir();
                Cursor c2 = db.query("equipo",null,"codeq = ?",id,null,null,null);
                if(c2.moveToFirst())
                {
                    return true;
                }

                return  false;
            }
            //verifica si hay registro de equipo en otra tabla
            case 3:
            {
                Equipo equipo = (Equipo)dato;
                Cursor c3 = db.query(true, "partidosclausura", new String[] { "codeq" }, "codeq='"+equipo.getCodeq()+"'",null, null, null, null, null);
                if(c3.moveToFirst())
                    return true;
                else
                    return false;
            }
        }

        return true;
    }

    public String insertarEquipo(Equipo equipo)
    {
        String regInsertado = "Registro Insertado N: ";
        long contador;

        ContentValues eq = new ContentValues();

        eq.put("codeq",equipo.getCodeq());
        eq.put("nomeq",equipo.getNomeq());
        eq.put("ganados",equipo.getGanados());
        eq.put("perdidos",equipo.getPerdidos());
        eq.put("empatados",equipo.getEmpatados());
        contador = db.insert("equipo",null,eq);
        regInsertado = regInsertado + contador;
        return regInsertado;
    }

    public String insertarPartidoClausura(PartidosClausura partidosClausura)
    {
        String regInsertado = "Registro Insertado N: ";
        long contador;

        ContentValues pc = new ContentValues();

        pc.put("numfecha",partidosClausura.getNumfecha());
        pc.put("codeq",partidosClausura.getCodeq());
        pc.put("golesafavor",partidosClausura.getGolesafavor());
        pc.put("golesencontra",partidosClausura.getGolesencontra());
        pc.put("codrival",partidosClausura.getCodrival());
        contador = db.insert("partidosclausura",null,pc);
        regInsertado = regInsertado + contador;
        return regInsertado;
    }


    public String llenadoInicial()
    {
        ArrayList<Equipo> equipos = new ArrayList<>();
        ArrayList<PartidosClausura> pc = new ArrayList<>();

        equipos.add(new Equipo("SA","San Alonso",2,3,1));
        equipos.add(new Equipo("SB","San Cristobal",4,2,6));
        for(Equipo eq :  equipos)
        {
            insertarEquipo(eq);
        }

        pc.add(new PartidosClausura("J1","SA",13,10,"SB"));
        pc.add(new PartidosClausura("j2","SC",10,8,"SD"));
        for(PartidosClausura partidos : pc)
        {
            insertarPartidoClausura(partidos);
        }

        return "llenado exitosamente";
    }

    public String eliminarEquipo(Equipo equipo)
    {
        String regAfectados = "filas afectadas = ";
        int contador=0;

        //verifica que exista el equipo
        if(verificarIntegridad(equipo, 2))
        {
            //verifica si hay partidos relacionados al equipo
             if(verificarIntegridad(equipo,3))
             {
                 return "No se puede eliminar el equipo porque tiene partidos relacionados";
             }
             else
             {
                 contador+=db.delete("equipo", "codeq='"+equipo.getCodeq()+"'", null);
             }
        }
        else
        {
            return " Registros con codigo "+equipo.getCodeq() + "No existe";
        }
        return regAfectados;
    }
}
