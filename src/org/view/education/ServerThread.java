package org.view.education;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


class ServerThread extends Thread {
	Socket socket;
	OpenSystenPanel serverFrame;
	public ServerSocket serverSocket;
	private boolean flag_exit = false;
	ServerThread(OpenSystenPanel serverFrame) {
		this.serverFrame = serverFrame;
		try {
			serverSocket = new ServerSocket(2016);
			serverFrame.success();
		} catch (IOException e) {
			this.serverFrame.setStartAndStopUnable();
		}
	}  
	
	
	
	public void run() {        
		Socket socket;
		while(flag_exit){
				try {
					if(serverSocket.isClosed()){
						flag_exit = false;
					}else{
						try{
							socket = serverSocket.accept();
						}catch(SocketException e){
							socket = null;
							flag_exit = false;
						}
						
						if(socket != null){
//							ClientThread clientThread = new ClientThread(socket, this);
//							clientThread.setFlag_exit(true);
//							clientThread.start();
//							synchronized (clients) {
//								clients.addElement(clientThread);
//							}
//							synchronized (messages) {
//								users.put((int) clientThread.getId(), "@login@");
//								messages.add(clientThread.getId() + "@clientThread");
//							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	} 
	
	
	
	public void stopServer() {
		try {
			if(this.isAlive()){
				serverSocket.close();
				setFlag_exit(false);
			}
		} catch (Throwable e) {}
	}

	
	public boolean isFlag_exit() {
		return flag_exit;
	}



	public void setFlag_exit(boolean b) {
		flag_exit = b;
	}
}
