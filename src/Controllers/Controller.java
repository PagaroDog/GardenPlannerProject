package Controllers;

import Model.Model;
import Views.View;

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
	
}
