package Controllers;

import Model.Model;
import Views.View;

public class Controller<T extends View> {
	Model model;
	T view;

	public Controller(Model model, T view) {
		this.model = model;
		this.view = view;
		
		view.setController(this);
		view.setup();
		model.setController(this);
	}
	
}
