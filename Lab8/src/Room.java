import java.util.Random;

public class Room{
		
		private boolean m_Switch;
		private boolean m_Solved;
		private int m_TimesVisited;
		private int m_Count;
		private Random m_Rand;
		private int m_CurID;
		
		public Room(int p_Count)
		{
			this.m_Switch = false;
			this.m_Solved = false;
			this.m_TimesVisited = 0;
			this.m_Count = p_Count;
			this.m_Rand = new Random();
			this.m_CurID = this.m_Rand.nextInt(this.m_Count);
		}
		
		public boolean getStatus()
		{
			return this.m_Switch;
		}
		
		public void switchStatus()
		{
			this.m_Switch = !this.m_Switch;
		}
		
		public boolean getSolved()
		{
			return this.m_Solved;
		}
		
		public void setSolved()
		{
			this.m_Solved = true;
		}
		
		public void visit()
		{
			this.m_TimesVisited++;
		}
		
		public int getTimesVisited()
		{
			return this.m_TimesVisited;
		}
		
		public boolean visit(Woolie p_Woolie)
		{
			if (p_Woolie.getID() == this.m_CurID)
			{
				this.m_CurID = this.m_Rand.nextInt(this.m_Count);
				this.m_TimesVisited++;
				return true;
			}
			return false;
		}
		
		public boolean visit(WoolieLeader p_Woolie)
		{
			if (p_Woolie.getID() == this.m_CurID)
			{
				this.m_CurID = this.m_Rand.nextInt(this.m_Count);
				this.m_TimesVisited++;
				return true;
			}
			return false;
		}
		
	}