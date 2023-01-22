$(document).ready(function(){
    player.volume = parseInt($("#volume_size").val()) * 0.1;
	player.src = "https://upsisa.ybmnet.co.kr/freezone/e900/mp3/" + $("#audio_file_date").val() + ".mp3";
    player.currentTime=parseInt($("#topic_dur_start").val());
    player.play();
    
    // play 버튼 클릭시 
    $("#play").click(function(){
        player.volume = parseInt($("#volume_size").val()) * 0.1;
    	player.src = "https://upsisa.ybmnet.co.kr/freezone/e900/mp3/" + $("#audio_file_date").val() + ".mp3";
        player.currentTime=parseInt($("#topic_dur_start").val());
        player.play();
    });     
    
    // start pause 버튼 클릭시    
    $("#s_pause").click(function(){ 
       	$("#topic_dur_start").val(parseInt(player.currentTime));
        /*
        var strTopicNum      = $("#topic_num").val();
        var strAudioFileDate = $("#audio_file_date").val();
        var strTopicDurStart = $("#topic_dur_start").val();
       
        // contentType : 한글깨짐 해결을 위한 설정임
    	$.ajax(
    	 	  {
    		    url:  '/reset_dur_start.do?topic_num='+strTopicNum+'&audio_file_date='+strAudioFileDate
    		         +'&topic_dur_start='+strTopicDurStart,
    		    success: function(dto){		
    		       alert("duration start resetted");
    		       $("#play").click();		       
    		    },
    		    error: function(){
     		       alert("duration start error");
    		    }
    	      }	
        );
        */
    });
    
    // end pause 버튼 클릭시    
    $("#e_pause").click(function(){ 
	    $("#topic_dur_end").val(parseInt(player.currentTime));
    });
    
    // stop 버튼 클릭시
    $("#stop").click(function(){
        player.pause();
    });    
    
    player.addEventListener("timeupdate", function(e){
        var start_time_val = $("#topic_dur_start").val();
        var end_time_val   = $("#topic_dur_end").val();    	
    	
        // duration 범위를 반복 재생하는 기능
        if (player.currentTime > parseInt(end_time_val) &&
        	parseInt(end_time_val) > 0 ) 
        {
            player.currentTime=parseInt(start_time_val);
            player.play();        	
        }
    });    
})
   
function submitTopic(pVal, pCrrntNum)
{
    var objDrctStatus = document.getElementById("drctStatus");
    objDrctStatus.value = pVal;
    
	// 현재 학습 위치 setup
	var objNum = document.getElementById("pnum");
	objNum.value = pCrrntNum;
	
    document.getElementById("frmTopic").submit();
}

//Topic Info insert/update ajax function     
function saveTopicInfo()
{
    var objTopicNum      = document.getElementById("topic_num");
    var objAudioFileDate = document.getElementById("audio_file_date");
    var objVolumeSize    = document.getElementById("volume_size");
    var objTopicKr       = document.getElementById("topic_kr");
    var objTopicEn       = document.getElementById("topic_en");
    var objTopicDurStart = document.getElementById("topic_dur_start");
    var objTopicDurEnd   = document.getElementById("topic_dur_end");
    
    var strTopicNum      = objTopicNum.value;
    var strAudioFileDate = objAudioFileDate.value;
    var strVolumeSize    = objVolumeSize.value;
    var strTopicKr       = objTopicKr.value;
    var strTopicEn       = objTopicEn.value;
    var strTopicDurStart = objTopicDurStart.value;
    var strTopicDurEnd   = objTopicDurEnd.value;
    
    // contentType : 한글깨짐 해결을 위한 설정임
	$.ajax(
	 	  {
		    url:  '/save_topic.do?topic_num='+strTopicNum+'&audio_file_date='+strAudioFileDate
		         +'&volume_size='+strVolumeSize+'&topic_kr='+strTopicKr+'&topic_en='+strTopicEn
		         +'&topic_dur_start='+strTopicDurStart+'&topic_dur_end='+strTopicDurEnd,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    success: function(dto){		
		       alert("insert/updating saved");
		       setRefresh(strTopicNum);
		    },
		    error: function(){
 		       alert("insert/updating error");
		    }
	      }	
    );
}

//Volume Resetting     
function resetVolumeInfo()
{
    var objTopicNum      = document.getElementById("topic_num");
    var objAudioFileDate = document.getElementById("audio_file_date");
    var objVolumeSize    = document.getElementById("volume_size");
    
    var strTopicNum      = objTopicNum.value;
    var strAudioFileDate = objAudioFileDate.value;
    var strVolumeSize    = objVolumeSize.value;
   
    // contentType : 한글깨짐 해결을 위한 설정임
	$.ajax(
	 	  {
		    url:  '/reset_volume.do?topic_num='+strTopicNum+'&audio_file_date='+strAudioFileDate
		         +'&volume_size='+strVolumeSize,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    success: function(dto){		
		       alert("volume resetted");
		       $("#play").click();		       
		    },
		    error: function(){
 		       alert("volume resetting error");
		    }
	      }	
    );
}

function setTitleReset() 
{
   $("#topic_kr").val("");
   $("#topic_en").val("");        		
}

function setReset() 
{
   player.pause();
   $("#topic_dur_start").val(0);
   $("#topic_dur_end").val(0);
   player.currentTime = parseInt(0);
}

function setRefresh(pCrrntNum)
{
   var objNum = document.getElementById("pnum");
   objNum.value = pCrrntNum;
	
    document.getElementById("frmTopic").submit();
}