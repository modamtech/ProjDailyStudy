<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    <link type="text/css" rel="stylesheet" href="css/daily_study.css" />    

    <script src="js/daily_study.js"></script>
    <script src="js/modam.js"></script>    

    <title>VOCBULARY STUDY</title>
    </head>
    <body>
   	<% %>            
    <div data-role="page" id="page1">  
	    <div data-role="header"  data-position="fixed" data-theme="e">          
			<h2 style="color:#800000;">MODAM VOCA</h2>
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
			 			
			<div data-role="navbar">
				<ul>
					<li><a href="#"      data-icon="carat-r"  onClick="submitWord('2', '${varSortStatus}', '${vocaBean.getNum()}')">NEXT</a></li>
					<li><a href="#"      data-icon="carat-l"  onClick="submitWord('1', '${varSortStatus}', '${vocaBean.getNum()}')">BACK</a></li>
					<li><a href="#"      data-icon="back"     onClick="submitWord('0', '${varSortStatus}', '${vocaBean.getNum()}')">START</a></li>
                    <li><a href="#page1" id="cmplte" data-icon="carat-d" onClick="updateDone('2', '${varSortStatus}', '${vocaBean.getNum()}')">COMP.</a></li>
                    <li><a href="#page2" data-icon="comment" onClick="setTextArea();" style="background-color:#E9E9E9;color:#800000;">INFO</a></li>                    
				</ul>                  	                  	
			</div>			        		
		</div>
	    <div data-role="content">		
			<form id="frmVoca" name="frmVoca" method=post action="/selectdlstudy.do">
				<div id="vocaDiv" style="display:block;">
   					<div class="ui-grid-a">
   						<div class="ui-bar" style="background-color:#E9E9E9;color:blue" align="center">
   							<b>학습 정보</b>
							<input type="button" onclick="initTodayStatus('0', '${varSortStatus}', '${vocaBean.getNum()}');" data-icon="refresh" data-iconpos="notext" data-inline="true"/>
   						</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" align="center">
							진행 : <h6 style="color:red;">${vocaBean.getTodayStudyCnt()}</h6> &nbsp;						
							완료 : <h6 style="color:red;">${vocaBean.getTodayCnt()}</h6> &nbsp; 
   							중요도 : <h6 id="priority" style="color:red;">${vocaBean.getOrderPriority()}</h6>
						</div>
					</div>
   					<div class="ui-grid-a">
   						<div class="ui-bar" style="background-color:#E9E9E9;color:blue" align="center">
    						 <strong><a href="#" id="ttsText"  data-inline="true"><b>${vocaBean.getWord()}</b></a></strong>
   						</div>
					</div>
					<div class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 발음 <input type="button" onclick="submitTTS(3, 1)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>
    						 중요도 <input type="button" onclick="updatePriority('${vocaBean.getNum()}')" data-icon="plus"  data-iconpos="notext"  data-inline="true"/>
						     <b><h6 id="is_verb" style="color:red;"> ${vocaBean.getIsVerb()} </h6></b> <input type="button" onclick="updateVerb('${vocaBean.getNum()}')" data-icon="check"  data-iconpos="notext"  data-inline="true"/>    						     						 
					   </div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" style="background-color:#E9E9E9;color:blue" align="center">
							<b>GUESSING</b>
    						 <input type="button" onclick="showGuessing(1)" data-icon="tag" data-iconpos="notext"  data-inline="true"/>							
						</div>
					</div>
					<div class="ui-bar">
						<span id="mtx_guess_cover" style="display:block;" align="center">-</span>
                        <textarea id="mtx_guess" name="mtx_guess" style="display:none;" cols="40" rows="3" required wrap="hard" style="margin: 0px; width:100%; height:40px; resize: vertical; background-color: #FFFFFF; font-size: 19px;"/></textarea>
					</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" align="center" style="background-color:#E9E9E9;color:blue">
							<b>DEFINITION</b>
    						 <input type="button" onclick="submitTTS(3, 2)"   data-icon="audio"  data-iconpos="notext"  data-inline="true"/>							
    						 <input type="button" onclick="showDefinition(1)" data-icon="tag"    data-iconpos="notext"  data-inline="true"/>    						 
						</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" align="center">
						     <span id="tag_info1" style="display:block;">-</span>							
						     <span id="tag_info2" style="display:none;" >${vocaBean.getTag()}</span>
						</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" style="background-color:#E9E9E9;color:blue" align="center">
							<b>MEANING</b>
    						 <input type="button" onclick="showMeans(1)" data-icon="tag" data-iconpos="notext"  data-inline="true"/>							
						</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" align="center">
						     <span id="tag_mean1" style="display:block;">-</span>							
						     <span id="tag_mean2" style="display:none;">${vocaBean.getMean()}</span>
						</div>
					</div>
				</div>
		        <input id="pnum"        name="pnum"        type="hidden" />
		        <input id="pdnstatus"   name="pdnstatus"   type="hidden" />		        
		        <input id="pword"       name="pword"       type="hidden" value="${vocaBean.getWord()}"/>		        
		        <input id="num"         name="num"         type="hidden" />
   		        <input id="nextNum"     name="nextNum"     type="hidden" />
		        <input id="freqStatus"  name="freqStatus"  type="hidden" />
		        <input id="prrtyStatus" name="prrtyStatus" type="hidden" />		        
		        <input id="doneStatus"  name="doneStatus"  type="hidden" />
		        <input id="drctStatus"  name="drctStatus"  type="hidden" />
		        <input id="sortStatus"  name="sortStatus"  type="hidden" />
   		        <input id="tts_guess"   name="tts_guess"   type="hidden"  value="${vocaBean.getIsGuess()}"/>		        
   		        <input id="tts_sntn"    name="tts_sntn"    type="hidden" />
   		        <input id="tts_voice"   name="tts_voice"   type="hidden" />   		        
		        <input id="tts_num"     name="tts_num"     type="hidden" value="${vocaBean.getNum()}"/>   		        
		        <input id="tts_word"    name="tts_word"    type="hidden" value="${vocaBean.getWord()}"/>
		        <input id="tts_tag"     name="tts_tag"     type="hidden" value="${vocaBean.getTag()}"/>		        
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
			<div class="ui-grid-a">
				<div class="ui-block-a"  align="center">       
					<h4 style="color:#800000;">
						<b>${vocaBean.getWord()}</b>
					</h4>
				</div>
			</div>
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
			<div data-role="navbar">
				<ul>
					<li><a href="#page1" data-icon="carat-l"  style="background-color:#E9E9E9;color:#800000;">BACK</a></li>
					<li><a href="#page1" data-icon="carat-r"  style="background-color:#E9E9E9;color:#800000;" onClick="submitWord('2', '${varSortStatus}', '${vocaBean.getNum()}');">NEXT</a></li>
					<li><a href="#"      data-icon="audio" onclick="submitTTS(3, 1);">word</a></li>					
					<li><a href="#"      data-icon="audio" onclick="submitTextTTS(4,'STN');">example</a></li>
					<!-- 
					<li><a href="#"      data-icon="audio" onclick="submitTextTTS(4,'CHK');">part1</a></li>					
					<li><a href="#"      data-icon="audio" onclick="submitTextTTS(4,'ADV');">part2</a></li>
					 -->
                    <li><a href="#page1" id="cmplte" data-icon="carat-d" onClick="updateDone('D', '${vocaBean.getNum()}')">COMP.</a></li>					
				</ul>                  	              
			</div>			        		
		</div>
	    <div data-role="content">
	  	   	<div id="meanDiv"> 
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar" style="background-color:#E9E9E9; font-size:15px">
                             <textarea id="tts_text" cols="40" rows="20" required wrap="hard" onclick="submitTextTTS(3,'STN');" placeholder="You can put sentences here to listen... " style="margin: 0px; width:100%; height:40px; resize: vertical; background-color: #A9D08E; font-size: 19px;"/></textarea>
						</div>
					</div>
				</div>
				<div class="ui-grid-a">
					<!-- 1. 영어 예문 -->								
   					<div id="eng_example1" class="ui-bar" style="background-color:#C6E0B4;" onClick="putEngExample('${vocaBean.getWordEngExample()}')">
	   					${vocaBean.getWordEngExample()}
					</div>
					<!-- 1. 한글 예문 -->										
					<div class="ui-bar" style="background-color:#FFFFFF;color:black;">
	   					${vocaBean.getWordKorExample()}					
					</div>
					<!-- 2. 영어 예문 -->										
					<div id="eng_example2" class="ui-bar" style="background-color:#C6E0B4;" onClick="putEngExample('${vocaBean.getWordEngExample2()}')">
	   					${vocaBean.getWordEngExample2()}					
					</div>
					<!-- 2. 한글 예문 -->
					<div class="ui-bar" style="background-color:#FFFFFF;color:black;">
	   					${vocaBean.getWordKorExample2()}					
					</div>
					<div class="ui-bar" align="center" style="background-color:#E9E9E9;color:blue">
						<b>PART1</b><input type="button" onclick="submitTextTTS(3,'CHK');"   data-icon="audio"  data-iconpos="notext"  data-inline="true"/>						
						<b>PART2</b><input type="button" onclick="submitTextTTS(3,'ADV');"   data-icon="audio"  data-iconpos="notext"  data-inline="true"/>						
					</div>					
   					<div id="div_part0" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 10);">
   					     <h><span id="tts_part0" style="color:black;"></span></h> 
					</div>
   					<div id="div_part1" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 11);">
   					     <span id="tts_part1" style="color:black;"></span>   					
					</div>
   					<div id="div_part2" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 12);">
   					     <span id="tts_part2" style="color:black;"></span>   					
					</div>					
   					<div id="div_part3" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 13);">
   					     <span id="tts_part3" style="color:black;"></span>   					
					</div>																				
   					<div id="div_part4" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 14);">
   					     <span id="tts_part4" style="color:black;"></span>   					
					</div>
   					<div id="div_part5" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 15);">
   					     <h><span id="tts_part5" style="color:black;"></span></h>	   					
					</div>
                    <div id="div_part6" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 16);">
   					     <span id="tts_part6" style="color:black;"></span>                    
					</div>
                    <div id="div_part7" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 17);">
   					     <span id="tts_part7" style="color:black;"></span>                    
					</div>
                    <div id="div_part8" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 18);">
   					     <span id="tts_part8" style="color:black;"></span>                    
					</div>
                    <div id="div_part9" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 19);">
   					     <span id="tts_part9" style="color:black;"></span>                    
					</div>					
                    <div id="div_part10" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 20);">
   					     <span id="tts_part10" style="color:black;"></span>                    
					</div>
                    <div id="div_part11" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 21);">
   					     <span id="tts_part11" style="color:black;"></span>                    
					</div>										
                    <div id="div_part12" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 22);">
   					     <span id="tts_part12" style="color:black;"></span>                    
					</div>										
                    <div id="div_part13" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 23);">
   					     <span id="tts_part13" style="color:black;"></span>                    
					</div>
                    <div id="div_part14" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 24);">
   					     <span id="tts_part14" style="color:black;"></span>                    
					</div>
                    <div id="div_part15" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 25);">
   					     <span id="tts_part15" style="color:black;"></span>                    
					</div>										
                    <div id="div_part16" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 26);">
   					     <span id="tts_part16" style="color:black;"></span>                    
					</div>										
                    <div id="div_part17" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 27);">
   					     <span id="tts_part17" style="color:black;"></span>                    
					</div>
                    <div id="div_part18" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 28);">
   					     <span id="tts_part18" style="color:black;"></span>                    
					</div>										
                    <div id="div_part19" class="ui-bar" style="background-color:#C6E0B4;display:none;" onClick="submitTTS(3, 29);">
   					     <span id="tts_part19" style="color:black;"></span>                    
					</div>																																																																																																																																																											
				</div><br/>
			</div>			
		</div>
		<!-- 
		<div data-role="footer" data-position="fixed">
		    <h4>Vocabulary is an instrument of English</h4>	       
		</div>
 		-->		
	</div>
    </body>
    </html>