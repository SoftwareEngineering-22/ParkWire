<?php
$db="ParkWireDb";
$host="localhost";
$user="root";
$pass="";
$conn= mysqli_connect($host,$user,$pass,$db);

$username=$_POST["user"];
$password=$_POST["pass"];


if($conn){
$testquery= "SELECT * users WHERE username LIKE '$username'";
$result= mysqli_query($conn,$testquery);

    echo"connection obtained!<br>";
    echo $username;
    
}


?>