package Views;

import Controllers.Controller;
import Models.Model;
import Controllers.State;

import java.lang.module.Configuration;

public class ViewFactory {
    public static View createView(Controller controller) {
        State type = controller.getModel().getCurrentState();
        View view = null;
        if (type == State.LOGIN) {
             view = new LoginView();
        }
        if(type == State.USER_HOME){
            view = new UserHomeView();
        }
        else if(type == State.DELETE_TKT){
            view = new CancelTicketView(controller);
        }
//        else if(model.getCurrentState() == Controllers.ControllerTypes.BOOK_TKT){
//            view = new BookTktView(model);
//        }
        return view;
    }
}
