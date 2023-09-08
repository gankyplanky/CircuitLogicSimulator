package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class NorLogic extends LogicTemplate {
	
	public NorLogic() {
		type = BuiltInLogicTypes.NOR;
		name = "NOR";
		inNodesCount = 2;
		outNodesCount = 1;
		
		truthTable.put("00", "1");
		truthTable.put("10", "0");
		truthTable.put("01", "0");
		truthTable.put("11", "0");	
	} 
}
