package edu.tongji.alex.awt;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.tongji.alex.udp.UdpController;

public class LightSlider extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6453859775514663764L;

	protected JLabel label;

	protected JSlider slider;
	

	UdpController udpController = new UdpController();

	public LightSlider() {
		init();
	}

	public void init() {
		getContentPane().setLayout(new FlowLayout());

		JPanel p0 = new JPanel();
		p0.setLayout(new BoxLayout(p0, BoxLayout.X_AXIS));

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1));

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

		label = new JLabel("Light Control");
		label.setFont(new Font("Arial", Font.BOLD, 24));
		p.add(label);

		p1.add(p);

		slider = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		MyChangeListener lst = new MyChangeListener();
		slider.addChangeListener(lst);

		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(slider);
		p1.add(p);

		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));

		for (int k = 1; k <= 8; k++) {
			JButton fv = new JButton(Integer.toString(k));
			fv.addActionListener(new MyActionListener(k));
			p2.add(fv);
		}

		p.add(p2);
		p1.add(p);
		p0.add(p1);

		p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

		p0.add(p);

		getContentPane().add(p0);

		lst.stateChanged(new ChangeEvent(slider));
	}

	public synchronized void playStation(int index) {
		slider.setValue(index);
	}

	class MyActionListener implements ActionListener {
		protected int m_index;

		MyActionListener(int index) {
			m_index = index;
		}

		public void actionPerformed(ActionEvent e) {
			playStation(m_index);
		}
	}

	class MyChangeListener implements ChangeListener {
		MyChangeListener() {
		}

		public synchronized void stateChanged(ChangeEvent e) {
			int frequency = slider.getValue();
			label.setText(frequency + " / 8");

			// TODO 连接代码
			udpController.send(frequency + " / 8");
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] za) {
		LightSlider d = new LightSlider();
		d.setSize(400, 200);
		d.show();
	}
}