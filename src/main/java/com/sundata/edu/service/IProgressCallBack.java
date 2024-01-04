package com.sundata.edu.service;
/**
 * 进度回调接口
 * @author kim
 * @date 2018年6月22日 上午12:24:51
 * @company yunxinlink.com
 */
public interface IProgressCallBack {

	/**
	 * 执行进度变化的时候回调
	 * @param taskId 任务ID
	 * @param progress 当前执行的第几条数据
	 * @param total 数据总数
	 */
	void onProgressChanged(String taskId, long total, long progress);
	
	/**
	 * 执行进度完成的时候回调
	 * @param taskId 任务ID
	 */
	void onComplete(String taskId, Object data);

	/**
	 * 执行进度完成的时候回调
	 * @param taskId 任务ID
	 */
	void onComplete(String taskId, Object data, boolean userHiddenData);

	/**
	 * 执行进度出错的时候回调
	 * @param taskId 任务ID
	 */
	void onError(String taskId, String errorMsg);
	
	/**
	 * 执行开始的时候回调
	 * @param taskId 任务ID
	 * @param total 数据总数
	 */
	void onStart(String taskId, long total);
}