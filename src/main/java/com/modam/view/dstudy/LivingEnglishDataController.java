package com.modam.view.dstudy;

import com.modam.biz.dstudy.LivingEngTopicVO;
import com.modam.biz.dstudy.impl.DstudyDAO;
import com.modam.biz.dstudy.impl.LivingEngTopicDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LivingEnglishDataController {
	
  @RequestMapping(value="/livingenglishdata.do")
  public String selectTopicInfo(String pnum, String drctStatus, LivingEngTopicDAO vocaMgr, Model model) throws Exception {
	//final Logger logger = LoggerFactory.getLogger(LivingEnglishDataController.class);
    LivingEngTopicVO topicBean = new LivingEngTopicVO();
    
    List<LivingEngTopicVO> vlist = null;
    String strTopicNum = "";
    int intTopicNum    = 0;
    try {
    	intTopicNum = Integer.valueOf(pnum);
    } catch (Exception ex) {
    	try {
    		strTopicNum = vocaMgr.getStartPointTopic();
    		intTopicNum = Integer.valueOf(strTopicNum);
    	} catch(Exception ex2) {   
        	intTopicNum = Integer.valueOf(vocaMgr.getLastTopic());
    	}
    }
        
    if ("0".equals(drctStatus)) { // back
  	    intTopicNum = Integer.valueOf(vocaMgr.getFirstTopic());
    } else if ("2".equals(drctStatus)) { // foreward
    	intTopicNum = intTopicNum + 1;
    } else if ("1".equals(drctStatus)) {// backward
    	intTopicNum = intTopicNum - 1;
    } else if ("3".equals(drctStatus)) {// completed
    	vocaMgr.updateStudyStatusTopic(String.valueOf(intTopicNum));
    	intTopicNum = intTopicNum + 1;
    }
        
    try {
	    vlist     = vocaMgr.selectTopic(String.valueOf(intTopicNum));
	    topicBean = vlist.get(0);
    } catch (Exception e ) {
    	intTopicNum = Integer.valueOf(vocaMgr.getLastTopic());
	    vlist     = vocaMgr.selectTopic(String.valueOf(intTopicNum));
	    topicBean = vlist.get(0);
    }
 	
    model.addAttribute("topicBean", topicBean);
    vocaMgr.closeMybatis();
    
    return "livingenglishdata";
  }
  
  @RequestMapping(value="/save_topic.do")
  public @ResponseBody String saveTopicInfo(String topic_num, String audio_file_date, String volume_size, String topic_kr, String topic_en, String topic_dur_start, String topic_dur_end, LivingEngTopicDAO vocaMgr, Model model) throws Exception {
	try {  
		vocaMgr.insertTopic(topic_num, audio_file_date, volume_size, topic_kr, topic_en, topic_dur_start, topic_dur_end);
	} catch (Exception e) {  
		vocaMgr.saveTopic(topic_num, audio_file_date, volume_size, topic_kr, topic_en, topic_dur_start, topic_dur_end);
	}
   
    vocaMgr.closeMybatis();
			
    return "ok";
  }
  
  @RequestMapping(value="/reset_volume.do")
  public @ResponseBody String resetVolume(String topic_num, String audio_file_date, String volume_size, LivingEngTopicDAO vocaMgr, Model model) throws Exception {
    
    vocaMgr.resetVolume(volume_size);
   
    vocaMgr.closeMybatis();
			
    return "ok";
  }
  
  @RequestMapping(value="/reset_dur_start.do")
  public @ResponseBody String resetDurStart(String topic_num, String audio_file_date, String topic_dur_start, LivingEngTopicDAO vocaMgr, Model model) throws Exception {
    
    vocaMgr.resetDurStart(topic_num, audio_file_date, topic_dur_start);
   
    vocaMgr.closeMybatis();
			
    return "ok";
  }    
}  
