package com.wanliang.small.util;

/**
 * 下载回调接口
 * 
 * @author lan
 * 
 */
public interface DownloadCallback {

	/**
	 * 回调方法
	 * 
	 * @param event
	 */
	public void call(CallbackEvent event);

	/**
	 * 回调间隔,单位为毫秒
	 * 
	 * @return
	 */
	public int interval();

}
