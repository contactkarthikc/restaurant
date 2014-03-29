package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAdapter {

	public static Connection con;

	// Connect to database
	public static void connect()
	{
		String url="";
		con = null;
		ResultSet rs;
		try{
			con = DriverManager.getConnection(url,"","");
		} catch (SQLException except ){
			// System.out.println(except);
			System.out.println ("Database connection cannot be established. Database is not exists,username or password might be wrong.");
		}
	}

	// True if user who has given email address and password exists in the system
	public static boolean checkLoginInfo(String email, String pass)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"' AND password='"+pass+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// Get username that belongs to given email address
	public static String getUsername(String email)
	{
		Statement stmt;
		String userName="";
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				userName = rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return userName;
		}
		return userName;
	}

	// Get credit card number that belongs to given email address
	public static String getCardNumber(String email)
	{
		Statement stmt;
		String creditcard_number="";
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				creditcard_number = rs.getString("creditcard_number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return creditcard_number;
		}
		return creditcard_number;
	}

	// Return true if user is not already in the system
	public static boolean checkUserEmail(String email)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// True if restaurant that has given id exists in the system
	public static boolean checkRestaurantId(int id)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// True if restaurant that has given id and given name exists in the system
	public static boolean checkRestaurant(int id, String pass)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"' AND restaurant_password='"+pass+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// Get restaurant's information which has given id
	public static ArrayList<String> getRestaurantParameters(int id)
	{
		Statement stmt;
		ArrayList<String> restaurantParameters = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"'");
			while(rs.next())
			{
				restaurantParameters.add(""+rs.getString("restaurant_name"));
				restaurantParameters.add(""+rs.getString("location"));
				restaurantParameters.add(""+rs.getString("open_time"));
				restaurantParameters.add(""+rs.getString("close_time"));
				restaurantParameters.add(""+rs.getString("restaurant_telephone"));
				restaurantParameters.add(""+rs.getString("bank_number"));
				restaurantParameters.add(""+rs.getString("restaurant_password"));
				restaurantParameters.add(""+rs.getString("food_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return restaurantParameters;
		}
		return restaurantParameters;
	}

	// Add customer to database
	public static void createCustomer(String name, String email, String pass, String creditCardNum)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO customer(username, email, password, creditcard_number) VALUES('"+name+"','"+email+"','"+pass+"','"+creditCardNum+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	// Add restaurant to database
	public static void createRestaurant(String password, String name, String location, String openTime, String closeTime, String telephone, String bankNumber, String url)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO restaurant(restaurant_password, restaurant_name, location, open_time, close_time, restaurant_telephone, bank_number, picture_url) VALUES('"+password+"','"+name+"','"+location+"','"+openTime+"','"+closeTime+"','"+telephone+"','"+bankNumber+"','"+url+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public static void modifyCustomer(int id, String newEmail, String newPass, String newName, String creditCard)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE customer SET email='"+newEmail+"', password='"+newPass+"', username='"+newName+"', creditcard_number='"+creditCard+"' WHERE customer_id = '"+id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public static int searchForEmptyTable(int restaurantId)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_status= 0 AND restaurant_id ='"+restaurantId+"'");
			while(rs.next())
			{
				return rs.getInt("table_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return -1;
	}

	public static void addToFavorites(String email, int restaurantId) throws SQLException
	{
		Statement stmt1;
		Statement stmt2;
		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		ResultSet rs;
		int c_id = 0;
		rs = stmt1.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			c_id = rs.getInt("customer_id");
		}
		rs = stmt1.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		if(c_id>0)
		{
			while(rs.next())
			{
				stmt2.executeUpdate("INSERT INTO favorite_restaurant(customer_id ,restaurant_id) VALUES('"+c_id+"','"+restaurantId+"')");
			}
		}
	}

	public static void deleteFavorite(String email, int restaurantId)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			int c_id = 0;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				c_id = rs.getInt("customer_id");
			}
			stmt.executeUpdate("DELETE FROM favorite_restaurant WHERE customer_id ='"+c_id+"'AND restaurant_id ='"+restaurantId+"'");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

public static void modifyOrderNote(String note,int food_id, int orderId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE order_food SET note='"+note+"' WHERE order_id = '"+orderId+"'AND food_id='"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getFavorites(String email)
{
	Statement stmt1;
	Statement stmt2;
	ArrayList<String> favorites = new ArrayList<String>();
	try {
		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		ResultSet rs;
		ResultSet rs2;
		int c_id = 0;
		rs = stmt1.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			c_id = rs.getInt("customer_id");
		}
		rs = stmt1.executeQuery("SELECT * FROM `favorite_restaurant` WHERE customer_id='"+c_id+"'");
		while(rs.next())
		{
			rs2 = stmt2.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+rs.getInt("restaurant_id")+"'");
			while(rs2.next())
			{
				favorites.add(rs2.getString("restaurant_name")+","+rs2.getString("location")+","+rs2.getString("open_time")+
						","+rs2.getString("close_time")+","+rs2.getString("restaurant_telephone")+","+rs2.getString("food_type"));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return favorites;
	}
	return favorites;
}

public static String getOpenTime(int restaurantId) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("open_time");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return null;
}

public static String getCloseTime(int restaurantId) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("close_time");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return null;
}

public static ArrayList<String> getRestaurantReservations(int restaurantId) {
	// TODO returns the reservations of the given customer,with customer id 
	//it returns the attributes of reservation as this:
	//restaurantId,time,tableNum,customerId,resCode

	Statement stmt;
	ArrayList<String> reservation = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `reservation` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			reservation.add(rs.getString("reservation_start_time") +","+rs.getInt("table_id")+","+rs.getString("reservationCode"));

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return reservation;
	}
	return reservation;
}

public static ArrayList<String> getCustomerReservations(int customerId) {
	// TODO returns the reservations of the given customer,with customer id 
	//it returns the attributes of reservation as this:
	//restaurantId,time,tableNum,customerId,resCode

	Statement stmt;
	ArrayList<String> reservations = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `reservation` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			String reservation = "";
			// restaurantId, String time, int tableNum,int customerId
			reservation+= rs.getInt("restaurant_id");
			reservation+=","+rs.getString("reservation_start_time");
			reservation+=","+rs.getInt("table_id");
			reservation+=","+rs.getInt("customer_id");
			reservation+=","+rs.getString("reservationCode");
			reservations.add(reservation);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return reservations;
	}
	return reservations;
}

public static ArrayList<String> getOrders(int customerId) {
	// TODO returns the orders of the given customer,with customer id 
	//it returns the attributes of order as this:

	Statement stmt;
	ArrayList<String> orders = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			orders.add(""+rs.getInt("restaurant_id"));
			orders.add(""+rs.getInt("table_id"));
			orders.add(rs.getString("order_date"));
			orders.add(""+rs.getInt("order_status"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return orders;
	}
	return orders;
}

public static ArrayList<String> getCustomerOrderFood(int customerId)
{
	Statement stmt;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmt3 = con.createStatement();
		ResultSet rs,rs2,rs3;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			int orderId = rs.getInt("order_id");
			
			rs2 = stmt2.executeQuery("SELECT * FROM `order_food` WHERE order_id='"+orderId+"'");
			while(rs2.next())
			{
				int foodId = rs2.getInt("food_id");
				
				rs3 = stmt3.executeQuery("SELECT * FROM `food` WHERE food_id='"+foodId+"'");
				while (rs3.next())
				{
					String food = "";
					//String foodName, double price, String food_details, String foodImage, String foodCategory
					food += rs3.getString("food_name");
					food += ","+rs3.getDouble("price");
					food += ","+rs3.getString("food_details");
					food += ","+" ";
					food += ","+rs3.getString("category");

					foods.add(food);
				}
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static int getRestaurantId(String telephone)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_telephone='"+telephone+"'");
		while(rs.next())
		{
			return rs.getInt("restaurant_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
	return 0;
}


public static void cancelOrder(int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order_food` WHERE order_id ='"+order_id+"'");
		stmt.executeUpdate("DELETE FROM `order` WHERE order_id ='"+order_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void addOrderFood(String food_name, int portion, String note, int restaurantId, int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int food_id=0;
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `food` NATURAL JOIN `menu` WHERE restaurant_id='"+restaurantId+"' AND food_name='" +food_name+"'");
		while(rs.next())
		{
			food_id = rs.getInt("food_id");
		}
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"' AND order_status= 0");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		if(food_id>0 && order_id>0)
		{
			stmt.executeUpdate("INSERT INTO `order_food`(order_id, food_id, portion, note) VALUES('"+order_id+"','"+food_id+"','"+portion+"','"+note+"')");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getRestaurants(String location, String type, String name) {
	Statement stmt;
	String restaurant;
	ArrayList<String> restaurants = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		String query;
		if (location == null && type == null && name == null)
			query = "SELECT * FROM `restaurant`";
		else
		{
			query = "SELECT * FROM `restaurant` WHERE ";
			if(location!=null)
			{
				query += "location like '%"+location+"%'";
			}

			if(type!=null)
			{
				if (location == null)
					query += "food_type like '%"+type+"%'";
				else
					query += " AND food_type like '%"+type+"%'";
			}

			if(name!=null)
			{
				if (type == null && location == null)
					query += " restaurant_name like '%"+name+"%'";
				else
					query += " AND restaurant_name like '%"+name+"%'";
			}
		}
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
			restaurant = rs.getString("restaurant_name")+","+rs.getString("location")+","+rs.getString("open_time")+","+rs.getString("close_time")+","
					+rs.getString("restaurant_telephone")+","+rs.getString("food_type")+","+rs.getString("picture_url");
			restaurants.add(restaurant);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return restaurants;
	}
	return restaurants;
}

public static int getCustomerId(String email)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			return(rs.getInt("customer_id"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
	return 0;
}

public static int createFood(String foodName, double price, String food_details, String foodImage)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("INSERT INTO `food`(food_name, price, food_details, food_image) VALUES('"+foodName+"','"+price+"','"+food_details+"','"+foodImage+"')");
		rs = stmt.executeQuery("SELECT * FROM `food` WHERE food_name='"+foodName+"'AND price='"+price+"'AND food_details='"+food_details+"'AND food_image='"+foodImage+"'");
		while(rs.next())
		{
			return rs.getInt("food_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
	return 0;
}

public static void modifyFoodInfo(int food_id, String food_details)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET food_details='"+food_details+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyFoodName(int food_id, String foodName)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET food_name='"+foodName+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyFoodPrice(int food_id, double price)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET price='"+price+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void deleteFood(int foodId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("DELETE FROM `food` WHERE food_id ='"+foodId+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getOrder(int restaurantID, int table_num) {
	Statement stmt;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` NATURAL JOIN `order_food` NATURAL JOIN `food` WHERE restaurant_id='"+restaurantID+"' AND table_id='"+table_num+"'");
		while(rs.next())
		{
			foods.add(""+rs.getString("food_name"));
			foods.add(""+rs.getInt("portion"));
			foods.add(""+rs.getString("note"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static void changeTableOccupy(int restaurantId, String tableCode, int i) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `table` SET table_status='"+i+"' WHERE restaurant_id = '"+restaurantId+"' AND table_code = '"+tableCode+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static String getTableCode(int restaurantId, int table_num)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_num+"'");
		while(rs.next())
		{
			return(rs.getString("table_code"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	return "";
}

public static boolean searchTable(int restaurantId, String tableCode) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_code='"+ tableCode +"' AND restaurant_id ='"+restaurantId+"'");
		while(rs.next())
		{
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return false;
}

public static int addOrder(int restaurantId, int customerId, String tableCode, String date)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		int table_id=0;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_code='"+ tableCode +"' AND restaurant_id ='"+restaurantId+"'");
		while(rs.next())
		{
			table_id= rs.getInt("table_id");
		}
		stmt.executeUpdate("INSERT INTO `order`(customer_id, restaurant_id, table_id, order_date) VALUES('"+customerId+"','"+restaurantId+"','"+table_id+"','"+date+"')");
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"'AND customer_id='"+customerId+"'AND order_date='"+date+"'");
		while(rs.next())
		{
			return rs.getInt("order_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
	return 0;
}

public static void removeOrderFood(int restaurantId, int customerId, String name)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int food_id=0;
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `food` NATURAL JOIN `menu` WHERE restaurant_id='"+restaurantId+"' AND food_name='" +name+"'");
		while(rs.next())
		{
			food_id = rs.getInt("food_id");
		}
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"' AND customer_id='" +customerId+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order_food` WHERE order_id='" + order_id +"' AND food_id ='"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void cancelOrder(int restaurantId, int table_num)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_num+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order` WHERE order_id ='"+order_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void reservationCancelling(String resCode)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM `reservation` WHERE reservationCode ='"+resCode+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static boolean createReservation(int restaurantId, String time, int tableNum, String reservationCode, int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO `reservation`(customer_id, restaurant_id, table_id, reservation_start_time, reservationCode) VALUES('"+customerId+"','"+restaurantId+"','"+tableNum+"','"+time+"','"+reservationCode+"')");
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

public static String getRestaurantName(int restaurantId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("restaurant_name");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	return "";
}

public static void modifyRestaurantLocation(int restaurant_id, String location)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET location='"+location+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantName(int restaurant_id, String name)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET restaurant_name='"+name+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantOpenTime(int restaurant_id, String openTime)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET open_time='"+openTime+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantCloseTime(int restaurant_id, String closeTime)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET close_time='"+closeTime+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantTelephone(int restaurant_id, String telephone)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET restaurant_telephone='"+telephone+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantBankAccount(int restaurant_id, String bankNumber)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET bank_number='"+bankNumber+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantType(int restaurant_id, String type)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET food_type='"+type+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantPicture(int restaurant_id, String url)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET picture_url='"+url+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static int getOccupation(int table_id, int restaurantId)
{
	Statement stmt;
	try {
		ResultSet rs;
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_id+"'");
		while(rs.next())
		{
			return rs.getInt("table_status");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -2;
	}
	return -2;
}

public static void setTableCode(int restaurantId, int table_id, String code)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `table` SET table_code='"+code+"' WHERE restaurant_id = '"+restaurantId+"'AND table_id='"+table_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void createTable(int table_id, String code, int restaurant_id)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("INSERT INTO `table`(restaurant_id, table_id, table_code) VALUES('"+restaurant_id+"','"+table_id+"','"+code+"')");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void deleteTable(int tableId, int restaurantId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM `table` WHERE table_id ='"+tableId+"'AND restaurant_id='"+restaurantId+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getTables(int restaurantId)
{
	Statement stmt;
	ArrayList<String>tables = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			String table = "";
			table += rs.getInt("table_id");
			table += ",";
			table += rs.getInt("table_code");
			tables.add(table);
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return tables;
	}
	return tables;
}


public static ArrayList<String> getMenu(int restaurantId)
{
	Statement stmt,stmt2;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		stmt2 = con.createStatement();
		int f_id;
		ResultSet rs;
		ResultSet rs2;
		rs = stmt.executeQuery("SELECT * FROM `menu` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			f_id = rs.getInt("food_id");
			rs2 = stmt2.executeQuery("SELECT * FROM `food` WHERE food_id='"+f_id+"'");
			while(rs2.next())
			{
				foods.add(rs2.getString("food_name")+","+rs2.getDouble("price")+","+rs2.getString("food_details")+","+rs2.getString("food_image")+","+rs2.getString("category"));
			}
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static void eraseReservation(String reservationCode) {
	// TODO Auto-generated method stub

}

}
=======
package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAdapter {

	public static Connection con;

	// Connect to database
	public static void connect()
	{
		String url="";
		con = null;
		ResultSet rs;
		try{
			con = DriverManager.getConnection(url,"","");
		} catch (SQLException except ){
			// System.out.println(except);
			System.out.println ("Database connection cannot be established. Database is not exists,username or password might be wrong.");
		}
	}

	// True if user who has given email address and password exists in the system
	public static boolean checkLoginInfo(String email, String pass)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"' AND password='"+pass+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// Get username that belongs to given email address
	public static String getUsername(String email)
	{
		Statement stmt;
		String userName="";
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				userName = rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return userName;
		}
		return userName;
	}

	// Get credit card number that belongs to given email address
	public static String getCardNumber(String email)
	{
		Statement stmt;
		String creditcard_number="";
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				creditcard_number = rs.getString("creditcard_number");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return creditcard_number;
		}
		return creditcard_number;
	}

	// Return true if user is not already in the system
	public static boolean checkUserEmail(String email)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// True if restaurant that has given id exists in the system
	public static boolean checkRestaurantId(int id)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// True if restaurant that has given id and given name exists in the system
	public static boolean checkRestaurant(int id, String pass)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"' AND restaurant_password='"+pass+"'");
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// Get restaurant's information which has given id
	public static ArrayList<String> getRestaurantParameters(int id)
	{
		Statement stmt;
		ArrayList<String> restaurantParameters = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+id+"'");
			while(rs.next())
			{
				restaurantParameters.add(""+rs.getString("restaurant_name"));
				restaurantParameters.add(""+rs.getString("location"));
				restaurantParameters.add(""+rs.getString("open_time"));
				restaurantParameters.add(""+rs.getString("close_time"));
				restaurantParameters.add(""+rs.getString("restaurant_telephone"));
				restaurantParameters.add(""+rs.getString("bank_number"));
				restaurantParameters.add(""+rs.getString("restaurant_password"));
				restaurantParameters.add(""+rs.getString("food_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return restaurantParameters;
		}
		return restaurantParameters;
	}

	// Add customer to database
	public static void createCustomer(String name, String email, String pass, String creditCardNum)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO customer(username, email, password, creditcard_number) VALUES('"+name+"','"+email+"','"+pass+"','"+creditCardNum+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	// Add restaurant to database
	public static void createRestaurant(String password, String name, String location, String openTime, String closeTime, String telephone, String bankNumber, String url)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO restaurant(restaurant_password, restaurant_name, location, open_time, close_time, restaurant_telephone, bank_number, picture_url) VALUES('"+password+"','"+name+"','"+location+"','"+openTime+"','"+closeTime+"','"+telephone+"','"+bankNumber+"','"+url+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public static void modifyCustomer(int id, String newEmail, String newPass, String newName, String creditCard)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("UPDATE customer SET email='"+newEmail+"', password='"+newPass+"', username='"+newName+"', creditcard_number='"+creditCard+"' WHERE customer_id = '"+id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public static int searchForEmptyTable(int restaurantId)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_status= 0 AND restaurant_id ='"+restaurantId+"'");
			while(rs.next())
			{
				return rs.getInt("table_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return -1;
	}

	public static void addToFavorites(String email, int restaurantId) throws SQLException
	{
		Statement stmt1;
		Statement stmt2;
		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		ResultSet rs;
		int c_id = 0;
		rs = stmt1.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			c_id = rs.getInt("customer_id");
		}
		rs = stmt1.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		if(c_id>0)
		{
			while(rs.next())
			{
				stmt2.executeUpdate("INSERT INTO favorite_restaurant(customer_id ,restaurant_id) VALUES('"+c_id+"','"+restaurantId+"')");
			}
		}
	}

	public static void deleteFavorite(String email, int restaurantId)
	{
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			int c_id = 0;
			rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
			while(rs.next())
			{
				c_id = rs.getInt("customer_id");
			}
			stmt.executeUpdate("DELETE FROM favorite_restaurant WHERE customer_id ='"+c_id+"'AND restaurant_id ='"+restaurantId+"'");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}

public static void modifyOrderNote(String note,int food_id, int orderId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE order_food SET note='"+note+"' WHERE order_id = '"+orderId+"'AND food_id='"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getFavorites(String email)
{
	Statement stmt1;
	Statement stmt2;
	ArrayList<String> favorites = new ArrayList<String>();
	try {
		stmt1 = con.createStatement();
		stmt2 = con.createStatement();
		ResultSet rs;
		ResultSet rs2;
		int c_id = 0;
		rs = stmt1.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			c_id = rs.getInt("customer_id");
		}
		rs = stmt1.executeQuery("SELECT * FROM `favorite_restaurant` WHERE customer_id='"+c_id+"'");
		while(rs.next())
		{
			rs2 = stmt2.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+rs.getInt("restaurant_id")+"'");
			while(rs2.next())
			{
				favorites.add(rs2.getString("restaurant_name")+","+rs2.getString("location")+","+rs2.getString("open_time")+
						","+rs2.getString("close_time")+","+rs2.getString("restaurant_telephone")+","+rs2.getString("food_type"));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return favorites;
	}
	return favorites;
}

public static String getOpenTime(int restaurantId) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("open_time");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return null;
}

public static String getCloseTime(int restaurantId) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("close_time");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return null;
}

public static ArrayList<String> getRestaurantReservations(int restaurantId) {
	// TODO returns the reservations of the given customer,with customer id 
	//it returns the attributes of reservation as this:
	//restaurantId,time,tableNum,customerId,resCode

	Statement stmt;
	ArrayList<String> reservation = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `reservation` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			reservation.add(rs.getString("reservation_start_time") +","+rs.getInt("table_id")+","+rs.getString("reservationCode"));

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return reservation;
	}
	return reservation;
}

public static ArrayList<String> getCustomerReservations(int customerId) {
	// TODO returns the reservations of the given customer,with customer id 
	//it returns the attributes of reservation as this:
	//restaurantId,time,tableNum,customerId,resCode

	Statement stmt;
	ArrayList<String> reservations = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `reservation` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			String reservation = "";
			// restaurantId, String time, int tableNum,int customerId
			reservation+= rs.getInt("restaurant_id");
			reservation+=","+rs.getString("reservation_start_time");
			reservation+=","+rs.getInt("table_id");
			reservation+=","+rs.getInt("customer_id");
			reservation+=","+rs.getString("reservationCode");
			reservations.add(reservation);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return reservations;
	}
	return reservations;
}

public static ArrayList<String> getOrders(int customerId) {
	// TODO returns the orders of the given customer,with customer id 
	//it returns the attributes of order as this:

	Statement stmt;
	ArrayList<String> orders = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			orders.add(""+rs.getInt("restaurant_id"));
			orders.add(""+rs.getInt("table_id"));
			orders.add(rs.getString("order_date"));
			orders.add(""+rs.getInt("order_status"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return orders;
	}
	return orders;
}

public static ArrayList<String> getCustomerOrderFood(int customerId)
{
	Statement stmt;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmt3 = con.createStatement();
		ResultSet rs,rs2,rs3;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			int orderId = rs.getInt("order_id");
			
			rs2 = stmt2.executeQuery("SELECT * FROM `order_food` WHERE order_id='"+orderId+"'");
			while(rs2.next())
			{
				int foodId = rs2.getInt("food_id");
				
				rs3 = stmt3.executeQuery("SELECT * FROM `food` WHERE food_id='"+foodId+"'");
				while (rs3.next())
				{
					String food = "";
					//String foodName, double price, String food_details, String foodImage, String foodCategory
					food += rs3.getString("food_name");
					food += ","+rs3.getDouble("price");
					food += ","+rs3.getString("food_details");
					food += ","+" ";
					food += ","+rs3.getString("category");

					foods.add(food);
				}
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static int getRestaurantId(String telephone)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_telephone='"+telephone+"'");
		while(rs.next())
		{
			return rs.getInt("restaurant_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
	return 0;
}


public static void cancelOrder(int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order_food` WHERE order_id ='"+order_id+"'");
		stmt.executeUpdate("DELETE FROM `order` WHERE order_id ='"+order_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void addOrderFood(String food_name, int portion, String note, int restaurantId, int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int food_id=0;
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `food` NATURAL JOIN `menu` WHERE restaurant_id='"+restaurantId+"' AND food_name='" +food_name+"'");
		while(rs.next())
		{
			food_id = rs.getInt("food_id");
		}
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id='"+customerId+"' AND order_status= 0");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		if(food_id>0 && order_id>0)
		{
			stmt.executeUpdate("INSERT INTO `order_food`(order_id, food_id, portion, note) VALUES('"+order_id+"','"+food_id+"','"+portion+"','"+note+"')");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getRestaurants(String location, String type, String name) {
	Statement stmt;
	String restaurant;
	ArrayList<String> restaurants = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		String query;
		if (location == null && type == null && name == null)
			query = "SELECT * FROM `restaurant`";
		else
		{
			query = "SELECT * FROM `restaurant` WHERE ";
			if(location!=null)
			{
				query += "location like '%"+location+"%'";
			}

			if(type!=null)
			{
				if (location == null)
					query += "food_type like '%"+type+"%'";
				else
					query += " AND food_type like '%"+type+"%'";
			}

			if(name!=null)
			{
				if (type == null && location == null)
					query += " restaurant_name like '%"+name+"%'";
				else
					query += " AND restaurant_name like '%"+name+"%'";
			}
		}
		rs = stmt.executeQuery(query);
		while(rs.next())
		{
			restaurant = rs.getString("restaurant_name")+","+rs.getString("location")+","+rs.getString("open_time")+","+rs.getString("close_time")+","
					+rs.getString("restaurant_telephone")+","+rs.getString("food_type")+","+rs.getString("picture_url");
			restaurants.add(restaurant);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return restaurants;
	}
	return restaurants;
}

public static int getCustomerId(String email)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `customer` WHERE email='"+email+"'");
		while(rs.next())
		{
			return(rs.getInt("customer_id"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
	return 0;
}

public static int createFood(String foodName, double price, String food_details, String foodImage)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("INSERT INTO `food`(food_name, price, food_details, food_image) VALUES('"+foodName+"','"+price+"','"+food_details+"','"+foodImage+"')");
		rs = stmt.executeQuery("SELECT * FROM `food` WHERE food_name='"+foodName+"'AND price='"+price+"'AND food_details='"+food_details+"'AND food_image='"+foodImage+"'");
		while(rs.next())
		{
			return rs.getInt("food_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
	return 0;
}

public static void modifyFoodInfo(int food_id, String food_details)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET food_details='"+food_details+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyFoodName(int food_id, String foodName)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET food_name='"+foodName+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyFoodPrice(int food_id, double price)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `food` SET price='"+price+"' WHERE food_id = '"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void deleteFood(int foodId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("DELETE FROM `food` WHERE food_id ='"+foodId+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getOrder(int restaurantID, int table_num) {
	Statement stmt;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` NATURAL JOIN `order_food` NATURAL JOIN `food` WHERE restaurant_id='"+restaurantID+"' AND table_id='"+table_num+"'");
		while(rs.next())
		{
			foods.add(""+rs.getString("food_name"));
			foods.add(""+rs.getInt("portion"));
			foods.add(""+rs.getString("note"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static void changeTableOccupy(int restaurantId, String tableCode, int i) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `table` SET table_status='"+i+"' WHERE restaurant_id = '"+restaurantId+"' AND table_code = '"+tableCode+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static String getTableCode(int restaurantId, int table_num)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_num+"'");
		while(rs.next())
		{
			return(rs.getString("table_code"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	return "";
}

public static boolean searchTable(int restaurantId, String tableCode) {
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_code='"+ tableCode +"' AND restaurant_id ='"+restaurantId+"'");
		while(rs.next())
		{
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return false;
}

public static int addOrder(int restaurantId, int customerId, String tableCode, String date)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		int table_id=0;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE table_code='"+ tableCode +"' AND restaurant_id ='"+restaurantId+"'");
		while(rs.next())
		{
			table_id= rs.getInt("table_id");
		}
		stmt.executeUpdate("INSERT INTO `order`(customer_id, restaurant_id, table_id, order_date) VALUES('"+customerId+"','"+restaurantId+"','"+table_id+"','"+date+"')");
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"'AND customer_id='"+customerId+"'AND order_date='"+date+"'");
		while(rs.next())
		{
			return rs.getInt("order_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
	return 0;
}

public static void removeOrderFood(int restaurantId, int customerId, String name)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int food_id=0;
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `food` NATURAL JOIN `menu` WHERE restaurant_id='"+restaurantId+"' AND food_name='" +name+"'");
		while(rs.next())
		{
			food_id = rs.getInt("food_id");
		}
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"' AND customer_id='" +customerId+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order_food` WHERE order_id='" + order_id +"' AND food_id ='"+food_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void cancelOrder(int restaurantId, int table_num)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		int order_id=0;
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `order` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_num+"'");
		while(rs.next())
		{
			order_id = rs.getInt("order_id");
		}
		stmt.executeUpdate("DELETE FROM `order` WHERE order_id ='"+order_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void reservationCancelling(String resCode)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM `reservation` WHERE reservationCode ='"+resCode+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static boolean createReservation(int restaurantId, String time, int tableNum, String reservationCode, int customerId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO `reservation`(customer_id, restaurant_id, table_id, reservation_start_time, reservationCode) VALUES('"+customerId+"','"+restaurantId+"','"+tableNum+"','"+time+"','"+reservationCode+"')");
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

public static String getRestaurantName(int restaurantId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `restaurant` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			return rs.getString("restaurant_name");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	return "";
}

public static void modifyRestaurantLocation(int restaurant_id, String location)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET location='"+location+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantName(int restaurant_id, String name)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET restaurant_name='"+name+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantOpenTime(int restaurant_id, String openTime)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET open_time='"+openTime+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantCloseTime(int restaurant_id, String closeTime)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET close_time='"+closeTime+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantTelephone(int restaurant_id, String telephone)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET restaurant_telephone='"+telephone+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantBankAccount(int restaurant_id, String bankNumber)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET bank_number='"+bankNumber+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantType(int restaurant_id, String type)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET food_type='"+type+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void modifyRestaurantPicture(int restaurant_id, String url)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `restaurant` SET picture_url='"+url+"' WHERE restaurant_id = '"+restaurant_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static int getOccupation(int table_id, int restaurantId)
{
	Statement stmt;
	try {
		ResultSet rs;
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'AND table_id='"+table_id+"'");
		while(rs.next())
		{
			return rs.getInt("table_status");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -2;
	}
	return -2;
}

public static void setTableCode(int restaurantId, int table_id, String code)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE `table` SET table_code='"+code+"' WHERE restaurant_id = '"+restaurantId+"'AND table_id='"+table_id+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void createTable(int table_id, String code, int restaurant_id)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		ResultSet rs;
		stmt.executeUpdate("INSERT INTO `table`(restaurant_id, table_id, table_code) VALUES('"+restaurant_id+"','"+table_id+"','"+code+"')");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static void deleteTable(int tableId, int restaurantId)
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM `table` WHERE table_id ='"+tableId+"'AND restaurant_id='"+restaurantId+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return;
	}
}

public static ArrayList<String> getTables(int restaurantId)
{
	Statement stmt;
	ArrayList<String>tables = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM `table` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			String table = "";
			table += rs.getInt("table_id");
			table += ",";
			table += rs.getInt("table_code");
			tables.add(table);
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return tables;
	}
	return tables;
}


public static ArrayList<String> getMenu(int restaurantId)
{
	Statement stmt,stmt2;
	ArrayList<String> foods = new ArrayList<String>();
	try {
		stmt = con.createStatement();
		stmt2 = con.createStatement();
		int f_id;
		ResultSet rs;
		ResultSet rs2;
		rs = stmt.executeQuery("SELECT * FROM `menu` WHERE restaurant_id='"+restaurantId+"'");
		while(rs.next())
		{
			f_id = rs.getInt("food_id");
			rs2 = stmt2.executeQuery("SELECT * FROM `food` WHERE food_id='"+f_id+"'");
			while(rs2.next())
			{
				foods.add(rs2.getString("food_name")+","+rs2.getDouble("price")+","+rs2.getString("food_details")+","+rs2.getString("food_image")+","+rs2.getString("category"));
			}
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return foods;
	}
	return foods;
}

public static void eraseReservation(String reservationCode) {
	// TODO Auto-generated method stub

}

}
