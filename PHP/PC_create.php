<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 占쎌쥙�⒳펺�뗭삕占쎈벊彛띸넭怨살삕占쎌궪�숅넫濡レ몷占쎄낀�숋옙�껊닱沃섅굤�▼뜝�뚯쪠�룹쉻�숋옙�뽳옙醫롫윪鴉딆늹�댐옙臾믪굲�좎�源뾣-8_unicode_ci占쎌쥙�ο옙占쎌넅占쎌쥙�⑼옙諛�퉲�좎럩�뺝뜝�뚯쪚獒뺤룊�숃쥈��뺧옙�곤옙占쎌닂�숋옙占쎄뎡�좎뜦維곫쾮짰�댐쭜�뱀굲占썬꺂�ο옙管異�뜝�뚀섇뜝�뚮닲占쎌빢�숋옙占쎈㎍占쎌쥙�⑼옙�몄삕筌먦룂�뺧옙�モ닪占쎌쉩�앾옙�덇독�좎뜦堉뱄옙�얠삕占쏙옙留띰옙醫롫윥占쎈냵�숋옙��맶�좎럩�울옙占�>
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216");
mysql_selectdb("zero"); //DB�좎럥�덄뙴�됱삕占쎌슜�삣뜝�덈쐞占쎌쉩�앾옙�덉굲�좎럥援앾옙節륁삕占쎈낌�뺧옙醫롫쑕占쎌빢�숋옙�덉꽀�좎뜦�ワ옙�곌뎡�좎럩伊숂뙴�묒삕占쎄퍊沅섓옙醫롳옙筌띾뿰�숋쭚占쏙옙�좎떬洹ｋ춣占쎌쥙�양빊占쎌삕占쎈굛�ο옙醫롫짗占쎌닂�숅넫濡レ쑅嶺뚣끇�∽옙�뗭삕占쎈뜆援뀐옙醫롫윥占싸몄쭍�좎룞�숁에�띾쐪�좎럥�わ옙�껓옙占썩뮓��숅넫濡レ쭢�좎럩�귨옙�용쐻占쎌늿�뺝뜝�뚮닲占쎌빢�숃쳸紐꾬옙占쎌쥙猷욑옙�뗭삕熬곥끇�쀥뜝�덈㎥占쎈벨�숋옙�녿뮝�좎럩瑗삼옙�뗭삕占쎈、嫄ュ뜝�뚯쪠�룹쉻�숋옙��굲�좎럩沅∽옙占썲뜝�뚯쪠�룹쉻�쇿뜝占퐉ysql_selectdb("zero"); //DB �좎럩伊숋옙�논렭占썩뫂�먲옙醫묒삕mysql_query("set names UTF8"); //�좎럩伊숋옙恝�쇿뜝�덈�占쎌쥙�댐옙�얠삕占쎈슣�뺝뜝�덈㎜占쎌닂�숅넫濡レ쑋�믩벚�쇽옙��쐻占쎈챾f8)�좎럩伊숂뙴�묒삕占쎄램�▼뜝�덉탳占쎈틶�앾옙��쵂�좎럩�귨옙�뗭삕占쎄쑬���좎럩伊숋옙�쎌삕占썬끇��옙醫롫쓡占쏙옙�쇠퉪占쎄뎡
mysql_query("set names UTF8");

	$name=$_REQUEST['name'];
	$password=$_REQUEST['password'];
	$id=$_REQUEST['id'];
	$number=$_REQUEST['number2'];
	

$qry = "insert into tbl_admin values('$id', '$password', '$number');";
$result = mysql_query($qry);





$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml占쎈슣�わ옙占썹빊�뺤젾占쏙옙�꾨뗀諭�
$xmlcode .= "<result>$result</result>\n"; //DB insert揶쏉옙占쎄퉫�э옙怨몄몵嚥∽옙占쎈Ŧ�쀯쭪占쏙옙�占썹몴占쏙옙類ㅼ뵥占쎌꼵由�占쎄쑵鍮�result揶쏅���xml嚥∽옙�곗뮆�곤옙�쀪땀
 
$dir = "C:/APM_Setup/htdocs"; //insertresult.xml 占쎈슣�わ옙占쏙옙占쎌삢占쏙옙野껋럥以�
$filename = $dir."/pc_create.xml";

file_put_contents($filename, $xmlcode); //xmlcode占쎌쥙�⒳펺�귣쇀占쎈쵐�뺝뜝�뚯뒠占쎌궍�앾옙�덉맄�좎럥��옙�얠삕占쏙옙�숋옙醫롫윪占썩뫅�숋옙�좎굲占쎈봿�뺧옙醫롫윪鴉딆닂�숁걹占쎌늹占썹몴怨멸뎡占쎌쥙�ο옙源�덩占쎈벊�뺧옙�モ닪占쎌옚�쇿뜝�덊깱�좎럩�귛퐲�룹삕占쎄퉮竊�
?>

