<?php
require_once '../dao/Param.php';
require_once '../dao/ServeurDAO.php';


print(json_encode(ServeurDAO::getCommandes()));