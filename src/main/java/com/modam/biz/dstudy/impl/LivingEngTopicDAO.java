package com.modam.biz.dstudy.impl;

import com.modam.biz.common.SqlSessionFactoryBean;
import com.modam.biz.dstudy.DstudyNumVO;
import com.modam.biz.dstudy.DstudyVO;
import com.modam.biz.dstudy.LivingEngTopicVO;
import com.modam.biz.dstudy.LivingEngVo;
import com.modam.biz.dstudy.StudyXmlVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("livingEngTopicDAO")
public class LivingEngTopicDAO {
  final Logger logger = LoggerFactory.getLogger(LivingEngTopicDAO.class);
  
  private SqlSession mybatis;
  
  public LivingEngTopicDAO() {
    this.mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
  }
  
  public Vector<DstudyVO> getDailyVocaNotAll(int pNum) {
    String varSql = "";
    String varSql1 = "";
    String varSql2 = "";
    String varSql3 = "";
    
	List<DstudyVO> llist   = new ArrayList<DstudyVO>();	    
	Vector<DstudyVO> vlist = new Vector<DstudyVO>();		
    
    varSql1 = "SELECT num, word, tag, frequency_chg, order_priority, mean, ";
    varSql1 = String.valueOf(varSql1) + "       word_eng_example, word_kor_example, word_eng_example2, word_kor_example2, start_date ";
    varSql1 = String.valueOf(varSql1) + "  FROM daily_voca ";
    varSql1 = String.valueOf(varSql1) + " WHERE num = 'D' ";
    varSql = String.valueOf(varSql1) + varSql2 + varSql3;
    
    Map<String, Object> inputData = new HashMap<String, Object>();
    
    inputData.put("varSql", varSql);
    llist = this.mybatis.selectList("LivingEngTopicDAO.getDailyVocaNotAll", inputData);
    Iterator<DstudyVO> l_itrtr = llist.iterator();
    if (!llist.isEmpty())
      while (l_itrtr.hasNext()) {
        DstudyVO regBean = new DstudyVO();
        regBean = l_itrtr.next();
        vlist.add(regBean);
      }  
    return vlist;
  }
  
  public DstudyNumVO getRccntNum() {
    return (DstudyNumVO)this.mybatis.selectOne("LivingEngTopicDAO.getRccntNum");
  }
  
  public DstudyNumVO getTotalCount() {
    return (DstudyNumVO)this.mybatis.selectOne("LivingEngTopicDAO.getTotalCount");
  }
  
  public String getTotalTodayStudy() {
    return (String)this.mybatis.selectOne("LivingEngTopicDAO.getTotalTodayStudy");
  }
  
  public void insertTtsText(String strKey, String strWord, String strSaveTTS) {
	  
    Map<String, Object> inputData = new HashMap<String, Object>();	  

    DstudyNumVO mvo = new DstudyNumVO();
    String strNum = "";
    
    try {
      mvo = (DstudyNumVO)this.mybatis.selectOne("LivingEngTopicDAO.getMaxNum");
      strNum = mvo.getNum();
    } catch (Exception e) {
      strNum = "0";
    } 
    strNum = String.valueOf(Integer.valueOf(strNum).intValue() + 1);
    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("no", strNum);
    inputData.put("sentence", strSaveTTS);
    this.mybatis.insert("LivingEngTopicDAO.insertTtsText", inputData);
    this.mybatis.commit();
  }
  
  public void insertDailyStudyList(String strKey, String strWord, String strStartDate) {
	  
	Map<String, Object> inputData = new HashMap<String, Object>();	  

    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("startdate", strStartDate);
    try {
      this.mybatis.insert("LivingEngTopicDAO.insertDailyStudyList", inputData);
    } catch (Exception e) {
      this.mybatis.update("LivingEngTopicDAO.updateDailyStudyList", inputData);
    } 
    this.mybatis.commit();
  }
  
  // LYH.201301
  public String getFirstTopic() {
    return (String)this.mybatis.selectOne("LivingEngTopicDAO.getFirstTopic");
  }
  
  public String getLastTopic() {
	    return (String)this.mybatis.selectOne("LivingEngTopicDAO.getLastTopic");
	  }
  
  public void insertTopic( String topic_num, String audio_file_date, String volume_size, String topic_kr, String topic_en, String topic_dur_start, String topic_dur_end ) {
	    Map<String, Object> inputData = new HashMap<String, Object>();

	    inputData.put("topic_num"      , topic_num      );
	    inputData.put("audio_file_date", audio_file_date);
	    inputData.put("volume_size"    , volume_size);
	    inputData.put("topic_kr"       , topic_kr);
	    inputData.put("topic_en"       , topic_en);
	    inputData.put("topic_dur_start", topic_dur_start);
	    inputData.put("topic_dur_end"  , topic_dur_end  );
	    this.mybatis.insert("LivingEngTopicDAO.insertTopic", inputData);
	    this.mybatis.commit();
	  }
  
  public void saveTopic( String topic_num, String audio_file_date, String volume_size, String topic_kr, String topic_en, String topic_dur_start, String topic_dur_end ) {
	Map<String, Object> inputData = new HashMap<String, Object>();	
	
    inputData.put("topic_num"      , topic_num      );
    inputData.put("audio_file_date", audio_file_date);
    inputData.put("volume_size"    , volume_size);
    inputData.put("topic_kr"       , topic_kr);
    inputData.put("topic_en"       , topic_en);
    inputData.put("topic_dur_start", topic_dur_start);
    inputData.put("topic_dur_end"  , topic_dur_end  );
    this.mybatis.update("LivingEngTopicDAO.saveTopic", inputData);
    this.mybatis.commit();
  }
  
  public void resetVolume(String volume_size) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    
    inputData.put("volume_size"    , volume_size);
    try {
      this.mybatis.insert("LivingEngTopicDAO.resetVolume", inputData);
    } catch (Exception e) {
      this.logger.debug(e.getMessage().toString());
    } 
    this.mybatis.commit();
  }
  
  public void resetDurStart(String topic_num, String audio_file_date, String topic_dur_start) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    
    inputData.put("topic_num"      , topic_num);
    inputData.put("audio_file_date", audio_file_date);
	inputData.put("topic_dur_start", topic_dur_start);

    try {
      this.mybatis.insert("LivingEngTopicDAO.resetDurStart", inputData);
    } catch (Exception e) {
      this.logger.debug(e.getMessage().toString());
    } 
    this.mybatis.commit();
  }
  
  public void completeTopic(String topic_num, String audio_file_date, String status) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    
    inputData.put("topic_num"      , topic_num);
    inputData.put("audio_file_date", audio_file_date);
    inputData.put("status"         , status);
    try {
      this.mybatis.insert("LivingEngTopicDAO.completeTopic", inputData);
    } catch (Exception e) {
      this.logger.debug(e.getMessage().toString());
    } 
    this.mybatis.commit();
  }
  
  public List<LivingEngTopicVO> selectTopic(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("topic_num", num);
	  
    return this.mybatis.selectList("LivingEngTopicDAO.selectTopic", inputData);
  }
  
  public void updateStartDate(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    this.mybatis.update("LivingEngTopicDAO.updateStartDate", inputData);
    this.mybatis.commit();
  }
  
  public void updateFrequency(String num, String optCase) {
	Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    if (optCase.equals("F"))
      this.mybatis.update("LivingEngTopicDAO.updateFrequency", inputData); 
    this.mybatis.commit();
  }
  
  public void updateWordGuess(String num, String strWordGuess) {
	Map<String, Object> inputData = new HashMap<String, Object>();	 
		
	inputData.put("num",        num);
	inputData.put("word_guess", strWordGuess);
	System.out.println("123");
    this.mybatis.update("LivingEngTopicDAO.updateWordGuess", inputData);
	System.out.println("456s");    
    this.mybatis.commit();
  }

  public String selectPriority(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    return (String)this.mybatis.selectOne("LivingEngTopicDAO.selectPriority", inputData);
  }  
  
  public void updateVerb(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    this.mybatis.update("LivingEngTopicDAO.updateVerb", inputData);
    this.mybatis.commit();
  }
  
  public void initConnection() {
    this.mybatis.update("LivingEngTopicDAO.initConnection");
    this.mybatis.commit();
  }
  
  public void initTodayStatus() {
    this.mybatis.update("LivingEngTopicDAO.initTodayStatus");
    this.mybatis.commit();
  }  
  
  public String selectVerb(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    return (String)this.mybatis.selectOne("LivingEngTopicDAO.selectVerb", inputData);
  }  
    
  public void updateDone(String num, String optCase) {
    Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    if (optCase.equals("D"))
      this.mybatis.update("LivingEngTopicDAO.updateDone", inputData); 
    this.mybatis.commit();
  }
  
  public void updateStatus(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEngTopicDAO.updateStatus", inputData);
    this.mybatis.commit();
  }
  
  public void deleteVocaTmp() {
    this.mybatis.delete("LivingEngTopicDAO.deleteVocaTmp");
    this.mybatis.commit();
  }
  

  
  public Vector<StudyXmlVO> writeStudyXml() {
	  
	List<StudyXmlVO> lStdyXml = new ArrayList<StudyXmlVO>();
	Vector<StudyXmlVO> vlist = new Vector<StudyXmlVO>();
	
    lStdyXml = this.mybatis.selectList("LivingEngTopicDAO.getStudyXml");
    Iterator<StudyXmlVO> l_itrtr = lStdyXml.iterator();
    if (!lStdyXml.isEmpty())
      while (l_itrtr.hasNext()) {
        StudyXmlVO xmlStudyVo = new StudyXmlVO();
        xmlStudyVo = l_itrtr.next();
        xmlStudyVo.setTest(xmlStudyVo.getTest());
        xmlStudyVo.setNo(xmlStudyVo.getNo());
        xmlStudyVo.setSeq(xmlStudyVo.getSeq());
        xmlStudyVo.setOrgText(xmlStudyVo.getOrgText());
        xmlStudyVo.setShortText(xmlStudyVo.getShortText());
        xmlStudyVo.setCreateDate(xmlStudyVo.getCreateDate());
        vlist.add(xmlStudyVo);
      }  
    return vlist;
  }
  
  public List<LivingEngVo> selectLivingEngTmp(String pNum) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", pNum);
    return this.mybatis.selectList("LivingEngTopicDAO.selectLivingEngTmp", inputData);
  }
  
  public void closeMybatis() {
    this.mybatis.close();
  }
  
  public String getTodayStudyCount() {
    return (String)this.mybatis.selectOne("LivingEngTopicDAO.getTodayStudyCount");
  }
}