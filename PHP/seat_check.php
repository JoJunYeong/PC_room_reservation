<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- �좎럩伊숋옙�듭쭍熬곻옙�삼옙醫롫쑟�곤옙�숃눧誘り뎡占쎌쥙猷욑옙�킖�좎럩伊숂뙴�묒삕占쏀깗f-8_unicode_ci�좎떥��솕�좎럩�밭빊占쎌삕占쎌쥓裕쏉옙袁ъ삕�됰��숋옙��굲占썩뫁沅®뙴戮녹삕�ル∥�η춯占쎌Ø占쎌눨�앾옙��맶�좎럩�울옙類㏃삕�ル∥�욃뜝�덈굶占썩뼹�앾옙��맶�좎럥�놅옙�대쐻占쎌늿��->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB占쎈씈猷딉옙�용쐻占쎈뜄�욃뜝�숈삕占쎈굝�ｏ옙�볦삕�좎뜴�앾옙�덈섕占썩뒫�우굲占쎌쥙猷욑옙�껁궘�좎�留뗰옙類��占싼귣뭄�좎뜾異�옙�낃텥�좎룞�숋옙醫롫윥筌ㅻ㉡�숋옙�덉굲�좎럥�θ짆占쏙옙濡ろ뜏占쎈톪�숃��⒟э옙醫롫짗占쎌닂�쇿뜝�숈삕占쎌눨�앾옙諛몄��좎룞�숋옙袁ㅻ쎗占쎈맩�뺧옙�낆뒩占쎌꼻�숋옙�롢걫占쎌쥙猷욑옙�뗭삕占쎌궡��占쎌쥙猷욑옙占�mysql_selectdb("zero"); //DB 占쎌쥙�⒳펺�⑦떐�좑옙mysql_query("set names UTF8"); //占쎌쥙�ο옙占쎈뼀�좎뜴�앾옙�뚯삕占쎈맮�숋옙醫롫윪獒뺢쐼�앭뜝�몋f8)占쎌쥙猷욑옙�귥땡占쎈쵐�뺝뜝�뚮묄占쎌닂�숋옙�꾨탿 占쎌쥙�∽옙�ㅻ눀�좎띂��뙼蹂�굲
mysql_selectdb("zero");
mysql_query("set names UTF8");

$birth=$_REQUEST['birth'];
$name=$_REQUEST['name'];

$result=  mysql_result(mysql_query("select number from customer where name='$name' and birth='$birth';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml占쎌쥙�ο옙節륁삕占쎈쪇�뺧옙醫롫쑟��뵃�숋쭚�쇱죷占쎌쥙猷욑옙�뗭삕熬곣뫀占썹뛾占쎌삕
$xmlcode .= "<result>$result</result>\n"; //DB inse1rt占쎈씈猷딉옙�용쐻占쎄쑵�㎩뜝�쇰씮�뺧옙�ㅼ굣筌뤿뱶�뽳옙�뚯굲占쎌쥙�ν걫�좎럩占썲퐲�낅쐻占쎌늿�뺝뜝�뀀쐻占쎈갭�뷂옙醫롫짗占쎌닂彛싷옙�곕뜲占쎌쥙�⑴몭�좊뎨�좎뜴�앾옙袁⑸쳯占쏙옙�셱esult占쎈씈猷녶뜝�숈삕�좏릶ml占싸뀀뙔占쎌닂�숁��レ툓�좎럡��옙�뗭삕占썬굥占�

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml 占쎌쥙�ο옙節륁삕占쎈쪇�뺧옙醫롫짗占쎌눨�앾옙��텑占쎌쥙猷욑옙�낅눇�용뿫�ι쨹恝��filename = $dir."/sendprofile.xml";
$filename = $dir."/seat_check.xml";

file_put_contents($filename, $xmlcode); //xmlcode�좎럩伊숂뙴�묒삕占쎌슜�삣뜝�덈쐞占쎈씢�앾옙��툙�좎럩�⑨옙�얠삕�딆퀕�좎럩伊숋옙恝�숂�瑜곸굲�좎럥�김굢�듭삕�ル∥�잞옙占쎈탟占쎌닂彛싷옙�깆７
?>

