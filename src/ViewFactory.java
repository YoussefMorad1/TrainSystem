import javax.swing.*;

public class ViewFactory {
    public static View createView(Model model) {
        ControllerTypes type = model.getCurrentState();
        View view = null;
        if (type == ControllerTypes.LOGIN) {
             view = new LoginView(model);
        }
        if(model.getCurrentState() == ControllerTypes.USER_HOME){
            view = new UserHomeView(model);
        }
//        else if(model.getCurrentState() == ControllerTypes.BOOK_TKT){
//            view = new BookTktView(model);
//        }
        return view;
    }
}
