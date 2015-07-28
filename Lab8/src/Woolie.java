class Woolie extends Thread{
		
		private Room m_SwitchRoom;
		private boolean m_Switched;
		
		public Woolie(Room p_Room)
		{
			this.m_SwitchRoom = p_Room;
			this.m_Switched = false;
		}
		
		public void run()
		{
			while (!this.m_SwitchRoom.getSolved())
			{
				synchronized(this.m_SwitchRoom)
				{
					this.m_SwitchRoom.visit();
					if (!this.m_SwitchRoom.getStatus() && !this.m_Switched)
					{
						this.m_SwitchRoom.switchStatus();
						this.m_Switched = true;
					}
				}
			}
		}
		
	} 