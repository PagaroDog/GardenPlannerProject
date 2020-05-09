package Controllers;

import Model.Model;
import Views.View;

/**
 * The parent class of all Controller classes, except the Main Controller. Holds
 * a model, a view, and a main controller.
 * 
 * @author Tommy White
 *
 * @param <T> The specific type of View class associated the controller
 */
public class Controller<T extends View> {
	Model model;
	T view;
	Main main;

	public Controller(Model model, T view, Main main) {
		this.model = model;
		this.view = view;
		this.main = main;

		view.setController(this);
		view.setup();
		model.setController(this);
	}
	
	public T getView() {
		return view;
	}

}
