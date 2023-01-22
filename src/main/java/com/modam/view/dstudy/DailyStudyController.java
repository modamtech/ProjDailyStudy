package com.modam.view.dstudy;

import com.modam.biz.dstudy.DstudyVO;
import com.modam.biz.dstudy.impl.DstudyDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DailyStudyController {
	
  @RequestMapping(value="/selectdlstudy.do")
  public String selectWordsInfo(String num, String tts_word, String mtx_guess,String tts_guess, String drctStatus, DstudyDAO vocaMgr, Model model) throws Exception {
	  
	final Logger logger = LoggerFactory.getLogger(DailyStudyController.class);
		
    DstudyVO vocaBean = new DstudyVO();
    
    List<DstudyVO> vlist = null;
    
    int intChr = 0;
    
    try {
      
      if (!tts_word.equals("") && !num.equals("")) {
   	  
        intChr = Character.toLowerCase(tts_word.charAt(0));
        
        if ("0".equals(drctStatus)) {
            String frstWrd = vocaMgr.getFirstWord();
            intChr = frstWrd.charAt(0);
        } else if ("2".equals(drctStatus) && intChr >= 97) {
            vocaMgr.updateStatus(num);
            intChr = tts_word.charAt(0) + 1;
        } else if ("1".equals(drctStatus) && intChr > 97) {
            intChr = tts_word.charAt(0) - 1;
        } else {
            intChr = tts_word.charAt(0);
        } 
        
        tts_word = String.valueOf((char)intChr);
        
    	String rtnVal = vocaMgr.selectPriority(num);
    	
    	if (!rtnVal.equals("H")) vocaMgr.updatePriority(num);
    	
        vocaMgr.updateWordGuess(num, mtx_guess);
        
        vocaMgr.deleteVocaTmp();
        vocaMgr.insertVocaTmp(tts_word);
        vlist    = vocaMgr.selectVocaTmp();
        
      } else {
  	    vlist    = vocaMgr.selectVocaTmp();
      }
    } catch (Exception e) {
      tts_word = "a";
    } 
    
    vlist    = vocaMgr.selectVocaTmp();
    vocaBean = vlist.get(0);    
    
    String todayCnt      = vocaMgr.getTotalTodayStudy();
    String todayStudyCnt = vocaMgr.getTodayStudyCount();
    
    vocaBean.setTodayCnt(todayCnt);
    vocaBean.setTodayStudyCnt(todayStudyCnt);

    String strlevel = vocaBean.getOrderPriority();
    
    strlevel = getLevelKor(strlevel);
  
    vocaBean.setOrderPriority(strlevel);    	
    
    String strIsVerb = "";	
    
    strIsVerb = vocaBean.getIsVerb();
	
    if (strIsVerb.equals("v")) vocaBean.setIsVerb("동사"); 
    else  vocaBean.setIsVerb("-");
 	
    model.addAttribute("vocaBean", vocaBean);
    vocaMgr.closeMybatis();
    
    return "selectdlstudy";
  }
  
  @RequestMapping(value="/completedlstudy.do")
  public @ResponseBody String completeWordsInfo(String pnum, String pdnstatus, String pword, DstudyDAO vocaMgr, Model model) throws Exception {
	  
    DstudyVO searchBean = new DstudyVO();
    
    pdnstatus = "D";
    
    vocaMgr.completeVocaTmp(pnum, pdnstatus, pword, "");
    
    vocaMgr.closeMybatis();
    
    return "ok";
  }
  
  @RequestMapping(value="/updatepriority.do")
  public @ResponseBody DstudyVO updatePriority(String pnum, DstudyDAO vocaMgr) throws Exception {
	  
    String strNum = pnum;
    
    DstudyVO searchBean = new DstudyVO();    
    
    vocaMgr.updatePriority(strNum);
    
    String rtnVal = vocaMgr.selectPriority(strNum);
    
    vocaMgr.closeMybatis();
    
    rtnVal = getLevelKor(rtnVal);
    
	searchBean.setOrderPriority(rtnVal);
			
    return searchBean;
  }
  
  @RequestMapping(value="/updateverb.do")
  public @ResponseBody String updateVerb(String pnum, DstudyDAO vocaMgr) throws Exception {
	  
    String strNum = pnum;
    
    vocaMgr.updateVerb(strNum);
    
    String rtnVal = vocaMgr.selectVerb(strNum);
    
    vocaMgr.closeMybatis();
    
    if (rtnVal.equals("v")) rtnVal = "동사";
    
    return rtnVal;
  }
  
  @RequestMapping(value="/initconnection.do")
  public @ResponseBody String initConnection(DstudyDAO vocaMgr) throws Exception {
	  
    vocaMgr.initConnection();
    
    vocaMgr.closeMybatis();
    
    return "ok";
  }  
  
  @RequestMapping(value="/initTodayStatus.do")
  public @ResponseBody String initTodayStatus( DstudyDAO vocaMgr ) throws Exception {
	  
    vocaMgr.initTodayStatus();
    
    vocaMgr.closeMybatis();
    
    return "ok";
  }  
  
  public void updateWord(int intChr, DstudyDAO vocaMgr) {

	int i = 0;
	
    String strWord = "";

    for (i = intChr + 1; i < 123; i++) {
      try {
        strWord = String.valueOf((char)i);
        vocaMgr.deleteVocaTmp();
        vocaMgr.insertVocaTmp(strWord);
        break;
      } catch (Exception exception) {}
    }
    
    if (i == 123) {
      strWord = "a";
      vocaMgr.deleteVocaTmp();
      vocaMgr.insertVocaTmp(strWord);
    }
  }

  @RequestMapping(value="/ajax.do")
  public @ResponseBody DstudyVO ajaxTest(DstudyDAO vocaMgr) {

	    List<DstudyVO> mlist = null;
	    
	    DstudyVO vocaBean = new DstudyVO();
	    
	     mlist = vocaMgr.selectVocaTmp();

	    vocaBean = mlist.get(0);
	    
	    return vocaBean;
  }

  public String getLevelKor(String pLevel) {
	
		String rLevel = "";
		
		if ( pLevel.equals("") || pLevel.equals("L")) rLevel = "하";
		else 
		if ( pLevel.equals("M") ) rLevel = "중";
		else 
		if ( pLevel.equals("H") ) rLevel = "상";
		
		return rLevel;
  }
  
}  