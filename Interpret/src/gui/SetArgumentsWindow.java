package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		// argsを生成
		this.args = new Object[argTypes.size()];

		this.setTitle("引数の設定");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.argTypes = argTypes;
		this.catchArguments = catchArguments;
		this.instanceTableViewer = instanceViewer;
		//this.setSize(510, 810);
		this.setBounds(10, 10, 500, 800);

		GridBagLayout layout =new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 510;

		this.setLayout(layout);


		// 設定済みの引数を表示するテーブル
		JLabel settingArgsLabel = new JLabel("設定済みの引数");
		layout.setConstraints(settingArgsLabel, c);
		this.add(settingArgsLabel);
		c.gridy++;

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
		sp1.setMinimumSize(new Dimension(450, 200));
		settingArgsTable.doLayout();

		layout.setConstraints(sp1, c);
		this.add(sp1);
		c.gridy++;

		// InstanceTableを表示
		JLabel instanceTableLabel = new JLabel("既存のインスタンスに設定:");
		layout.setConstraints(instanceTableLabel, c);
		this.add(instanceTableLabel);
		c.gridy++;

		instanceTable = instanceViewer.getInstanceTable();
		JScrollPane sp2 = new JScrollPane(instanceTable);
		sp2.setPreferredSize(new Dimension(450, 200));
		sp2.setPreferredSize(new Dimension(450, 200));
		instanceTable.doLayout();

		layout.setConstraints(sp2, c);
		this.add(sp2);
		c.gridy++;

		// InstanceTable の反映ボタン
		JButton selectedInstanceButton = new JButton("既存のオブジェクトを値に設定");
		selectedInstanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArgFromSelectedInstanceTable();
			}
		});

		layout.setConstraints(selectedInstanceButton, c);
		this.add(selectedInstanceButton);
		c.gridy++;

		// プライマリな値に設定するためのパネル
		JLabel primaryLable = new JLabel("プライマリな型から設定:");
		layout.setConstraints(primaryLable, c);
		this.add(primaryLable);
		c.gridy++;


		primaryComboBox = new JComboBox<String>(intepret.Util.getPrimaryTypes());
		layout.setConstraints(primaryComboBox, c);
		this.add(primaryComboBox);
		c.gridy++;

		primaryTextField = new JTextField();
		primaryTextField.setColumns(15);
		layout.setConstraints(primaryTextField, c);
		this.add(primaryTextField);
		c.gridy++;

		JButton setButton = new JButton("この値に変更");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addArgFromPrimaryValue();
			}
		});

		layout.setConstraints(setButton, c);
		this.add(setButton);
		c.gridy++;
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
