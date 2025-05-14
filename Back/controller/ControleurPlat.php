<?php
require_once '../dao/Param.php';
require_once '../dao/Auth.php';


print(json_encode(ServeurDAO::getDetail($_POST['id'])));