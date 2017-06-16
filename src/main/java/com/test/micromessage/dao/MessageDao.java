package com.test.micromessage.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.Connection;
import com.test.micromessage.bean.Message;
import com.test.micromessage.bean.Page;
import com.test.micromessage.db.DBAccess;

/**
 * 和messag相关的操作
 * 
 * @author LXH
 *
 */
public class MessageDao {


	/**
	 * 查询操作
	 * 
	 * @param command，可空，
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messList = new ArrayList<Message>();
		try {
			sqlSession = dbAccess.getSqlSession();
			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description);
			// messList = sqlSession.selectList("Message.queryMessageList",
			// message);

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put(command, command);
			parameter.put(description, description);
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messList = imessage.queryMessageList(parameter);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messList;
	}

	public List<Message> queryMessageListByPage(String command, String description, Page page) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messList = new ArrayList<Message>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		try {
			sqlSession = dbAccess.getSqlSession();
			IMessage imessage = sqlSession.getMapper(IMessage.class);

			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description);

			int count = imessage.count(message);
			if (count > 0) {
				page.setTotalNumber(count);
				page.count();
			}

			parameter.put("message", message);
			parameter.put("page", page);

			messList = imessage.queryMessageListByPage(parameter);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messList;
	}

	/**
	 * 删除一条
	 * 
	 * @param id
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// sqlSession.selectList("Message.deleteOne", id);
			// session.commit();
			//
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteOne(id);
			sqlSession.commit();// 最后要提交
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * 多条删除
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			// sqlSession.delete("Message.deleteBatch", ids);
			// sqlSession.commit();
			//

			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteBatch(ids);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/**
	 * jdbc访问sql
	 */
	// public List<Message> queryMessageList(String command, String description)
	// {
	// List<Message> messages = new ArrayList<Message>();
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	//
	// Connection connection = DriverManager.getConnection(
	// "jdbc:mysql://127.0.0.1:3306/web-demo?useUnicode=true&characterEncoding=utf8",
	// "root", "123123");
	// StringBuilder sql = new StringBuilder("select
	// id,command,description,content from message where 1=1 ");
	//
	// List<String> parmList = new ArrayList<String>();
	// if (command != null && !"".equals(command.trim())) {
	// sql.append(" and command=?");
	// parmList.add(command);
	// }
	// if (description != null && !"".equals(description.trim())) {
	// sql.append(" and description = like '%' ? '%'");
	// parmList.add(description);
	// }
	// PreparedStatement state = connection.prepareStatement(sql.toString());
	// for (int i = 0; i < parmList.size(); i++) {
	// state.setString(i + 1, parmList.get(i));
	// }
	//
	// ResultSet rSet = state.executeQuery();
	//
	// while (rSet.next()) {
	// Message msg = new Message();
	// messages.add(msg);
	// msg.setId(rSet.getString("ID"));
	// msg.setCommand(rSet.getString("Command"));
	// msg.setDescription(rSet.getString("Description"));
	// msg.setContent(rSet.getString("CONTENT"));
	// }
	//
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return messages;
	// }
}
