public class LoginModel implements Model{ //database
    public boolean login(String userName, String password, Boolean isAdmin) {
        DataBase db = new DataBase();
        return db.checkLoginCredentials(userName, password, isAdmin);
    }
}
