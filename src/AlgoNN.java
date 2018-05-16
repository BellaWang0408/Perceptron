import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AlgoNN {

	public class data {
		double x;
		double y;
		int classification;

		data(double a, double b, int c) 
		{
			x = a;
			y = b;
			classification = c;
		}
	}

	ArrayList<data> datas = new ArrayList<data>();
	ArrayList<Integer> store = new ArrayList<Integer>();
	ArrayList<data> test = new ArrayList<data>();
	ArrayList<data> train = new ArrayList<data>();
	private float testcount=0;
	public float get_testcount()
	{
		System.out.print("test:"+testcount);
		return testcount;

	}
	public float traincount=0;
	public float get_traincount()
	{
		System.out.print("train:"+traincount);
		return traincount;
	}
	public void run(String absolutePath) throws IOException {
		FileReader read = new FileReader(absolutePath);// 讀檔,absolutePath路徑
		datas.clear();
		test.clear();
		train.clear();
		store.clear();
		testcount=0;
		traincount=0;
		BufferedReader br = new BufferedReader(read);// 方便讀取整行
		while (br.ready()) {
			String cut = br.readLine();
			String[] token = cut.split(" ");
			double x = Double.parseDouble(token[0]);
			double y = Double.parseDouble(token[1]);
			int classification = Integer.parseInt(token[2]);
			if(store.indexOf(classification) == -1)
				store.add(classification);
			datas.add(new data(x, y, classification));
		}
		datas = RandomArray(datas);
		for(int j=0;j<datas.size();j++)
		{
			if(j*3<datas.size())
				test.add(datas.get(j));
			else
				train.add(datas.get(j));
		}
		read.close(); // 關檔
		System.out.println(datas.get(1).y);
		System.out.println(datas.size());
	}
	
	private ArrayList<data> RandomArray(ArrayList<data> datas2) {
		int mLength = datas2.size();
	    int mRandom;
		data mNumber;

	     for(int i = 0; i < mLength; i++) 
	     {      
	         mRandom = (int)(Math.random()* mLength);
	         mNumber = datas2.get(i);
	         datas2.set(i, datas2.get(mRandom));
	         datas2.set(mRandom,mNumber);
	     }

	    return datas2;
	}

	int sgn(double x)
	{
		if(x>=0)
			return store.get(0);
		else
			return store.get(1);
	}
	public void algo(double learn, double Iteration) throws IOException
	{
		int i;
		float xx, yy,zz;
		xx = (float) Math.random();
		yy = (float) Math.random();
		zz = (float) Math.random();
		float[] w = {
				xx,yy,zz	
		};
		while((Iteration--) != 0 )
		{
			for(i=0;i<datas.size();i++)
			{
				double s = datas.get(i).x;//arr[i]
				double e = datas.get(i).y;
				double fx = w[0]*s + w[1]*e - w[2];
				int move = datas.get(i).classification;
				if(sgn(fx) != move)
				{
					if(fx>=0)
					{
						w[0] -= learn * s ;
						w[1] -= learn * e ;
						w[2] -= learn * (-1);
					}
					else
					{
						w[0] += learn * s ;
						w[1] += learn * e ;
						w[2] += learn * (-1);
					}
				}			
			}
		}
		
		for(i=0;i<test.size();i++)
		{
			double s = test.get(i).x;
			double e = test.get(i).y;
			double fx = w[0]*s + w[1]*e - w[2];
			int move = test.get(i).classification;
			if(sgn(fx) == move)
			{
				testcount++;
			}			
		}
		testcount = (100*testcount)/test.size();
		for(i=0;i<train.size();i++)
		{
			double s = train.get(i).x;
			double e = train.get(i).y;
			double fx = w[0]*s + w[1]*e - w[2];
			int move = train.get(i).classification;
			if(sgn(fx) == move)
			{
				traincount++;
			}			
		}
		traincount = (100*traincount)/train.size();
		FileWriter fw = new FileWriter("datas.txt");
		for(int q = 0;q < datas.size();q++)
		{
			fw.write(datas.get(q).x+" "+datas.get(q).y+" "+datas.get(q).classification+"\r\n");
			
		}
        fw.flush();
        fw.close();
		FileWriter testfw = new FileWriter("test.txt");
		for(int q = 0;q < test.size();q++)
		{
			testfw.write(test.get(q).x+" "+test.get(q).y+" "+test.get(q).classification+"\r\n");
			
		}
		testfw.flush();
		testfw.close();
		FileWriter trainfw = new FileWriter("train.txt");
		for(int q = 0;q < train.size();q++)
		{
			trainfw.write(train.get(q).x+" "+train.get(q).y+" "+train.get(q).classification+"\r\n");
			
		}
		trainfw.flush();
		trainfw.close();
		FileWriter fw1 = new FileWriter("result.txt");
		fw1.write(w[0]+" "+w[1]+" "+w[2]);
        fw1.flush();
        fw1.close();
	}
}
