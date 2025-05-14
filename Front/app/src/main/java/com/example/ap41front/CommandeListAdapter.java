package com.example.ap41front;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ap41front.Commande.Commande;

import java.util.List;

public class CommandeListAdapter extends BaseAdapter {

    private List<Commande> listeCommandes;
    private LayoutInflater layoutInflater;
    private Context context;

    public CommandeListAdapter(Context aContext, List<Commande> listeCommandes) {
        this.context = aContext;
        this.listeCommandes = listeCommandes;
        layoutInflater = LayoutInflater.from(aContext);}

    @Override
    public int getCount() {
        return listeCommandes.size();
    }

    @Override
    public Object getItem(int position) {
        return listeCommandes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CommandeListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_commande, null);
            holder = new CommandeListAdapter.ViewHolder();
            holder.idView = convertView.findViewById(R.id.textView_id);
            holder.etatView = convertView.findViewById(R.id.textView_etat);
            holder.tableView = convertView.findViewById(R.id.textView_table);
            convertView.setTag(holder);
        } else {
            holder = (CommandeListAdapter.ViewHolder) convertView.getTag();
        }

        Commande commande = this.listeCommandes.get(position);
        holder.idView.setText(Integer.toString(commande.getId()));
        holder.etatView.setText(commande.getEtat());
        holder.tableView.setText(Integer.toString(commande.getTable()));


        return convertView;
    }

    static class ViewHolder {
        TextView idView;
        TextView etatView;
        TextView tableView;
    }
}
