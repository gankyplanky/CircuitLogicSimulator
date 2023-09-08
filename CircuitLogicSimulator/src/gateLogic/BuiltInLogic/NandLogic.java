package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class NandLogic extends LogicTemplate {
	
	public NandLogic() {
		type = BuiltInLogicTypes.NAND;
		name = "NAND";
		inNodesCount = 2;
		outNodesCount = 1;
		
		truthTable.put("00", "1");
		truthTable.put("10", "1");
		truthTable.put("01", "1");
		truthTable.put("11", "0");	
	} 
}
