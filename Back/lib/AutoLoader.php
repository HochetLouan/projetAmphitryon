<?php

spl_autoload_register('Autoloader::autoloadDao');
spl_autoload_register('Autoloader::autoloadLib');


class AutoLoader{
     
    private string $file;
    
    static function autoloadDao($class): void
    {
        $file = '../dao/' . $class . '.php';
        if(is_file($file)&& is_readable($file)){
            require $file;
        }
        
    }
    
    
    static function autoloadLib($class): void{
        $file = '../lib/' . $class . '.php';
        if(is_file($file)&& is_readable($file)){
            require $file;
        }
      
    }
}


