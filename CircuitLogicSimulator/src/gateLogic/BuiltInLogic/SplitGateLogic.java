package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class SplitGateLogic extends LogicTemplate{
	
	public SplitGateLogic() {
		type = BuiltInLogicTypes.SPLIT;
		name = "SPLIT";
		inNodesCount = 1;
		outNodesCount = 2;
		
		truthTable.put("0", "00");
		truthTable.put("1", "11");
	} 
}
