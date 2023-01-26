<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    
    <script src="js/daily_study.js"></script>
    <script src="js/living_english_data.js"></script>    
    <script src="js/modam.js"></script>    

	<style>
	.ui-block-a, .ui-block-b, .ui-block-c {
	    text-align: center;
	}
	</style>

    <title>LIVING ENGLISH STUDY</title>
    </head>
    <body>
    <div data-role="page" id="page1">  
	    <div data-role="header"  data-position="fixed" data-theme="e">          
			<h2 style="color:#800000;">MODAM DIALOG</h2>
			<a href="/modam.html" data-icon="home" data-iconpos="notext" onClick="player.pause();">home</a>
			 			
			<div data-role="navbar">
				<ul>
					<li><a href="#"      data-icon="carat-r"  onClick="submitTopic('2', '${topicBean.getTopicNum()}')">NEXT</a></li>
					<li><a href="#"      data-icon="carat-l"  onClick="submitTopic('1', '${topicBean.getTopicNum()}')">BACK</a></li>
					<li><a href="#"      data-icon="back"     onClick="submitTopic('0', '${topicBean.getTopicNum()}')">FIRST</a></li>
                    <li><a href="#"      data-icon="carat-d"  onClick="submitTopic('3', '${topicBean.getTopicNum()}')">COMPLETE</a></li>
                    <li><a href="#page2" data-icon="comment"  style="color:#800000;">NEXT PAGE</a></li>
				</ul>                  	                  	
			</div>			        		
		</div>
	    <div data-role="content">		
			<form id="frmTopic" name="frmTopic" method=post action="/livingenglishdata.do">
				<!-- 한/영 Topic 레이아웃 -->
				<fieldset class="ui-grid-a">
					<div class="ui-block-a" style="width: 25%;">
						<p style="font-size: 16px;">한글 Topic</p>
                       </div>
					<div class="ui-block-b" style="width: 75%;">
                            <textarea id="topic_kr" name="topic_kr" cols="30" rows="2" required wrap="hard" 
                                      style="resize: vertical; background-color: #FFFFFF; font-size: 16px;">${topicBean.getTopicKr()}</textarea>
                       </div>
					<div class="ui-block-a" style="width: 25%;">
					    <p style="font-size: 16px;">영어 Topic</p>
                       </div>
					<div class="ui-block-b" style="width: 75%;">
                            <textarea id="topic_en" name="topic_en" cols="30" rows="2" required wrap="hard" 
                                      style="resize: vertical; background-color: #FFFFFF; font-size: 16px;">${topicBean.getTopicEng()}</textarea>
                       </div>   							
				</fieldset>
				<!-- Topic No/File Name 레이아웃 -->
				<fieldset class="ui-grid-c">
					<div class="ui-block-a">
						<p style="font-size: 16px;">Topic No</p>
                    </div>
					<div class="ui-block-b">
				        <input id="topic_num" type="text" value="${topicBean.getTopicNum()}" />
                    </div>
					<div class="ui-block-c">
					    <p style="font-size: 16px;">Audio File</p>
                    </div>
					<div class="ui-block-d">
					    <input id="audio_file_date" type="text" value="${topicBean.getAudioFileDate()}" />
                    </div>   							
				</fieldset>
				<!-- Topic 버튼 레이아웃 -->
				<fieldset class="ui-grid-b">
					<div class="ui-block-a">
					    	 <input type="button" id="title_reset"  value="Reset"  onclick="setTitleReset()" data-icon="delete" data-iconpos="right" />
                       </div>
					<div class="ui-block-b">
				    	 <input type="button" id="save"  value="Save"  style="background-color: #f44336;" onclick="saveTopicInfo()" data-icon="check" data-iconpos="right" />
                       </div>
					<div class="ui-block-c">
				    	 <input type="button" id="refresh"  value="Refresh"  style="background-color: #4CAF50;" onclick="setRefresh('${topicBean.getTopicNum()}')" data-icon="refresh" data-iconpos="right" />
                       </div>
				</fieldset>
				<!-- Audio Control 레이아웃 -->
				<fieldset class="ui-grid-solo" style="display:block;">
					<div class="ui-block-a">
 					   <audio id="player" src=""  controls="controls" loop="loop" preload="auto"  type="audio/mpeg" style="width:100%;">
					   </audio>
					</div>
                </fieldset>
				<!-- Topic Duration Setting 버튼 레이아웃 -->
				<fieldset class="ui-grid-b" >
					<div class="ui-block-a">
						<input type="radio" name="rad_dur" id="rad_dur0" value="r0" checked="checked" />
						<label for="rad_dur0">Full Dialog</label>
                    </div>
					<div class="ui-block-b">
						<input type="radio" name="rad_dur" id="rad_dur1" value="r1" />
						<label for="rad_dur1">Topic Dialog</label>
                    </div>
					<div class="ui-block-c">
						<input type="radio" name="rad_dur" id="rad_lesson_dur" value="r2" />
						<label for="rad_lesson_dur">Lesson Dialog</label>
                    </div>
				</fieldset>
				<!-- Topic Duration Setting 버튼 레이아웃 -->
				<fieldset class="ui-grid-b">
					<div class="ui-block-a">
   			    	     <input type="button" id="play"  value="Play"  data-icon="audio" data-iconpos="right" />
                    </div>
					<div class="ui-block-b">
				    	 <input type="button" id="stop"  value="Stop"  data-icon="check" data-iconpos="right" />
                    </div>
					<div class="ui-block-c">
				    	 <input type="button" id="reset"  value="Reset"  onclick="setReset()" style="background-color: #4CAF50;" data-icon="delete" data-iconpos="right" />
                    </div>
				</fieldset>
				<fieldset class="ui-grid-b">
					<div class="ui-block-a">
					    <p style="font-size: 16px;">Audio Volume</p>
                    </div>
					<div class="ui-block-b">
				        <input id="volume_size" type="text" value="${topicBean.getVolumeSize()}" />
                    </div>
					<div class="ui-block-c">
			    	    <input type="button" id="reset_volume" value="Vol Set" onclick="resetVolumeInfo()" style="background-color: #f44336;" data-icon="delete" data-iconpos="right" />
                    </div>
				</fieldset>
				<!-- Topic Duration 레이아웃 -->
				<fieldset class="ui-grid-b">
					<div class="ui-block-a">
						<p style="font-size: 16px;">Duration Start</p>
                    </div>
					<div class="ui-block-b">
				        <input id="topic_dur_start" type="text" value="${topicBean.getTopicDurStart()}" />
                    </div>
					<div class="ui-block-c">
				    	 <input type="button" id="s_pause" value="S-Point" style="background-color: #f44336;" data-icon="check" data-iconpos="right" />
                    </div>
					<div class="ui-block-a">
					    <p style="font-size: 16px;">Duration End</p>
                    </div>
					<div class="ui-block-b">
				        <input id="topic_dur_end" type="text" value="${topicBean.getTopicDurEnd()}" />
                    </div>
					<div class="ui-block-c">
				    	 <input type="button" id="e_pause" value="E-Point" style="background-color: #f44336;" data-icon="check" data-iconpos="right" />
                    </div>
				</fieldset>
				<!-- Hidden 변수 -->
		        <input id="pnum"        name="pnum"        type="hidden" />
		        <input id="drctStatus"  name="drctStatus"  type="hidden" />
	        </form>
			<br>
 		</div> 
 		<!-- 
		<div data-role="footer" data-position="fixed">
		    <h4>Vocabulary is an instrument of English</h4>	       
		</div>
 		-->
 	</div>	
    <div data-role="page" id="page2"> 
	    <div data-role="header" data-theme="e">   
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
			<div data-role="navbar">
				<ul>
					<li><a href="#page1" data-icon="carat-l"  style="background-color:#E9E9E9;color:#800000;">BACK</a></li>
				</ul>                  	              
			</div>			        		
		</div>
	    <div data-role="content">
	    </div>
    </div>
    </body>
    </html>