package memoryManger;

public class Segment {
	
	public int start;
	public int size;
	public int end;
	public String processName;
	public String segmentName;
	public Boolean busy;
	
	Segment()
	{start=-1;end=-1;size=-1;processName="";segmentName= "";busy=true;}

	
	
}
