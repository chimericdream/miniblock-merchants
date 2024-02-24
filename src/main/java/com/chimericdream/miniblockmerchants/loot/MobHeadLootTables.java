package com.chimericdream.miniblockmerchants.loot;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.AlternativeEntry;
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
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
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

    private static LootPoolEntry.Builder<?> getZombieHeadLootTable(String name, String profession, Pair<String, int[]> data) {
        return getHeadLootTable(name, profession, data, true);
    }

    private static LootPoolEntry.Builder<?> getHeadLootTable(String name, String profession, Pair<String, int[]> data) {
        return getHeadLootTable(name, profession, data, false);
    }

    private static LootPoolEntry.Builder<?> getHeadLootTable(String name, String profession, Pair<String, int[]> data, boolean isZombieVillager) {
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

        NbtCompound beTag = new NbtCompound();
        if (isZombieVillager) {
            beTag.putString("note_block_sound", "minecraft:entity.zombie_villager.ambient");
        } else {
            beTag.putString("note_block_sound", "minecraft:entity.villager.ambient");
        }

        NbtCompound skullNbt = new NbtCompound();
        skullNbt.put("SkullOwner", owner);
        skullNbt.put("BlockEntityTag", beTag);

        Text formattedName = MutableText.of(PlainTextContent.EMPTY)
            .append(name)
            .setStyle(Style.EMPTY.withItalic(false));

        LootFunction.Builder nameBuilder = () -> SetNameLootFunction.builder(formattedName).build();

        LootFunction.Builder textureBuilder = () -> SetNbtLootFunction.builder(skullNbt).build();

        NbtPredicate professionPredicate = makeProfessionPredicate(profession);

        return ItemEntry.builder(headItem)
            .apply(nameBuilder)
            .apply(textureBuilder)
            .conditionally(() -> EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().nbt(professionPredicate)).build());
    }

    public static LootPool.Builder getVillagerHeadLootTable() {
        LootPool.Builder builder = LootPool.builder();

        return builder
            .with(AlternativeEntry.builder(
                getAlchemistHeadLootTable(),
                getArboriculturistHeadLootTable(),
                getAstronomerHeadLootTable(),
                getBakerHeadLootTable(),
                getBartenderHeadLootTable(),
                getBeekeeperHeadLootTable(),
                getBlacksmithHeadLootTable(),
                getChefHeadLootTable(),
                getEngineerHeadLootTable(),
                getEremologistHeadLootTable(),
                getFurnisherHeadLootTable(),
                getGamemasterHeadLootTable(),
                getGrieferHeadLootTable(),
                getHorticulturistHeadLootTable(),
                getMineralogistHeadLootTable(),
                getNetherographerHeadLootTable(),
                getOceanographerHeadLootTable(),
                getOlericulturistHeadLootTable(),
                getPetrologistHeadLootTable(),
                getPlushieManiacHeadLootTable(),
                getPomologistHeadLootTable(),
                getRecyclerHeadLootTable(),
                getRitualistHeadLootTable(),
                getSculptorHeadLootTable(),
                getSteampunkerHeadLootTable(),
                getTailorHeadLootTable()
            ).build())
            .conditionally(() -> KilledByPlayerLootCondition.builder().build())
            .rolls(ConstantLootNumberProvider.create(1));
    }

    public static LootPool.Builder getZombieVillagerHeadLootTable() {
        LootPool.Builder builder = LootPool.builder();

        return builder
            .with(AlternativeEntry.builder(
                getZombieAlchemistHeadLootTable(),
                getZombieArboriculturistHeadLootTable(),
                getZombieAstronomerHeadLootTable(),
                getZombieBakerHeadLootTable(),
                getZombieBartenderHeadLootTable(),
                getZombieBeekeeperHeadLootTable(),
                getZombieBlacksmithHeadLootTable(),
                getZombieChefHeadLootTable(),
                getZombieEngineerHeadLootTable(),
                getZombieEremologistHeadLootTable(),
                getZombieFurnisherHeadLootTable(),
                getZombieGamemasterHeadLootTable(),
                getZombieGrieferHeadLootTable(),
                getZombieHorticulturistHeadLootTable(),
                getZombieMineralogistHeadLootTable(),
                getZombieNetherographerHeadLootTable(),
                getZombieOceanographerHeadLootTable(),
                getZombieOlericulturistHeadLootTable(),
                getZombiePetrologistHeadLootTable(),
                getZombiePlushieManiacHeadLootTable(),
                getZombiePomologistHeadLootTable(),
                getZombieRecyclerHeadLootTable(),
                getZombieRitualistHeadLootTable(),
                getZombieSculptorHeadLootTable(),
                getZombieSteampunkerHeadLootTable(),
                getZombieTailorHeadLootTable()
            ).build())
            .conditionally(() -> KilledByPlayerLootCondition.builder().build())
            .conditionally(() -> RandomChanceWithLootingLootCondition.builder(0.5f, 0.02f).build())
            .rolls(ConstantLootNumberProvider.create(1));
    }

    public static LootPoolEntry.Builder<?> getAlchemistHeadLootTable() {
        return getHeadLootTable("Alchemist", "miniblockmerchants:mm_alchemist", ALCHEMIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieAlchemistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Alchemist", "miniblockmerchants:mm_alchemist", ALCHEMIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getArboriculturistHeadLootTable() {
        return getHeadLootTable("Arboriculturist", "miniblockmerchants:mm_arboriculturist", ARBORICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieArboriculturistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Arboriculturist", "miniblockmerchants:mm_arboriculturist", ARBORICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getAstronomerHeadLootTable() {
        return getHeadLootTable("Astronomer", "miniblockmerchants:mm_astronomer", ASTRONOMER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieAstronomerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Astronomer", "miniblockmerchants:mm_astronomer", ASTRONOMER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBakerHeadLootTable() {
        return getHeadLootTable("Baker", "miniblockmerchants:mm_baker", BAKER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBakerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Baker", "miniblockmerchants:mm_baker", BAKER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBartenderHeadLootTable() {
        return getHeadLootTable("Bartender", "miniblockmerchants:mm_bartender", BARTENDER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBartenderHeadLootTable() {
        return getZombieHeadLootTable("Zombie Bartender", "miniblockmerchants:mm_bartender", BARTENDER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBeekeeperHeadLootTable() {
        return getHeadLootTable("Beekeeper", "miniblockmerchants:mm_beekeeper", BEEKEEPER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBeekeeperHeadLootTable() {
        return getZombieHeadLootTable("Zombie Beekeeper", "miniblockmerchants:mm_beekeeper", BEEKEEPER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getBlacksmithHeadLootTable() {
        return getHeadLootTable("Blacksmith", "miniblockmerchants:mm_blacksmith", BLACKSMITH_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieBlacksmithHeadLootTable() {
        return getZombieHeadLootTable("Zombie Blacksmith", "miniblockmerchants:mm_blacksmith", BLACKSMITH_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getChefHeadLootTable() {
        return getHeadLootTable("Chef", "miniblockmerchants:mm_chef", CHEF_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieChefHeadLootTable() {
        return getZombieHeadLootTable("Zombie Chef", "miniblockmerchants:mm_chef", CHEF_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getEngineerHeadLootTable() {
        return getHeadLootTable("Engineer", "miniblockmerchants:mm_engineer", ENGINEER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieEngineerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Engineer", "miniblockmerchants:mm_engineer", ENGINEER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getEremologistHeadLootTable() {
        return getHeadLootTable("Eremologist", "miniblockmerchants:mm_eremologist", EREMOLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieEremologistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Eremologist", "miniblockmerchants:mm_eremologist", EREMOLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getFurnisherHeadLootTable() {
        return getHeadLootTable("Furnisher", "miniblockmerchants:mm_furnisher", FURNISHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieFurnisherHeadLootTable() {
        return getZombieHeadLootTable("Zombie Furnisher", "miniblockmerchants:mm_furnisher", FURNISHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getGamemasterHeadLootTable() {
        return getHeadLootTable("Gamemaster", "miniblockmerchants:mm_gamemaster", GAMEMASTER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieGamemasterHeadLootTable() {
        return getZombieHeadLootTable("Zombie Gamemaster", "miniblockmerchants:mm_gamemaster", GAMEMASTER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getGrieferHeadLootTable() {
        return getHeadLootTable("Griefer", "miniblockmerchants:mm_griefer", GRIEFER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieGrieferHeadLootTable() {
        return getZombieHeadLootTable("Zombie Griefer", "miniblockmerchants:mm_griefer", GRIEFER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getHorticulturistHeadLootTable() {
        return getHeadLootTable("Horticulturist", "miniblockmerchants:mm_horticulturist", HORTICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieHorticulturistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Gorticulturist", "miniblockmerchants:mm_horticulturist", HORTICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getMineralogistHeadLootTable() {
        return getHeadLootTable("Mineralogist", "miniblockmerchants:mm_mineralogist", MINERALOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieMineralogistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Mineralogist", "miniblockmerchants:mm_mineralogist", MINERALOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getNetherographerHeadLootTable() {
        return getHeadLootTable("Netherographer", "miniblockmerchants:mm_netherographer", NETHEROGRAPHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieNetherographerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Netherographer", "miniblockmerchants:mm_netherographer", NETHEROGRAPHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getOceanographerHeadLootTable() {
        return getHeadLootTable("Oceanographer", "miniblockmerchants:mm_oceanographer", OCEANOGRAPHER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieOceanographerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Oceanographer", "miniblockmerchants:mm_oceanographer", OCEANOGRAPHER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getOlericulturistHeadLootTable() {
        return getHeadLootTable("Olericulturist", "miniblockmerchants:mm_olericulturist", OLERICULTURIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieOlericulturistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Olericulturist", "miniblockmerchants:mm_olericulturist", OLERICULTURIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPetrologistHeadLootTable() {
        return getHeadLootTable("Petrologist", "miniblockmerchants:mm_petrologist", PETROLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePetrologistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Petrologist", "miniblockmerchants:mm_petrologist", PETROLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPlushieManiacHeadLootTable() {
        return getHeadLootTable("Plushie Maniac", "miniblockmerchants:mm_plushie_maniac", PLUSHIE_MANIAC_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePlushieManiacHeadLootTable() {
        return getZombieHeadLootTable("Zombie Plushie Maniac", "miniblockmerchants:mm_plushie_maniac", PLUSHIE_MANIAC_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getPomologistHeadLootTable() {
        return getHeadLootTable("Pomologist", "miniblockmerchants:mm_pomologist", POMOLOGIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombiePomologistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Pomologist", "miniblockmerchants:mm_pomologist", POMOLOGIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getRecyclerHeadLootTable() {
        return getHeadLootTable("Recycler", "miniblockmerchants:mm_recycler", RECYCLER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieRecyclerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Recycler", "miniblockmerchants:mm_recycler", RECYCLER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getRitualistHeadLootTable() {
        return getHeadLootTable("Ritualist", "miniblockmerchants:mm_ritualist", RITUALIST_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieRitualistHeadLootTable() {
        return getZombieHeadLootTable("Zombie Ritualist", "miniblockmerchants:mm_ritualist", RITUALIST_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getSculptorHeadLootTable() {
        return getHeadLootTable("Sculptor", "miniblockmerchants:mm_sculptor", SCULPTOR_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieSculptorHeadLootTable() {
        return getZombieHeadLootTable("Zombie Sculptor", "miniblockmerchants:mm_sculptor", SCULPTOR_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getSteampunkerHeadLootTable() {
        return getHeadLootTable("Steampunker", "miniblockmerchants:mm_steampunker", STEAMPUNKER_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieSteampunkerHeadLootTable() {
        return getZombieHeadLootTable("Zombie Steampunker", "miniblockmerchants:mm_steampunker", STEAMPUNKER_ZOMBIE_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getTailorHeadLootTable() {
        return getHeadLootTable("Tailor", "miniblockmerchants:mm_tailor", TAILOR_VILLAGER);
    }

    public static LootPoolEntry.Builder<?> getZombieTailorHeadLootTable() {
        return getZombieHeadLootTable("Zombie Tailor", "miniblockmerchants:mm_tailor", TAILOR_ZOMBIE_VILLAGER);
    }
}
