package gui;

import intepret.IConstructor;

class SetArgumentsWindowManagerForConsructor extends SetArgumentsWindowManager {
	final private IConstructor con;
	final private InstanceTableViewer instanceViewer;

	public SetArgumentsWindowManagerForConsructor(IConstructor con, InstanceTableViewer instanceViewer) {
		super(con.getArgTypesAsString(), instanceViewer);
		this.con = con;
		this.instanceViewer = instanceViewer;

		super.start();
	}

	public void catchArguments(Object[] args) {
		if (args == null) {
			args = new Object[0];
		}
		instanceViewer.addInstance(con, args);
	}
}
