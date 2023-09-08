package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class OrLogic extends LogicTemplate {
	
	public OrLogic() {
		type = BuiltInLogicTypes.OR;
		name = "OR";
		inNodesCount = 2;
		outNodesCount = 1;
		
		truthTable.put("00", "0");
		truthTable.put("10", "1");
		truthTable.put("01", "1");
		truthTable.put("11", "1");	
	} 
}
