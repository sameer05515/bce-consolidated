/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistory;
import com.p.interview.mgmt.pojo.vo.CategQuestionHistoryReport;

/**
 * 
 * @author PREMENDRA
 */
public class QuestionDAO extends AbstractDAO {

	public boolean keyExists(QuestionDTO objQuestionDTO) throws Exception {
		boolean keyExists = false;

		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select ques_id,linked_cat_id,ques,creation_date,last_updation_date,rating"
							+ " from t_catg_ques" + " where ques_id=? and linked_cat_id=? ");
			int j = 1;
			// where
			ps.setInt(j++, objQuestionDTO.getQuestionID());
			ps.setInt(j++, objQuestionDTO.getLinkedCatID());
			// ps.setString(2, txtStudName.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				keyExists = true;
			}
			closeConnection(con);
		} catch (Exception ex) {
			keyExists = false;
			ex.printStackTrace();
		}
		System.out.println("keyExists  " + keyExists);

		return keyExists;
	}

	public void saveDetails(QuestionDTO objQuestionDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into t_catg_ques(ques_id,linked_cat_id,ques,creation_date,last_updation_date,rating)"
						+ " values (?,?,?,?,?,?)");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		int nextWish_srno = generateNextsrno(objQuestionDTO);
		objQuestionDTO.setQuestionID(nextWish_srno);
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		ps.setString(j++, objQuestionDTO.getQuestion());

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++, date);
		ps.setTimestamp(j++, date);

		ps.setInt(j++, objQuestionDTO.getRating());

		ps.executeUpdate();
		System.out.println("save");
		closeConnection(con);
	}

	private int generateNextsrno(QuestionDTO objQuestionDTO) throws Exception {
		int nextWish_srno = 0;
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement ps = con
				.prepareStatement("select max(ques_id) " + " from t_catg_ques" + " where linked_cat_id=? ");

		int j = 1;
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		rs = ps.executeQuery();
		if (rs.next()) {
			nextWish_srno = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextWish_srno;

	}

	/**Create batch to update last read time in category and category_read_history table*/
	public void addQuestionHistoryDetails(int linkedCategoryID,int id,String action) throws Exception {

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("update t_catg_ques set "
				+ "last_read_date=?, action=?" + " where ques_id=? and linked_cat_id=? ");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++, date);
		ps.setString(j++, action);

		// where
		ps.setInt(j++, id);
		ps.setInt(j++, linkedCategoryID);
		
//		ps.executeUpdate();
		ps.addBatch();
		
		int next_id=generateNextsrnoIn_t_catg_ques_read_history();
		
		ps.addBatch("insert into t_catg_ques_read_history (id,ques_id,linked_cat_id,last_read_date) values ("
				+ "'"+next_id+"' , "
				+ "'"+id+"' , "
				+ "'"+linkedCategoryID+"' , "				
				+ "'"+date+"' "
				+ ")");
		
		int counts[]=ps.executeBatch();
		System.out.println("Batch update done successfully");
		
//		ps.addBatch("INSERT INTO `t_catg_ques_read_history`(`id`, `ques_id`, `linked_cat_id`, `last_read_date`) VALUES ([value-1],[value-2],[value-3],[value-4])");
		System.out.println("update");
		closeConnection(con);
	}
	
	
	/**Create batch to update hidden in category and category_read_history table*/
	public void markPrivate(int linkedCategoryID,int id) throws Exception {

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("update t_catg_ques set "
				+ "hidden=?" + " where ques_id=? and linked_cat_id=? ");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
//		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
//		ps.setTimestamp(j++, date);
		
		ps.setBoolean(j++, true);

		// where
		ps.setInt(j++, id);
		ps.setInt(j++, linkedCategoryID);
		
//		ps.executeUpdate();
		ps.addBatch();
		
//		int next_id=generateNextsrnoIn_t_catg_ques_read_history();
//		
//		ps.addBatch("insert into t_catg_ques_read_history (id,ques_id,linked_cat_id,last_read_date) values ("
//				+ "'"+next_id+"' , "
//				+ "'"+id+"' , "
//				+ "'"+linkedCategoryID+"' , "				
//				+ "'"+date+"' "
//				+ ")");
		
		int counts[]=ps.executeBatch();
		System.out.println("Batch update done successfully");
		
//		ps.addBatch("INSERT INTO `t_catg_ques_read_history`(`id`, `ques_id`, `linked_cat_id`, `last_read_date`) VALUES ([value-1],[value-2],[value-3],[value-4])");
		System.out.println("update");
		closeConnection(con);
	}
	
	private int generateNextsrnoIn_t_catg_ques_read_history() throws Exception {
		int nextT_catg_ques_read_history_id = 0;
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement ps = con
				.prepareStatement("select max(id) " + " from t_catg_ques_read_history");

		int j = 1;
//		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		rs = ps.executeQuery();
		if (rs.next()) {
			nextT_catg_ques_read_history_id = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextT_catg_ques_read_history_id;

	}

	public void updateDetails(QuestionDTO objQuestionDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("update t_catg_ques set "
				+ "ques=? ,linked_cat_id=?,last_updation_date=?,rating=?" + " where ques_id=? and linked_cat_id=? ");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		ps.setString(j++, objQuestionDTO.getQuestion());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++, date);

		ps.setInt(j++, objQuestionDTO.getRating());

		// where
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		ps.executeUpdate();
		System.out.println("update");
		closeConnection(con);
	}

	public QuestionDTO retrieve(QuestionDTO objQuestionDTO) throws Exception {
		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select ques_id,linked_cat_id,ques,creation_date,last_updation_date,rating,last_read_date,"
							+ " ( select count(*) from t_catg_ques_read_history where ques_id = ? AND linked_cat_id = ?) as total_read,hidden "
							+ " from t_catg_ques where ques_id=? and linked_cat_id=?");
			int j = 1;
			
			ps.setInt(j++, objQuestionDTO.getQuestionID());
			ps.setInt(j++, objQuestionDTO.getLinkedCatID());
			
			ps.setInt(j++, objQuestionDTO.getQuestionID());
			ps.setInt(j++, objQuestionDTO.getLinkedCatID());

			rs = ps.executeQuery();
			if (rs.next()) {
				// status = true;
				objQuestionDTO.setQuestionID(rs.getInt("ques_id"));
				objQuestionDTO.setLinkedCatID(rs.getInt("linked_cat_id"));
				objQuestionDTO.setQuestion(rs.getString("ques"));

				java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
				objQuestionDTO.setDateCreated(Date.from(timestamp.toInstant()));
				timestamp = rs.getTimestamp("last_updation_date");
				objQuestionDTO.setDateLastModified(Date.from(timestamp.toInstant()));

				objQuestionDTO.setRating(rs.getInt("rating"));
				
				timestamp = rs.getTimestamp("last_read_date");
				objQuestionDTO.setDateLastRead(Date.from(timestamp.toInstant()));
				
				objQuestionDTO.setTotalRead(rs.getInt("total_read"));
				
				objQuestionDTO.setPersonal(rs.getBoolean("hidden"));

				// System.out.println("wish_srno = " + rs.getInt("wish_srno")
				// + "\t wish_stmt = " + rs.getString("wish_stmt"));
			} else {
				throw new RestServiceException("404", "no question found for category id == "
						+ objQuestionDTO.getLinkedCatID() + " and question id == " + objQuestionDTO.getQuestionID());
			}
			closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		System.out.println("##########################################################");
		System.out.println("question found for category id == "
				+ objQuestionDTO.getLinkedCatID() + " and question id == " + objQuestionDTO.getQuestionID() +"\n\n "+objQuestionDTO);
		System.out.println("##########################################################");
		
		return objQuestionDTO;
	}

	public Vector<QuestionDTO> fetchAllByCategory(int linkedCategId) throws Exception {
		Vector<QuestionDTO> vecAllStudName = new Vector<QuestionDTO>();
		ResultSet rs = null;

		Connection con = getConnection();
		PreparedStatement ps = con
				.prepareStatement("select ques_id,linked_cat_id,ques,creation_date,last_updation_date,rating,last_read_date,hidden"
//						+ ","
							//+ " ( select count(*) from t_catg_ques_read_history where ques_id = ? AND linked_cat_id = ?) as total_read "
						+ " from t_catg_ques where linked_cat_id=? order by last_updation_date desc");

		int j = 1;
		ps.setInt(j++, linkedCategId);
		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			QuestionDTO objQuestionDTO = new QuestionDTO();
			objQuestionDTO.setQuestionID(rs.getInt("ques_id"));
			objQuestionDTO.setLinkedCatID(rs.getInt("linked_cat_id"));
			objQuestionDTO.setQuestion(rs.getString("ques"));

			java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
			objQuestionDTO.setDateCreated(Date.from(timestamp.toInstant()));
			timestamp = rs.getTimestamp("last_updation_date");
			objQuestionDTO.setDateLastModified(Date.from(timestamp.toInstant()));

			objQuestionDTO.setRating(rs.getInt("rating"));
			
			timestamp = rs.getTimestamp("last_read_date");
			objQuestionDTO.setDateLastRead(Date.from(timestamp.toInstant()));
			objQuestionDTO.setPersonal(rs.getBoolean("hidden"));
			
			//objQuestionDTO.setTotalRead(rs.getInt("total_read"));

			// String value = rs.getInt("wish_srno") + ":"
			// + rs.getString("wish_stmt");
			vecAllStudName.add(objQuestionDTO);

		}
		closeConnection(con);
		return vecAllStudName;
	}

	public String deleteQuestion(QuestionDTO objQuestionDTO) throws Exception {
		boolean isSuccess = false;
		String msg = "";
		PreparedStatement ps = null;

		Connection con = getConnection();

		msg = "";
		// con = DBUtil.getInstance().getConnection();
		String sql = "delete from t_catg_ques" + " where ques_id=? and linked_cat_id=?";
		ps = con.prepareStatement(sql);
		int j = 1;
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());

		isSuccess = ps.executeUpdate() > 0 ? true : false;

		if (isSuccess) {
			msg = "Question deleted from database ";
		} else {
			msg = "Unable to delete Question from database ";
		}

		closeConnection(con);

		return msg;
	}

	public List<CategQuestionHistory> getQuestionHistory(int linkedCategoryID, int id) throws Exception {
		List<CategQuestionHistory> reads=new ArrayList<>();		
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement ps = con
				.prepareStatement("select id, ques_id, linked_cat_id, last_read_date, action"
						+ " from t_catg_ques_read_history"
						+ " where linked_cat_id=? and ques_id=? order by last_read_date desc");

		int j = 1;
		ps.setInt(j++, linkedCategoryID);
		ps.setInt(j++, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			CategQuestionHistory c=new CategQuestionHistory();
			c.setId(rs.getInt("id"));
			c.setAction(rs.getString("action"));
			c.setLinkedCatID(rs.getInt("linked_cat_id"));
			c.setQuestionID(rs.getInt("ques_id"));
			java.sql.Timestamp timestamp = rs.getTimestamp("last_read_date");
			c.setDateLastRead(Date.from(timestamp.toInstant()));
			reads.add(c);
		}
		
		return reads;
	}

	public List<CategQuestionHistoryReport> getAllQuestionsHistoryForAction(String action) {
		List<CategQuestionHistoryReport> list =new ArrayList<>();
		
		return list;
	}

}
