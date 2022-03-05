package edu.fje.dam2.projecte_simon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Classe que controla la llista on es mostren els artistes
 * @version 5.0 27.01.2020
 */

public class LlistaJugadors  extends ArrayAdapter<Jugador> {
    private Activity context;
    List<Jugador> jugadors;

    public LlistaJugadors(Activity context, List<Jugador> jugadors) {
        super(context, R.layout.llistajugadors, jugadors);
        this.context = context;
        this.jugadors = jugadors;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.llistajugadors, null, true);

        TextView usr =  listViewItem.findViewById(R.id.usr);
        TextView pts = listViewItem.findViewById(R.id.pts);

        Jugador jugador = jugadors.get(position);
        usr.setText(jugador.getNom());
        pts.setText(jugador.getPunts());

        return listViewItem;
    }
}
