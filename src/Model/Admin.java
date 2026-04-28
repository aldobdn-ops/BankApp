package Model;

import java.time.LocalDateTime;

public class Admin extends User {

	public enum AdminRank {
		ADMIN, SUPERADMIN
	}
	private AdminRank adminRank;
	
	public Admin(int idUser, String NIE, String name, String address, String phoneNumber,
            String email, String password, int passwordAttempts,
            LocalDateTime lastConnection, LocalDateTime registerDate,
            Role role, AdminRank adminRank) {
   super(idUser, NIE, name, address, phoneNumber, email, password,
         passwordAttempts, lastConnection, registerDate, role);
   this.adminRank = adminRank;
}
	public AdminRank getAdminRank() {
		return adminRank;
	}
	public void setAdminRank(AdminRank adminRank) {
		this.adminRank = adminRank;
	}
	
	
	

}
