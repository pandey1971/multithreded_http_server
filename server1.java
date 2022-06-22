import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class server1 implements Runnable{
    Socket socket;
    public static Vector client =new Vector();
    public server1(Socket socket){
try{
 this.socket=socket;
}catch(Exception e){
    e.printStackTrace();
}
    }
    @Override
    public void run() {
try{
    BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    client.add(writer);
    while(true){
        String data=reader.readLine().trim();
        System.out.println("recieved"+data);
        for(int i=0;i<client.size();i++){
            try{
                BufferedWriter bw = (BufferedWriter) client.get(i);
                bw.write(data);
                bw.write("\r\n");
                bw.flush();

            }catch (Exception e){}
        }
    }

}catch (Exception e){
    e.printStackTrace();
}

    }

    public static  void main(String[]args)throws Exception{
        ServerSocket s= new ServerSocket(2001);
        while(true){
            Socket socket=s.accept();
            server1 sv=new server1(socket);
            Thread thread=new Thread(sv);
            thread.start();
        }
    }


}