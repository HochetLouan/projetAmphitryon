package com.example.ap41front.ligneCommande;

public class LigneCommande {
    private int idPlat;
    private int quantite;
    private String commentaire;
    private String etat;
    private String nomPlat;


    public LigneCommande(int idPlat, int quantite, String commentaire, String etat, String nomPlat) {
        this.idPlat = idPlat;
        this.quantite = quantite;
        this.commentaire = commentaire;
        this.etat = etat;
        this.nomPlat = nomPlat;
    }


    public int getIdPlat() {
        return idPlat;
    }


    public int getQuantite() {
        return quantite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getEtat() {
        return etat;
    }


    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }


    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }
}
