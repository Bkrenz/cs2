public class Room{
		
		private boolean m_Switch;
		private boolean m_Solved;
		
		public Room()
		{
			this.m_Switch = false;
			this.m_Solved = false;
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
		
	}