package strategy.config.infantry;

import strategy.error.BasicAppError;
import strategy.item.military.infantry.warrior.WarriorConfig;
import strategy.json.JSON;

public class WarriorConfigParser extends InfantryConfigParser<WarriorConfig> {
    @Override
    protected WarriorConfig createInfantryConfig(JSON json) {
        int damage = json.getInt("damage");
        int defense = json.getInt("defense");
        int health = json.getInt("health");
        if(damage < 0) {
            throw new BasicAppError("Warrior damage can't be less than 0.");
        }
        if(defense < 0) {
            throw new BasicAppError("Warrior defense can't be less than 0.");
        }
        if(health <= 0) {
            throw new BasicAppError("Warrior health must be greater than 0.");
        }
        return new WarriorConfig(damage, defense, health);
    }
}
