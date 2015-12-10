package com.cjf.project.aoods.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cjf.project.aoods.weixin.message.bean.Article;
import com.cjf.project.aoods.weixin.message.reps.MusicMessage;
import com.cjf.project.aoods.weixin.message.reps.NewsMessage;
import com.cjf.project.aoods.weixin.message.reps.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息处理工具类
 * @author cjf
 *
 */
public class MessageUtil {
	
	/**
	 * 返回消息类型:文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
	/**
	 * 返回消息类型:音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	/**
	 * 返回消息类型:图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news"; 
	
	/**
	 * 请求消息类型:文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	
	/**
	 * 请求消息类型:图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	
	/**
	 * 请求消息类型:链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	/**
	 * 请求消息类型:地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	/**
	 * 请求消息类型:音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	
	/**
	 * 请求消息类型:推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	/**
	 * 事件类型:订阅
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * 事件类型:取消订阅
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	/**
	 * 事件类型:自定义菜单点击事件
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 解析微信发来的请求
	 * @return
	 */
	public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
		
		//将解析结果存储在map中
		Map<String,String> map = new HashMap<String,String>();
		
		//从请求中取出输入流
		InputStream in = request.getInputStream();
		
		//读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);
		
		//取出xml根元素
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		
		//遍历根元素
		for(Element e : elementList){
			map.put(e.getName(), e.getText());
		}
		
		//释放资源
		in.close();
		in = null;
		
		return map;
	}
	
	/**
	 * 文本消息对象转换成xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		
		//使用xml替换类名
		xstream.alias("xml", textMessage.getClass());		
		return xstream.toXML(textMessage);
	}
	
	 /**
	  * 音乐消息转换成xml 
	  * @param musicMessage
	  * @return
	  */
    public static String musicMessageToXml(MusicMessage musicMessage) {  
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }
    
    /**
     * 图文消息转换成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {  
        xstream.alias("xml", newsMessage.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(newsMessage);  
    }  
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				 // 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				 
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }
                
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }
                }
			};
			
		}
	});

}
