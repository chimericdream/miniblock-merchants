package com.chimericdream.miniblockmerchants.loot;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetNameLootFunction;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Pair;

import static com.chimericdream.miniblockmerchants.data.SkullTextureData.*;

public class MobHeadLootTables {
    private static NbtPredicate makeProfessionPredicate(String profession) {
        NbtCompound villagerData = new NbtCompound();
        villagerData.putString("profession", profession);

        NbtCompound nbt = new NbtCompound();
        nbt.put("VillagerData", villagerData);

        return new NbtPredicate(nbt);

    }

    private static LootPoolEntry.Builder<?> getHeadLootTable(String name, String profession, Pair<String, int[]> data) {
        Item headItem = Items.PLAYER_HEAD;

        NbtCompound headTexture = new NbtCompound();
        headTexture.putString("Value", data.getLeft());
        NbtList textureList = new NbtList();
        textureList.add(headTexture);

        NbtCompound properties = new NbtCompound();
        properties.put("textures", textureList);

        NbtCompound owner = new NbtCompound();
        owner.putIntArray("Id", data.getRight());
        owner.put("Properties", properties);
        NbtCompound skullOwner = new NbtCompound();
        skullOwner.put("SkullOwner", owner);

        Text formattedName = MutableText.of(TextContent.EMPTY)
            .append(name)
            .setStyle(Style.EMPTY.withItalic(false));

        LootFunction.Builder nameBuilder = () -> SetNameLootFunction.builder(formattedName).build();

        LootFunction.Builder textureBuilder = () -> SetNbtLootFunction.builder(skullOwner).build();

        NbtPredicate professionPredicate = makeProfessionPredicate(profession);

        return ItemEntry.builder(headItem)
            .apply(nameBuilder)
            .apply(textureBuilder)
            .conditionally(() -> EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().nbt(professionPredicate)).build());
    }

    public static LootPool.Builder getVillagerHeadLootTable() {
        LootPool.Builder builder = LootPool.builder();

        return builder
            .with(getAlchemistHeadLootTable())
            .with(getArboriculturistHeadLootTable())
            .with(getAstronomerHeadLootTable())
            .with(getBakerHeadLootTable())
            .with(getBartenderHeadLootTable())
            .with(getBeekeeperHeadLootTable())
            .with(getBlacksmithHeadLootTable())
            .with(getChefHeadLootTable())
            .with(getEngineerHeadLootTable())
            .with(getEremologistHeadLootTable())
            .with(getFurnisherHeadLootTable())
            .with(getGamemasterHeadLootTable())
            .with(getGrieferHeadLootTable())
            .with(getHorticulturistHeadLootTable())
            .with(getMineralogistHeadLootTable())
            .with(getNetherographerHeadLootTable())
            .with(getOceanographerHeadLootTable())
            .with(getOlericulturistHeadLootTable())
            .with(getPetrologistHeadLootTable())
            .with(getPlushieManiacHeadLootTable())
            .with(getPomologistHeadLootTable())
            .with(getRecyclerHeadLootTable())
            .with(getRitualistHeadLootTable())
            .with(getSculptorHeadLootTable())
            .with(getSteampunkerHeadLootTable())
            .with(getTailorHeadLootTable())
            .conditionally(() -> KilledByPlayerLootCondition.builder().build())
            .rolls(ConstantLootNumberProvider.create(1));
    }

    public static LootPool.Builder getZombieVillagerHeadLootTable() {
        LootPool.Builder builder = LootPool.builder();

        return builder
            .with(getZombieAlchemistHeadLootTable())
            .with(getZombieArboriculturistHeadLootTable())
            .with(getZombieAstronomerHeadLootTable())
            .with(getZombieBakerHeadLootTable())
            .with(getZombieBartenderHeadLootTable())
            .with(getZombieBeekeeperHeadLootTable())
            .with(getZombieBlacksmithHeadLootTable())
            .with(getZombieChefHeadLootTable())
            .with(getZombieEngineerHeadLootTable())
            .with(getZombieEremologistHeadLootTable())
            .with(getZombieFurnisherHeadLootTable())
            .with(getZombieGamemasterHeadLootTable())
            .with(getZombieGrieferHeadLootTable())
            .with(getZombieHorticulturistHeadLootTable())
            .with(getZombieMineralogistHeadLootTable())
            .with(getZombieNetherographerHeadLootTable())
            .with(getZombieOceanographerHeadLootTable())
            .with(getZombieOlericulturistHeadLootTable())
            .with(getZombiePetrologistHeadLootTable())
            .with(getZombiePlushieManiacHeadLootTable())
            .with(getZombiePomologistHeadLootTable())
            .with(getZombieRecyclerHeadLootTable())
            .with(getZombieRitualistHeadLootTable())
            .with(getZombieSculptorHeadLootTable())
            .with(getZombieSteampunkerHeadLootTable())
            .with(getZombieTailorHeadLootTable())
            .conditionally(() -> KilledByPlayerLootCondition.builder().build())
            .conditionally(() -> RandomChanceWithLootingLootCondition.builder(0.5f, 0.02f).build())
            .rolls(ConstantLootNumberProvider.create(1));
    }

    public static LootPoolEntry.Builder<?> getAlchemistHeadLootTable() {
        return getHeadLootTable("Alchemist", "miniblockmerchants:mm_alchemist", ALCHEMIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieAlchemistHeadLootTable() {
        return getHeadLootTable("Zombie Alchemist", "miniblockmerchants:mm_alchemist", ALCHEMIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getArboriculturistHeadLootTable() {
        return getHeadLootTable("Arboriculturist", "miniblockmerchants:mm_arboriculturist", ARBORICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieArboriculturistHeadLootTable() {
        return getHeadLootTable("Zombie Arboriculturist", "miniblockmerchants:mm_arboriculturist", ARBORICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getAstronomerHeadLootTable() {
        return getHeadLootTable("Astronomer", "miniblockmerchants:mm_astronomer", ASTRONOMER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieAstronomerHeadLootTable() {
        return getHeadLootTable("Zombie Astronomer", "miniblockmerchants:mm_astronomer", ASTRONOMER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBakerHeadLootTable() {
        return getHeadLootTable("Baker", "miniblockmerchants:mm_baker", BAKER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBakerHeadLootTable() {
        return getHeadLootTable("Zombie Baker", "miniblockmerchants:mm_baker", BAKER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBartenderHeadLootTable() {
        return getHeadLootTable("Bartender", "miniblockmerchants:mm_bartender", BARTENDER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBartenderHeadLootTable() {
        return getHeadLootTable("Zombie Bartender", "miniblockmerchants:mm_bartender", BARTENDER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBeekeeperHeadLootTable() {
        return getHeadLootTable("Beekeeper", "miniblockmerchants:mm_beekeeper", BEEKEEPER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBeekeeperHeadLootTable() {
        return getHeadLootTable("Zombie Beekeeper", "miniblockmerchants:mm_beekeeper", BEEKEEPER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBlacksmithHeadLootTable() {
        return getHeadLootTable("Blacksmith", "miniblockmerchants:mm_blacksmith", BLACKSMITH_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBlacksmithHeadLootTable() {
        return getHeadLootTable("Zombie Blacksmith", "miniblockmerchants:mm_blacksmith", BLACKSMITH_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getChefHeadLootTable() {
        return getHeadLootTable("Chef", "miniblockmerchants:mm_chef", CHEF_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieChefHeadLootTable() {
        return getHeadLootTable("Zombie Chef", "miniblockmerchants:mm_chef", CHEF_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getEngineerHeadLootTable() {
        return getHeadLootTable("Engineer", "miniblockmerchants:mm_engineer", ENGINEER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieEngineerHeadLootTable() {
        return getHeadLootTable("Zombie Engineer", "miniblockmerchants:mm_engineer", ENGINEER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getEremologistHeadLootTable() {
        return getHeadLootTable("Eremologist", "miniblockmerchants:mm_eremologist", EREMOLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieEremologistHeadLootTable() {
        return getHeadLootTable("Zombie Eremologist", "miniblockmerchants:mm_eremologist", EREMOLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getFurnisherHeadLootTable() {
        return getHeadLootTable("Furnisher", "miniblockmerchants:mm_furnisher", FURNISHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieFurnisherHeadLootTable() {
        return getHeadLootTable("Zombie Furnisher", "miniblockmerchants:mm_furnisher", FURNISHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getGamemasterHeadLootTable() {
        return getHeadLootTable("Gamemaster", "miniblockmerchants:mm_gamemaster", GAMEMASTER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieGamemasterHeadLootTable() {
        return getHeadLootTable("Zombie Gamemaster", "miniblockmerchants:mm_gamemaster", GAMEMASTER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getGrieferHeadLootTable() {
        return getHeadLootTable("Griefer", "miniblockmerchants:mm_griefer", GRIEFER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieGrieferHeadLootTable() {
        return getHeadLootTable("Zombie Griefer", "miniblockmerchants:mm_griefer", GRIEFER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getHorticulturistHeadLootTable() {
        return getHeadLootTable("Horticulturist", "miniblockmerchants:mm_horticulturist", HORTICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieHorticulturistHeadLootTable() {
        return getHeadLootTable("Zombie Gorticulturist", "miniblockmerchants:mm_horticulturist", HORTICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getMineralogistHeadLootTable() {
        return getHeadLootTable("Mineralogist", "miniblockmerchants:mm_mineralogist", MINERALOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieMineralogistHeadLootTable() {
        return getHeadLootTable("Zombie Mineralogist", "miniblockmerchants:mm_mineralogist", MINERALOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getNetherographerHeadLootTable() {
        return getHeadLootTable("Netherographer", "miniblockmerchants:mm_netherographer", NETHEROGRAPHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieNetherographerHeadLootTable() {
        return getHeadLootTable("Zombie Netherographer", "miniblockmerchants:mm_netherographer", NETHEROGRAPHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getOceanographerHeadLootTable() {
        return getHeadLootTable("Oceanographer", "miniblockmerchants:mm_oceanographer", OCEANOGRAPHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieOceanographerHeadLootTable() {
        return getHeadLootTable("Zombie Oceanographer", "miniblockmerchants:mm_oceanographer", OCEANOGRAPHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getOlericulturistHeadLootTable() {
        return getHeadLootTable("Olericulturist", "miniblockmerchants:mm_olericulturist", OLERICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieOlericulturistHeadLootTable() {
        return getHeadLootTable("Zombie Olericulturist", "miniblockmerchants:mm_olericulturist", OLERICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPetrologistHeadLootTable() {
        return getHeadLootTable("Petrologist", "miniblockmerchants:mm_petrologist", PETROLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePetrologistHeadLootTable() {
        return getHeadLootTable("Zombie Petrologist", "miniblockmerchants:mm_petrologist", PETROLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPlushieManiacHeadLootTable() {
        return getHeadLootTable("Plushie Maniac", "miniblockmerchants:mm_plushie_maniac", PLUSHIE_MANIAC_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePlushieManiacHeadLootTable() {
        return getHeadLootTable("Zombie Plushie Maniac", "miniblockmerchants:mm_plushie_maniac", PLUSHIE_MANIAC_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPomologistHeadLootTable() {
        return getHeadLootTable("Pomologist", "miniblockmerchants:mm_pomologist", POMOLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePomologistHeadLootTable() {
        return getHeadLootTable("Zombie Pomologist", "miniblockmerchants:mm_pomologist", POMOLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getRecyclerHeadLootTable() {
        return getHeadLootTable("Recycler", "miniblockmerchants:mm_recycler", RECYCLER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieRecyclerHeadLootTable() {
        return getHeadLootTable("Zombie Recycler", "miniblockmerchants:mm_recycler", RECYCLER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getRitualistHeadLootTable() {
        return getHeadLootTable("Ritualist", "miniblockmerchants:mm_ritualist", RITUALIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieRitualistHeadLootTable() {
        return getHeadLootTable("Zombie Ritualist", "miniblockmerchants:mm_ritualist", RITUALIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getSculptorHeadLootTable() {
        return getHeadLootTable("Sculptor", "miniblockmerchants:mm_sculptor", SCULPTOR_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieSculptorHeadLootTable() {
        return getHeadLootTable("Zombie Sculptor", "miniblockmerchants:mm_sculptor", SCULPTOR_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getSteampunkerHeadLootTable() {
        return getHeadLootTable("Steampunker", "miniblockmerchants:mm_steampunker", STEAMPUNKER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieSteampunkerHeadLootTable() {
        return getHeadLootTable("Zombie Steampunker", "miniblockmerchants:mm_steampunker", STEAMPUNKER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getTailorHeadLootTable() {
        return getHeadLootTable("Tailor", "miniblockmerchants:mm_tailor", TAILOR_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieTailorHeadLootTable() {
        return getHeadLootTable("Zombie Tailor", "miniblockmerchants:mm_tailor", TAILOR_ZOMBIE_VILLAGER);
    }
}
