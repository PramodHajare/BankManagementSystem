package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.model.User;
import com.util.DatabaseUtil;

public class UserDao {

	// create account
	public int[] createAccount(User u) {
		int a[] = new int[2];
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		u.setUseracpin(generatePin());
		String sql = "insert into user1(useracname,useractype,useracbalance,useracpin)values(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUseracname());
			pst.setString(2, u.getUseractype());
			pst.setDouble(3, u.getUseracbalance());
			pst.setInt(4, u.getUseracpin());
			if (pst.executeUpdate() > 0) {
				a[0] = getAcNo();
				a[1] = u.getUseracpin();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(pst, con);
		}
		return a;
	}

	// generate Pin
	public int generatePin() {
		Random r = new Random();
		String s = String.format("%04d", r.nextInt(10000));
		return Integer.parseInt(s);
	}

	public int getAcNo() {
		int check = 0;
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select max(useracno) from user1";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				check = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(rs, pst, con);
		}
		return check;
	}

	public double checkBalance(int acno, int acpin) {
		double d = 0;
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select useracbalance from user1 where useracno=? and useracpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, acno);
			pst.setInt(2, acpin);
			rs = pst.executeQuery();
			while (rs.next()) {
				d = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(rs, pst, con);
		}
		return d;
	}

	public int updateBalance(int acno, int acpin, double balance) {
		int check = 0;
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		String sql = "update user1 set useracbalance=? where useracno=? and useracpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setDouble(1, balance);
			pst.setInt(2, acno);
			pst.setInt(3, acpin);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(pst, con);
		}
		return check;
	}

	public int changePin(int acno, int oldpin, int newpin) {
		int check = 0;
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		String sql = "update user1 set useracpin=? where useracno=? and useracpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setDouble(1, newpin);
			pst.setInt(2, acno);
			pst.setInt(3, oldpin);
			check = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(pst, con);
		}
		return check;
	}

	public String accountDetails(int acno, int acpin) {
		String s = "";
		Connection con = new DatabaseUtil().openConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select useracno,useracname,useractype,useracbalance from user1 where useracno=? and useracpin=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, acno);
			pst.setInt(2, acpin);
			rs = pst.executeQuery();
			while (rs.next()) {
				s = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDouble(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			new DatabaseUtil().closeConnection(rs, pst, con);
		}
		return s;
	}
}
