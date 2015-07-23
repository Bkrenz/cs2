class WoolieLeader extends Thread{
		
		private Room m_SwitchRoom;
		private int m_Count;
		
		public WoolieLeader(Room p_Room, int p_Count)
		{
			this.m_Count = p_Count;
			this.m_SwitchRoom = p_Room;
		}
		
		public void run()
		{
			while (!this.m_SwitchRoom.getSolved())
			{
				synchronized(this.m_SwitchRoom)
				{
					if (this.m_SwitchRoom.getStatus())
					{
						this.m_SwitchRoom.switchStatus();
						this.m_Count--;
						System.out.println("Woolie Leader flipped the switch! Woolies Left: " + this.m_Count);
					}
					
					if (this.m_Count == 0)
					{
						this.m_SwitchRoom.setSolved();
					}
				}
			}
			
		
	}
}