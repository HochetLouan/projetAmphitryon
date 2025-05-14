package com.example.ap41front;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.ap41front.ligneCommande.LigneCommande;

import java.util.List;

public class LigneCommandeListAdapter extends BaseAdapter {

    private List<LigneCommande> listeLignesCommandes;
    private LayoutInflater layoutInflater;
    private Context context;
    public LigneCommandeListAdapter(Context aContext, List<LigneCommande> listeLignesCommandes) {
        this.context = aContext;
        this.listeLignesCommandes = listeLignesCommandes;
        layoutInflater = LayoutInflater.from(aContext);}

    @Override
    public int getCount() {
        return listeLignesCommandes.size();
    }

    @Override
    public Object getItem(int position) {
        return listeLignesCommandes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LigneCommandeListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_plat, null);
            holder = new LigneCommandeListAdapter.ViewHolder();
            holder.nom_platview = convertView.findViewById(R.id.textView_id);
            holder.commentaireview = convertView.findViewById(R.id.textView_etat);
            holder.quantiteview = convertView.findViewById(R.id.textView_table);
            holder.etatview = convertView.findViewById(R.id.textView_table);
            convertView.setTag(holder);
        } else {
            holder = (LigneCommandeListAdapter.ViewHolder) convertView.getTag();
        }

        LigneCommande ligneCommande = this.listeLignesCommandes.get(position);
        holder.nom_platview.setText((ligneCommande.getNomPlat()));
        holder.commentaireview.setText(ligneCommande.getCommentaire());
        holder.quantiteview.setText(Integer.toString(ligneCommande.getQuantite()));
        holder.etatview.setText((ligneCommande.getEtat()));


        return convertView;
    }

    static class ViewHolder {
        TextView nom_platview;
        TextView commentaireview;
        TextView quantiteview;
        TextView etatview;
    }

    
}
