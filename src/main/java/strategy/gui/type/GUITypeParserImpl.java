package strategy.gui.type;

import strategy.error.BasicAppError;
import strategy.gui.GUIType;

import java.util.HashMap;
import java.util.Map;

public class GUITypeParserImpl implements GUITypeParser {

	private final Map<String, GUIType> guiTypes;

	public GUITypeParserImpl() {
		guiTypes = new HashMap<>();
		guiTypes.put("console", GUIType.CONSOLE);
	}

	@Override
	public GUIType parse(String parseStr) {
		if (!guiTypes.containsKey(parseStr)) {
			throw new BasicAppError("Incorrect GUI type.");
		}
		return guiTypes.get(parseStr);
	}
}
