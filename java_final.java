import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UI{
	private JFrame mainFrame;
	private JButton loadBtn;
	private JButton unloadBtn;
	private JButton refresh;
	private JLabel stateLabel;
	private JLabel railway;
	private LinkedList<Car> train_ui;
	private JComboBox destBox;
	private JComboBox numBox;
	private JLabel destLabel;
	private JLabel numLabel;

	public LinkedList<int[]> train = new LinkedList<int[]>();
	public int length = 0;

	public UI(){
		train_ui = new LinkedList<Car>();
		mainFrame = new JFrame("Java");
		mainFrame.setSize(800, 400);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		stateLabel = new JLabel("Update button: refresh the frame"); 
		stateLabel.setBounds(20, 300,360, 50);

		railway = new JLabel();
		railway.setBounds(0, 150, 800, 50);
		railway.setIcon(new ImageIcon("railway.jpg"));


		loadBtn = new JButton("Load");
		loadBtn.setBounds(600, 200, 100, 50);
		loadBtn.setActionCommand("load");
		loadBtn.addActionListener(new ButtonClickListener());

		unloadBtn = new JButton("Unload");
		unloadBtn.setBounds(600, 300, 100, 50);
		unloadBtn.setActionCommand("unload");
		unloadBtn.addActionListener(new ButtonClickListener());

		refresh = new JButton("Refresh");
		refresh.setBounds(650, 300, 100, 50);
		refresh.setActionCommand("refresh");
		refresh.addActionListener(new ButtonClickListener());

		destBox = new JComboBox<Integer>();
		destBox.setBounds(50, 300, 80, 30);
		destBox.addItem(1);
		destBox.addItem(2);
		destBox.addItem(3);
		destBox.addItem(4);
		destBox.addItem(5);
		destBox.addItem(6);
		destBox.addItem(7);

		destLabel = new JLabel("Destination:");
		destLabel.setBounds(350, 290, 100, 30);

		numBox = new JComboBox<Integer>();
		numBox.setBounds(50, 50, 80, 30);
		numBox.addItem(1);
		numBox.addItem(2);
		numBox.addItem(3);
		numBox.addItem(4);
		numBox.addItem(5);
		numBox.addItem(6);
		numBox.addItem(7);

		numLabel = new JLabel("Numbers:");
		numLabel.setBounds(500, 290, 100, 30);

		mainFrame.getContentPane().add(railway);
		mainFrame.getContentPane().add(unloadBtn);
		mainFrame.getContentPane().add(loadBtn);
		mainFrame.getContentPane().add(refresh);
		mainFrame.getContentPane().add(stateLabel);
		mainFrame.getContentPane().add(destBox);
		mainFrame.getContentPane().add(numBox);
		mainFrame.getContentPane().add(destLabel);
		mainFrame.getContentPane().add(numLabel);

		unloadBtn.setLocation(200,300);
		loadBtn.setLocation(50, 300);
		stateLabel.setLocation(20, 200);
		destBox.setLocation(350, 320);
		numBox.setLocation(500, 320);

		mainFrame.setVisible(true);
		init();
	}
	//末尾位置处添加车厢
	public void addCars(){
		int len = train_ui.size();
		Car car = new Car();
		train_ui.addLast(car);
		car.p.setLocation(40+50*len, 50);
		car.car.setLocation(40 + 50*len, 100);
		mainFrame.getContentPane().add(car.p);
		mainFrame.getContentPane().add(car.car);
		flush();
	}
	//为指定的车厢设置四个位置的值
	public void setPos(int car_index,int index,int value){
		if(index == 0)
			train_ui.get(car_index).l0.setText(Integer.toString(value));
		else if(index == 1)
			train_ui.get(car_index).l1.setText(Integer.toString(value));
		else if(index == 2)
			train_ui.get(car_index).l2.setText(Integer.toString(value));
		else if(index == 3)
			train_ui.get(car_index).l3.setText(Integer.toString(value));
		else System.out.println("Error");
	}
	//指定需要移除的车厢
	public void removeCars(int car_index){
		mainFrame.getContentPane().remove(train_ui.getFirst().p);
		mainFrame.getContentPane().remove(train_ui.getFirst().car);
		train_ui.removeFirst();
		flush();
	}
	//在移除车厢或添加车厢后重绘
	public void flush(){
		mainFrame.repaint();
	}
	//想法：将现有的车厢拼接，然后更新火车状态
	//之后可以将其合并到flush函数中
	public void update(){
		int len = train_ui.size();
		for(int i=0;i<len;i++){
			train_ui.get(i).p.setLocation(40 + 50 * i,50);
			train_ui.get(i).car.setLocation(40 + 50 * i,100);
		}
		flush();
	}
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		   	String command = e.getActionCommand();  
		   	if(command.equals("load")){
				int dest = (int)destBox.getSelectedItem();
				int num  = (int)numBox.getSelectedItem();
				stateLabel.setText("Loading "+ num +" goods from destination "+dest);
				load(dest, num);
		   	}
		   	else if(command.equals("unload")){
				int dest = (int)destBox.getSelectedItem();
				stateLabel.setText("Unload goods of destination "+ dest);
				unload(dest);
			}
			else if(command.equals("refresh")){
				stateLabel.setText("Update the picture.");
				update();
			}  
		}     
	}

	public void init()
	{
		/*Initializer, 0 for empty freight*/
		train.add(new int[4]);addCars();
		train.add(new int[4]);addCars();
		train.add(new int[4]);addCars();
		train.add(new int[4]);addCars();
		train.add(new int[4]);addCars();
	}
	public void add()
	{
		train.addLast(new int[4]);
		addCars();
	}

	public void remove(int index)
	{
		train.remove(index);
		removeCars(index);
	}

	public void load(int dest, int num)
	{
		if(num >=4)
		{
			int length = train.size();
			int _number = num;	
			for(int i = 0; i < length; i++)
			{
				int[] _temp = train.get(i);
				if(_temp[0] == 0 && _temp[1] == 0 && _temp[2] == 0 && _temp[3] == 0)
				{
					train.get(i)[0] = dest;setPos(i,0, dest);
					train.get(i)[1] = dest;setPos(i,1, dest);
					train.get(i)[2] = dest;setPos(i,2, dest);
					train.get(i)[3] = dest;setPos(i,3, dest);
					_number -= 4;
					break;
				}
			}
			if(_number == num)
			{
				add();
				train.getLast()[0] = dest;setPos(train.size()-1,0, dest);
				train.getLast()[1] = dest;setPos(train.size()-1,1, dest);
				train.getLast()[2] = dest;setPos(train.size()-1,2, dest);
				train.getLast()[3] = dest;setPos(train.size()-1,3, dest);
				_number -= 4;
			}
			
			length = train.size();
			for(int i = 0; i < length; i++)
			{
				if(_number <= 0)break;
				if(train.get(i)[0] == 0 && _number > 0)
				{
					train.get(i)[0] = dest;setPos(i,0, dest);
					_number--;
				}
				if(train.get(i)[1] == 0 && _number > 0)
				{
					train.get(i)[1] = dest;setPos(i,1, dest);
					_number--;
				}
				if(train.get(i)[2] == 0 && _number > 0)
				{
					train.get(i)[2] = dest;setPos(i,2, dest);
					_number--;
				}
				if(train.get(i)[3] == 0 && _number > 0)
				{
					train.get(i)[3] = dest;setPos(i,3, dest);
					_number--;
				}
			}
			if(_number > 0)
			{
				add();
				for(int i=0; i < _number; i++)
				{
					train.getLast()[i] = dest;setPos(train.size()-1,i, dest);
				}
			}
		}
		else
		{
			int length = train.size();
			int _number = num;
			for(int i=0; i<length; i++)
			{
				if(_number <= 0)break;
				if(train.get(i)[0] == 0 && _number > 0)
				{
					train.get(i)[0] = dest;setPos(i,0, dest);
					_number--;
				}
				if(train.get(i)[1] == 0 && _number > 0)
				{
					train.get(i)[1] = dest;setPos(i,1, dest);
					_number--;
				}
				if(train.get(i)[2] == 0 && _number > 0)
				{
					train.get(i)[2] = dest;setPos(i,2, dest);
					_number--;
				}
				if(train.get(i)[3] == 0 && _number > 0)
				{
					train.get(i)[3] = dest;setPos(i,3, dest);
					_number--;
				}
			}
			if(_number > 0)
			{
				add();
				for(int i=0; i < _number; i++)
				{
					train.getLast()[i] = dest;setPos(train.size()-1,i, dest);
				}
			}
		}
	}

	public void unload(int dest)
	{
		int length = train.size();
		int index=0;
		while(true)
		{
			if(index >= train.size())
				break;
			if(train.get(index)[0] == dest && train.get(index)[1] == dest && train.get(index)[2] == dest && train.get(index)[3] == dest)
			{
				train.remove(index);removeCars(index);
			}
			else
			{
				if(train.get(index)[0] == dest){
					train.get(index)[0] = 0;
					setPos(index, 0, 0);
				}					
				if(train.get(index)[1] == dest){
					train.get(index)[1] = 0;
					setPos(index, 1, 0);
				}
				if(train.get(index)[2] == dest){
					train.get(index)[2] = 0;
					setPos(index, 2, 0);
				}
					
				if(train.get(index)[3] == dest){
					train.get(index)[3] = 0;
					setPos(index, 3, 0);
				}
				index++;
			}
		}
	}

	public static void main(String[] args) 
	{
		UI ui = new UI();
	}
}
public class Car{
	JLabel car;
	JLabel l0;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JPanel p;
	
	public Car(){
		ImageIcon icon = new ImageIcon("train_v1.jpg");
		car = new JLabel();
		car.setBounds(50, 150, 50, 50);
		car.setIcon(icon);

		p = new JPanel();
		p.setBounds(50, 100, 50, 50);
		p.setBackground(Color.lightGray);
		p.setLayout(new GridLayout(2,2));

		l0 = new JLabel("0",JLabel.CENTER);
		l1 = new JLabel("0",JLabel.CENTER);
		l2 = new JLabel("0",JLabel.CENTER);
		l3 = new JLabel("0",JLabel.CENTER);

		p.add(l0);
		p.add(l1);
		p.add(l2);
		p.add(l3);
	}
}