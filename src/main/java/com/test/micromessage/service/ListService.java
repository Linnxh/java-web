package com.test.micromessage.service;

import java.util.List;

import com.test.micromessage.bean.Message;
import com.test.micromessage.bean.Page;
import com.test.micromessage.dao.MessageDao;

/**
 * 列表相关的业务功能
 * 
 * @author LXH
 *
 */
public class ListService {
	public List<Message> queryMessageList(String comm, String desc) {
		MessageDao dao = new MessageDao();
		return dao.queryMessageList(comm, desc);
	}

	public List<Message> queryMessageListByPage(String comm, String desc, Page page) {
		MessageDao dao = new MessageDao();
		return dao.queryMessageListByPage(comm, desc, page);
	}
}
