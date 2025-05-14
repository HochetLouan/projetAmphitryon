<?php
require_once 'DBConnex.php';
require_once '../lib/AutoLoader.php';

class auth{
    public static function verification($login , $mdp){
        $sql = "select login , statut  from UTILISATEUR where login = :login and password = :password";


        $requetePrepa = DBConnex::getInstance()->prepare($sql);

        $requetePrepa->bindParam( ":login", $login);
        $requetePrepa->bindParam( ":password" ,  $mdp);

        $requetePrepa->execute();

    return $requetePrepa->fetch(PDO::FETCH_ASSOC);
    }
}