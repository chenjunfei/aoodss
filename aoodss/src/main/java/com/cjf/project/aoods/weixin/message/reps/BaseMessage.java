package com.cjf.project.aoods.weixin.message.reps;

/**
 * 响应消息的基类
 * @author cjf
 *
 */
public class BaseMessage {
	
	//发给谁
	private String ToUserName;
	//谁发的
	private String FromUserName;
	//创建时间
	private long CreateTime;
	//消息类型（text/music/news）
	private String MsgType;
	//位0x0001被标志时，星标刚收到的消息 
	private int FuncFlag;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public int getFuncFlag() {
		return FuncFlag;
	}
	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
	
	

}
