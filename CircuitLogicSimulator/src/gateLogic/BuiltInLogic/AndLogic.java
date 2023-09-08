package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class AndLogic extends LogicTemplate {
	
	public AndLogic() {
		type = BuiltInLogicTypes.AND;
		name = "AND";
		inNodesCount = 2;
		outNodesCount = 1;
		
		truthTable.put("00", "0");
		truthTable.put("10", "0");
		truthTable.put("01", "0");
		truthTable.put("11", "1");	
	} 
}
