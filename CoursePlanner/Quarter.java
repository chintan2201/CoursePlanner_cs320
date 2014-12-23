package cp5.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class Quarter {
	
	
	public String quarter;
	String nextquarter;
	public String code;
	public String title;
	public String prerec;
	
	HashMap<String,String> m = new HashMap<String, String>();
	
	public Quarter()
	{
		
	}
	
	
	public Quarter(String quarter , String code, String title , String prerec)
	{
		this.quarter=quarter;
		this.code = code;
		this.title=title;
		this.prerec=prerec;
	}

	
	
	public String getQuarter() {
		return quarter;
	}


	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPrerec() {
		return prerec;
	}


	public void setPrerec(String prerec) {
		this.prerec = prerec;
	}


	public HashMap<String,String> getQuarter(int week,int y) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		if(week >=1 && week <=12)
		{
		
			if(y!=0)
			{
				this.quarter = "Winter"+" "+y;
				this.nextquarter = "Spring"+" "+y;
				
			}
			else
			{
				this.quarter = "Winter"+" "+year;
				this.nextquarter = "Spring"+" "+year;
			}
	
			m.put("cquarter", this.quarter);
			m.put("nquarter", this.nextquarter);
		}
		else if(week >=13 && week <=24)
		{
			this.quarter = "Spring"+" "+y;
			this.nextquarter = "Summer"+" "+y;
			m.put("cquarter", this.quarter);
			m.put("nquarter", this.nextquarter);
		}
		else if(week >=25 && week <=37)
		{
			this.quarter = "Summer"+" "+y;
			this.nextquarter = "Fall"+" "+y;
			m.put("cquarter", this.quarter);
			m.put("nquarter", this.nextquarter);
		}
		else
		{
			this.quarter = "Fall"+" "+y;
			this.nextquarter = "Winter"+" "+(y+1);
			m.put("cquarter", this.quarter);
			m.put("nquarter", this.nextquarter);
		}
		return m;
	}

	public void setQuarter(int week) {
		
		
	}
	
	public List<Filemanager> NextQuarterPlan(List<Filemanager> entries,List<String> selected)
	{
		List<Filemanager> nextquartersub = new ArrayList<Filemanager>();
		
		OUTER :
		for(int i =0 ; i< entries.size();i++)
		{
			if (!selected.contains(entries.get(i).ccode))
			{
				Filemanager e = entries.get(i);
				if(e.cprerec == null)
				{
					e.cprerec="";
				}
				if (e.cprerec.equals("") && e.cprerec.isEmpty())
				{
					nextquartersub.add(new Filemanager(e.ccode,e.ctitle,e.cprerec));
				}
				else
				{
					int count =0;
					String[] prereq = e.cprerec.split(" ");
					List<String> pre = Arrays.asList(prereq);
					
					for(int j =0;j<pre.size();j++)
					{
						if (!selected.contains(pre.get(j)))
						{
							continue OUTER;
						}
						else
						{
							count ++;
						}
					}
					
					if (count == pre.size())
					{
						nextquartersub.add(new Filemanager(e.ccode,e.ctitle,e.cprerec));
					}
				}
			}
		}
		return nextquartersub;
	}

	
}
