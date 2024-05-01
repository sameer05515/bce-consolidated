/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;



/**
 * 
 * @author PREMENDRA
 */
public class AnswerDAO extends AbstractDAO {

	public boolean keyExists(AnswerDTO objAnswerDTO) throws Exception {
		boolean keyExists = false;

		try {
			ResultSet rs = null;
			Connection con=getConnection();
			PreparedStatement ps = con
					.prepareStatement("select ans_id,linked_ques_id,answer,linked_cat_id,creation_date,last_updation_date,rating "
							+ "from t_catg_ques_ans"
							+ " where ans_id=? and linked_ques_id=? and linked_cat_id=?");
			int j = 1;
			ps.setInt(j++, objAnswerDTO.getAnsID());
			ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
			ps.setInt(j++, objAnswerDTO.getLinkedCatID());

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

	public void save(AnswerDTO objAnswerDTO) throws Exception {
		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into "
						+ "t_catg_ques_ans(ans_id,linked_ques_id,answer,linked_cat_id,creation_date,last_updation_date,rating)"
						+ " values (?,?,?,?,?,?,?)");

		int j = 1;
		int nextWish_srno = generateNextsrno(objAnswerDTO);
		objAnswerDTO.setAnsID(nextWish_srno);
		ps.setInt(j++, objAnswerDTO.getAnsID());
		ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
		ps.setString(j++, objAnswerDTO.getAnswer());
		ps.setInt(j++, objAnswerDTO.getLinkedCatID());
		
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++,  date);
		ps.setTimestamp(j++,  date);
		
		ps.setInt(j++, objAnswerDTO.getRating());
		
		ps.executeUpdate();
		System.out.println("save");
		closeConnection(con);
	}

	private int generateNextsrno(AnswerDTO objAnswerDTO) throws Exception {
		int nextWish_srno = 0;
		ResultSet rs = null;
		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("select max(ans_id) "
						+ "from t_catg_ques_ans "
						+ "where linked_ques_id=? and linked_cat_id=?");

		int j = 1;
		ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
		ps.setInt(j++, objAnswerDTO.getLinkedCatID());

		rs = ps.executeQuery();
		if (rs.next()) {
			nextWish_srno = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextWish_srno;

	}

	public void update(AnswerDTO objAnswerDTO) throws Exception {
		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("update t_catg_ques_ans set "
						+ "linked_ques_id=? ,answer=? ,linked_cat_id=?,last_updation_date=?,rating=? "
						+ "where ans_id=? and linked_ques_id=? and linked_cat_id=?");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
		ps.setString(j++, objAnswerDTO.getAnswer());
		ps.setInt(j++, objAnswerDTO.getLinkedCatID());
		
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++,  date);
		
		ps.setInt(j++, objAnswerDTO.getRating());
		

		// where
		ps.setInt(j++, objAnswerDTO.getAnsID());
		ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
		ps.setInt(j++, objAnswerDTO.getLinkedCatID());
		ps.executeUpdate();
		System.out.println("update");
		closeConnection(con);
	}

	public AnswerDTO retrieve(AnswerDTO objAnswerDTO) throws Exception {
		try {
			ResultSet rs = null;
			Connection con=getConnection();
			PreparedStatement ps = con
					.prepareStatement("select ans_id,linked_ques_id,answer,linked_cat_id,creation_date,last_updation_date,rating "
							+ "from t_catg_ques_ans "
							+ "where ans_id=? and linked_ques_id=? and linked_cat_id=?");
			int j = 1;
			// where
			ps.setInt(j++, objAnswerDTO.getAnsID());
			ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
			ps.setInt(j++, objAnswerDTO.getLinkedCatID());

			rs = ps.executeQuery();
			if (rs.next()) {
				// status = true;
				objAnswerDTO.setAnsID(rs.getInt("ans_id"));
				objAnswerDTO.setLinkedQuesID(rs.getInt("linked_ques_id"));
				objAnswerDTO.setAnswer(rs.getString("answer"));
				objAnswerDTO.setLinkedCatID(rs.getInt("linked_cat_id"));

				java.sql.Timestamp timestamp=rs.getTimestamp("creation_date");				
				objAnswerDTO.setDateCreated(Date.from(timestamp.toInstant()));
				timestamp=rs.getTimestamp("last_updation_date");
				objAnswerDTO.setDateLastModified(Date.from(timestamp.toInstant()));
				
				objAnswerDTO.setRating(rs.getInt("rating"));
				
				// System.out.println("wish_srno = " + rs.getInt("wish_srno")
				// + "\t  wish_stmt  = " + rs.getString("wish_stmt"));
			} else {
				throw new RestServiceException("404", "no question found for category id == "
						+ objAnswerDTO.getLinkedCatID() + " and question id == " + objAnswerDTO.getLinkedQuesID() +" and answer id == "+objAnswerDTO.getAnsID());
			}
			closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		return objAnswerDTO;
	}

	public Vector<AnswerDTO> fetchAllByQuestion(QuestionDTO questionDTO)
			throws Exception {
		Vector<AnswerDTO> vecAllStudName = new Vector<AnswerDTO>();
		ResultSet rs = null;

		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("select ans_id,linked_ques_id,answer,linked_cat_id,creation_date,last_updation_date,rating,last_read_date"
						+ " from t_catg_ques_ans"
						+ " where linked_ques_id=? and linked_cat_id=? order by last_updation_date desc");

		int j = 1;
		// where
		ps.setInt(j++, questionDTO.getQuestionID());
		ps.setInt(j++, questionDTO.getLinkedCatID());

		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			AnswerDTO objAnswerDTO = new AnswerDTO();
			objAnswerDTO.setAnsID(rs.getInt("ans_id"));
			objAnswerDTO.setLinkedQuesID(rs.getInt("linked_ques_id"));
			objAnswerDTO.setAnswer(rs.getString("answer"));
			objAnswerDTO.setLinkedCatID(rs.getInt("linked_cat_id"));

			java.sql.Timestamp timestamp=rs.getTimestamp("creation_date");				
			objAnswerDTO.setDateCreated(Date.from(timestamp.toInstant()));
			timestamp=rs.getTimestamp("last_updation_date");
			objAnswerDTO.setDateLastModified(Date.from(timestamp.toInstant()));
			
			objAnswerDTO.setRating(rs.getInt("rating"));
			
			timestamp=rs.getTimestamp("last_read_date");
			objAnswerDTO.setDateLastRead(Date.from(timestamp.toInstant()));
			
			// String value = rs.getInt("wish_srno") + ":"
			// + rs.getString("wish_stmt");
			vecAllStudName.add(objAnswerDTO);

		}
		closeConnection(con);
		return vecAllStudName;
	}

	public String deleteAnswer(AnswerDTO objAnswerDTO) throws Exception {
		boolean isSuccess = false;
		String msg = "";
		PreparedStatement ps = null;
		Connection con=getConnection();

		msg = "";
		// con = DBUtil.getInstance().getConnection();
		String sql = "delete from t_catg_ques_ans "
				+ "where ans_id=? and linked_ques_id=? and linked_cat_id=?";
		ps = con.prepareStatement(sql);
		int j = 1;
		ps.setInt(j++, objAnswerDTO.getAnsID());
		ps.setInt(j++, objAnswerDTO.getLinkedQuesID());
		ps.setInt(j++, objAnswerDTO.getLinkedCatID());

		isSuccess = ps.executeUpdate() > 0 ? true : false;

		if (isSuccess) {
			msg = "Answer deleted from database ";
		} else {
			msg = "Unable to delete Answer from database ";
		}
		closeConnection(con);

		return msg;
	}
}
