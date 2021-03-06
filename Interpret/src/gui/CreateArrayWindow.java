package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CreateArrayWindow extends JFrame {
	private final JTable table;
	private final DefaultTableModel model;
	private final JTextField inputClass;

	private final InstanceTableViewer instanceTableViewer;

	public CreateArrayWindow(InstanceTableViewer instanceTableViewer) {
		this.instanceTableViewer = instanceTableViewer;

		JPanel classPanel = new JPanel();
		classPanel.add(new JLabel("生成するクラス："));

		inputClass = new JTextField();
		inputClass.setColumns(15);
		classPanel.add(inputClass);

		getContentPane().add(classPanel, BorderLayout.NORTH);

		// モデルを生成する
		model = new DefaultTableModel(new String[] {"次元", "サイズ"} , 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// テーブルを表示するための設定
		JPanel tablePanel = new JPanel();
		this.setBounds(10, 10, 500, 800);
		table = new JTable(model);

		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(450, 700));
		table.doLayout();
		tablePanel.add(sp);
		getContentPane().add(tablePanel, BorderLayout.CENTER);


		JPanel buttonPanel = new JPanel();
		// 次元を追加ボタン

		JButton addDimensionButton = new JButton("次元を追加");
		addDimensionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAddDimensionDialog();
			}
		});
		buttonPanel.add(addDimensionButton);

		// 生成ボタン
		JButton startButton = new JButton("この配列を生成");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createArray();
			}
		});
		buttonPanel.add(startButton);

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	private void openAddDimensionDialog() {
		int nextDim = this.model.getRowCount() + 1;
		String value = JOptionPane.showInputDialog(this, nextDim + "次元のサイズを入力：");

		try {
			int num = new Integer(value);
			if (num < 0) {
				JOptionPane.showInternalMessageDialog(this, "配列のサイズとして扱える数字を入力してください");
				return;
			}
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(this, "数字を入力してください");
			return;
		}

		model.addRow(new String[] { nextDim + "",  value});
	}

	private void createArray() {
		String cls = inputClass.getText();
		if (cls.length() == 0) {
			JOptionPane.showInternalMessageDialog(this, "クラスを入力してください");
			return;
		}

		int[] size = new int[model.getRowCount()];

		for (int i = 0; i < size.length; i++) {
			size[i] = new Integer(model.getValueAt(i, 1).toString());
		}

		instanceTableViewer.addInstance(cls, size);
		this.dispose();
	}
}
