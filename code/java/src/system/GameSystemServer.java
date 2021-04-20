package system;

import java.util.ArrayList;
import java.util.Iterator;

public class GameSystemServer {
	boolean gameflag, readyflag;
	int k;
	static ArrayList<Integer> mice;
	static ArrayList<Integer> number;
	static Iterator<Integer> mcit;// mcit = mice.iterator();
	static Iterator<Integer> nuit;// nuit = number.iterator();
	
	
	
	public GameSystemServer() {
		CreateMice();
		//init();
	}
	
	public void  init() {
		gameflag = false; // 나중에 false로 바꾸고, 시작하면 true 되는 로직 생성해야함
		//준비 + 시작하는 과정
		if (gameflag = true) {
			CreateMice();//지뢰, 숫자칸 생성
			//각 클라이언트에 지뢰 숫자칸 정보 주기
			//게임이 끝났다는 신호를 받으면 전적 갱신후 메인화면으로(?)
		}else {
			init();
		}
	}
	
	public void CreateMice() {
		mice = new ArrayList<Integer>();
		number = new ArrayList<Integer>();//지뢰, 숫자 어레이리스트 생성
		boolean mflag = true; // 중복값 판단하는 flag
		k = 11 + (int)((Math.random())*10000)%6; // 지뢰갯수 설정
		System.out.println("k="+k); // 지뢰갯수 출력
		
		for (int i=1; i<k; i++) {
			System.out.println(i);
			mcit = mice.iterator();
			nuit = number.iterator();
			int l=(int)(Math.random()*10000)%81;
			System.out.println("지뢰="+l);
			System.out.println(mice.size());
			while(mcit.hasNext()){
			    int value = mcit.next();
			    if(value == l){
			       mflag = false;
			    }else {
			    	mflag = true;
			    }
			}
			System.out.println(mflag);
			if(mflag==false) {
			i--;
			System.out.println("중복!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}else if(mflag==true){
				mice.add(l);
				System.out.println("입력완료");
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
			}//else
		}//for
		
		
	}
	public static void main(String[] args) {
		new GameSystemServer();
	}
}