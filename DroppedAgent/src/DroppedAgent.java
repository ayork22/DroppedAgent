import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DroppedAgent {

	public static void main(String[] args) {
		    try {
		      String line;
		      Process p = Runtime.getRuntime().exec("java -Duser=admin -Dhost=192.168.99.100 -jar /Users/yoral01/DevStuff/CLWorkstation.jar get cluster configuration");
		      BufferedReader input =
		        new BufferedReader
		          (new InputStreamReader(p.getInputStream()));
		      while ((line = input.readLine()) != null) {
		        System.out.println(line);
		      }
		      input.close();
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		  }
	}
