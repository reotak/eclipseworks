package gui;

import intepret.IInstance;
import intepret.InstanceField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Enumeration;

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

class ChangeValueWindow extends JFrame {
	private final InstanceField field;
	private final JTable instanceTable;
	private final InstanceTableViewer instanceViewer;

	private final JTextField primaryTextField;
	private final JComboBox<String> primaryComboBox;
	private final InstanceViewer parent;

	public ChangeValueWindow(InstanceField field,
			InstanceTableViewer instanceViewer, InstanceViewer parent) {
		this.field = field;
		this.instanceViewer = instanceViewer;
		this.parent = parent;

		this.setTitle(field.getName());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(10, 10, 500, 800);

		// 現在の値と型を表示
		JPanel nowValuePannel = new JPanel();
		nowValuePannel.add(new JLabel("現在の値:" + field.getValue()),
				BorderLayout.CENTER);
		nowValuePannel.add(new JLabel("型      :" + field.getType()),
				BorderLayout.SOUTH);
		getContentPane().add(nowValuePannel, BorderLayout.NORTH);

		// InstanceTableを表示
		JPanel instanceTablePanel = new JPanel();
		instanceTablePanel.add(new JLabel("既存のインスタンスに設定:"), BorderLayout.NORTH);
		instanceTable = instanceViewer.getInstanceTable();
		JScrollPane sp = new JScrollPane(instanceTable);
		sp.setPreferredSize(new Dimension(450, 450));
		instanceTable.doLayout();
		instanceTablePanel.add(sp);

		// InstanceTable の反映ボタン
		JButton changeButton = new JButton("既存のオブジェクトを値に設定");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trySetObjectSelectedTable();
			}
		});
		instanceTablePanel.add(changeButton);
		getContentPane().add(instanceTablePanel, BorderLayout.CENTER);

		// プライマリな値に設定するためのパネル
		JPanel primaryPanel = new JPanel();
		instanceTablePanel.add(new JLabel("プライマリな型から設定:"), BorderLayout.NORTH);

		primaryComboBox = new JComboBox<String>(intepret.Util.getPrimaryTypes());
		primaryPanel.add(primaryComboBox, BorderLayout.CENTER);

		JButton setButton = new JButton("この値に変更");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trySetPrimaryValue();
			}
		});
		primaryPanel.add(setButton, BorderLayout.SOUTH);

		primaryTextField = new JTextField();
		primaryTextField.setColumns(15);
		primaryPanel.add(primaryTextField, BorderLayout.SOUTH);

		getContentPane().add(primaryPanel, BorderLayout.SOUTH);
	}

	public DefaultTableModel createInstanceTabelModel(
			Dictionary<String, IInstance> table) {
		String[] columnNames = new String[] { "ID", "型" };

		// モデルを生成する
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Enumeration<String> e = table.keys(); e.hasMoreElements();) {
			String key = e.nextElement();
			model.addRow(new String[] { key, table.get(key).getCls() });
		}
		return model;
	}

	private void trySetObjectSelectedTable() {
		int row = instanceTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}
		String key = (String) instanceTable.getModel().getValueAt(row, 0);
		setValue(instanceViewer.getInstance(key).getObject());
	}

	private void trySetPrimaryValue() {
		String selectedType = (String) primaryComboBox.getSelectedItem();
		String inputValue = primaryTextField.getText();
		if (inputValue == null) {
			inputValue = "";
		}
		setValue(intepret.Util.getPrimaryObject(selectedType, inputValue));
	}

	private void setValue(Object value) {
		try {
			field.setValue(value);
		} catch (IllegalArgumentException | IllegalAccessException
				| SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e);
		}
		parent.refresh();
		JOptionPane.showMessageDialog(this, "値の変更が完了しました");
		this.dispose();
	}
}
