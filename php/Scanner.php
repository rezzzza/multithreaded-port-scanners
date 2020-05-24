<?php
error_reporting(~E_ALL);

function scanPort($host)
{

    $fPort = 1;
    $lPort = 65535;

    function checkPort($host , $port)
    {
        try {
            $socket = socket_create(AF_INET, SOCK_STREAM, 0);
            $connect = socket_connect($socket, $host, $port);
            if ($connect)
            {
              socket_close($socket);
              return true;
            }
            return false;
        } catch (Exception $exception) {
            return false;
        }
    }

    for($port=$fPort ; $port <= $lPort ; $port++)
    {
        if (checkPort($host, $port))
        {
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