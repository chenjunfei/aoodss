package com.cjf.project.aoods.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cjf.project.aoods.weixin.message.bean.Article;
import com.cjf.project.aoods.weixin.message.reps.NewsMessage;
import com.cjf.project.aoods.weixin.message.reps.TextMessage;
import com.cjf.project.aoods.weixin.util.MessageUtil;

/**
 * 核心服务类
 * @author cjf
 *
 */
public class CoreService {
	
	//处理微信发来的请求
	public static String processRequest(HttpServletRequest request){
		
		String respMessage = null;
		
		try{
			//默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			
			//xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			//谁发的
			String fromUserName = requestMap.get("FromUserName");
			//发给谁(公众账号)
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType = requestMap.get("MsgType");
			
			//默认回复text消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			textMessage.setContent("欢迎访问我的公众号");
			
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
			
			
			//文本消息
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				//respContent = "您发送的是文本消息";
				//接收用户发送到文本内容
				String content = requestMap.get("Content");
				
				//创建图文消息
				NewsMessage newMessage = new NewsMessage();
				newMessage.setToUserName(fromUserName);
				newMessage.setFromUserName(toUserName);
				newMessage.setCreateTime(new Date().getTime());
				newMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newMessage.setFuncFlag(0);
				
				List<Article> articleList = new ArrayList<Article>();
				
				//单图文回复
				if("1".equals(content)){
					Article article = new Article();
					article.setTitle("欢迎关注我的微信公众账号");
					article.setDescription("me，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
					//第一条图文的图片大小建议为640*320，其他图文的图片大小建议为80*80
					article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article.setUrl("http://blog.csdn.net/lyq8479");
					
					articleList.add(article);
					//图文条数
					newMessage.setArticleCount(articleList.size());
					newMessage.setArticles(articleList);
					
					respMessage = MessageUtil.newsMessageToXml(newMessage);
				}
				
				//多图文回复
				else if("2".equals(content)){
					
					Article article1 = new Article();  
                    article1.setTitle("欢迎关注我的微信公众账号");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第2篇\n微信公众帐号的类型");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第3篇\n开发模式启用及接口配置");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988"); 
                    
                    Article article4 = new Article();  
                    article4.setTitle("第4篇\n开发模式启用及接口配置");  
                    article4.setDescription("");  
                    article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3); 
                    articleList.add(article4);
                    newMessage.setArticleCount(articleList.size());  
                    newMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newMessage);  
					
				}
				
			}
			//图片消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				respContent = "您发送的是图片消息";
			}
			//地理位置消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "您发送的是地理位置消息";
			}
			//链接消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "您发送的是链接消息";
			}
			//音频消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "您发送的是音频消息";
			}
			//事件消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				//事件类型
				String eventType = requestMap.get("Event");
				
				// 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
                
			}
			
			//textMessage.setContent(respContent);
			//java对象转换成xml
			//respMessage = MessageUtil.textMessageToXml(textMessage);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		//返回xml
		return respMessage;
	}

}
