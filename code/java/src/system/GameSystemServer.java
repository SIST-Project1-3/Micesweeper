package system;

import java.util.ArrayList;
import java.util.Iterator;

public class GameSystemServer {
	boolean gameflag, readyflag;
	int k;
	ArrayList<Integer> mice;
	ArrayList<Integer> number;
	Iterator<Integer> mcit = mice.iterator();
	Iterator<Integer> nuit = number.iterator();
	
	
	
	public GameSystemServer() {
		init();
	}
	
	public void  init() {
		gameflag = false;
		//�غ� + �����ϴ� ����
		if (gameflag = true) {
			CreateMice();//����, ����ĭ ����
			//�� Ŭ���̾�Ʈ�� ���� ����ĭ ���� �ֱ�
			//������ �����ٴ� ��ȣ�� ������ ���� ������ ����ȭ������(?)
		}else {
			init();
		}
	}
	
	public void CreateMice() {
		boolean mflag = false;
		k = 11 + (((int)Math.random())*10000)%6;
		for (int i=1; i<k; i++) {
			int l=((int)Math.random()*10000)%81;
			while(mcit.hasNext()){
			    int value = mcit.next();
			    if(value == l){
			       mflag = true;
			    }
			}
			if(mflag=true) {
			i--;
			}else {
				mice.add(l);
				int a=l/9;
				int b=l%9;
				if(a==0) {
					if(b==0) {
						number.add((a)*9+(b+1));
						number.add((a+1)*9+(b));
						number.add((a+1)*9+(b+1));
					}else if(b==8) {
						number.add((a)*9+(b-1));
						number.add((a+1)*9+(b-1));
						number.add((a+1)*9+(b));
					}else {
						number.add((a)*9+(b-1));
						number.add((a)*9+(b+1));
						number.add((a+1)*9+(b-1));
						number.add((a+1)*9+(b));
						number.add((a+1)*9+(b+1));
					}
				}else if(a==8) {
					if(b==0) {
						number.add((a-1)*9+(b));
						number.add((a-1)*9+(b+1));
						number.add((a)*9+(b+1));
					}else if(b==8) {
						number.add((a-1)*9+(b-1));
						number.add((a-1)*9+(b));
						number.add((a)*9+(b-1));
					}else {
						number.add((a-1)*9+(b-1));
						number.add((a-1)*9+(b));
						number.add((a-1)*9+(b+1));
						number.add((a)*9+(b-1));
						number.add((a)*9+(b+1));
					}
				}else {
					if(b==0) {
						number.add((a-1)*9+(b));
						number.add((a-1)*9+(b+1));
						number.add((a)*9+(b+1));
						number.add((a+1)*9+(b));
						number.add((a+1)*9+(b+1));
					}else if(b==8) {
						number.add((a-1)*9+(b-1));
						number.add((a-1)*9+(b));
						number.add((a)*9+(b-1));
						number.add((a+1)*9+(b-1));
						number.add((a+1)*9+(b));
					}else {
						number.add((a-1)*9+(b-1));
						number.add((a-1)*9+(b));
						number.add((a-1)*9+(b+1));
						number.add((a)*9+(b-1));
						number.add((a)*9+(b+1));
						number.add((a+1)*9+(b-1));
						number.add((a+1)*9+(b));
						number.add((a+1)*9+(b+1));
					}
				}
			}
		}//for 끝
		
		
	}
}
	
