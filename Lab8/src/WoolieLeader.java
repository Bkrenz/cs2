class WoolieLeader extends Thread{
		
		private Room m_SwitchRoom;
		private int m_Count;
		private int m_ID;
		
		public WoolieLeader(Room p_Room, int p_Count, int p_ID)
		{
			this.m_Count = p_Count;
			this.m_SwitchRoom = p_Room;
			this.m_ID = p_ID;
		}
		
		public void run()
		{
			while (true)
			{
				synchronized(this.m_SwitchRoom)
				{
					if (this.m_SwitchRoom.visit(this))
					{
						if (this.m_SwitchRoom.getStatus())
						{
							this.m_SwitchRoom.switchStatus();
							this.m_Count--;
						}
						
						if (this.m_Count == 0)
						{
							this.m_SwitchRoom.setSolved();
							break;
						}
					}
				}
			}
		}
		
		public int getID()
		{
			return this.m_ID;
		}
}