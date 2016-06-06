import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DroppedAgent {

	public static void main(String[] args) {
		
		String line;
		String emHost = GetProperties.getPropertyValue("EnterpriseManager_Hostname");
		String emPort = GetProperties.getPropertyValue("EnterpriseManager_Port");
		String user = GetProperties.getPropertyValue("EnterpriseManager_UserName");
		String pass = GetProperties.getPropertyValue("EnterpriseManager_Password");
		String clwLocation = GetProperties.getPropertyValue("CLW_JAR_Location");
		String clwCommand = GetProperties.getPropertyValue("CLW_Command");
		String javaLocation = GetProperties.getPropertyValue("JavaLocation");
		String Agent = GetProperties.getPropertyValue("Agent");
		
		
		System.out.println("test Property = " + GetProperties.getPropertyValue("EnterpriseManagerUserName"));
		try {
			// Template for using File
			// File file = new File("/tmp/AgentList.txt");
			// // if file doesn't exists, then create it
			// if (!file.exists()) {
			// file.createNewFile();
			// }

			
//			Process p = Runtime.getRuntime().exec(
//					"java -Duser=admin -Dhost=192.168.99.100 -jar /Users/yoral01/DevStuff/CLWorkstation.jar list agents matching .*");

			Process p = Runtime.getRuntime().exec(javaLocation + " -Duser=" + user + " -Dhost=" + emHost + " -jar " + clwLocation + " " + clwCommand);
			
			String test = javaLocation + " -Duser=" + user + " -Dhost=" + emHost + " -jar " + clwLocation + " " + clwCommand;
			System.out.println("test =" + test);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			// Create String Builder to handle in memory
			StringBuilder sb = new StringBuilder();

			// Use FileWriter if want to write CLW output to file
			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);

			while ((line = input.readLine()) != null) {

				// Used for writing to File
				// bw.append(line);
				// bw.newLine();
				// bw.append("test");

				// Build String in WHILE loop

				sb.append(line);
				// Adds line if desired
				sb.append(System.getProperty("line.separator"));

			}
			// Print FINAL String of all Agents
			String result = sb.toString();
			System.out.println("Agent List = " + result);
			// Close connections
			// bw.close();
			input.close();

			// System.out.println("Find Docker = " + result.contains("WAS
			// Liberty Docker"));
			if (result.contains(Agent)) {
				System.out.println("Agent is Connected");
			} else {
				System.out.println("Agent is DisConnected");
				SendEmail.sendemail();
			}
		} catch (Exception err) {
			err.printStackTrace();
		}

	}
}
