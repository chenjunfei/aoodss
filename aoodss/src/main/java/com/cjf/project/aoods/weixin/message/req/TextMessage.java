package com.cjf.project.aoods.weixin.message.req;

/**
 * 文本消息
 * @author cjf
 *
 */
public class TextMessage extends BaseMessage {
	
	//消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	

}
