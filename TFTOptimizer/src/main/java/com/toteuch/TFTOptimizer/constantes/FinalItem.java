package com.toteuch.TFTOptimizer.constantes;

public enum FinalItem {
	GS("Giant Slayer", Item.BF_SWORD, Item.RECURVE_BOW),
	DEATHBLADE("Deathblade", Item.BF_SWORD, Item.BF_SWORD),
	HEXTECH_GUNBLADE("Hextech Gunblade", Item.BF_SWORD, Item.NEEDLESSLY_LARGE_ROD),
	SUNFIRE_CAPE("Sunfire Cape", Item.CHAIN_VEST, Item.GIANTS_BELT),
	GA("Guardian Angel", Item.BF_SWORD, Item.CHAIN_VEST),
	BLOODTHIRSTER("Bloodthirster", Item.BF_SWORD, Item.NEGATRON_CLOAK),
	SOJ("Spear of Shojin", Item.BF_SWORD, Item.TEAR_OF_THE_GODESS),
	ZEKE("Zeke's Herald", Item.BF_SWORD, Item.GIANTS_BELT),
	DIVINE("Sword of the Divine", Item.SPATULA, Item.BF_SWORD),
	IE("Infinity Edge", Item.BF_SWORD, Item.SPARRING_GLOVE),
	RFC("Rapid Firecannon", Item.RECURVE_BOW, Item.RECURVE_BOW),
	TITAN_RESOLVE("Titan's Resolve", Item.RECURVE_BOW, Item.CHAIN_VEST),
	RUNAAN("Runaan's Hurricane", Item.RECURVE_BOW, Item.NEGATRON_CLOAK),
	GUINSOO("Guinsoo's Rageblade", Item.RECURVE_BOW, Item.NEEDLESSLY_LARGE_ROD),
	STATIKK("Statikk Shiv", Item.RECURVE_BOW, Item.TEAR_OF_THE_GODESS),
	ZZROT("Zz'Rot Portal", Item.RECURVE_BOW, Item.GIANTS_BELT),
	DUELIST_ZEAL("Duelist's Zeal", Item.RECURVE_BOW, Item.SPATULA),
	LAST_WHISPER("Last Whisper", Item.RECURVE_BOW, Item.SPARRING_GLOVE),
	BRAMBLE("Bramble Vest", Item.CHAIN_VEST, Item.CHAIN_VEST),
	GARGOYLE("Gargoyle Stoneplate", Item.CHAIN_VEST, Item.NEGATRON_CLOAK),
	SOLARI("Locket of the Iron Solari", Item.CHAIN_VEST, Item.NEEDLESSLY_LARGE_ROD),
	FROZEN_HEART("Frozen Heart", Item.CHAIN_VEST, Item.TEAR_OF_THE_GODESS),
	VANGUARD_CUIRASS("Vanguard's Cuirass", Item.CHAIN_VEST, Item.SPATULA),
	SHROUD("Shroud of Stillness", Item.CHAIN_VEST, Item.SPARRING_GLOVE),
	DRAGONS_CLAW("Dragons Claw", Item.NEGATRON_CLOAK, Item.NEGATRON_CLOAK),
	IONIC_SPARK("Ionic Spark", Item.NEGATRON_CLOAK, Item.NEEDLESSLY_LARGE_ROD),
	CHALICE("Chalice of Power", Item.NEGATRON_CLOAK, Item.TEAR_OF_THE_GODESS),
	ZEPHYR("Zephyr", Item.NEGATRON_CLOAK, Item.GIANTS_BELT),
	ELDERWOOD_SPROOT("Elderwood Sprout", Item.NEGATRON_CLOAK, Item.SPATULA),
	QSS("Quicksilver", Item.NEGATRON_CLOAK, Item.SPARRING_GLOVE),
	RABADON("Rabadon's Deathcap", Item.NEEDLESSLY_LARGE_ROD, Item.NEEDLESSLY_LARGE_ROD),
	LUDEN("Luden's Echo", Item.NEEDLESSLY_LARGE_ROD, Item.TEAR_OF_THE_GODESS),
	MORELLO("Morellonomicon", Item.NEEDLESSLY_LARGE_ROD, Item.GIANTS_BELT),
	SPIRIT_OF_DRAGON("Spirit of the Dragon", Item.NEEDLESSLY_LARGE_ROD, Item.SPATULA),
	JEWELED_GAUNTLET("Jeweled Gauntlet", Item.NEEDLESSLY_LARGE_ROD, Item.SPARRING_GLOVE),
	BLUE_BUFF("Blue Buff", Item.TEAR_OF_THE_GODESS, Item.TEAR_OF_THE_GODESS),
	REDEMPTION("Redemption", Item.TEAR_OF_THE_GODESS, Item.GIANTS_BELT),
	MAGE_HAT("Mage's Hat", Item.TEAR_OF_THE_GODESS, Item.SPATULA),
	HOJ("Hand of Justice", Item.TEAR_OF_THE_GODESS, Item.SPARRING_GLOVE),
	WARMORG("Warmog's Armor", Item.GIANTS_BELT, Item.GIANTS_BELT),
	WARLORD_BANNER("Warlord's Banner", Item.GIANTS_BELT, Item.SPATULA),
	TRAP_CLAW("Trap Claw", Item.GIANTS_BELT, Item.SPARRING_GLOVE),
	FON("Force of Nature", Item.SPATULA, Item.SPATULA),
	YOUMUU("Youmuu's Ghostblade", Item.SPATULA, Item.SPARRING_GLOVE),
	THIEF_GLOVE("Thief's Gloves", Item.SPARRING_GLOVE, Item.SPARRING_GLOVE);
	
	private String name;
	private Item item1;
	private Item item2;
	private FinalItem(String name, Item item1, Item item2) {
		this.name = name;
		this.item1 = item1;
		this.item2 = item2;
	}
	
	public String getName() {
		return name;
	}

	public Item getItem1() {
		return item1;
	}

	public Item getItem2() {
		return item2;
	}
	
}
