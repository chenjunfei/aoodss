package com.cjf.project.aoods.weixin.message.reps;

import com.cjf.project.aoods.weixin.message.req.BaseMessage;
import com.cjf.project.aoods.weixin.message.bean.Music;

//音乐消息
public class MusicMessage extends BaseMessage {
	
	//音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
