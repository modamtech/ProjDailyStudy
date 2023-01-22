package com.modam.view.dstudy;

import com.modam.biz.dstudy.LivingEngVo;
import com.modam.biz.dstudy.impl.LivingEnglishDAO;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LivingEnglishController {
  @RequestMapping({"/livingenglish.do"})
  public String selectLivingEngInfo(String num, LivingEnglishDAO vocaMgr, Model model) throws Exception {
    LivingEngVo exprBean = new LivingEngVo();
    num = vocaMgr.getCrrNum();
    try {
      if (num == null) {
        exprBean.setNum("1");
      } else {
        exprBean.setNum(num);
      } 
    } catch (Exception exception) {}
    List<LivingEngVo> vlist = null;
    int intChr = 0;
    try {
      vlist = vocaMgr.selectLivingEngTmp(exprBean.getNum());
      exprBean = vlist.get(0);
    } catch (Exception exception) {}
    model.addAttribute("exprBean", exprBean);
    vocaMgr.closeMybatis();
    return "livingenglish";
  }
  
  @RequestMapping({"/upd_living_english_frequency.do"})
  public String updateFrequency(String num, LivingEnglishDAO vocaMgr) throws Exception {
    LivingEngVo exprBean = new LivingEngVo();
    num = vocaMgr.getCrrNum();
    try {
      if (num == null)
        num = "1"; 
    } catch (Exception e) {
      num = "1";
    } 
    exprBean.setNum(num);
    vocaMgr.updateFrequency(num);
    vocaMgr.clearStarMark();
    vocaMgr.updateNextNum(num);
    vocaMgr.closeMybatis();
    return "redirect:/livingenglish.do";
  }
  
  @RequestMapping({"/upd_living_english_nextnum.do"})
  public String updateNextNum(String num, String direction, LivingEnglishDAO vocaMgr) throws Exception {
    vocaMgr.clearStarMark();
    String tmpNum = "";
    String tmpStatus = vocaMgr.getCrrStatus(num);
    if (tmpStatus == null) {
      vocaMgr.updateNextNum("1");
    } else if (tmpStatus.equals("D")) {
      if (direction.equals("F")) {
        tmpNum = String.valueOf(Integer.valueOf(num).intValue() + 1);
      } else {
        tmpNum = String.valueOf(Integer.valueOf(num).intValue() - 1);
      } 
      vocaMgr.updateNextNum(tmpNum);
    } else {
      vocaMgr.updateNextNum(num);
    } 
    vocaMgr.closeMybatis();
    return "redirect:/livingenglish.do";
  }
  
  @RequestMapping({"/completed_living_english.do"})
  public String updateDone(String num, LivingEnglishDAO vocaMgr) throws Exception {
    vocaMgr.clearStarMark();
    vocaMgr.updateDone(num);
    String tmpNum = String.valueOf(Integer.valueOf(num).intValue() + 1);
    vocaMgr.updateNextNum(tmpNum);
    vocaMgr.closeMybatis();
    return "redirect:/livingenglish.do";
  }
}