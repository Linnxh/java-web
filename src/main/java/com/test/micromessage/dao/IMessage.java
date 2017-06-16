package com.test.micromessage.dao;

import java.util.List;
import java.util.Map;

import com.test.micromessage.bean.Message;

/**
 * 与Message配置文件相对应的接口，要在message.xml文件中namespace写入imessage的全路径
 */
public interface IMessage {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter);

	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message);

	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> parameter);

	public void deleteOne(int id);

	public void deleteBatch(List<Integer> list);

}
