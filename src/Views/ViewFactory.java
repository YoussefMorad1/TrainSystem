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
        else if(type == State.EDIT_INFO){
            view = new EditProfileView(controller);
        }
        else if(type == State.USER_HOME){
            view = new UserHomeView();
        }
        else if(type == State.ADMIN_HOME)
        {
            view = new adminView();
        }
        else if(type == State.REGISTER){
            view = new RegisterView();
        }
        else if(type == State.DELETE_TKT){
            view = new CancelTicketView(controller);
        }
        else if(type == State.ADD_TRAIN)
        {
            view = new addTrainView();
        }
        else if(type == State.UPDATE_TRAIN)
        {
            view = new trainUpdateView();
        }
        else if(type == State.ADD_TRIP)
        {
            view = new addTripView();
        }
        else if(type == State.BOOK_TKT)
        {
            view = new BookTicketView();
        }
        else if(type == State.REPORT){
            view = new reportView();
        }

        return view;
    }
    public static View createEditTripView(int tripId){
        return new updateTripView(tripId);
    }
}
