package com.wanliang.small.util;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wanliang.small.Setting;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import com.wanliang.small.Setting;

 

public class WXUtil {
	
	/** CacheManager */
	private static final CacheManager cacheManager = CacheManager.create();

	public static String appid = "wxb4d1f5dc9fa28010";
	
	public static String secret = "4915aa4b4ceb6f8e8edb8c4dad8f7fd3";
	
	public static String wxToken = "WX_TOKEN";
	
	public static HttpInvoker invoker = HttpInvoker.getInstance();
	
	static{
		invoker.setHost("https://api.weixin.qq.com/");
		invoker.setWrap(true).setSSL();
	}
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws HeadlessException
	 */
	public synchronized static String getToken(){
		String resultToken = null;
		Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
	    Element token = cache.get(wxToken);
		if(token == null){
			Map<String,Object> resultMap = null;
			String result = invoker.get("cgi-bin/token?grant_type=client_credential&appid="
					+appid+"&secret="+secret);
			resultMap =  JsonUtils.toObject(result, HashMap.class);
			if(resultMap.containsKey("access_token")){
				resultToken = (String)resultMap.get("access_token");
				token = new Element(wxToken, resultToken);
				token.setTimeToLive((Integer)resultMap.get("expires_in"));
				cache.put(token);
			}else{
				throw new RuntimeException("获取token 发生错误："+resultMap);
			}
		}else{
			resultToken = (String)token.getObjectValue();
		}
		return resultToken;
	}
	
	public static void sendMenu (){
		String token = getToken();
		Map<String,String> params = new HashMap<String,String>();
		String result = invoker.post("cgi-bin/token?grant_type=client_credential&appid="
				+appid,params);
		
		
	}
	
	
	public static void main(String[] args) {
		String tokenMap = getToken();
		System.out.println("tokenMap:"+tokenMap);
	}
}
