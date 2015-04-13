package com.wanliang.small.util;

/**
 * 下载回调事件
 * 
 * @author lan
 * 
 */
public class CallbackEvent {

	/**
	 * 文件大小,有可能是-1
	 */
	private long total;
	/**
	 * 当前已获得大小
	 */
	private long current;
	/**
	 * 距离上次回调的时间间隔,单位为毫秒
	 */
	private long realInterval;

	public CallbackEvent(long total, long current, long realInterval) {
		this.total = total;
		this.current = current;
		this.realInterval = realInterval;
	}

	public long getTotal() {
		return total;
	}

	public long getCurrent() {
		return current;
	}

	public long getRealInterval() {
		return realInterval;
	}

}
