class Woolie extends Thread{
		
		private Room m_SwitchRoom;
		private boolean m_Switched;
		private int m_ID;
		
		public Woolie(Room p_Room, int p_ID)
		{
			this.m_SwitchRoom = p_Room;
			this.m_Switched = false;
			this.m_ID = p_ID;
		}
		
		public void run()
		{
			while (!this.m_SwitchRoom.getSolved())
			{
				synchronized(this.m_SwitchRoom)
				{
					if (!this.m_SwitchRoom.getStatus() && !this.m_Switched)
					{
						this.m_SwitchRoom.switchStatus();
						this.m_Switched = true;
						System.out.println("Woolie " + this.m_ID + " turned flipped the switched!");
					}
				}
			}
		}
		
	} 