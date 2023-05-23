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
            view = new AdminView();
        }
        else if(type == State.REGISTER){
            view = new RegisterView();
        }
        else if(type == State.DELETE_TKT){
            view = new CancelTicketView(controller);
        }
        else if(type == State.ADD_TRAIN)
        {
            view = new AddTrainView();
        }

        else if(type == State.ADD_TRIP)
        {
            view = new AddTripView();
        }
        else if(type == State.BOOK_TKT)
        {
            view = new BookTicketView();
        }
        else if(type == State.REPORT){
            view = new ReportV2();
        }
        else if(type == State.VIEW_ALL_TRIPS){
            view = new AllTripsView(controller);
        }
        else if(type == State.VIEW_ALL_TRAINS){
            view = new allTrainsView(controller);
        }

        return view;
    }
    public static View createEditView(int id, Controller controller){
        State type = controller.getModel().getCurrentState();
        View view = null;
        if(type == State.UPDATE_TRIP)
            view = new UpdateTripView(id, controller);
        else if(type == State.UPDATE_TRAIN)
            view = new TrainUpdateView(id, controller);
        return view;
    }
}
