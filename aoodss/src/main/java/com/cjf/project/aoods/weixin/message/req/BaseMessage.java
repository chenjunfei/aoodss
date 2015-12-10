package com.cjf.project.aoods.weixin.message.req;

/**
 * 消息基类
 * 
 * @author cjf
 *
 */
public class BaseMessage {

	// 发给谁
	private String ToUserName;
	// 谁发的
	private String FromUserName;
	// 时间
	private long CreateTime;
	// 消息类型
	private String MsgType;
	// 消息id 64位
	private long MsgId;

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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
