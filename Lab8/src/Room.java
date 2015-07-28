public class Room{
		
		private boolean m_Switch;
		private boolean m_Solved;
		private int m_TimesVisited;
		
		public Room()
		{
			this.m_Switch = false;
			this.m_Solved = false;
			this.m_TimesVisited = 0;
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
		
	}