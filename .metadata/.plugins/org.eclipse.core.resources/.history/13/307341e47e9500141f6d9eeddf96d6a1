package gui;

import intepret.InstanceMethod;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

class SetArgumentsWindowManagerForMethod extends SetArgumentsWindowManager {
	final private InstanceMethod method;
	final private InstanceViewer instanceViewer;

	public SetArgumentsWindowManagerForMethod(InstanceMethod m,
			InstanceTableViewer instanceTableViewer, InstanceViewer instanceViewer) {
		super(m.getArgTypes(), instanceTableViewer);

		this.method = m;
		this.instanceViewer = instanceViewer;

		super.start();
	}

	public void catchArguments(Object[] args) {
		if (args == null) {
			args = new Object[0];
		}
		try {
			Object result = method.invoke(args);

			if (method.isVoidMethod()) {
				JOptionPane.showMessageDialog(instanceViewer, "戻り値voidのメソッドを実行しました");
			}else if (result != null) {
				JOptionPane.showMessageDialog(instanceViewer, "実行結果 : " + result.toString());
			} else {
				JOptionPane.showMessageDialog(instanceViewer, "戻り値は null でした");
			}
			instanceViewer.refresh();

		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(instanceViewer, e.toString());
		}
	}

}
