package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Admin.AdminRank;
import DB.connectionDB;
import Model.Admin;
import Model.User;
import exceptions.AdminNotFoundException;
import exceptions.BusinessException;

public class AdminDAO {

	public Admin buildSpecificAdmin(Admin a) throws BusinessException,SQLException {
		a.setAdminRank(getAdminRank(a));
		return a;
		
	}
	public AdminRank getAdminRank(Admin a) throws BusinessException,SQLException {
		String query = "SELECT ADMIN_RANK FROM ADMIN WHER ID_USER= ?";
		try(Connection cn=connectionDB.connect();
				PreparedStatement ps=cn.prepareStatement(query)){
			ps.setInt(1,a.getIdUser());
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				throw new AdminNotFoundException();
			}
			String Ar=rs.getString("admin_rank");
			return AdminRank.valueOf(Ar);
		}
	}
}
