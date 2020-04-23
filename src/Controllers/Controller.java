package Controllers;

import Model.Model;
import Views.View;

public class Controller {
	Model model;
	View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.setController(this);
		view.setup();
		model.setController(this);
	}
	
}
