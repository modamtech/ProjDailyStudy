package com.modam.biz.dstudy;

import jdk.nashorn.internal.objects.annotations.Getter;

public class LivingEngTopicVO {
	private String topic_num;
	private String audio_file_date;
	private String topic_kr;
	private String topic_en;
	private String volume_size;
	private String topic_dur_start;
	private String topic_dur_end;
	private String native_dur_start;
	private String native_dur_end;
	private String etc_dur1_start;
	private String etc_dur1_end;
	private String etc_dur2_start;
	private String etc_dur2_end;
	private String etc_dur3_start;
	private String etc_dur3_end;
	private String study_status;
	private String topic_frequency;
	private String native_frequency;
	private String curr_point;
	private String create_date;
	private String start_date;
	private String finish_date;
  
  public void setTopicNum(String topic_num) {
    this.topic_num = topic_num;
  }

  @Getter  
  public String getTopicNum() {
    return this.topic_num;
  }
  
  public void setAudioFileDate(String audio_file_date) {
    this.audio_file_date = audio_file_date;
  }
  
  @Getter
  public String getAudioFileDate() {
    return this.audio_file_date;
  }
  
  public void setTopicKr(String topic_kr) {
    this.topic_kr = topic_kr;
  }
  
  @Getter
  public String getTopicKr() {
    return this.topic_kr;
  }
  
  public void setTopicEng(String topic_en) {
    this.topic_en = topic_en;
  }
  
  @Getter
  public String getTopicEng() {
    return this.topic_en;
  }
  
  public void setVolumeSize(String volume_size) {
    this.volume_size = volume_size;
  }
  
  @Getter
  public String getVolumeSize() {
    return this.volume_size;
  }
  
  public void setTopicDurStart(String topic_dur_start) {
    this.topic_dur_start = topic_dur_start;
  }
  
  @Getter
  public String getTopicDurStart() {
    return this.topic_dur_start;
  }
  
  public void setTopicDurEnd(String topic_dur_end) {
    this.topic_dur_end = topic_dur_end;
  }
  
  @Getter
  public String getTopicDurEnd() {
    return this.topic_dur_end;
  }
  
  public void setNativeDurStart(String native_dur_start) {
    this.native_dur_start = native_dur_start;
  }
  
  @Getter
  public String getNativeDurStart() {
    return this.native_dur_start;
  }
  
  public void setNativeDurEnd(String native_dur_end) {
    this.native_dur_end = native_dur_end;
  }
  
  @Getter
  public String getNativeDurEnd() {
    return this.native_dur_end;
  }
  
  public void setEtcDur1Start(String etc_dur1_start) {
    this.etc_dur1_start = etc_dur1_start;
  }
  
  @Getter
  public String getEtcDur1Start() {
    return this.etc_dur1_start;
  }
  
  public void setEtcDur1End(String etc_dur1_end) {
    this.etc_dur1_end = etc_dur1_end;
  }
  
  @Getter
  public String getEtcDur1End() {
    return this.etc_dur1_end;
  }
  
  public void setEtcDur2Start(String etc_dur2_start) {
    this.etc_dur2_start = etc_dur2_start;
  }
  
  @Getter
  public String getEtcDur2Start() {
    return this.etc_dur2_start;
  }
  
  public void setEtcDur2End(String etc_dur2_end) {
    this.etc_dur2_end = etc_dur2_end;
  }
  
  @Getter
  public String getEtcDur2End() {
    return this.etc_dur2_end;
  }
  
  public void setEtcDur3Start(String etc_dur3_start) {
    this.etc_dur3_start = etc_dur3_start;
  }
  
  @Getter
  public String getEtcDur3Start() {
    return this.etc_dur3_start;
  }
    
  public void setEtcDur3End(String etc_dur3_end) {
    this.etc_dur3_end = etc_dur3_end;
  }
  
  @Getter
  public String getEtcDur3End() {
    return this.etc_dur3_end;
  }
  
  public void setStudyStatus(String study_status) {
	    this.study_status = study_status;
  }
	  
  @Getter
  public String getStudyStatus() {
    return this.study_status;
  }
  
  public void setTopicFrequency(String topic_frequency) {
    this.topic_frequency = topic_frequency;
  }
	  
  @Getter
  public String getTopicFrequency() {
    return this.topic_frequency;
  }  
  
  public void setNativeFrequency(String native_frequency) {
    this.native_frequency = native_frequency;
  }
	  
  @Getter
  public String getNativeFrequency() {
    return this.native_frequency;
  }  
  
  public void setCurrPoint(String curr_point) {
    this.curr_point = curr_point;
  }
  
  @Getter
  public String getCurrPoint() {
    return this.curr_point;
  }
  
  public void setCreateDate(String create_date) {
    this.create_date = create_date;
  }
  
  @Getter
  public String getCreateDate() {
    return this.create_date;
  }
  
  public void setStartDate(String start_date) {
    this.start_date = start_date;
  }
  
  @Getter
  public String getStartDate() {
    return this.start_date;
  }

  public void setTodayStudyCnt(String finish_date) {
    this.finish_date = finish_date;
  }
  
  @Getter
  public String getTodayStudyCnt() {
    return this.finish_date;
  }
}