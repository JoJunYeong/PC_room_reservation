<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 占쎌쥙�⑵짆袁�쐻�좎뜾異�옙臾믪굲�좎룞�섵B占쎌쥙猷욑옙�탏f-8_unicode_ci占싸뀀뙔占쎌늹異�옙�좊뮛�꾬옙堉뀐옙�뗭삕�⑥궢猷뽳옙醫롫윥筌�쨪�쇿뜝�뀀쐻占쎌늿�뺧옙醫롫윞占쎈뱾�▼뜝�뀀쐻占쎈뜆�⒴뜝�숈삕-->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB�띠룊�쇿뜝�덈쐞占쏙옙�낅슣�섓옙占썲뜝�덈뼬�⊥띿삕�좎룞�숃キ占쏀맋�뺢퀗�т빳占썹춯�딅궚占쏙옙�좎럥理먲옙�숈삕占쎈∥裕��롪퍒�뷂옙議용Ь�좎룞�숋옙占쏙옙�쇿뜝�밸쇀占쏙옙�꾤뛾�됱삕�낅슣�섓옙�섎ご�좎룞�숋옙�살┣ �좎룞��
mysql_selectdb("zero"); //DB �좎럩伊싨틦占�mysql_query("set names UTF8"); //�좎럥��떋占썲뜝�덌옙�됵옙�좎럩裕꾢뜝占퐑tf8)�좎룞�숂춯�묒삕占쎌빢�숋옙�얄뵛 �좎럡�들뇡占썽뇦猿볦삕
mysql_query("set names UTF8");

$birth=$_REQUEST['birth'];
$name=$_REQUEST['name'];

$result=  mysql_result(mysql_query("select time from customer where name='$name' and birth='$birth';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml�좎럥�ｏ옙�륁삕�좎뜾鍮딉옙類ㅼ졑�좎룞�숋옙袁⑤�獄�옙
$xmlcode .= "<result>$result</result>\n"; //DB inse1rt�띠룊�쇿뜝�꾪돧占싼띿삕�⑤챷紐드슖�쎌삕�좎럥큔占쎌�彛ゅ뜝�숈삕占썲뜝�밸ご�좎룞�숋쭚�쇰데�좎럩瑗든뵳占썲뜝�꾩뫓��옙result�띠룆占쏙옙占퐔ml�β댙�숋옙怨쀫츊占쎄낀�숋옙�る�

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml �좎럥�ｏ옙�륁삕�좎룞�쇿뜝�뚯궋�좎룞�숅뇦猿뗫윥餓ο옙$filename = $dir."/sendprofile.xml";
$filename = $dir."/time_table.xml";

file_put_contents($filename, $xmlcode); //xmlcode占쎌쥙猷욑옙�용쐻占쎈뜄�띶뜝�뚮츐占쎌눨�앾옙遊쳊占쎌쥙�ο옙節륁삕占쎈냱肉됵옙醫롫쑟��뵃�숋쭚�쇱죷
?>

