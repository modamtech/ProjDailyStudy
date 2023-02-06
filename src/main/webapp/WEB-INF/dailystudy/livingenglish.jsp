<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
    <link type="text/css" rel="stylesheet" href="css/modam.css" />    
    <script src="js/living_english.js"></script>
    <script src="js/modam.js"></script>    

    <title>VOCBULARY STUDY</title>
    </head>
    <body onLoad="init()">
   	<% %>            
    <div data-role="page" id="page1">  
	    <div data-role="header"  data-position="fixed" data-theme="e"> 
	     <!--         
			<h2 style="color:#800000;">MODAM VOCA</h2>
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
		 -->	 			
			<div data-role="navbar">
				<ul>
					<li><a href="#"      data-icon="arrow-r"  onclick="updateNextNum('${exprBean.getNum()}')">Next</a></li>
					<li><a href="#"      data-icon="arrow-l"  onclick="updatePrevNum('${exprBean.getNum()}')">Prev</a></li>
					<li><a href="#"      data-icon="back"     onclick="updateNextNum('0')">First</a></li>
                    <li><a href="#page1" id="cmplte" data-icon="search" onClick="updateDone('${exprBean.getNum()}')">Complete</a></li>
                    <li><a href="#page2" data-icon="search" onClick="setTextArea()" style="background-color:#E9E9E9;color:#800000;">Info</a></li>                    
				</ul>                  	                  	
			</div>			        		
		</div>
	    <div data-role="content">		
			<form id="frmVoca" name="frmVoca" method=post action="/livingenglish.do">
				<div id="vocaDiv" style="display:block;">					
					<div class="ui-grid-a">
						<div class="ui-bar" align="center" style="background-color:#E9E9E9;color:blue">
   							<b>학습 대상 표현</b>
    						 <input type="button" onclick="submitTTS(3, 21)"   data-icon="audio"    data-iconpos="notext"  data-inline="true"/>							
    						 <input type="button" onclick="showDefinition(1)"  data-icon="tag"      data-iconpos="notext"  data-inline="true"/>    						 
    						 <input type="button" onclick="updateFrequency('${exprBean.getNum()}')" data-icon="heart"      data-iconpos="notext"  data-inline="true"/>
						</div>
					</div>
					<div class="ui-grid-a">
						<div class="ui-bar" align="center">
						     <span id="tag_info1" style="display:block;">학습횟수 : ${exprBean.getFrequency()}</span>							
						     <span id="tag_info2" style="display:none;" >${exprBean.getMainExpr()}</span>
						</div>
					</div>
   					<div class="ui-grid-a">
   						<div class="ui-bar" style="background-color:#E9E9E9;color:blue" align="center">
   							<b>학습 부가 표현</b>
   						</div>
					</div>
					<div id="ttsSpeaker1" style="display:none;" class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 <strong><a href="#" id="ttsText1"  data-inline="true"><b>${exprBean.getExpr1()}</b></a></strong>
    						 <input type="button" onclick="submitTTS(3, 11)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>
    						 
					   </div>
					</div>
					<div id="ttsSpeaker2" style="display:none;" class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 <strong><a href="#" id="ttsText2"  data-inline="true"><b>${exprBean.getExpr2()}</b></a></strong>
    						 <input type="button" onclick="submitTTS(3, 12)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>    						 
					   </div>
					</div>
					<div id="ttsSpeaker3" style="display:none;" class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 <strong><a href="#" id="ttsText3"  data-inline="true"><b>${exprBean.getExpr3()}</b></a></strong>
    						 <input type="button" onclick="submitTTS(3, 13)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>    						 
					   </div>
					</div>	
                    <div id="ttsSpeaker4" style="display:none;" class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 <strong><a href="#" id="ttsText4"  data-inline="true"><b>${exprBean.getExpr4()}</b></a></strong>
    						 <input type="button" onclick="submitTTS(3, 14)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>    						 
					   </div>
					</div>
                    <div id="ttsSpeaker5" style="display:none;" class="ui-grid-a">
                       <div class="ui-bar" align="center" > 			
    						 <strong><a href="#" id="ttsText5"  data-inline="true"><b>${exprBean.getExpr5()}</b></a></strong>
    						 <input type="button" onclick="submitTTS(3, 15)" data-icon="audio"  data-iconpos="notext"  data-inline="true"/>    						 
					   </div>
					</div>						
				</div>

		        <input id="tts_expr0"      name="tts_expr0"    type="hidden"    value="${exprBean.getExpr1()}"/>   		        
		        <input id="tts_expr1"      name="tts_expr1"    type="hidden"    value="${exprBean.getExpr1()}"/>
		        <input id="tts_expr2"      name="tts_expr2"    type="hidden"    value="${exprBean.getExpr2()}"/>
		        <input id="tts_expr3"      name="tts_expr3"    type="hidden"    value="${exprBean.getExpr3()}"/>
		        <input id="tts_expr4"      name="tts_expr4"    type="hidden"    value="${exprBean.getExpr4()}"/>
		        <input id="tts_expr5"      name="tts_expr5"    type="hidden"    value="${exprBean.getExpr5()}"/>
		        <input id="tts_mainexpr"   name="tts_mainexpr" type="hidden"    value="${exprBean.getMainExpr()}"/>		        
		        
		        <input id="num"            name="num"          type="hidden"    value="${exprBean.getNum()}"/>
		        <input id="direction"      name="direction"    type="hidden"    value=""/>
	        </form>
			<br>
 		</div>
 		<!-- 
		<div data-role="footer" data-position="fixed">
		    <h4>Vocabulary is an instrument of English</h4>	       
		</div>
 		-->		
 	</div>	
    </body>
    </html>