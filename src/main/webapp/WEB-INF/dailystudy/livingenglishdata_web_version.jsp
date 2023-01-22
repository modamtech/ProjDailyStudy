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

    <title>LIVING ENGLISH STUDY</title>
    </head>
    <body>
    <div data-role="page" id="page1">  
	    <div data-role="header"  data-position="fixed" data-theme="e">          
			<h2 style="color:#800000;">MODAM DIALOG</h2>
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
			 			
			<div data-role="navbar">
				<ul>
					<li><a href="#"      data-icon="carat-r"  onClick="submitTopic('2', '${topicBean.getTopicNum()}')">NEXT</a></li>
					<li><a href="#"      data-icon="carat-l"  onClick="submitTopic('1', '${topicBean.getTopicNum()}')">BACK</a></li>
					<li><a href="#"      data-icon="back"     onClick="submitTopic('0', '${topicBean.getTopicNum()}')">FIRST</a></li>
				</ul>                  	                  	
			</div>			        		
		</div>
	    <div data-role="content">		
			<form id="frmTopic" name="frmTopic" method=post action="/livingenglishdata.do">
				<div id="vocaDiv" style="display:block;">
					<div class="ui-grid-c">
						<div class="ui-bar" style="background-color:#E9E9E9;" align="center">
							<table>
							    <tr>	
							 	   <td>							
								     <input id="topic_kr" type="text" style="width:300px;  margin:10px;  padding:10px" 
								            value="${topicBean.getTopicKr()}" data-inline="true"/>
								   </td>
							 	   <td>							
								     <input id="topic_en" type="text" style="width:300px;  margin:10px;  padding:10px" 
								            value="${topicBean.getTopicEng()}" data-inline="true"/>
								   </td>
								   <td>								   								   
							    	 <input type="button" id="title_reset"  value="Reset"  onclick="setTitleReset()" data-icon="delete" data-iconpos="right" data-inline="true" />
								   </td>
								   <td>								   								   
							    	 <input type="button" id="save"  value="Save"  style="background-color: #f44336;" onclick="saveTopicInfo()" data-icon="check" data-iconpos="right" data-inline="true" />
								   </td>							    								   
								   <td>								   								   
							    	 <input type="button" id="refresh"  value="Refresh"  style="background-color: #4CAF50;" onclick="setRefresh('${topicBean.getTopicNum()}')" data-icon="refresh" data-iconpos="right" data-inline="true" />
								   </td>
								</tr>
							</table>
						</div>	   					
						<br/>						
						<div class="ui-bar" style="background-color:#E9E9E9;" align="center">
							<table>
							    <tr>	
							 	   <td> TOPIC NO </td>							    
							 	   <td>							
								      <input id="topic_num" type="text" style="width:120px;  margin:10px;  padding:10px" 
								             value="${topicBean.getTopicNum()}" data-inline="true"/>
								   </td>
							 	   <td> AUDIO FILE </td>							    
							 	   <td>							
								      <input id="audio_file_date" type="text" style="width:120px;  margin:10px;  padding:10px" 
								             value="${topicBean.getAudioFileDate()}" data-inline="true"/>
								   </td>
								   <td>			
									  <audio id="player" src=""  controls="controls" loop="loop" preload="auto"  type="audio/mpeg" width="100%" style="width:500px;  margin:10px;  padding:10px">
									  </audio>
								   </td>
							    </tr>
							 </table>    		
						</div>
						<br/>												
						<div class="ui-grid-d" style="background-color:#E9E9E9;"  align="center">	
						<table>
						  <tr>	
					 	    <td> DURATION START </td>								   
						    <td>            
						      <input id="topic_dur_start" type="text" style="width:120px;  margin:10px;  padding:20px"  
						     		value="${topicBean.getTopicDurStart()}" data-inline="true"/>
						    </td>
					 	    <td> DURATION END </td>								   
						    <td>            
						      <input id="topic_dur_end" type="text" style="width:120px;  margin:10px;  padding:20px"  
						     		value="${topicBean.getTopicDurEnd()}" data-inline="true"/>
						    </td>
						 	<td>							
							    <input type="button" id="play"  value="Play"  data-icon="audio" data-iconpos="right" data-inline="true" />
							    <input type="button" id="stop"  value="Stop"  data-icon="check" data-iconpos="right" data-inline="true" />
							    <input type="button" id="s_pause" value="S-Point" data-icon="check" data-iconpos="right" data-inline="true" />
							    <input type="button" id="e_pause" value="E-Point" data-icon="check" data-iconpos="right" data-inline="true" />
							</td>
						    <td>								   								   
					    	    <input type="button" id="reset"  value="Reset"  onclick="setReset()" data-icon="delete" data-iconpos="right" data-inline="true" />
						    </td>
						  </tr>    
						</table>						    			
						</div>						

					</div>
				</div>
		        <input id="pnum"        name="pnum"        type="hidden" />
		        <input id="pdnstatus"   name="pdnstatus"   type="hidden" />		        
		        <input id="num"         name="num"         type="hidden" />
   		        <input id="nextNum"     name="nextNum"     type="hidden" />
		        <input id="freqStatus"  name="freqStatus"  type="hidden" />
		        <input id="prrtyStatus" name="prrtyStatus" type="hidden" />		        
		        <input id="doneStatus"  name="doneStatus"  type="hidden" />
		        <input id="drctStatus"  name="drctStatus"  type="hidden" />
		        <input id="sortStatus"  name="sortStatus"  type="hidden" />
   		        <input id="tts_sntn"    name="tts_sntn"    type="hidden" />
   		        <input id="tts_voice"   name="tts_voice"   type="hidden" />   		        
	        </form>
			<br>
 		</div> 
		<div data-role="footer" data-position="fixed">
		    <h4>Vocabulary is an instrument of English</h4>	       
		</div>		
 	</div>	
    </body>
    </html>