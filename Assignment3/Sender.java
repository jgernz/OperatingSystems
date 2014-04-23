import java.util.ArrayList;


public class Sender {
	public ArrayList list = new ArrayList();
	int wait;
	
	public void sending(ArrayList list){
		this.list = list;
	}
	
	public ArrayList receive(){
		return list;	
	}
	
	public void sendWait(int wait){
		this.wait = wait;
	}
	
	public int getWait(){
		return wait;
	}
	
}
