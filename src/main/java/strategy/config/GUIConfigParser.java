package strategy.config;

import strategy.error.CriticalAppError;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.json.JSON;

import java.util.HashMap;
import java.util.Map;

public class GUIConfigParser implements ConfigParser<GUIConfig> {

	private final Map<String, GUIType> guiTypes;

	public GUIConfigParser() {
		guiTypes = new HashMap<>();
		guiTypes.put("console", GUIType.CONSOLE);
	}

	@Override
	public GUIConfig createConfig(JSON json) {
		String guiTypeStr = json.getString("type");
		if (!guiTypes.containsKey(guiTypeStr)) {
			throw new CriticalAppError("Incorrect GUI type.");
		}
		GUIType guiType = guiTypes.get(guiTypeStr);
		return new GUIConfig(guiType);
	}
}
