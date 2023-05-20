package Views;

import Controllers.Controller;
import Controllers.State;

public class ViewFactory {
    public static View createView(Controller controller) {
        State type = controller.getModel().getCurrentState();
        View view = null;
        if (type == State.LOGIN) {
             view = new LoginView();
        }
        else if(type == State.USER_HOME){
            view = new UserHomeView();
        }
        else if(type == State.REGISTER){
            view = new RegisterView();
        }
        else if(type == State.DELETE_TKT){
            view = new CancelTicketView(controller);
        }
        else if(type == State.BOOK_TKT){
            view = new BookTicketView();
        }
//        else if(model.getCurrentState() == Controllers.ControllerTypes.BOOK_TKT){
//            view = new BookTktView(model);
//        }
        return view;
    }
}
