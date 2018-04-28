<?php
require 'vendor/autoload.php';
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;

$connection = new AMQPStreamConnection('localhost', 5672, 'guest', 'guest');
$channel = $connection->channel();
$channel->queue_declare('weather',false, true, false, false);


$query_string = $_SERVER['QUERY_STRING'];
parse_str($query_string, $query_array);


$data = $query_array["name"];
$msg = new AMQPMessage($data,['delivery_mode' => AMQPMessage::DELIVERY_MODE_PERSISTENT]);
$channel->basic_publish($msg,'','weather');
echo " [x] Sent ",':',$data," \n";

$channel->close();
$connection->close();
