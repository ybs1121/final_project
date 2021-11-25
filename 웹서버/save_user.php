<?php

$id = $_POST['id'];

$passwd = $_POST['passwd'];

$kind = $_POST['kind'];


$con = mysqli_connect("dogcat.ctokivcp0aft.ap-northeast-2.rds.amazonaws.com","admin","didqudtjr",dog_cat_db);
$query = "INSERT INTO user VALUES('$id','$passwd','$kind')";

$response = array();

if(!mysqli_query($con,$query)){
                         array_push($response,array("success" => "fail"));

}
else{
                       array_push($response,array("success" => "success"));
}


 echo json_encode(array("response" => $response));
 mysqli_close($con);



?>

