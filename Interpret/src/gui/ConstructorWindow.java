package gui;

import intepret.IConstructor;
import intepret.InstanceCreater;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ConstructorWindow extends JFrame {
	private final JTable table;
	private final DefaultTableModel model;
	private final JTextField inputClass;

	private final InstanceTableViewer instanceTableViewer;
	final List<IConstructor> cons = null;

	public ConstructorWindow(InstanceTableViewer instanceViewer) {
		this.instanceTableViewer  = instanceViewer ;

		this.setTitle("実行するコンストラクタの選択");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel classPanel = new JPanel();
		classPanel.add(new JLabel("生成するクラス："));

		inputClass = new JTextField();
		inputClass.setColumns(15);
		classPanel.add(inputClass);

		JButton classSetButton = new JButton("クラスを決定");
		classSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConstuctorsToTable();
			}
		});
		classPanel.add(classSetButton);
		getContentPane().add(classPanel, BorderLayout.NORTH);

		// モデルを生成する
		model = new DefaultTableModel(new String[] {"コンストラクタ"} , 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// テーブルを表示するための設定
		JPanel tablePanel = new JPanel();
		this.setBounds(10, 10, 500, 800);
		table = new JTable(model) {
			public int getSelectedRow() {
				int[] selections = this.getSelectedRows();
				if (selections.length == 0) return -1;
				else return this.convertRowIndexToModel(selections[0]);
			}
		};
		table.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(450, 700));
		table.doLayout();
		tablePanel.add(sp);
		getContentPane().add(tablePanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("決定");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openArgSettingDialog();
			}
		});
		buttonPanel.add(startButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setConstuctorsToTable() {
		List<IConstructor> cons = null;
		try {
			cons = InstanceCreater.getConstructorList(inputClass.getText());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e);
			return;
		}

		// このテーブルに表示する内容を設定する
		for (IConstructor con : cons) {
			model.addRow(new IConstructor[] { con });
		}
	}

	private void openArgSettingDialog() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}
		IConstructor con = (IConstructor)((DefaultTableModel)table.getModel()).getValueAt(row, 0);

		new SetArgumentsWindowManagerForConsructor(con, instanceTableViewer);
		this.dispose();

	}
}
