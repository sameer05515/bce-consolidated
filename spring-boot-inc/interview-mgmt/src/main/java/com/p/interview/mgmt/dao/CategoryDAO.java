/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.pojo.vo.CategoryVO;

/**
 * 
 * @author PREMENDRA
 */
public class CategoryDAO extends AbstractDAO {

	public boolean keyExists(CategoryDTO objCategoryDTO) throws Exception {
		boolean keyExists = false;

		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select cat_id,cat_name,creation_date,last_updation_date,rating "
							+ "from t_category where cat_id=?");

			ps.setInt(1, objCategoryDTO.getCatID());

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

		// closeConnection(con);
		return keyExists;
	}

	public void save(CategoryDTO objCategoryDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into t_category (cat_id,cat_name,creation_date,last_updation_date,rating)"
						+ " values (?,?,?,?,?)");

		int j = 1;
		int nextWish_srno = generateNextsrno();
		objCategoryDTO.setCatID(nextWish_srno);
		ps.setInt(j++, objCategoryDTO.getCatID());
		ps.setString(j++, objCategoryDTO.getCatgoryName());

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++, date);
		ps.setTimestamp(j++, date);

		ps.setInt(j++, objCategoryDTO.getRating());

		ps.executeUpdate();
		System.out.println("save");
		closeConnection(con);
	}

	private int generateNextsrno() throws Exception {
		int nextWish_srno = 0;
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("select max(cat_id) from t_category");

		rs = ps.executeQuery();
		if (rs.next()) {
			nextWish_srno = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextWish_srno;

	}

	public void update(CategoryDTO objCategoryDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"update t_category set " + "cat_name=?,last_updation_date=?,rating=?  " + "where cat_id=?");

		int j = 1;
		ps.setString(j++, objCategoryDTO.getCatgoryName());

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		ps.setTimestamp(j++, date);

		ps.setInt(j++, objCategoryDTO.getRating());

		// where
		ps.setInt(j++, objCategoryDTO.getCatID());
		ps.executeUpdate();
		System.out.println("update");
		closeConnection(con);
	}

	public CategoryDTO retrieve(CategoryDTO objCategoryDTO) throws Exception {
		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select cat_id,cat_name,creation_date,last_updation_date,rating,last_read_date" +
							", (SELECT COUNT(*) FROM t_catg_ques tcq WHERE tcq.linked_cat_id=cat_id) AS 'totalQuestionsCount'"
							+ " from t_category" + " where cat_id=?");
			int j = 1;
			ps.setInt(j++, objCategoryDTO.getCatID());

			rs = ps.executeQuery();
			if (rs.next()) {
				// status = true;
				objCategoryDTO.setCatID(rs.getInt("cat_id"));
				objCategoryDTO.setCatgoryName(rs.getString("cat_name"));

				java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
				objCategoryDTO.setDateCreated(Date.from(timestamp.toInstant()));
				timestamp = rs.getTimestamp("last_updation_date");
				objCategoryDTO.setDateLastModified(Date.from(timestamp.toInstant()));

				objCategoryDTO.setRating(rs.getInt("rating"));

				timestamp = rs.getTimestamp("last_read_date");
				objCategoryDTO.setDateLastRead(Date.from(timestamp.toInstant()));
				objCategoryDTO.setTotalQuestionsCount(rs.getInt("totalQuestionsCount"));

				// System.out.println("wish_srno = " + rs.getInt("wish_srno")
				// + "\t wish_stmt = " + rs.getString("wish_stmt"));
			} else {
				throw new RestServiceException("404",
						"can not find category for category id " + objCategoryDTO.getCatID());
			}
			closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return objCategoryDTO;
	}

	public Vector<CategoryDTO> fetchAll() throws Exception {
		Vector<CategoryDTO> vecAllStudName = new Vector<CategoryDTO>();
		ResultSet rs = null;

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("select cat_id,cat_name,creation_date,last_updation_date,rating"
				+ " from t_category" + " order by last_updation_date desc");

		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			CategoryDTO objCategoryDTO = new CategoryDTO();
			objCategoryDTO.setCatID(rs.getInt("cat_id"));
			objCategoryDTO.setCatgoryName(rs.getString("cat_name"));

			java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
			objCategoryDTO.setDateCreated(Date.from(timestamp.toInstant()));
			timestamp = rs.getTimestamp("last_updation_date");
			objCategoryDTO.setDateLastModified(Date.from(timestamp.toInstant()));

			objCategoryDTO.setRating(rs.getInt("rating"));

			vecAllStudName.add(objCategoryDTO);
		}
		closeConnection(con);
		return vecAllStudName;
	}

	public String deleteCategory(int catId) throws Exception {
		boolean isSuccess = false;
		String msg = "";
		PreparedStatement ps = null;

		Connection con = getConnection();

		msg = "";
		// con = DBUtil.getInstance().getConnection();
		String sql = "delete from t_category where cat_id=?";
		ps = con.prepareStatement(sql);
		int j = 1;
		ps.setInt(j++, catId);

		isSuccess = ps.executeUpdate() > 0 ? true : false;

		if (isSuccess) {
			msg = "category deleted from database ";
		} else {
			msg = "Unable to delete category from database ";
		}

		closeConnection(con);
		return msg;
	}

	public List<CategoryVO> getAllCategoriesOptimized() throws Exception {
		List<CategoryVO> vecAllStudName = new ArrayList<CategoryVO>();
		ResultSet rs = null;

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT tc.cat_id,tc.cat_name,tc.creation_date,tc.last_updation_date,tc.rating,\r\n" + 
				"(SELECT COUNT(*) FROM t_catg_ques tcq WHERE tcq.linked_cat_id=tc.cat_id) AS 'totalQuestionsCount'\r\n" + 
				" FROM t_category tc ORDER BY last_updation_date DESC");

		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			CategoryVO objCategoryDTO = new CategoryVO();
			objCategoryDTO.setCatID(rs.getInt("cat_id"));
			objCategoryDTO.setCatgoryName(rs.getString("cat_name"));

			java.sql.Timestamp timestamp = rs.getTimestamp("creation_date");
			objCategoryDTO.setDateCreated(Date.from(timestamp.toInstant()));
			timestamp = rs.getTimestamp("last_updation_date");
			objCategoryDTO.setDateLastModified(Date.from(timestamp.toInstant()));

			objCategoryDTO.setRating(rs.getInt("rating"));
			objCategoryDTO.setTotalQuestionsCount(rs.getInt("totalQuestionsCount"));

			vecAllStudName.add(objCategoryDTO);
		}
		closeConnection(con);
		return vecAllStudName;
	}
}
