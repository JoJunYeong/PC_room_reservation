<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 占쎌쥙�⑵짆袁�쐻�좎뜾異�옙臾믪굲�좎룞�섵B占쎌쥙猷욑옙�탏f-8_unicode_ci占싸뀀뙔占쎌늹異�옙�좊뮛�꾬옙堉뀐옙�뗭삕�⑥궢猷뽳옙醫롫윥筌�쨪�쇿뜝�뀀쐻占쎌늿�뺧옙醫롫윞占쎈뱾�▼뜝�뀀쐻占쎈뜆�⒴뜝�숈삕-->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB占쎈씈猷딉옙�용쐻占쎈뜄�욃뜝�숈삕占쎈굝�ｏ옙�볦삕�좎뜴�앾옙�덈섕占썩뒫�우굲占쎌쥙猷욑옙�껁궘�좎�留뗰옙類��占싼귣뭄�좎뜾異�옙�낃텥�좎룞�숋옙醫롫윥筌ㅻ㉡�숋옙�덉굲�좎럥�θ짆占쏙옙濡ろ뜏占쎈톪�숃��⒟э옙醫롫짗占쎌닂�쇿뜝�숈삕占쎌눨�앾옙諛몄��좎룞�숋옙袁ㅻ쎗占쎈맩�뺧옙�낆뒩占쎌꼻�숋옙�롢걫占쎌쥙猷욑옙�뗭삕占쎌궡��占쎌쥙猷욑옙占�mysql_selectdb("zero"); //DB 占쎌쥙�⒳펺�⑦떐�좑옙mysql_query("set names UTF8"); //占쎌쥙�ο옙占쎈뼀�좎뜴�앾옙�뚯삕占쎈맮�숋옙醫롫윪獒뺢쐼�앭뜝�몋f8)占쎌쥙猷욑옙�귥땡占쎈쵐�뺝뜝�뚮묄占쎌닂�숋옙�꾨탿 占쎌쥙�∽옙�ㅻ눀�좎띂��뙼蹂�굲
mysql_selectdb("zero");
mysql_query("set names UTF8");

$name=$_REQUEST['name'];
	$birth=$_REQUEST['birth'];
	$password=$_REQUEST['password'];
	$seat=$_REQUEST['seat'];
	
	 
$result=  mysql_result(mysql_query("select count(birth) from customer where birth='$birth' AND pw='$password' AND Status_category=2 AND RequestState=2 ;"),0);

 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml占쎌쥙�ο옙節륁삕占쎈쪇�뺧옙醫롫쑟��뵃�숋쭚�쇱죷占쎌쥙猷욑옙�뗭삕熬곣뫀占썹뛾占쎌삕
$xmlcode .= "<result>$result</result>\n"; //DB inse1rt占쎈씈猷딉옙�용쐻占쎄쑵�㎩뜝�쇰씮�뺧옙�ㅼ굣筌뤿뱶�뽳옙�뚯굲占쎌쥙�ν걫�좎럩占썲퐲�낅쐻占쎌늿�뺝뜝�뀀쐻占쎈갭�뷂옙醫롫짗占쎌닂彛싷옙�곕뜲占쎌쥙�⑴몭�좊뎨�좎뜴�앾옙袁⑸쳯占쏙옙�셱esult占쎈씈猷녶뜝�숈삕�좏릶ml占싸뀀뙔占쎌닂�숁��レ툓�좎럡��옙�뗭삕占썬굥占�
 
$dir = "C:/APM_Setup/htdocs"; //insertresult.xml 占쎌쥙�ο옙節륁삕占쎈쪇�뺧옙醫롫짗占쎌눨�앾옙��텑占쎌쥙猷욑옙�낅눇�용뿫�ι쨹恝��$filename = $dir."/reservation_search.xml";
$filename = $dir."/reservation_fail.xml"; 

file_put_contents($filename, $xmlcode); //xmlcode占쎌쥙猷욑옙�용쐻占쎈뜄�띶뜝�뚮츐占쎌눨�앾옙遊쳊占쎌쥙�ο옙節륁삕占쎈냱肉됵옙醫롫쑟��뵃�숋쭚�쇱죷
?>

