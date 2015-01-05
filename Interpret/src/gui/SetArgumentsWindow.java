package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class SetArgumentsWindow extends JFrame {
	private final List<String> argTypes;
	private final Object[] args;
	private final CatchArguments catchArguments;
	private final JTable settingArgsTable;
	private final InstanceTableViewer instanceTableViewer;
	private final JTable instanceTable;
	private final JTextField primaryTextField;
	private final JComboBox<String> primaryComboBox;


	private int setArgCount = 0;


	public SetArgumentsWindow(List<String> argTypes, CatchArguments catchArguments, InstanceTableViewer instanceViewer) {
		this.setTitle("引数の設定");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.argTypes = argTypes;
		this.catchArguments = catchArguments;
		this.instanceTableViewer = instanceViewer;
		this.setBounds(10, 10, 500, 800);

		// argsを生成
		this.args = new Object[argTypes.size()];

		// 設定済みの引数を表示するテーブル
		JPanel settingArgsPanel = new JPanel();
		settingArgsPanel.add(new JLabel("設定済みの引数"), BorderLayout.NORTH);
		settingArgsTable = new JTable(new DefaultTableModel(new String[] {"型", "値"}, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		for (String type : argTypes) {
			((DefaultTableModel)settingArgsTable.getModel()).addRow(
				new String[] { type, "未設定" });
		}
		JScrollPane sp1 = new JScrollPane(settingArgsTable);
		sp1.setPreferredSize(new Dimension(450, 200));
		settingArgsTable.doLayout();
		settingArgsPanel.add(sp1, BorderLayout.CENTER);
		getContentPane().add(settingArgsPanel, BorderLayout.NORTH);


		// InstanceTableを表示
		JPanel instanceTablePanel = new JPanel();
		instanceTablePanel.add(new JLabel("既存のインスタンスに設定:"), BorderLayout.NORTH);
		instanceTable = instanceViewer.getInstanceTable();
		JScrollPane sp2 = new JScrollPane(instanceTable);
		sp2.setPreferredSize(new Dimension(450, 450));
		instanceTable.doLayout();
		instanceTablePanel.add(sp2);

		// InstanceTable の反映ボタン
		JButton selectedInstanceButton = new JButton("既存のオブジェクトを値に設定");
		selectedInstanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArgFromSelectedInstanceTable();
			}
		});
		instanceTablePanel.add(selectedInstanceButton);
		getContentPane().add(instanceTablePanel, BorderLayout.CENTER);

		// プライマリな値に設定するためのパネル
		JPanel primaryPanel = new JPanel();
		instanceTablePanel.add(new JLabel("プライマリな型から設定:"), BorderLayout.NORTH);

		primaryComboBox = new JComboBox<String>(intepret.Util.getPrimaryTypes());
		primaryPanel.add(primaryComboBox, BorderLayout.CENTER);

		JButton setButton = new JButton("この値に変更");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArgFromPrimaryValue();
			}
		});
		primaryPanel.add(setButton, BorderLayout.SOUTH);

		primaryTextField = new JTextField();
		primaryTextField.setColumns(15);
		primaryPanel.add(primaryTextField, BorderLayout.SOUTH);

		getContentPane().add(primaryPanel, BorderLayout.SOUTH);

	}

	private void addArgFromSelectedInstanceTable() {
		int row = instanceTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}
		String key = (String)instanceTable.getModel().getValueAt(row, 0);
		addArg(instanceTableViewer.getInstance(key).getObject());
	}

	private void addArgFromPrimaryValue() {
		String selectedType = (String) primaryComboBox.getSelectedItem();
		String inputValue = primaryTextField.getText();
		if (inputValue == null) {
			inputValue = "";
		}
		addArg(intepret.Util.getPrimaryObject(selectedType, inputValue));
	}

	private void addArg(Object obj) {
		args[setArgCount] = obj;
		((DefaultTableModel)settingArgsTable.getModel()).setValueAt(obj, setArgCount, 1);

		setArgCount++;

		if (setArgCount >= argTypes.size()) {
			catchArguments.catchArguments(args);
			this.dispose();
		}
	}
}
