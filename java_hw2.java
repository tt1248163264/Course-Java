import java.util.*;
/*
tip:输入1，开始装载货物，之后依次输入‘目的地’和‘数量’
	输入2，卸下货物，需再输入目的地
	输入3，查看当前火车的状态
	输入0，退出

作者：王志强

学号：PB17111657
 */
public class Train{
	public LinkedList<int[]> train = new LinkedList<int[]>();
	public int length = 0;
	
	public Train()
	{
		/*Initializer, 0 for empty freight*/
		train.add(new int[4]);
		train.add(new int[4]);
		train.add(new int[4]);
		train.add(new int[4]);
		train.add(new int[4]);
	}
	public void add()
	{
		train.addLast(new int[4]);
	}

	public void remove(int index)
	{
		train.remove(index);
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
					train.get(i)[0] = dest;
					train.get(i)[1] = dest;
					train.get(i)[2] = dest;
					train.get(i)[3] = dest;
					_number -= 4;
					break;
				}
			}
			if(_number == num)
			{
				add();
				train.getLast()[0] = dest;
				train.getLast()[1] = dest;
				train.getLast()[2] = dest;
				train.getLast()[3] = dest;
				_number -= 4;
			}
			
			length = train.size();
			for(int i = 0; i < length; i++)
			{
				if(_number <= 0)break;
				if(train.get(i)[0] == 0 && _number > 0)
				{
					train.get(i)[0] = dest;
					_number--;
				}
				if(train.get(i)[1] == 0 && _number > 0)
				{
					train.get(i)[1] = dest;
					_number--;
				}
				if(train.get(i)[2] == 0 && _number > 0)
				{
					train.get(i)[2] = dest;
					_number--;
				}
				if(train.get(i)[3] == 0 && _number > 0)
				{
					train.get(i)[3] = dest;
					_number--;
				}
			}
			if(_number > 0)
			{
				add();
				for(int i=0; i < _number; i++)
				{
					train.getLast()[i] = dest;
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
					train.get(i)[0] = dest;
					_number--;
				}
				if(train.get(i)[1] == 0 && _number > 0)
				{
					train.get(i)[1] = dest;
					_number--;
				}
				if(train.get(i)[2] == 0 && _number > 0)
				{
					train.get(i)[2] = dest;
					_number--;
				}
				if(train.get(i)[3] == 0 && _number > 0)
				{
					train.get(i)[3] = dest;
					_number--;
				}
			}
			if(_number > 0)
			{
				add();
				for(int i=0; i < _number; i++)
				{
					train.getLast()[i] = dest;
				}
			}
		}
	}

	public void unload(int dest)
	{
		int length = train.size();
		int index=0;;
		while(true)
		{
			if(index >= train.size())
				break;
			if(train.get(index)[0] == dest && train.get(index)[1] == dest && train.get(index)[2] == dest && train.get(index)[3] == dest)
			{
				train.remove(index);
			}
			else
			{
				if(train.get(index)[0] == dest)
					train.get(index)[0] = 0;
				if(train.get(index)[1] == dest)
					train.get(index)[1] = 0;
				if(train.get(index)[2] == dest)
					train.get(index)[2] = 0;
				if(train.get(index)[3] == dest)
					train.get(index)[3] = 0;
				index++;
			}
		}
	}
	public void print_state()
	{
		for(int i=0; i < train.size(); i++)
		{
			System.out.println(Arrays.toString(train.get(i)));
		}
	}
	public static void main(String[] args) 
	{
		Train train = new Train();
		while(true)
		{
			int dest,num,op;
			Scanner in =new Scanner(System.in);
			System.out.println("Option:1 for loading,2 for unloading,3 to see current state,0 for exit;");
			op = in.nextInt();
			if(op == 0)
				break;
			else if(op == 1){
				System.out.println("Please input the destination and number:");
				dest = in.nextInt();
				num = in.nextInt();
				train.load(dest,num);
			}
			else if(op == 2){
				System.out.println("Please input the destination:");
				dest = in.nextInt();
				train.unload(dest);
			}
			else if(op == 3){
				System.out.println("Current state:");
				train.print_state();
			}
			else
			{
				System.out.println("Error option!");
			}
		}
	}
}

