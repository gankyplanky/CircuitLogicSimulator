package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class NotLogic extends LogicTemplate {
	
	public NotLogic() {
		type = BuiltInLogicTypes.NOT;
		name = "Not";
		inNodesCount = 1;
		outNodesCount = 1;
		
		truthTable.put("0", "1");
		truthTable.put("1", "0");
	} 
}
