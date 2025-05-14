<?php

require_once '../lib/AutoLoader.php';
class ServeurDAO{
    public static function getCommandes(){
        $requetePrepa = DBConnex::getInstance()->prepare("SELECT idCommande, EtatCommande, numTable FROM commande;");
        $requetePrepa->execute();
        return $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
    }

    public static function getDetail($id){
        $requetePrepa = DBConnex::getInstance()->prepare("SELECT ligne_commande.idPlat, plat.nomPlat, ligne_commande.commentaire, ligne_commande.quantite, ligne_commande.etat FROM ligne_commande, plat WHERE ligne_commande.idPlat = plat.idPlat AND ligne_commande.idCommande = :id;");
        $requetePrepa->bindParam(":id", $id);
        $requetePrepa->execute();
        return $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
    }

    public static function getAllPlat(){
        $requetePrepa = DBConnex::getInstance()->prepare("SELECT nomPlat FROM plat;");
        $requetePrepa->execute();
        return $requetePrepa->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>