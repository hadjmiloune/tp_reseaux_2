import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;






public class Client {
  public static void main(String[] args) throws Exception {
    utilisateur uti1=new utilisateur("2", "123");
    int port = 9999;
    String host = "localhost";

    Socket client = new Socket(host, port);

    ObjectInputStream inp = new ObjectInputStream(client.getInputStream());
    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

    
    out.writeObject(uti1);

    String oper = (String) inp.readObject();
    System.out.println(oper);
    Scanner scn = new Scanner(System.in);
    out.writeObject(scn.next());
    int A[][]={{1,3,4},{2,4,3},{3,4,5}};    
    int B[][]={{1,3,4},{2,4,3},{1,2,4}};    

     out.writeObject(A);
    out.writeObject(B);
    
    scn.close();
    // get result

    int[][] R = (int[][]) inp.readObject();

    for (int i = 0; i < R.length; i++) {
      for (int j = 0; j < R[i].length; j++)
        System.out.print(R[i][j] + " , ");
      System.out.println();
    }
    // print result

  }
}