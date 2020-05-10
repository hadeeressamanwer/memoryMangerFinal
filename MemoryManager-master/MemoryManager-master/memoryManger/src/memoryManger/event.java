package memoryManger;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

import javax.swing.JOptionPane;

class  holeComparator implements Comparator<Hole>{ 
    
   
    public int compare(Hole p1,Hole p2) { 
        if (p1.start >p2.start) 
            return 1;
        else if (p1.start <= p2.start) 
            return -1;
        	return 0; 
        	
        	
}  
};
class  holeComparator1 implements Comparator<Hole>{ 
    
	   
    public int compare(Hole p1,Hole p2) { 
        if (p1.size >p2.size) 
            return 1;
        else if (p1.size <= p2.size) 
            return -1;
        	return 0; 
        	
        	
}  
};
public class event {
	private   int memsize;
	private   int size;
	private   int segmentsCount;
	private   String ProcessName;
	private   PriorityQueue<Hole> holeq = new PriorityQueue<Hole>(10, new holeComparator());
	private   Vector<Segment> segmentV=new Vector<Segment>(segmentsCount);// added
	public   LinkedList<Segment> memory = new LinkedList<Segment>(); 
	private   PriorityQueue<Hole> holeqSize = new PriorityQueue<Hole>(10, new holeComparator1());
	public    Vector <Integer> baseVector=new Vector<Integer>(segmentsCount); //added

	
	public  void setmemSize(int i)
	{
		memsize=i;
	}
	public  void setSize(int i)
	{
		size=i;
	}
	public void setSegmentsCount(int i)
	{
	
		segmentsCount=i;
	}
	public void setProcessName(String i)
	{
		ProcessName=i;
	}
	
	public void addHoles(Vector v)

	{
	
	for(int i=0;i<size;i++)
	{	
	    
		Hole p=new Hole();
		p.start=(Integer) ((Vector)v.elementAt(i)).elementAt(0);
		p.size=(Integer) ((Vector)v.elementAt(i)).elementAt(1);
		holeq.add(p); //Queue of holes sorted by the start
	    
	}
    }
	
	public void addSegments(Vector v)

	{
	
	for(int i=0;i<segmentsCount;i++)
	{	
	    
		Segment p=new Segment();
		p.segmentName=(String) ((Vector)v.elementAt(i)).elementAt(0);
		p.size=(Integer) ((Vector)v.elementAt(i)).elementAt(1);
		p.processName=ProcessName;
	    segmentV.add(p);
		
		
	    
	}
	
    }
	
	public void initiallization()  
	{
		Hole h;
		h=holeq.peek(); 
		
		if(h.start==0)
		{
			
			Segment s=new Segment();
			s.start=h.start;
			s.size=h.size;
			s.end=s.start+s.size;
			s.busy=false;
			memory.add(s);
			holeq.remove();
			
			
		}
		
		else
		{
			Segment s=new Segment();
			s.start=0;
			s.size=h.start;
			s.end=s.start+s.size;
			s.busy=true;
			s.processName="Busy"+"0";
			memory.add(s); 
			
		}
		
		int i=1;
		int j =1;
		while(!holeq.isEmpty())
		{
			h=holeq.peek(); 
			if(h.start==memory.get(i-1).end)
			{
				Segment s=new Segment();
				s.start=h.start;
				s.size=h.size;
				s.end=s.start+s.size;
				s.busy=false;
				memory.add(s);
				holeq.remove();
				i++; 
				
			}
			else
			{
				Segment s=new Segment();
				s.start=memory.get(i-1).end;
				s.size=h.start-s.start;
				s.end=s.start+s.size;
				s.busy=true;
				s.processName="Busy"+Integer.toString(j);
				memory.add(s); 
				j++;
				i++; 
			}
		}
		
		if(memory.getLast().end!=memsize)
		{
			Segment s=new Segment();
			s.start=memory.getLast().end;
			s.size=(memsize)-s.start;
			s.end=s.start+s.size;
			s.busy=true;
			s.processName="Busy"+Integer.toString(j);
			memory.add(s);
		}
		
	
	}
	
	public void firstFit()
	{
		int i=0;
		while(i<segmentV.size())
		{
			int j=0;
			boolean found=false;
			while(j<memory.size())
			{
				
				
				if(!memory.get(j).busy)
				{
					Segment s=new Segment();
					if(memory.get(j).size==segmentV.get(i).size)
					{
						s.processName=segmentV.get(i).processName;
						s.segmentName=segmentV.get(i).segmentName;
						s.size=segmentV.get(i).size;
						s.busy=true;
						s.start=memory.get(j).start;
						s.end=memory.get(j).end;
						memory.set(j, s);
						baseVector.add(s.start);
						found=true;
						
						break;
						
						
					}
					else if(memory.get(j).size>segmentV.get(i).size)
					{
						s.processName=segmentV.get(i).processName;
						s.segmentName=segmentV.get(i).segmentName;
						s.size=segmentV.get(i).size;
						s.busy=true;
						s.start=memory.get(j).start;
						s.end=s.start+s.size;
						Segment h=new Segment();
						h.start=s.end;
						h.size=memory.get(j).size-s.size;
						h.end=h.start+h.size;
						h.busy=false;
						baseVector.add(s.start);
								
						memory.set(j, s);
						memory.add(j+1, h);
						found=true;
						
						break;
						
					}
					
				}
				j++;
			}
			
			if(!found)
			{
				
				deallocateProcess(segmentV.get(i).processName);
				if(!baseVector.isEmpty())
				baseVector.clear();
				JOptionPane.showMessageDialog(null, "There Is no Enough Size To Allocate This Process");
				break;
			}
			i++;
			
		}
	
		
	}
	public void clearSegment()
	{
		segmentV.removeAllElements();
	}
	public void clearBase()
	{
		baseVector.clear();
	}
	

	public void deallocateProcess(String processName) 
	{
		for(int i=0;i<memory.size();i++)
		{
			if(memory.get(i).busy&&processName.equals(memory.get(i).processName)) 
			{
				if((i!=0&&i!=memory.size()-1&&memory.get(i-1).busy&&memory.get(i+1).busy)||(i==0&&memory.get(i+1).busy)||(i==memory.size()-1&&memory.get(i-1).busy))
				{
				Segment hole=new Segment();
				hole.start=memory.get(i).start;
				hole.end=memory.get(i).end;
				hole.busy=false;
				hole.size=memory.get(i).size;
				memory.set(i, hole);
				}
				else if(i!=0&&i!=memory.size()-1&&!memory.get(i-1).busy&&!memory.get(i+1).busy)
				{
					Segment hole=new Segment();
					hole.start=memory.get(i-1).start;
					hole.end=memory.get(i+1).end;
					hole.busy=false;
					hole.size=hole.end-hole.start;
					memory.set(i, hole);
					memory.remove(i-1);
					memory.remove(i);
					i=i-2;
					
					
				}
				else if((i!=0&&i!=memory.size()-1&&memory.get(i-1).busy&&!memory.get(i+1).busy)||(i==0&&!memory.get(i+1).busy))
				{
					Segment hole=new Segment();
					hole.start=memory.get(i).start;
					hole.end=memory.get(i+1).end;
					hole.busy=false;
					hole.size=hole.end-hole.start;
					memory.set(i, hole);
					memory.remove(i+1);
				i=i-1;
				}
				else if((i!=0&&i!=memory.size()-1&&!memory.get(i-1).busy&&memory.get(i+1).busy)||(i==memory.size()-1&&!memory.get(i-1).busy))
				{
					Segment hole=new Segment();
					hole.start=memory.get(i-1).start;
					hole.end=memory.get(i).end;
					hole.busy=false;
					hole.size=hole.end-hole.start;
					memory.set(i, hole);
					memory.remove(i-1);
				i=i-1;
				}
				
				
			}
		}
	}
	

	
	public void bestFit()
	{
	for(int i=0;i<segmentV.size();i++)

	{

	int segmentSize=segmentV.get(i).size;
	Boolean found=false;
	for(int j=0;j<memory.size();j++) //put all fit holes in Queue
	{
	if(!memory.get(j).busy&&memory.get(j).size>=segmentSize)
	{
	found=true;
	Hole hole=new Hole();
	hole.start=memory.get(j).start;
	hole.end=memory.get(j).end;
	hole.size=memory.get(j).size;
	holeqSize.add(hole);
	}

	}
	if(found)
	{
		
	int bestFitSize=holeqSize.peek().size;
	for(int j=0;j<memory.size();j++)
	{
	if(!memory.get(j).busy&&memory.get(j).size==bestFitSize)
	{
	Segment s=new Segment();
	if(memory.get(j).size==segmentV.get(i).size)
	{
	s.processName=segmentV.get(i).processName;
	s.segmentName=segmentV.get(i).segmentName;
	s.size=segmentV.get(i).size;
	s.busy=true;
	s.start=memory.get(j).start;
	s.end=memory.get(j).end;
	memory.set(j, s);

    holeqSize.clear();
    baseVector.add(s.start);
	break;


	}
	else if(memory.get(j).size>segmentV.get(i).size)
	{
	s.processName=segmentV.get(i).processName;
	s.segmentName=segmentV.get(i).segmentName;
	s.size=segmentV.get(i).size;
	s.busy=true;
	s.start=memory.get(j).start;
	s.end=s.start+s.size;
	Segment h=new Segment();
	h.start=s.end;
	h.size=memory.get(j).size-s.size;
	h.end=h.start+h.size;
	h.busy=false;

	memory.set(j, s);
	memory.add(j+1, h);

	holeqSize.clear();
	baseVector.add(s.start);
	break;
	}
	}

	}
	}
	else
	{
	deallocateProcess(segmentV.get(i).processName);
	if(!baseVector.isEmpty())
	baseVector.clear();
	JOptionPane.showMessageDialog(null, "There Is no Enough Size To Allocate This Process");
	break;
	}

	}
	}
	
}