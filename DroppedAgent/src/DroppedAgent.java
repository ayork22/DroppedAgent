import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class DroppedAgent {

	public static void main(String[] args) {
		try {

			File file = new File("/tmp/AgentList.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			String line;
			Process p = Runtime.getRuntime().exec(
					"java -Duser=admin -Dhost=192.168.99.100 -jar /Users/yoral01/DevStuff/CLWorkstation.jar list agents matching .*");

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			// Create String Builder
			StringBuilder sb = new StringBuilder();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while ((line = input.readLine()) != null) {
				// System.out.println(line);
				bw.append(line);
				bw.newLine();
				bw.append("test");

				// Build String in WHILE loop

				sb.append(line);
				sb.append(System.getProperty("line.separator"));

			}
			// Print FINAL String of all Agents
			String result = sb.toString();
			System.out.println("Agent List = " + result);
			// Close connections
			bw.close();
			input.close();
			System.out.println("File Written to");
			
			System.out.println("Find Docker = " + result.contains("DockerContainer"));
		} catch (Exception err) {
			err.printStackTrace();
		}

	}
}
