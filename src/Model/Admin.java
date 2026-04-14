package Model;

public class Admin {

	private String adminId;

	private enum adminRank {
		ADMIN, SUPERADMIN
	}

	public String getId() {
		return adminId;
	}

	public void setManagerId(String managerId) {
		this.adminId = adminId;
	}

}
