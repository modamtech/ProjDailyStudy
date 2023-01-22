var synth = window.speechSynthesis;
var voices = [];	    

$(document).ready(function(){
	//var db = null;
	//openDB();
	//createTable();
	
	// Toeic RC result review
	$('#btnLoadXml').click(function(){
		$.ajax({
			url: 'xml/toeic_voca_info.xml',
			type: 'get',
			dataType: 'xml',
			timeout: 10000,
			success: function(xmlData){
				var tagList="";

				$(xmlData).find('item').each(function(){
					tagList+="<li onclick='liText($(this).text());'>"+$(this).find('content').text()+"</li>";
				});
				$('#listsentence').empty();
				$('#listsentence').append(tagList);	
				$('#listsentence').listview('refresh');
			},
			error: function(){
				$('#listsentence').html("<p>Error!!</p>");
			}
		});
	});
	
	// Toeic LC result review
	$('#btnLoadToeicLcXml').click(function(){
		$.ajax({
			url: 'xml/toeic_test_review.xml',
			type: 'get',
			dataType: 'xml',
			timeout: 10000,
			success: function(xmlData){
				var tagList="";
				
				$(xmlData).find('item').each(function(){
					
					strPsntn_no = $(this).find('no').text();					
					strP1sntn = $(this).find('content_p').find('sentence_p1').text().trim();
					strP2sntn = $(this).find('content_p').find('sentence_p2').text().trim();
					strP3sntn = $(this).find('content_p').find('sentence_p3').text().trim();
					strP4sntn = $(this).find('content_p').find('sentence_p4').text().trim();					
					strAsntn = $(this).find('content_a').find('sentence').text().trim();
					strBsntn = $(this).find('content_b').find('sentence').text().trim();
					strCsntn = $(this).find('content_c').find('sentence').text().trim();
					strDsntn = $(this).find('content_d').find('sentence').text().trim();
					
					if (strP1sntn != '')
						tagList+="<li onclick='liText($(this).text());'>"+"("+strPsntn_no+") "+strP1sntn+"</li>";

					if (strP2sntn != '')
						tagList+="<li onclick='liText($(this).text());'>"+strP2sntn+"</li>";					

					if (strP3sntn != '')
						tagList+="<li onclick='liText($(this).text());'>"+strP3sntn+"</li>";
					
					if (strP4sntn != '')
						tagList+="<li onclick='liText($(this).text());'>"+strP4sntn+"</li>";					
					
					if (strAsntn != '')					
						tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (A) "+strAsntn+"</li>";
					
					if (strBsntn != '')					
						tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (B) "+strBsntn+"</li>";
					
					if (strCsntn != '')					
						tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (C) "+strCsntn+"</li>";
					
					if (strDsntn != '')					
						tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (D) "+strDsntn+"</li>";					
				});
				
				$('#listsentence').empty();
				$('#listsentence').append(tagList);	
				$('#listsentence').listview('refresh');
			},
			error: function(){
				$('#listsentence').html("<p>Error!!</p>");
			}
		});
	});	
});

function openDB(){
	//DB 연결
	db=window.openDatabase('dailystudyDB', '1.0', 'dailystudy',1024*1024);
	console.log('1_DB생성...');
}

function createTable(){
	db.transaction(function(tr){
		var createSQL = "create table if not exists paragraph(word text, name text)";
		tr.executeSql(createSQL, [], function(){});
	})
}

function liText(selectedLiText){
	//var objTtsText = document.getElementById("tts_text");
	//objTtsText.value = selectedLiText.trim();

	$('#tts_text').val(selectedLiText);
}
function init() {
    document.getElementById("frmVoca").submit();
}

function submitStnceTTS(gubun) {
	var objNation  = "";	
	var strNation  = "";	
	var voice_name = "";
    var objInTxt   = ""; 
    var strStnce   = ""; 
    var arrStnce   = "";
    var findBe     = "";    
    	
    objNation = document.getElementById("pronun_nations"); 
    strNation = objNation.value.trim(); 
    
    objInTxt  = document.getElementById("tts_text");
    objInTxt.value = objInTxt.value.trim();
    
    //strStnce  = objInTxt.value;
    
    var arrStnce = objInTxt.value.split(")");
    var strSplitedStn = arrStnce[arrStnce.length-1];
    
    strStnce = strSplitedStn;    
    
    if (gubun == 'TRN'){
    	// Special Cases
        strStnce = strStnce.replace(/ Inc. /g  , " incoperated. ");
        
        // > 뒤에 . 붙는 경우 (한 단어)    	
        strStnce = strStnce.replace(/, /g      , ". ");        
        strStnce = strStnce.replace(/Now./g    , "Now,");
        
        strStnce = strStnce.replace(/ that /g  , " that. ");
        strStnce = strStnce.replace(/ to /g    , " to. ");
        strStnce = strStnce.replace(/ be /g    , " be. ");
        
        strStnce = strStnce.replace(/ in /g    , " in. ");
        strStnce = strStnce.replace(/ on /g    , " on. ");        
        strStnce = strStnce.replace(/ at /g    , " at. ");
        strStnce = strStnce.replace(/ of /g    , " of. ");        
        strStnce = strStnce.replace(/ with /g  , " with. ");        
        strStnce = strStnce.replace(/ for /g   , " for. ");        
        strStnce = strStnce.replace(/ from /g  , " from. ");        
        strStnce = strStnce.replace(/ by /g    , " by. ");
        strStnce = strStnce.replace(/ about /g , " about. ");        
        strStnce = strStnce.replace(/ into /g  , " into. ");                
       
        strStnce = strStnce.replace(/ is /g    , " is. ");
        strStnce = strStnce.replace(/ are /g   , " are. ");        
        strStnce = strStnce.replace(/ was /g   , " was. ");        
        strStnce = strStnce.replace(/ were /g  , " were. ");        
        
        strStnce = strStnce.replace(/ has /g   , " has. ");        
        strStnce = strStnce.replace(/ have /g  , " have. ");
        strStnce = strStnce.replace(/ had /g   , " had. ");        
        
        strStnce = strStnce.replace(/ what /g  , " what. ");        
        strStnce = strStnce.replace(/ when /g  , " when. ");        
        strStnce = strStnce.replace(/ who /g   , " who. ");        
        strStnce = strStnce.replace(/ which /g , " which. ");
        
        strStnce = strStnce.replace(/ can /g   , " can. ");
        strStnce = strStnce.replace(/ will /g  , " will. ");
        strStnce = strStnce.replace(/ shall /g , " shall. ");
        strStnce = strStnce.replace(/ could /g , " could. ");
        strStnce = strStnce.replace(/ would /g , " would. ");
        strStnce = strStnce.replace(/ should /g, " should. ");        
        
        // > 뒤에 . 붙는 경우 (줄임)
        strStnce = strStnce.replace(/I'd/g     , "I'd.");   
        strStnce = strStnce.replace(/I'll/g    , "I'll.");        
        strStnce = strStnce.replace(/You'll/g  , "You'll.");        
        strStnce = strStnce.replace(/He'll/g   , "He'll.");        
        strStnce = strStnce.replace(/She'll/g  , "She'll.");
        strStnce = strStnce.replace(/They'll/g , "They'll."); 
        strStnce = strStnce.replace(/We'll/g   , "We'll.");
        
        strStnce = strStnce.replace(/it's/g    , "it's.");        
        strStnce = strStnce.replace(/there's/g , "there's.");
        strStnce = strStnce.replace(/that's/g  , "that's.");
        
        strStnce = strStnce.replace(/It's/g    , "It's.");        
        strStnce = strStnce.replace(/There's/g , "There's.");
        strStnce = strStnce.replace(/That's/g  , "That's."); 

        strStnce = strStnce.replace(/don't/g   , "don't.");
        strStnce = strStnce.replace(/doesn't/g , "doesn't.");
        strStnce = strStnce.replace(/didn't/g  , "didn't.");        
        strStnce = strStnce.replace(/isn't/g   , "isn't.");        
        strStnce = strStnce.replace(/wasn't/g  , "wsn't."); 
        
        // > 뒤에 . 붙는 경우 (두 단어)        
        strStnce = strStnce.replace(/ Do you /g    , " Do you. ");        
        strStnce = strStnce.replace(/ Did you /g   , " Did you. ");        
        strStnce = strStnce.replace(/ Would you /g , " Would you. ");        
        strStnce = strStnce.replace(/ Could you /g , " Could you. ");        
        
        strStnce = strStnce.replace(/to. have./g   , "to. have");        
        strStnce = strStnce.replace(/to. has./g    , "to. has");
        strStnce = strStnce.replace(/to. be./g     , "to. be");        

        strStnce = strStnce.replace(/what. to./g   , "what to.");        
        strStnce = strStnce.replace(/how. to./g    , "how to.");        
        strStnce = strStnce.replace(/when. to./g   , "when to.");        
        strStnce = strStnce.replace(/which. to./g  , "which to.");   
        
        strStnce = strStnce.replace(/can. be./g    , "can be.");  
        strStnce = strStnce.replace(/will. be./g   , "will be.");
        strStnce = strStnce.replace(/shall. be./g  , "shall be.");        
        strStnce = strStnce.replace(/could. be./g  , "could be.");        
        strStnce = strStnce.replace(/would. be./g  , "would be.");        
        strStnce = strStnce.replace(/should. be./g , "should be."); 
        
        strStnce = strStnce.replace(/ has. to. /g  , " has to. ");        
        strStnce = strStnce.replace(/ have. to. /g , " have to. ");      
        strStnce = strStnce.replace(/that. into./g , "that into.");
        
        strStnce = strStnce.replace(/I'll. be./g   , "I'll be.");        
        strStnce = strStnce.replace(/You'll. be./g , "You'll be.");
        strStnce = strStnce.replace(/He'll. be./g  , "He'll be.");
        strStnce = strStnce.replace(/She'll. be./g , "She'll be.");
        strStnce = strStnce.replace(/They'll. be./g, "They'll be.");        
        
        // < 앞에 . 붙는 경우 (한 단어)
        strStnce = strStnce.replace(/ because /g   , ". because ");
        strStnce = strStnce.replace(/ or /g        , ". or ");  
        
        strStnce = strStnce.trim();
    }
	
    voices = synth.getVoices();		
    
	for (i = 0; i < voices.length ; i++) {
	    if(voices[i].name == strNation){
	    	langno = i;
	        break;
	    }
    }
	console.log(strStnce);
    var utterThis = new SpeechSynthesisUtterance(strStnce);

    utterThis.voice = voices[langno];
    utterThis.pitch = 1;
    utterThis.rate  = 1;			

    synth.speak(utterThis);
};