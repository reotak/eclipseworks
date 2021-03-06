package gui;

import intepret.InstanceField;
import intepret.InstanceMethod;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InstanceViewer extends JFrame {
	private final DefaultTableModel fieldModel;
	private final JTable fieldTable;
	private final DefaultTableModel methodModel;
	private final JTable methodTable;
	private final intepret.IInstance ins;
	private final InstanceTableViewer instanceTable;

	InstanceViewer(intepret.IInstance ins, InstanceTableViewer instanceTable) {
		this.ins = ins;
		this.instanceTable = instanceTable;

		this.setTitle(ins.getInstanceId());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(10, 10, 500, 800);

		GridBagLayout layout =new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 510;

		this.setLayout(layout);


		// フィールドを表示するための設定
		String[] fieldColumnNames = { "フィールド名", "値" };
		fieldModel = new DefaultTableModel(fieldColumnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		addFieldTableFromFields();
		fieldTable = new JTable(fieldModel){
			public int getSelectedRow() {
				int[] selections = this.getSelectedRows();
				if (selections.length == 0) return -1;
				else return this.convertRowIndexToModel(selections[0]);
			}
		};
		fieldTable.setAutoCreateRowSorter(true);
		JScrollPane sp1 = new JScrollPane(fieldTable);
		sp1.setPreferredSize(new Dimension(450, 300));
		sp1.setMinimumSize(new Dimension(450, 300));
		fieldTable.doLayout();
		layout.setConstraints(sp1, c);
		this.add(sp1);
		c.gridy++;

		JButton changeButton = new JButton("値の変更");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openChangeValueDialog();
			}
		});
		layout.setConstraints(changeButton, c);
		this.add(changeButton);
		c.gridy++;

		// メソッドを表示するための設定
		String[] methodColumnNames = { "戻り値", "メソッド名", "引数" };
		methodModel = new DefaultTableModel(methodColumnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		addMethodTableFromMethods();
		methodTable = new JTable(methodModel) {
			public int getSelectedRow() {
				int[] selections = this.getSelectedRows();
				if (selections.length == 0) return -1;
				else return this.convertRowIndexToModel(selections[0]);
			}
		};
		methodTable.setAutoCreateRowSorter(true);

		JScrollPane sp2 = new JScrollPane(methodTable);
		sp2.setPreferredSize(new Dimension(450, 300));
		sp2.setMinimumSize(new Dimension(450, 300));
		methodTable.doLayout();

		layout.setConstraints(sp2, c);
		this.add(sp2);
		c.gridy++;

		JButton callButton = new JButton("このメソッドの呼び出し");
		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCallMethodDialog();
			}
		});

		layout.setConstraints(callButton, c);
		this.add(callButton);
		c.gridy++;


		// 更新ボタン
		JButton renewButton = new JButton("最新の情報に更新");
		renewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		layout.setConstraints(renewButton, c);
		this.add(renewButton);
		c.gridy++;

	}

	public void refresh() {

		int row = fieldModel.getRowCount();
		// clear tables
		for (int i = 0; i < row; i++) {
			try {
				fieldModel.removeRow(0);
			} catch (IndexOutOfBoundsException e) {
				// ソータが例外を送ることがあるが無視をする
			}
		}
		addFieldTableFromFields();

		row = methodModel.getRowCount();
		for (int i = 0; i < row; i++) {
			try {
				methodModel.removeRow(0);
			} catch (IndexOutOfBoundsException e) {
				// ソータが例外を送ることがあるが無視をする
			}
		}
		addMethodTableFromMethods();
	}

	private void addFieldTableFromFields() {
		List<InstanceField> fields = this.ins.getFields();
		for (int i = 0; i < fields.size(); i++) {
			fieldModel.addRow(new String[] { fields.get(i).getName(),
					fields.get(i).getValue() });
		}
	}

	private void addMethodTableFromMethods() {
		List<InstanceMethod> methods = this.ins.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			methodModel.addRow(new String[] { methods.get(i).getReturnType(),
					methods.get(i).getName(),
					methods.get(i).getArgTypesAsString() });
		}
	}

	private void openChangeValueDialog() {
		int row = fieldTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}
		String name = (String) fieldTable.getModel().getValueAt(row, 0);
		InstanceField f = ins.getField(name);

		ChangeValueWindow dialog = new ChangeValueWindow(f, instanceTable,
				this);
		dialog.setVisible(true);
	}

	private void openCallMethodDialog() {
		int row = methodTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}

		String name = (String) methodTable.getModel().getValueAt(row, 1);
		String types = (String) methodTable.getModel().getValueAt(row, 2);

		InstanceMethod m = ins.getMethod(name, types);

		// メソッド呼び出しを実行
		new SetArgumentsWindowManagerForMethod(m, instanceTable, this);
	}
}