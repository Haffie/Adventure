package uk.co.amberservices.AdventureModel;

public class Action {
	
	private ActionEnum actionType;
	private Item item;
	
	public ActionEnum getActionType() {
		return actionType;
	}
	
	public Item getItem() {
		return item;
	}
	public Action(ActionEnum actionType, Item item) {
		super();
		this.actionType = actionType;
		this.item = item;
	}
}
