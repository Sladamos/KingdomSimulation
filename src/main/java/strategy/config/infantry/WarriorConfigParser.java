package strategy.config.infantry;

import strategy.item.military.infantry.warrior.WarriorConfig;
import strategy.json.JSON;

public class WarriorConfigParser extends InfantryConfigParser<WarriorConfig> {
    @Override
    protected WarriorConfig createInfantryConfig(JSON json) {
        int damage = json.getInt("damage");
        int defense = json.getInt("defense");
        int health = json.getInt("health");
        return new WarriorConfig(damage, defense, health);
    }
}
