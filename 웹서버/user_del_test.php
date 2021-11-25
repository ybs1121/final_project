<?php

$num = $_POST['num'];
$con = mysqli_connect("dogcat.ctokivcp0aft.ap-northeast-2.rds.amazonaws.com","admin","didqudtjr",dog_cat_db);
$query = "DELETE FROM user_dog_cat where num = $num;";

$msg_sc = 1;
$msg_fa = 0;

$response = array();



if(!mysqli_query($con,$query)){
                        array_push($response,array("num" => $msg_fa));

}
else{
                        array_push($response,array("num" => $msg_sc));
}
 echo json_encode(array("response" => $response));
 mysqli_close($con);



?>