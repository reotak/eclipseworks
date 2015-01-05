package gui;

import java.util.List;

abstract class SetArgumentsWindowManager implements CatchArguments {
	final private List<String> argTypes;
	final private InstanceTableViewer instanceTableViewer;
	public SetArgumentsWindowManager(List<String> argTypes, InstanceTableViewer instanceViewer) {
		this.argTypes = argTypes;
		this.instanceTableViewer = instanceViewer;
	}

	abstract public void catchArguments(Object[] args);

	public void start() {

		// no argument
		if (argTypes == null || argTypes.size() == 0) {
			catchArguments(new Object[0]);
			return;
		}

		SetArgumentsWindow window = new SetArgumentsWindow(argTypes, this, instanceTableViewer);
		window.setVisible(true);
	}
}
