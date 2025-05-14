package com.example.ap41front.ligneCommande;

import java.util.ArrayList;
import java.util.List;

public class LignesCommandes {
    private List<LigneCommande> lignesCommandes;

    public LignesCommandes() {
        lignesCommandes = new ArrayList<>(); // ← Initialisation obligatoire ici
    }

    public void ajouterLigneCommandes(LigneCommande ligneCommande) {
        lignesCommandes.add(ligneCommande); // ← Crash ici si la liste est null
    }

    public List<LigneCommande> getLignesCommandes() {
        return lignesCommandes;
    }

}


