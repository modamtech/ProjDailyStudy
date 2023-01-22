var db = null;
var synth = window.speechSynthesis;
var voices = [];

var pidx  = 0;
var index = 0;
var min_index = 0;
var max_index = 0;

$(document).ready(function(){
	openDB();
	createTable();
	
	pidx  = 0;
	
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
	
	$('#btnPrev').click(function(){
		index -= 1;
		if (index < min_index) {
			$('#tts_text').val('First!!');
			index = min_index;
		} else {
			$('#tts_text').val('');
		}
		fnListview();
	});
	
	$('#btnNext').click(function(){
		index += 1;	
		if (index > max_index) {
			$('#tts_text').val('End!!');
			index = max_index;
		} else {
			$('#tts_text').val('');
		}
		fnListview();
	});	
	
	// Toeic LC result review
	$('#btnLoadToeicLcXml').click(function(){
		
		pidx = 0;
		
		db.transaction(function(tr){
			var deleteSQL = "delete from  tbl_toeic_test";
			tr.executeSql( deleteSQL, [],function(tr,rs){},function(tr,err){});		
		});
		
		$.ajax({
			url: 'xml/toeic_test_review.xml',
			type: 'get',
			dataType: 'xml',
			timeout: 10000,
			success: function(xmlData){
				
				$(xmlData).find('item').each(function(){
					
					strPsntn_no = $(this).find('no').text();					
					strP1sntn = $(this).find('content_p').find('sentence_p1').text().trim();
					strP2sntn = $(this).find('content_p').find('sentence_p2').text().trim();
					strP3sntn = $(this).find('content_p').find('sentence_p3').text().trim();
					strP4sntn = $(this).find('content_p').find('sentence_p4').text().trim();					
					strP5sntn = $(this).find('content_p').find('sentence_p5').text().trim();
					strP6sntn = $(this).find('content_p').find('sentence_p6').text().trim();					
					strAsntn = $(this).find('content_a').find('sentence').text().trim();
					strBsntn = $(this).find('content_b').find('sentence').text().trim();
					strCsntn = $(this).find('content_c').find('sentence').text().trim();
					strDsntn = $(this).find('content_d').find('sentence').text().trim();
					
					insertItem(strPsntn_no, strP1sntn, strP2sntn, strP3sntn, strP4sntn, strP5sntn, strP6sntn,strAsntn, strBsntn, strCsntn, strDsntn);
				});
				
				fnfindMinMaxIdx();
				
				var valSelNo_from = $('#selno').val();

				fnfindIdx(valSelNo_from);				
				fnListview();
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
	console.log('create DB');
}

function createTable(){

	db.transaction(function(tr){
		var createSQL = "drop table tbl_toeic_test";
		tr.executeSql(createSQL, [], function(){});
	})

	db.transaction(function(tr){
		var createSQL = "create table if not exists tbl_toeic_test(idx int, no int, cntnt1 text, cntnt2 text, cntnt3 text, cntnt4 text, cntnt5 text, cntnt6 text, exmpla text,  exmplb text, exmplc text, exmpld text)";
		tr.executeSql(createSQL, [], function(){});
	})
}

function insertItem(pno, pcntnt1, pcntnt2, pcntnt3, pcntnt4, pcntnt5, pcntnt6, pexmpla, pexmplb, pexmplc, pexmpld) {
 
	db.transaction(function(tr){
		var insertSQL  = 'insert into tbl_toeic_test(idx, no, cntnt1, cntnt2, cntnt3, cntnt4, cntnt5, cntnt6, exmpla, exmplb, exmplc, exmpld) ';
		    insertSQL += 'values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ';
			pidx += 1;		    
		tr.executeSql( insertSQL, [pidx, pno, pcntnt1, pcntnt2, pcntnt3, pcntnt4, pcntnt5, pcntnt6, pexmpla, pexmplb, pexmplc, pexmpld], 
				      function(tr,rs){ 
		                 console.log('input success');	
			          }, 
			          function(tr,err){
		                 console.log('input error');			        	  
		                 console.log(insertSQL);		                 
			          }
	    );
	});
}

function fnfindIdx(pPartVal_no){
	db.transaction(function(tr){
		var selectIdxSQL  = "";
		selectIdxSQL += "select min(idx) as min_idx";
		selectIdxSQL += "  from tbl_toeic_test ";
		selectIdxSQL += " where no >= " + pPartVal_no;
		
		tr.executeSql( selectIdxSQL, [], 
				       function(tr,rs){ 
			               index = rs.rows.item(0).min_idx;
			           });
	});		
}

function fnfindMinMaxIdx(){
	db.transaction(function(tr){
		var selectIdxSQL  = "";
		selectIdxSQL += "select min(idx) as min_idx, max(idx) as max_idx";
		selectIdxSQL += "  from tbl_toeic_test ";
		
		tr.executeSql( selectIdxSQL, [], 
				       function(tr,rs){ 
			               min_index = rs.rows.item(0).min_idx;
			               max_index = rs.rows.item(0).max_idx;			               
			           });
	});		
}

function fnListview(){
	var tagList="";
	
	db.transaction(function(tr){
		var selectSQL  = "";
		selectSQL += "select no, cntnt1, cntnt2, cntnt3, cntnt4, cntnt5, cntnt6, exmpla, exmplb, exmplc, exmpld ";
		selectSQL += "  from tbl_toeic_test ";
		selectSQL += " where idx = " + index;
		
		tr.executeSql( selectSQL, [], 
				      function(tr,rs){ 
		                 for (i=0;i<rs.rows.length;i++)
		                 {
			                 var rs_no = rs.rows.item(i).no;
			                 var rs_cntnt1 = rs.rows.item(i).cntnt1.replace(/#/g, "<br/>");			                 
			                 var rs_cntnt2 = rs.rows.item(i).cntnt2.replace(/#/g, "<br/>");;
			                 var rs_cntnt3 = rs.rows.item(i).cntnt3.replace(/#/g, "<br/>");;
			                 var rs_cntnt4 = rs.rows.item(i).cntnt4.replace(/#/g, "<br/>");;
			                 var rs_cntnt5 = rs.rows.item(i).cntnt5.replace(/#/g, "<br/>");;
			                 var rs_cntnt6 = rs.rows.item(i).cntnt6.replace(/#/g, "<br/>");;			                 
			                 var rs_exmpla = rs.rows.item(i).exmpla;			                 
			                 var rs_exmplb = rs.rows.item(i).exmplb;
			                 var rs_exmplc = rs.rows.item(i).exmplc;
			                 var rs_exmpld = rs.rows.item(i).exmpld;
			                 
							 if (rs_cntnt1 != '')
								tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+"("+rs_no+")<br/> "+rs_cntnt1+"</div></li>";
	
							 if (rs_cntnt2 != '')
								tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+rs_cntnt2+"</div></li>";					
	
							 if (rs_cntnt3 != '')
								tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+rs_cntnt3+"</div></li>";
							
							 if (rs_cntnt4 != '')
								tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+rs_cntnt4+"</div></li>";					
							 
							 if (rs_cntnt5 != '')
									tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+rs_cntnt5+"</div></li>";
							 
							 if (rs_cntnt6 != '')
									tagList+="<li style='font-weight: bold;' onclick='liText($(this).text());'><div>"+rs_cntnt6+"</div></li>";							 
							
							 if (rs_exmpla != '')					
								tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (A) "+rs_exmpla+"</li>";
							
							 if (rs_exmplb != '')					
								tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (B) "+rs_exmplb+"</li>";
							
							 if (rs_exmplc != '')					
								tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (C) "+rs_exmplc+"</li>";
							
							 if (rs_exmpld != '')					
								tagList+="<li onclick='liText($(this).text());'>"+"&nbsp;&nbsp; (D) "+rs_exmpld+"</li>";
	                	 }						
						
						 $('#listsentence').empty();
						 $('#listsentence').append(tagList);	
						 $('#listsentence').listview('refresh');		                 
			          }, 
			          function(tr,err){
		                 console.log('input error');			        	  
			          }
	    );
	});	
	
}

function liText(selectedLiText){
	//var objTtsText = document.getElementById("tts_text");
	//objTtsText.value = selectedLiText.trim();
	
	var tmpLiText = selectedLiText.replace(/            /g, "");			
	
	$('#tts_text').val(tmpLiText);
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
        
        strStnce = strStnce.replace(/can. you/g    , "can you.");  
        strStnce = strStnce.replace(/will. you/g   , "will you.");
        strStnce = strStnce.replace(/shall. you/g  , "shall you.");        
        strStnce = strStnce.replace(/could. you/g  , "could you.");        
        strStnce = strStnce.replace(/would. you/g  , "would you.");        
        strStnce = strStnce.replace(/should. you/g , "should you.");        
        
        strStnce = strStnce.replace(/has. to./g  , "has to.");        
        strStnce = strStnce.replace(/have. to./g , "have to.");      
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
    
    var langno = 0;

    for (i = 0; i < voices.length ; i++) {
	    if(voices[i].name == strNation){
	    	langno = i;
	        break;
	    }
    }
   
	console.log(strStnce);
    var utterThis = new SpeechSynthesisUtterance(strStnce);

    if (langno==0){
    	if (strNation == "Google US English") langno = 4;
    	if (strNation == "Google UK English Female") langno = 5;
        utterThis.pitch = 1;
        if (gubun == 'TRN'){
           utterThis.rate = 0.9;
        } else {
           utterThis.rate = 1;        	
        }   
    } else {
        utterThis.pitch = 1;
        utterThis.rate  = 1;
    }    
    
    utterThis.voice = voices[langno];

    synth.speak(utterThis);
};


