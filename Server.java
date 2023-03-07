import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
;
public class Server {
  static ArrayList<utilisateur> users = new ArrayList<>();

  public static void main(String[] args) throws Exception {
   
   users.add(new utilisateur("1", "123"));
    int port = 9999;
    ServerSocket srv = new ServerSocket(port);

    System.out.println("server active on port : " + port);

    while (true) {
      Socket client = srv.accept();

      ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
      ObjectInputStream inp = new ObjectInputStream(client.getInputStream());
      utilisateur ut=(utilisateur)inp.readObject();

      for(int i=0;i<users.size();i++){
        if(ut.id.equals(users.get(i).id)){
          if(ut.pass.equals(users.get(i).pass)){
          String operations = " 1) add : + \n 2) sub : - \n 3) mul : * \n";
           out.writeObject(operations);
          }
          else{
            inp.close();
            out.close();
          }
        }
        else{
          users.add(ut);
        }

      }
      
      String op = (String) inp.readObject();

      int[]a[]=(int[][])inp.readObject();
      int[]b[]=(int[][])inp.readObject();
     int[] A[] = new int[a.length][b.length];

      switch (op) {
        case "+":
        for(int i=0;i<a.length;i++){    
          for(int j=0;j<b.length;j++){    
          A[i][j]=a[i][j]+b[i][j];    //use - for subtraction      
          }    
          }
          out.writeObject(A);
          break;
        case "*":
        for(int i=0;i<a.length;i++){    
          for(int j=0;j<a.length;j++){    
          A[i][j]=0;      
          for(int k=0;k<a.length;k++)      
          {      
          A[i][j]+=a[i][k]*b[k][j];      
          }
  
          }}
          out.writeObject(A);
          break;
        case "-":
        for(int i=0;i<a.length;i++){    
          for(int j=0;j<b.length;j++){    
          A[i][j]=a[i][j]-b[i][j];    //use - for subtraction      
          }    
          }
          out.writeObject(A);
          break;
        default:
      }
    }
  }

}
