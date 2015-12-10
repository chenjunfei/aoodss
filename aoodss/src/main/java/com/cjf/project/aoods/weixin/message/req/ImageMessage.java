package com.cjf.project.aoods.weixin.message.req;

/**
 * 图片消息
 * @author cjf
 *
 */
public class ImageMessage extends BaseMessage {
	
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
