package com.example.ap41front.Commande;

import java.util.ArrayList;

public class Commandes {

    private ArrayList<Commande> commandes ;

    public Commandes() {
        this.commandes = new ArrayList();}

    public ArrayList<Commande> getCommandes() {
        return commandes;}

    public Integer getNbCommandes(){
        return commandes.size();}

    public void ajouterCommandes(Commande uneCommande){
        ArrayList<Commande> listeCommandes = new ArrayList<Commande>();
        commandes.add(uneCommande);}

    public Commande getcommande(Integer unIndex){
        return commandes.get(unIndex);}

}
