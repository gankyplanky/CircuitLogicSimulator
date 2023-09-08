package gateLogic.BuiltInLogic;

import gateLogic.BuiltInLogicTypes;
import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class XorLogic extends LogicTemplate {
	
	public XorLogic() {
		type = BuiltInLogicTypes.XOR;
		name = "XOR";
		inNodesCount = 2;
		outNodesCount = 1;
		
		truthTable.put("00", "0");
		truthTable.put("10", "1");
		truthTable.put("01", "1");
		truthTable.put("11", "0");	
	} 

}
