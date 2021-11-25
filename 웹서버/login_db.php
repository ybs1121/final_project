<?php

$id = $_POST['id'];
$passwd = $_POST['passwd'];
$con = mysqli_connect("dogcat.ctokivcp0aft.ap-northeast-2.rds.amazonaws.com","admin","didqudtjr",dog_cat_db);
$query = "SELECT * FROM user where binary(id) = '$id' AND binary(passwd) = '$passwd';";
$result = mysqli_query($con,$query);
$response = array();


while($row = mysqli_fetch_array($result)){
                array_push($response,array("success" => "success","id" => $row[0],"passwd" => $row[1],"kind" => $row[2]));

}

 echo json_encode(array("response" => $response));
   mysqli_close($con);



?>