package gateLogic;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;

public abstract class LogicTemplate implements Serializable{		

	//Version ID
	public static final long serialVersionUID = 1;
	
	public BuiltInLogicTypes type = BuiltInLogicTypes.NONE;
	public Dictionary<String, String> truthTable = new Hashtable<String, String>();
	public String name = "DEFAULT";
	public int inNodesCount = 0;
	public int outNodesCount = 0;
}
