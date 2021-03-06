package gui;

import intepret.IConstructor;
import intepret.IInstance;
import intepret.InstanceCreater;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InstanceTableViewer extends JFrame {
	private final JTable instanceTable;
	private final DefaultTableModel tableModel;
	private final Dictionary<String, IInstance> realTable = new Hashtable<String, IInstance>();
	private int lastId = 0;

	public InstanceTableViewer() {
		setTitle("インスタンステーブル");
		this.setBounds(10, 10, 500, 800);

		// InstanceTableを表示
		tableModel = createInstanceTabelModel();
		instanceTable = new JTable(tableModel){
			public int getSelectedRow() {
				int[] selections = this.getSelectedRows();
				if (selections.length == 0) return -1;
				else return this.convertRowIndexToModel(selections[0]);
			}
		};
		instanceTable.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(instanceTable);
		sp.setPreferredSize(new Dimension(450, 700));
		instanceTable.doLayout();
		JPanel tablePanel = new JPanel();
		tablePanel.add(sp);

		getContentPane().add(tablePanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		// このインスタンスを見るボタン
		JButton viewButton = new JButton("このインスタンスを見る");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFieldViewer();
			}
		});
		buttonPanel.add(viewButton);

		// 新しいインスタンスの生成ボタン
		JButton createInstanceButton = new JButton("インスタンスを生成");
		createInstanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateInstanceDialog();
			}
		});
		buttonPanel.add(createInstanceButton);

		// 新しい配列を生成ボタン
		JButton createArrayButton = new JButton("配列を生成");
		createArrayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateArrayDialog();
			}
		});
		buttonPanel.add(createArrayButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private DefaultTableModel createInstanceTabelModel() {
		String[] columnNames = new String[] { "ID", "型" };

		// モデルを生成する
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (Enumeration<String> e = realTable.keys(); e.hasMoreElements();) {
			String key = e.nextElement();
			IInstance value = realTable.get(key);
			model.addRow(new String[] { key, value.getCls() });
		}
		return model;
	}

	private void openFieldViewer() {
		int row = instanceTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "行を選択してください");
			return;
		}
		String id = (String) instanceTable.getModel().getValueAt(row, 0);

		IInstance ins = realTable.get(id);

		InstanceViewer viewer = new InstanceViewer(ins, this);
		viewer.setVisible(true);
	}

	private void openCreateInstanceDialog() {
		ConstructorWindow window = new ConstructorWindow(this);
		window.setVisible(true);
	}

	private void openCreateArrayDialog() {
		CreateArrayWindow window = new CreateArrayWindow(this);
		window.setVisible(true);
	}

	public IInstance getInstance(String key) {
		return realTable.get(key);
	}

	public void addInstance(IInstance ins) {
		realTable.put(ins.getInstanceId(), ins);
		tableModel.addRow(new String[] { ins.getInstanceId(), ins.getCls() });
	}

	public void addInstance(IConstructor con, Object[] args) {
		try {
			String id = nextId();
			addInstance(InstanceCreater.createInstance(id, con, args));
			addId();
		} catch (Throwable e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	public void addInstance(String cls, int[] size) {
		try{
			String id = nextId();
			addInstance(InstanceCreater.createInstance(id, cls, size));
			addId();
		} catch (ClassNotFoundException | RuntimeException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}

	public JTable getInstanceTable() {
		// return clone
		JTable table = new JTable(createInstanceTabelModel()){
			public int getSelectedRow() {
				int[] selections = this.getSelectedRows();
				if (selections.length == 0) return -1;
				else return this.convertRowIndexToModel(selections[0]);
			}
		};
		table.setAutoCreateRowSorter(true);
		return table;
	}

	private String nextId() {
		return String.format("#%04d", lastId + 1);
	}

	private void addId() {
		lastId++;
	}

	public static void main(String[] args) {
		InstanceTableViewer viewer = new InstanceTableViewer();

		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer.setVisible(true);
	}


}
