package pic;

import connect.Database;
import dao.*;
public class test {

	public static void main(String[] args) {
		Database.getInstance().getConnection();
		HoaDonDao  a = new HoaDonDao();
		a.getAllHoaDon().forEach(n->{
			System.out.println(n.toString());
		});
		

	}

}
