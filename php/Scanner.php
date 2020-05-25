<?php
error_reporting(~E_ALL);

function scanPort($host)
{

    function checkPort($host , $port)
    {
        try {

            $address = "tcp://".trim($host).":$port";
            $timeout = 3;

            $connect = stream_socket_client($address, $errno, $errstr, $timeout);

            if ($connect) {
              fclose($connect);
              return true;
            }
        } catch (Exception $exception) {
            return false;
        }
    }

    foreach(range(1,65535) as $port){
        if (checkPort($host, $port)) {
            echo "port [$port] : open!\n";
        }
    }

}

function getHost()
{
    echo "Please enter the host you want to scan\n";
    $handle = fopen ("php://stdin","r");
    return fgets($handle);
}

$host = getHost();
echo "Trying to scan $host";
scanPort($host);