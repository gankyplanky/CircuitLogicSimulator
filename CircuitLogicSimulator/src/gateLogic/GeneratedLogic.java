package gateLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class GeneratedLogic extends LogicTemplate{

	public GeneratedLogic(String fileName) {
		File savedFile = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedGates\\" + fileName + ".txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(savedFile));
			
			
			String line = reader.readLine();
			line = reader.readLine();
			truthTable.put(line.split("\\|")[0], line.split("\\|")[1]);
			inNodesCount = line.split("\\|")[0].length();
			outNodesCount = line.split("\\|")[1].length();
			while((line = reader.readLine()) != null) {
				String[] segments = line.split("\\|");
				truthTable.put(segments[0], segments[1]);
			}
			reader.close();
			
			type = BuiltInLogicTypes.GENERATED;
			name = fileName;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
