var synth = window.speechSynthesis;

$(document).ready(function(){
	//openDB();
	//createTable();
})

function openDB(){
	//DB 연결
	var db = null;
	db=window.openDatabase('bookDB2', '1.0', 'selectdlstudy',1024*1024);
	console.log('1_DB생성...');
}

/*
function createTable(){
	db.transaction(function(tr){
		var createSQL = "create table if not exists book(type text, name text)";
		tr.executeSql(createSQL, [], function(){});
	})
}
*/

function init() {
	
    document.getElementById("frmVoca").submit();
    
}

function setTextArea() {
	// $("tts_text").textinput({autogrow: false});

	var v_text = document.getElementById("tts_text");
  	v_text.value = "\n\n\n";	
	
}

function submitTTS(langno, styleFlag) {
    
    voices = synth.getVoices();

	var voice_name   = "";  

    if (langno == 3) {
    	voice_name = "Google US English";
    } else if (langno == 4) {
    	voice_name = "Google UK English Female";
    }
	for (i = 0; i < voices.length ; i++) {
	    if(voices[i].name == voice_name) langno = i;
    }
	
    if (styleFlag == 1)	
       var inputTxt  = document.getElementById("tts_word");
    else if (styleFlag == 2)
       var inputTxt  = document.getElementById("tts_tag");    	
    else if (styleFlag == 10)
        var inputTxt  = document.getElementById("tts_part0");    
    else if (styleFlag == 11)
        var inputTxt  = document.getElementById("tts_part1");    
    else if (styleFlag == 12)
        var inputTxt  = document.getElementById("tts_part2");    
    else if (styleFlag == 13)
        var inputTxt  = document.getElementById("tts_part3");    
    else if (styleFlag == 14)
        var inputTxt  = document.getElementById("tts_part4");    
    else if (styleFlag == 15)
        var inputTxt  = document.getElementById("tts_part5");    
    else if (styleFlag == 16)
        var inputTxt  = document.getElementById("tts_part6");    
    else if (styleFlag == 17)
        var inputTxt  = document.getElementById("tts_part7");    
    else if (styleFlag == 18)
        var inputTxt  = document.getElementById("tts_part8");    
    else if (styleFlag == 19)
        var inputTxt  = document.getElementById("tts_part9");    
    else if (styleFlag == 20)
        var inputTxt  = document.getElementById("tts_part10");    
    else if (styleFlag == 21)
        var inputTxt  = document.getElementById("tts_part11");    
    else if (styleFlag == 22)
        var inputTxt  = document.getElementById("tts_part12");    
    else if (styleFlag == 23)
        var inputTxt  = document.getElementById("tts_part13");    
    else if (styleFlag == 24)
        var inputTxt  = document.getElementById("tts_part14");    
    else if (styleFlag == 25)
        var inputTxt  = document.getElementById("tts_part15");    
    else if (styleFlag == 26)
        var inputTxt  = document.getElementById("tts_part16");    
    else if (styleFlag == 27)
        var inputTxt  = document.getElementById("tts_part17");    
    else if (styleFlag == 28)
        var inputTxt  = document.getElementById("tts_part18");    
    else if (styleFlag == 29)
        var inputTxt  = document.getElementById("tts_part19");    
   
    inputTxt.value = inputTxt.value.trim();
    
    var utterThis = new SpeechSynthesisUtterance(inputTxt.value);

    utterThis.voice = voices[langno];
    utterThis.pitch = 1;
    utterThis.rate  = 1;			

    synth.speak(utterThis);

};   

function showDefinition(definitnFlag) {
    if (definitnFlag == 1) {
        document.getElementById("tag_info1").style.display = "none";
        document.getElementById("tag_info2").style.display = "block";    	
    } else {
        document.getElementById("tag_info1").style.display = "block";
        document.getElementById("tag_info2").style.display = "none";    	
    }
    
    submitTTS(3, 2);
}

function showMeans(meanFlag) {
    if (meanFlag == 1) {
        document.getElementById("tag_mean1").style.display = "none";
        document.getElementById("tag_mean2").style.display = "block";    	
    } else {
        document.getElementById("tag_mean1").style.display = "block";
        document.getElementById("tag_mean2").style.display = "none";    	
    }
}

function showGuessing(meanFlag) {
	var v_mtx_guess_cover = document.getElementById("mtx_guess_cover");
	var v_mtx_guess = document.getElementById("mtx_guess");
	
    if (meanFlag == 1) {
    	v_mtx_guess_cover.style.display = "none";
    	v_mtx_guess.style.display = "block";    	
    } else {
    	v_mtx_guess_cover.style.display = "block";
    	v_mtx_guess.style.display = "none";    	
    }
}

function putEngExample(txt_example) {
	var v_text = document.getElementById("tts_text");
	var v_tts_voice = document.getElementById("tts_voice");	
	
	txt_example1 = txt_example.replace(/`/g      , "'");
	txt_example2 = txt_example.replace(/`/g      , "");	
  	v_text.value      = txt_example1;
  	v_tts_voice.value = txt_example2;
  	
	submitTextTTS(3,"STN");
	
	//put_example_parts(txt_example1)
}

function put_example_parts(txt_example) {

	//var txt_example_parts = makeParts(txt_example);
	var txt_example_parts = txt_example;
	
	var div_detail_part = new Array();
	var arr_detail_part = new Array();	

	div_detail_part[0] = document.getElementById("div_part0");	
	div_detail_part[1] = document.getElementById("div_part1");
	div_detail_part[2] = document.getElementById("div_part2");
	div_detail_part[3] = document.getElementById("div_part3");
	div_detail_part[4] = document.getElementById("div_part4");
	div_detail_part[5] = document.getElementById("div_part5");
	div_detail_part[6] = document.getElementById("div_part6");
	div_detail_part[7] = document.getElementById("div_part7");
	div_detail_part[8] = document.getElementById("div_part8");
	div_detail_part[9] = document.getElementById("div_part9");
	div_detail_part[10] = document.getElementById("div_part10");	
	div_detail_part[11] = document.getElementById("div_part11");
	div_detail_part[12] = document.getElementById("div_part12");
	div_detail_part[13] = document.getElementById("div_part13");
	div_detail_part[14] = document.getElementById("div_part14");
	div_detail_part[15] = document.getElementById("div_part15");
	div_detail_part[16] = document.getElementById("div_part16");
	div_detail_part[17] = document.getElementById("div_part17");
	div_detail_part[18] = document.getElementById("div_part18");
	div_detail_part[19] = document.getElementById("div_part19");	
	
	arr_detail_part[0] = document.getElementById("tts_part0");	
	arr_detail_part[1] = document.getElementById("tts_part1");
	arr_detail_part[2] = document.getElementById("tts_part2");
	arr_detail_part[3] = document.getElementById("tts_part3");
	arr_detail_part[4] = document.getElementById("tts_part4");
	arr_detail_part[5] = document.getElementById("tts_part5");
	arr_detail_part[6] = document.getElementById("tts_part6");
	arr_detail_part[7] = document.getElementById("tts_part7");
	arr_detail_part[8] = document.getElementById("tts_part8");
	arr_detail_part[9] = document.getElementById("tts_part9");
	arr_detail_part[10] = document.getElementById("tts_part10");	
	arr_detail_part[11] = document.getElementById("tts_part11");
	arr_detail_part[12] = document.getElementById("tts_part12");
	arr_detail_part[13] = document.getElementById("tts_part13");
	arr_detail_part[14] = document.getElementById("tts_part14");
	arr_detail_part[15] = document.getElementById("tts_part15");
	arr_detail_part[16] = document.getElementById("tts_part16");
	arr_detail_part[17] = document.getElementById("tts_part17");
	arr_detail_part[18] = document.getElementById("tts_part18");
	arr_detail_part[19] = document.getElementById("tts_part19");	
	
    var arrTtsParts = txt_example_parts.split(".");

    for (i=0; i<arrTtsParts.length; i++)
	{
    	show_example_parts(div_detail_part[i], "block");    	
    	show_example_parts(arr_detail_part[i], "block");
    	arr_detail_part[i].value     = arrTtsParts[i];    	
    	arr_detail_part[i].innerHTML = arrTtsParts[i];
	}
    
    for (j=arrTtsParts.length; j<20 ;j++)
	{   
    	show_example_parts(div_detail_part[j], "none");    	
    	show_example_parts(arr_detail_part[j], "none");    	
	}    
}

function show_example_parts(p_arr_detail_part, p_kind) 
{
	if (p_kind == "none") 
	   p_arr_detail_part.style.display = "none";
	else 
       p_arr_detail_part.style.display = "block";		
}


function submitTextTTS(langno, gubun) {
	var voice_name = "";
    var inputTxt   = ""; 
    var objInTxt   = "";    
    var strStnce   = ""; 
    var arrStnce   = "";
    var findBe     = "";  
    var ttsText    = "";
    var strTtsText = "";
    var strEditedTtsText = "";    
    	
	ttsText   = document.getElementById("tts_text");	
    inputTxt  = document.getElementById("tts_voice");
    
    ttsText.value  =  ttsText.value.trim();  
    inputTxt.value = inputTxt.value.trim();   

    strTtsText = ttsText.value;
    strStnce   = inputTxt.value;
    strEditedTtsText = ttsText.value;    

    if (strStnce == '') strStnce = "insert sentence !";
    
    if (gubun == 'CHK')
   	{
    	strTtsText = makeParts(strTtsText);
    	strStnce = makeParts(strStnce);
    	put_example_parts(strTtsText);    	
   	} else if  (gubun == 'ADV') {
    	strTtsText = makeParts_adverse(strTtsText);
    	strStnce = makeParts_adverse(strStnce);
    	put_example_parts(strTtsText);    	
   	}  	
	
    voices = synth.getVoices();		
    
    if (langno == 3) {
    	voice_name = "Google US English";
    } else if (langno == 4) {
    	voice_name = "Google UK English Female";
    }
    	
	for (i = 0; i < voices.length ; i++) {
	    if(voices[i].name == voice_name) langno = i;
    }    
	
	var utterThis = ""
	if (gubun == 'CHK' || gubun == 'ADV')
   	{
		utterThis = new SpeechSynthesisUtterance(strStnce);
   	} else {
		utterThis = new SpeechSynthesisUtterance(strEditedTtsText);   		
   	}	

    utterThis.voice = voices[langno];
    utterThis.pitch = 1;
    utterThis.rate  = 1;			

    synth.speak(utterThis);
};

//function 위치    
function makeParts(p_stnce)
{
	var strStnce = p_stnce;
	 
	// Special Cases
	strStnce = strStnce.replace(/ Inc. /g  , " incoperated. ");
	
	// 뒤에 . 붙는 경우 (한 단어)    	
	strStnce = strStnce.replace(/, /g      , ". ");        
	strStnce = strStnce.replace(/Now./g    , "Now,");
	
	strStnce = strStnce.replace(/ and /gi  , " and. ");        
	strStnce = strStnce.replace(/. and. /gi  , " and. ");	
	strStnce = strStnce.replace(/ that /gi  , " that. ");
	strStnce = strStnce.replace(/ to /gi    , " to. ");
	
	strStnce = strStnce.replace(/ be /gi    , " be. ");
	strStnce = strStnce.replace(/ become /gi    , " become. ");	
	strStnce = strStnce.replace(/ became /gi    , " became. ");

	strStnce = strStnce.replace(/ bring /gi    , " .bring ");	
	strStnce = strStnce.replace(/ brings /gi    , " .brings ");		
	
	strStnce = strStnce.replace(/ in /gi    , " in. ");
	strStnce = strStnce.replace(/ on /gi    , " on. ");        
	strStnce = strStnce.replace(/ at /gi    , " at. ");
	strStnce = strStnce.replace(/ of /gi    , " of. ");        
	strStnce = strStnce.replace(/ with /gi  , " with. ");        
	strStnce = strStnce.replace(/ for /gi   , " for. ");        
	strStnce = strStnce.replace(/ from /gi  , " from. ");        
	strStnce = strStnce.replace(/ by /gi    , " by. ");
	strStnce = strStnce.replace(/ about /gi , " about. ");        
	strStnce = strStnce.replace(/ into /gi  , " into. ");                
	
	strStnce = strStnce.replace(/ is /gi    , " is. ");
	strStnce = strStnce.replace(/ are /gi   , " are. ");        
	strStnce = strStnce.replace(/ was /gi   , " was. ");        
	strStnce = strStnce.replace(/ were /gi  , " were. ");        
	
	strStnce = strStnce.replace(/ has /gi   , " has. ");        
	strStnce = strStnce.replace(/ have /gi  , " have. ");
	strStnce = strStnce.replace(/ had /gi   , " had. ");        
	
	strStnce = strStnce.replace(/ what /gi  , " what. ");        
	strStnce = strStnce.replace(/ when /gi  , " when. ");        
	strStnce = strStnce.replace(/ who /gi   , " who. ");        
	strStnce = strStnce.replace(/ which /gi , " which. ");
	
	strStnce = strStnce.replace(/ can /gi   , " can. ");
	strStnce = strStnce.replace(/ will /gi  , " will. ");
	strStnce = strStnce.replace(/ shall /gi , " shall. ");
	strStnce = strStnce.replace(/ could /gi , " could. ");
	strStnce = strStnce.replace(/ would /gi , " would. ");
	strStnce = strStnce.replace(/ should /gi, " should. ");        
	
	// > 뒤에 . 붙는 경우 (줄임)
	strStnce = strStnce.replace(/ Id /g     ,  " Id. ");   
	strStnce = strStnce.replace(/ Ill /g    ,  " Ill. ");
	strStnce = strStnce.replace(/ youll /gi  , " youll. ");
	strStnce = strStnce.replace(/ hell /gi   , " hell. ");
	strStnce = strStnce.replace(/ shell /gi  , " shell. ");
	strStnce = strStnce.replace(/ theyll /gi , " theyll. "); 
	strStnce = strStnce.replace(/ well /gi   , " well. ");
	
	strStnce = strStnce.replace(/ its /gi    , " its. ");
	strStnce = strStnce.replace(/ theres /gi , " theres. ");
	strStnce = strStnce.replace(/ thats /gi  , " thats. ");
	
	strStnce = strStnce.replace(/ dont /gi   , " dont. ");
	strStnce = strStnce.replace(/ doesnt /gi , " doesnt. ");
	strStnce = strStnce.replace(/ didnt /gi  , " didnt. ");
	strStnce = strStnce.replace(/ isnt /gi   , " isnt. ");
	strStnce = strStnce.replace(/ wasnt /gi  , " wsnt. ");
	
	strStnce = strStnce.replace(/ theyre /gi , " Theyre. ");        
	
	strStnce = strStnce.replace(/ youre /gi , " youre. ");                
	
	// > 뒤에 . 붙는 경우 (두 단어)        
	strStnce = strStnce.replace(/ do you /gi    , " do you. ");
	strStnce = strStnce.replace(/ did you /gi   , " did you. ");
	strStnce = strStnce.replace(/ would you /gi , " would you. ");        
	strStnce = strStnce.replace(/ could you /gi , " could you. ");        
	
	strStnce = strStnce.replace(/ to. have. /gi   , " to. have ");        
	strStnce = strStnce.replace(/ to. has. /gi    , " to. has ");
	strStnce = strStnce.replace(/ to. be. /gi     , " to. be ");
	
	strStnce = strStnce.replace(/ what. to. /gi   , " what to. ");        
	strStnce = strStnce.replace(/ how. to. /gi    , " how to. ");
	strStnce = strStnce.replace(/ when. to. /gi   , " when to. ");
	strStnce = strStnce.replace(/ which. to. /gi  , " which to. ");   
	
	strStnce = strStnce.replace(/ can. be. /gi    , " can be. ");
	strStnce = strStnce.replace(/ will. be. /gi   , " will be. ");
	strStnce = strStnce.replace(/ shall. be. /gi  , " shall be. ");        
	strStnce = strStnce.replace(/ could. be. /gi  , " could be. ");        
	strStnce = strStnce.replace(/ would. be. /gi  , " would be. ");
	strStnce = strStnce.replace(/ should. be. /gi , " should be. "); 
	
	strStnce = strStnce.replace(/ has. to. /gi  , " has to. ");
	strStnce = strStnce.replace(/ have. to. /gi , " have to. ");
	strStnce = strStnce.replace(/ that. into. /gi , " that into.");
	strStnce = strStnce.replace(/ had. to. /gi   , " had to. ");        
	
	strStnce = strStnce.replace(/ Ill. be. /g   ,  " Ill be. ");
	strStnce = strStnce.replace(/ youll. be. /gi , " youll be. ");
	strStnce = strStnce.replace(/ hell. be. /gi  , " hell be. ");
	strStnce = strStnce.replace(/ shell. be. /gi , " shell be. ");
	strStnce = strStnce.replace(/ theyll. be. /gi, " theyll be. ");
	
	// 앞에 . 붙는 경우 (한 단어)
	strStnce = strStnce.replace(/ because /gi   , ". because ");
	strStnce = strStnce.replace(/ or /gi        , ". or ");  
	strStnce = strStnce.replace(/is. up to./gi  , "is up to.");	
	strStnce = strStnce.replace(/are. up to./gi , "are up to.");	
	

	
	strStnce = strStnce.trim();
	
	return strStnce;
}

//function 위치    
function makeParts_adverse(p_stnce)
{
	var strStnce = p_stnce;
	 
	// Special Cases
	strStnce = strStnce.replace(/ Inc. /g  , " incoperated. ");
	
	// 뒤에 . 붙는 경우 (한 단어)    	
	strStnce = strStnce.replace(/, /g      , ". ");        
	strStnce = strStnce.replace(/Now./g    , "Now,");
	strStnce = strStnce.replace(/ that /gi  , ". that ");
	
	strStnce = strStnce.replace(/ and /gi  , ". and ");        
	strStnce = strStnce.replace(/.. and /gi  , ". and ");

	strStnce = strStnce.replace(/ to /gi    , ". to ");
	strStnce = strStnce.replace(/up. to /gi    , ". up to ");	
	
	strStnce = strStnce.replace(/ be /gi    , " be. ");
	strStnce = strStnce.replace(/ become /gi    , " become. ");	
	strStnce = strStnce.replace(/ became /gi    , " became. ");	
	strStnce = strStnce.replace(/ bring /gi    , " .bring ");	
	strStnce = strStnce.replace(/ brings /gi    , " .brings ");			
	
	strStnce = strStnce.replace(/ in /gi    , ". in ");
	strStnce = strStnce.replace(/ on /gi    , ". on ");        
	strStnce = strStnce.replace(/ at /gi    , ". at ");
	strStnce = strStnce.replace(/ of /gi    , " of. ");        
	strStnce = strStnce.replace(/ with /gi  , " with. ");        
	strStnce = strStnce.replace(/ for /gi   , ". for ");        
	strStnce = strStnce.replace(/ from /gi  , ". from ");        
	strStnce = strStnce.replace(/ by /gi    , ". by ");
	strStnce = strStnce.replace(/ about /gi , ". about ");        
	strStnce = strStnce.replace(/ into /gi  , ". into ");                
	
	strStnce = strStnce.replace(/ is /gi    , " is. ");
	strStnce = strStnce.replace(/ are /gi   , " are. ");        
	strStnce = strStnce.replace(/ was /gi   , " was. ");        
	strStnce = strStnce.replace(/ were /gi  , " were. ");        
	
	strStnce = strStnce.replace(/ has /gi   , " has. ");        
	strStnce = strStnce.replace(/ have /gi  , " have. ");
	strStnce = strStnce.replace(/ had /gi   , " had. ");        
	
	strStnce = strStnce.replace(/ what /gi  , " what. ");        
	strStnce = strStnce.replace(/ when /gi  , " when. ");        
	strStnce = strStnce.replace(/ who /gi   , " who. ");        
	strStnce = strStnce.replace(/ which /gi , " which. ");
	
	strStnce = strStnce.replace(/ can /gi   , " can. ");
	strStnce = strStnce.replace(/ will /gi  , " will. ");
	strStnce = strStnce.replace(/ shall /gi , " shall. ");
	strStnce = strStnce.replace(/ could /gi , " could. ");
	strStnce = strStnce.replace(/ would /gi , " would. ");
	strStnce = strStnce.replace(/ should /gi, " should. ");        
	
	// > 뒤에 . 붙는 경우 (줄임)
	strStnce = strStnce.replace(/ Id /g     ,  " Id. ");   
	strStnce = strStnce.replace(/ Ill /g    ,  " Ill. ");
	strStnce = strStnce.replace(/ youll /gi  , " youll. ");
	strStnce = strStnce.replace(/ hell /gi   , " hell. ");
	strStnce = strStnce.replace(/ shell /gi  , " shell. ");
	strStnce = strStnce.replace(/ theyll /gi , " theyll. "); 
	strStnce = strStnce.replace(/ well /gi   , " well. ");
	
	strStnce = strStnce.replace(/ its /gi    , " its. ");
	strStnce = strStnce.replace(/ theres /gi , " theres. ");
	strStnce = strStnce.replace(/ thats /gi  , " thats. ");
	
	strStnce = strStnce.replace(/ dont /gi   , " dont. ");
	strStnce = strStnce.replace(/ doesnt /gi , " doesnt. ");
	strStnce = strStnce.replace(/ didnt /gi  , " didnt. ");
	strStnce = strStnce.replace(/ isnt /gi   , " isnt. ");
	strStnce = strStnce.replace(/ wasnt /gi  , " wsnt. ");
	
	strStnce = strStnce.replace(/ theyre /gi , " Theyre. ");        
	
	strStnce = strStnce.replace(/ youre /gi , " youre. ");                
	
	// > 뒤에 . 붙는 경우 (두 단어)        
	strStnce = strStnce.replace(/ do you /gi    , " do you. ");
	strStnce = strStnce.replace(/ did you /gi   , " did you. ");
	strStnce = strStnce.replace(/ would you /gi , " would you. ");        
	strStnce = strStnce.replace(/ could you /gi , " could you. ");        
	
	strStnce = strStnce.replace(/ to. have. /gi   , ". to have ");        
	strStnce = strStnce.replace(/ to. has. /gi    , ". to has ");
	strStnce = strStnce.replace(/ to. be. /gi     , ". to be ");
	
	strStnce = strStnce.replace(/ what. to. /gi   , " what to ");        
	strStnce = strStnce.replace(/ how. to. /gi    , " how to ");
	strStnce = strStnce.replace(/ when. to. /gi   , " when to ");
	strStnce = strStnce.replace(/ which. to. /gi  , " which to ");   
	
	strStnce = strStnce.replace(/ can. be. /gi    , " can be. ");
	strStnce = strStnce.replace(/ will. be. /gi   , " will be. ");
	strStnce = strStnce.replace(/ shall. be. /gi  , " shall be. ");        
	strStnce = strStnce.replace(/ could. be. /gi  , " could be. ");        
	strStnce = strStnce.replace(/ would. be. /gi  , " would be. ");
	strStnce = strStnce.replace(/ should. be. /gi , " should be. "); 
	
	strStnce = strStnce.replace(/ has. to. /gi  , " has to. ");
	strStnce = strStnce.replace(/ have. to. /gi , " have to. ");
	strStnce = strStnce.replace(/ that. into. /gi , " that into.");
	strStnce = strStnce.replace(/ had. to. /gi   , " had to. ");        
	
	strStnce = strStnce.replace(/ Ill. be. /g   ,  " Ill be. ");
	strStnce = strStnce.replace(/ youll. be. /gi , " youll be. ");
	strStnce = strStnce.replace(/ hell. be. /gi  , " hell be. ");
	strStnce = strStnce.replace(/ shell. be. /gi , " shell be. ");
	strStnce = strStnce.replace(/ theyll. be. /gi, " theyll be. ");
	
	// 앞에 . 붙는 경우 (한 단어)
	strStnce = strStnce.replace(/ because /g   , ". because ");
	strStnce = strStnce.replace(/ or /g        , ". or ");  
	strStnce = strStnce.replace(/. . up to/g        , ". up to");	
	
	strStnce = strStnce.trim();
	
	return strStnce;
}

// function 위치    
function submitWord(pVal, pCrrntSort, pCrrntNum)
{
    var objDrctStatus = document.getElementById("drctStatus");
    objDrctStatus.value = pVal;
    
    var objSortStatus = document.getElementById("sortStatus");
    objSortStatus.value = pCrrntSort;
    
	// 현재 학습 위치 setup
	var objNum = document.getElementById("num");
	objNum.value = pCrrntNum;        
	
    document.getElementById("frmVoca").submit();
}

function submitSort(pCrrntSort, pCrrntNum)
{
    var objSortStatus = document.getElementById("sortStatus");
    objSortStatus.value = pCrrntSort;
    
	// 현재 학습 위치 setup
	var objNum = document.getElementById("num");
	objNum.value = pCrrntNum;        

    document.getElementById("frmVoca").submit();
}
// F 처리 함수
function updateFreq(pVal, pCrrntSort, pCrrntNum)
{
	var objFreqStatus = document.getElementById("freqStatus");
	objFreqStatus.value = pVal;
	
    var objSortStatus = document.getElementById("sortStatus");
    objSortStatus.value = pCrrntSort;
    
	// 현재 학습 위치 setup
	var objNum = document.getElementById("num");
	objNum.value = pCrrntNum;
	
	var objDrctStatus = document.getElementById("drctStatus");
    objDrctStatus.value ="2";
	
    document.getElementById("frmVoca").submit();    	
}    
// P 처리 함수
function updatePrrty(pVal, pCrrntSort, pCrrntNum)
{
	var objPrrtyStatus = document.getElementById("prrtyStatus");
	objPrrtyStatus.value = pVal;

    var objSortStatus = document.getElementById("sortStatus");
    objSortStatus.value = pCrrntSort;
	
	// 현재 학습 위치 setup
	var objNum = document.getElementById("num");
	objNum.value = pCrrntNum;
	
    var objDrctStatus = document.getElementById("drctStatus");
    objDrctStatus.value ="2";
	
    document.getElementById("frmVoca").submit();    	
}

//D 처리 함수
function updateDone(pVal, pCrrntSort, pCrrntNum )
{
   // contentType : 한글깨짐 해결을 위한 설정임
   $.ajax(
 	      {
		     url: '/completedlstudy.do?pnum='+pCrrntNum,
             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		     success: function(dto){
	 	  	          submitWord(pVal, pCrrntSort, pCrrntNum);
		     },
		     error: function(){
	 		        alert("error");
		     }
	      }
   );
}

//priority 처리 함수    
function updatePriority(pCrrntNum)
{
    // contentType : 한글깨짐 해결을 위한 설정임
	$.ajax(
	 	  {
		     url: '/updatepriority.do?pnum='+pCrrntNum,
             contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			 success: function(dto){		
			          $("#priority").html(dto.orderPriority);
			 },
			 error: function(){
			        alert("error");
			 }
	      }	
	);
}

// 금일 상태 초기화 처리 함수    
function initTodayStatus(pVal, pCrrntSort, pCrrntNum)
{
    // contentType : 한글깨짐 해결을 위한 설정임
	$.ajax(
	 	  {
			url: '/initTodayStatus.do',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(dto){		
			         submitWord(pVal, pCrrntSort, pCrrntNum);
			},
			error: function(){
			       alert("error");
			}
	      }	
    );
}

//verb 여부 처리 함수    
function updateVerb(pCrrntNum)
{
    // contentType : 한글깨짐 해결을 위한 설정임
	$.ajax(
	 	  {	
		    url: '/updateverb.do?pnum='+pCrrntNum,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    success: function(dto){		
			         $("#is_verb").html(dto);
		    },
		    error: function(){
			       alert("error");
		    }
	      }	
    );
}

$(document).ready(function(){
	$("#mtx_guess").val($("#tts_guess").val());
	if ($("#tts_guess").val() != "") $("#mtx_guess_cover").html(".....");
});	