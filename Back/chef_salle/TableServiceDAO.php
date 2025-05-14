<?php
class TableServiceDAO{


    public static function getLesTables(){
        $result = [];
        $requetePrepa = DBConnex::getInstance()->prepare("SELECT * from TABLES_SERVICE" );
       
        $requetePrepa->execute();
        $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 
        
        if(!empty($liste)){
            foreach($liste as $table){
                $uneTable = new TableServiceDTO(null,null,null,null,null);
                $uneTable->hydrate($table);
                $result[] = $uneTable;
            }
        }
        return $result;
    }

    public static function postCreerTable(TableServiceDTO $tableService ){
        $requetePrepa = DBConnex::getInstance()->prepare("INSERT INTO `TABLES_SERVICE`
        (DateDuJour , idService , numTable , nbPlace , id)
        VALUES(CURRENT_DATE,:idService, :numTable , :nbPlace , :id) ");

        $idService=$tableService->getIdService();
        $numTable=$tableService->getNumTable();
        $nbPlace=$tableService->getNbPlace();
        $id=$tableService->getId();

        $requetePrepa->bindParam(":idService",$idService);
        $requetePrepa->bindParam(":numTable", $numTable);
        $requetePrepa->bindParam(":nbPlace", $nbPlace);
        $requetePrepa->bindParam(":id",$id);

        $requetePrepa->execute();

        return json_encode($requetePrepa->fetchAll(PDO::FETCH_ASSOC));
    }

    public static function deleteTable(TableServiceDTO $tableService){
        $requetePrepa = DBConnex::getInstance()->prepare("DELETE FROM TABLE_SERVICE
        WHERE DateDuJour =:DateDuJour 
        AND idService =:idService 
        AND numTable = :numTable;");

        $idService=$tableService->getIdService();
        $numTable=$tableService->getNumTable();
        $id=$tableService->getId();
        $DateDuJour=$tableService->getDateDuJour();
        $nbPlace=$tableService->getNbPlace();


        $requetePrepa->bindParam(":idService",$idService);
        $requetePrepa->bindParam(":numTable", $numTable);
        $requetePrepa->bindParam(":id",$id);
        $requetePrepa->bindParam(":DateDuJour",$DateDuJour);
        $requetePrepa->bindParam(":nbPlace", $nbPlace);

        $requetePrepa->execute();

        return json_encode($requetePrepa->fetchAll(PDO::FETCH_ASSOC));
    }

    public static function modifierTable1(TableServiceDTO $tableService){
        $requetePrepa = DBConnex::getInstance()->prepare("UPDATE TABLE_SERVICE
        SET id =:id
        WHERE DateDuJour =:DateDuJour
        AND idService =:idService
        AND numTable =:numTable ;");

        $idService=$tableService->getIdService();
        $numTable=$tableService->getNumTable();
        $id=$tableService->getId();
        $DateDuJour=$tableService->getDateDuJour();
    

        $requetePrepa->bindParam(":idService",$idService);
        $requetePrepa->bindParam(":numTable", $numTable);
        $requetePrepa->bindParam(":id",$id);
        $requetePrepa->bindParam(":DateDuJour",$DateDuJour);

        $requetePrepa->execute();
    }

    public static function modifierTable2(TableServiceDTO $tableService){
        $requetePrepa = DBConnex::getInstance()->prepare("UPDATE TABLE_SERVICE
        SET nbPlace = :nbPlace
        WHERE DateDuJour =:DateDuJour  AND idService :idService =  AND numTable = :numTable ;");

        $idService=$tableService->getIdService();
        $numTable=$tableService->getNumTable();
        $id=$tableService->getId();
        $DateDuJour=$tableService->getDateDuJour();
        $nbPlace=$tableService->getNbPlace();
    
        $requetePrepa->bindParam(":idService",$idService);
        $requetePrepa->bindParam(":numTable", $numTable);
        $requetePrepa->bindParam(":id",$id);
        $requetePrepa->bindParam(":DateDuJour",$DateDuJour);
        $requetePrepa->bindParam(":nbPlace", $nbPlace);

        $requetePrepa->execute();

        return json_encode($requetePrepa->fetchAll(PDO::FETCH_ASSOC));
    }






//trouve un nom pour les modifcation des tables













}